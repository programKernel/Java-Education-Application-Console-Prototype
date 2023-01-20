package prototype;

public class Problem {
    private String title = "";
    private String question = "";
    private String correctAnswer;

    Problem(String title, String question, String correctAnswer) {
        this.title = title;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
