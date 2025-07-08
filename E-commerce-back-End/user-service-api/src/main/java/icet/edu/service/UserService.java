package icet.edu.service;

import icet.edu.dto.request.RequestUser;
import icet.edu.dto.request.RequestUserLoginRequest;
import icet.edu.dto.request.RequestUserPasswordReset;
import icet.edu.dto.response.ResponseUser;

import java.io.IOException;
import java.util.List;

public interface UserService  {
    public void createUser(RequestUser dto) throws IOException;
    public boolean verifyEmail(String otp,String email);
    public Object userLogin(RequestUserLoginRequest request);
    public List<ResponseUser> findUsersPaginate(String searchText ,int page, int size);
    public void resend(String email);
    public void forgotPasswordSendVerificationCode(String email) ;
    public boolean verifyReset(String otp ,String email);
    public boolean passwordReset(RequestUserPasswordReset dto);

    String getUserId(String email);

}
