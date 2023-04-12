package AuthService.service.mail;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String authMail;

    @Value("${frontend.port}")
    private Integer serverPort;

    @Value("${auth.service.base-url}")
    private String baseAuthUrl;

    private static final String RECOVERY_MAIL_TEMPLATE = "<a href=\"%spassword/recovery/%s\">Follow the link to recover your password</a>";

    public void sendRecoveryMail(String[] to, String uuid) {
        String mess = String.format(RECOVERY_MAIL_TEMPLATE, getBaseAuthUrl(), uuid);
        sendEmail(to, "Recovery link", mess);
    }

    private String getBaseAuthUrl() {
        return String.format(baseAuthUrl, serverPort);
    }

    @SneakyThrows
    private void sendEmail(String[] to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setFrom(authMail);
        mimeMessageHelper.setText(text, true);
        mailSender.send(message);
    }
}
