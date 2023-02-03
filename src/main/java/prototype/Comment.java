package prototype;

import java.io.Serializable;

public class Comment implements Serializable {
    private User sender;
    private String answer = "";

    Comment(User sender, String answer) {
        this.sender = sender;
        this.answer = answer;
    }

    public User getSender() {
        return sender;
    }

    public String getAnswer() {
        return answer;
    }
}
