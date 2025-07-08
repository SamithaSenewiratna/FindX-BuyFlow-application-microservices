package icet.edu.service.impl;

import icet.edu.config.KeycloakSecurityUtil;
import icet.edu.dto.request.RequestUser;
import icet.edu.dto.request.RequestUserLoginRequest;
import icet.edu.dto.request.RequestUserPasswordReset;
import icet.edu.dto.response.ResponseUser;
import icet.edu.entity.OtpEntity;
import icet.edu.entity.UserEntity;
import icet.edu.exceptions.*;
import icet.edu.repository.OtpRepository;
import icet.edu.repository.UserRepository;
import icet.edu.service.EmailService;
import icet.edu.service.UserService;
import icet.edu.util.FileDataExtractor;
import icet.edu.util.OtpGenerator;
import io.jsonwebtoken.io.IOException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakSecurityUtil keycloakUtil;

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final OtpRepository otpRepository;
    private  final JwtService jwtService;
    private OtpGenerator otpGenerator;
    private final FileDataExtractor fileDataExtractor;

    @Value("e-commerce")
    private String realm;

    @Value("e-commerce-client")
    private String clientId;

    @Value("fhZcnesnl3142d6HiDOgmRPcanscJdnY")
    private String secret;

    @Value("http://localhost:8080")
    private String keyCloakApiUrl;



    @Override
    public void createUser(RequestUser dto) throws IOException {
       String userId = "" ;
       String otpId = "" ;
       Keycloak keycloak = null;

        UserRepresentation existingUser = null;
        keycloak = keycloakUtil.getKeycloakInstance();
        // check if user already exists
        existingUser = keycloak.realm(realm).users().search(dto.getUsername()).stream()
                .findFirst().orElse(null);
        if(existingUser != null){
            Optional<UserEntity> byEmail = userRepository.findByUsename(existingUser.getEmail());
            if(byEmail.isEmpty()){
                keycloak.realm(realm).users().delete(existingUser.getId());
            }else {
                throw new DuplicateEntryException("User with email"+dto.getUsername()+"already exists");
            }
        }else{
          Optional<UserEntity> byEmail = userRepository.findByUsename(dto.getUsername());
            if (byEmail.isPresent()) {
                Optional<OtpEntity> bySystemUserId = otpRepository.findBySystemUserId(byEmail.get().getUserId());
                if (bySystemUserId.isPresent()) {
                    otpRepository.deleteById(bySystemUserId.get().getPropertyId());
                }
               userRepository.deleteById(byEmail.get().getUserId());
            }
        }

        UserRepresentation userRep = mapUserRep(dto);
        Response res = keycloak.realm(realm).users().create(userRep);
        // Add the admin role to the newly created user
        if(res.getStatus()==Response.Status.CREATED.getStatusCode()){
            RoleRepresentation userRole = keycloak.realm(realm).roles().get("user").toRepresentation();
            userId = res.getLocation().getPath().replaceAll(".*/([^/])$","$1");
            keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Arrays.asList(userRole));
            UserEntity createdSystemUser = UserEntity.builder()
                    .userId(userId)
                    .activeStatus(false)
                    .userName(dto.getUsername())
                    .FirstName(dto.getFirstName())
                    .LastName(dto.getLastName())
                    .isAccountNonExpired(true)
                    .isEmailVerified(false)
                    .isAccountNonLocked(true)
                    .isEnabled(false)
                    .createdDate(new Date())
                    .build();

            UserEntity savedUser = userRepository.save(createdSystemUser);

            OtpEntity otp = OtpEntity.builder()
                    .propertyId(UUID.randomUUID().toString())
                    .code(otpGenerator.generateOtp(4))
                    .createdDate(new Date())
                    .isVerified(false)
                    .attempts(0)
                    .systemUser(savedUser)
                    .build();
            otpRepository.save(otp);
            emailService.sendUserSignupVerificationCode(dto.getUsername(), "Verify Your Email Address for Access",otp.getCode());

        }

    }

    @Override
    public boolean verifyEmail(String otp, String email) {
        try {
            Optional<UserEntity> selectedUserObj = userRepository.findByUsename(email);
            if(selectedUserObj.isEmpty()){
                throw new EntryNotFoundException("Unable to find any users");
            }
            UserEntity systemUser = selectedUserObj.get();

            OtpEntity selectedOtpObj = systemUser.getOtp();

            if(selectedOtpObj.isVerified()){
                throw new BadRequestException("this OTP has already in used");
            }
            if(selectedOtpObj.getAttempts()>=5){
                String code = otpGenerator.generateOtp(4);

            emailService.sendUserSignupVerificationCode(email,"Verify Your Email for access",code);

             selectedOtpObj.setAttempts(0);
             selectedOtpObj.setCode(code);
             selectedOtpObj.setCreatedDate(new Date());
             otpRepository.save(selectedOtpObj);

           throw new TooManyRequestException("Too many unsucessful attempts New OTP sent and try again");

          }

          if(selectedOtpObj.getCode().equals(otp)){
              UserRepresentation keycloakUser = keycloakUtil.getKeycloakInstance().realm(realm)
                      .users()
                      .search(email)
                      .stream()
                      .findFirst()
                      .orElseThrow(()->new EntryNotFoundException("User Not Found!"));

              keycloakUser.setEmailVerified(true);
              keycloakUser.setEnabled(true);

              keycloakUtil.getKeycloakInstance().realm(realm)
                      .users()
                      .get(keycloakUser.getId())
                      .update(keycloakUser);

              systemUser.setActiveStatus(true);
              systemUser.setIsEnabled(true);
              systemUser.setIsEmailVerified(true);

              userRepository.save(systemUser);

              selectedOtpObj.setVerified(true);
              selectedOtpObj.setAttempts(selectedOtpObj.getAttempts()+1);

              otpRepository.save(selectedOtpObj);

              return true;

          }else {
              selectedOtpObj.setAttempts(selectedOtpObj.getAttempts()+1);
              otpRepository.save(selectedOtpObj);
          }


        }catch (IOException exception){
            throw new InternalServerException("Something went wrong pleace try again later..");
        }
        return false;
    }

    @Override
    public Object userLogin(RequestUserLoginRequest request) {
        return null;
    }

    @Override
    public List<ResponseUser> findUsersPaginate(String searchText, int page, int size) {
        return List.of();
    }

    @Override
    public void resend(String email) {

    }

    @Override
    public void forgotPasswordSendVerificationCode(String email) {

    }

    @Override
    public boolean verifyReset(String otp, String email) {
        return false;
    }

    @Override
    public boolean passwordReset(RequestUserPasswordReset dto) {
        return false;
    }

    @Override
    public String getUserId(String email) {
        return "";
    }

    private UserRepresentation mapUserRep(RequestUser user){
        UserRepresentation userRep = new UserRepresentation();
        userRep.setUsername(user.getUsername());
        userRep.setFirstName(user.getFirstName());
        userRep.setLastName(user.getLastName());
        userRep.setEmail(user.getUsername());
        userRep.setEnabled(false);
        userRep.setEmailVerified(false);
        List<CredentialRepresentation> creds = new ArrayList<>();
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setValue(user.getPassword());
        creds.add(cred);
        userRep.setCredentials(creds);
        return userRep;


    }
}
