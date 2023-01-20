package prototype;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private String title = "";
    private String question = "";
    private List<Comment> answers;

    Problem(String title, String question) {
        this(title,question,new ArrayList<>());
    }

    Problem(String title, String question, List<Comment> answers) {
        this.title = title;
        this.question = question;
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public List<Comment> getAnswers() {
        return answers;
    }
}
