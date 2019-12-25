package data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {

    @Value
    public static class LoginInfo {
        private String login;
        private String password;
    }

    public static LoginInfo getValidLoginInfo() {
        return new LoginInfo("vasya", "qwerty123");
    }

}
