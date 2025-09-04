package kyuspring.shop.adapter.integration;

import kyuspring.shop.application.member.required.EmailSender;
import kyuspring.shop.domain.member.Email;
import org.springframework.stereotype.Component;

@Component
public class DummyEmailSender implements EmailSender {
    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("DummyEmailSender send email: " + email);
    }
}
