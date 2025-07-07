package icet.edu.service;

public interface EmailService {
    public boolean sendUserSignupVerificationCode(String toEmail, String subject, String otp);
    public boolean sendForgotPasswordVerificationCode(String toEmail, String subject, String otp);
}
