package kyuspring.shop.domain.member;

public class MemberFixture {
    public static MemberRegisterRequest createMemberRegisterRequest(String emailAddress) {
        return new MemberRegisterRequest(emailAddress, "nickname", "password");
    }

    public static MemberRegisterRequest createMemberRegisterRequest() {
        return createMemberRegisterRequest("test@email.com");
    }

    public static PasswordEncoder createPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
    }
}
