package AuthService.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String authMail;

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${auth.service.base-url}")
    private String baseAuthUrl;

    private static final String RECOVERY_MAIL_TEMPLATE = "<a href=\"%s/password/recovery/%s\">Follow the link to recover your password</a>";

    public void sendRecoveryMail(String[] to, String uuid) {
        String mess = String.format(RECOVERY_MAIL_TEMPLATE, getBaseAuthUrl(), uuid);
        sendSimpleEmail(to, "Recovery link", mess);
    }

    private String getBaseAuthUrl() {
        return String.format(baseAuthUrl, serverPort);
    }

    private void sendSimpleEmail(String[] to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setFrom(authMail);
        message.setText(text);
        mailSender.send(message);
    }
}
