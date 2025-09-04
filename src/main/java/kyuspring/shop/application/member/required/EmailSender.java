package kyuspring.shop.application.member.required;

import kyuspring.shop.domain.member.Email;

/**
 * 이메일을 발송한다
 */
public interface EmailSender {
    void send(Email email, String subject, String body);
}
