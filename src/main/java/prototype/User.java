package prototype;

import java.io.Serializable;

public class User implements Serializable {
    private String username = "";
    private String password = "";

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCSVLine() {
        return String.format("%s,%s",username,password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
