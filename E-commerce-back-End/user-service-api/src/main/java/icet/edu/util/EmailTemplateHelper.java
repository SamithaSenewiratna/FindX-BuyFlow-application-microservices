package icet.edu.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class EmailTemplateHelper {
    public String generateTemplate(String templateFileName, String otp) {
        try {
            String path = "src/main/resources/templates/" + templateFileName;
            String content = new String(Files.readAllBytes(Paths.get(path)));
            return content.replace("{{otp}}", otp);
        } catch (IOException e) {
            e.printStackTrace();
            return "<p>Error loading email template.</p>";
        }
    }
}
