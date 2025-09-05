package kyuspring.shop.domain.member;

import kyuspring.shop.domain.shared.Email;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailTest {
    @Test
    void equality() {
        Email email1 = new Email("test@email.com");
        Email email2 = new Email("test@email.com");

        assertThat(email1).isEqualTo(email2);
    }
}