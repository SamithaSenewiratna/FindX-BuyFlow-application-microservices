package icet.edu.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {


    private final SecretKey secretKey ;

    public JWTService() {
        SecretKey tempKey;
        try {
            SecretKey k = KeyGenerator.getInstance("HmacSHA256").generateKey();
            tempKey = Keys.hmacShaKeyFor(k.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate secret key", e);
        }
        this.secretKey = tempKey;
    }




    public String getJWTToken(String username, Map<String,Object> claims) {
        return  Jwts.builder()
                .claim("claims", claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) // 15 minits
                .signWith(secretKey)
                .compact();


    }
    public String getUsername(String token) {

        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .ge
                    .getSubject();

        } catch (Exception e) {
          return  "Invalid token provided" ;
        }



    }

}
