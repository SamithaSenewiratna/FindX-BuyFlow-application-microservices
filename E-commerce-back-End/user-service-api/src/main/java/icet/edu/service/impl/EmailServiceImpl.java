package icet.edu.service.impl;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import icet.edu.service.EmailService;
import icet.edu.util.EmailTemplateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailTemplateHelper emailTemplateHelper;

    @Value("${samithani17gmail.com}")
    private String fromEmail;

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Override
    public boolean sendUserSignupVerificationCode(String toEmail, String subject, String otp) {
        String html = emailTemplateHelper.generateTemplate("welcome-welcome-email.html", otp);
        return sendEmail(toEmail, subject, html);
    }

    @Override
    public boolean sendForgotPasswordVerificationCode(String toEmail, String subject, String otp) {
        String html = emailTemplateHelper.generateTemplate("forgot-password-welcome-email.html", otp);
        return sendEmail(toEmail, subject, html);
    }

    private boolean sendEmail(String to, String subject, String htmlContent) {
        Email from = new Email(fromEmail);
        Email toEmail = new Email(to);
        Content content = new Content("text/html", htmlContent);
        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            return response.getStatusCode() >= 200 && response.getStatusCode() < 300;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
