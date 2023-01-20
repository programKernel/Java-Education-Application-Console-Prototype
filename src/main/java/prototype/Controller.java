package prototype;

import java.util.Locale;
import java.util.Scanner;

public class Controller {
    private Scanner reader = new Scanner(System.in);
    private Service service = new Service();

    public void run() {
        System.out.print(":: ZADANIOMAT Z JAVY (PROTOTYP) ::\n");
        mainMenu();
    }

    public void mainMenu() {
        boolean menuRun = true;
        while (menuRun) {
            System.out.println("\n1. Zarejestruj się\n2. Zaloguj się\n3. Wyloguj się i wyjdź");
            System.out.print("Wybierz jedną z opcji: ");
            String menuInput = reader.nextLine();

            switch (menuInput) {
                case "1":
                    registerScenario();
                    problemSolving();
                    menuRun = false;
                    break;
                case "2":
                    logInScenario();
                    problemSolving();
                    menuRun = false;
                    break;
                case "3":
                    System.out.println("Do zobaczenia.");
                    menuRun = false;
                    break;
                default:
                    System.out.println("Niepoprawna wartość.");
                    break;
            }
        }
    }

    public void registerScenario() {
        String newUsername = "";
        String newPassword = "";
        boolean running = true;
        while (running) {
            System.out.print("\nWpisz nick: ");
            newUsername = reader.nextLine();
            boolean running2 = true;
            while (running2) {
                System.out.print("Wpisz hasło: ");
                newPassword = reader.nextLine();
                System.out.print("Wpisz hasło jeszcze raz: ");
                String secondPassword = reader.nextLine();
                if (!secondPassword.equals(newPassword)) {
                    System.out.println("Hasła nie są takie same. Spróbuj jeszcze raz!");
                } else {
                    running2 = false;
                }
            }
            if (service.doesNicknameExist(newUsername)) {
                System.out.println("Użytkownik o tym nicku już istnieje. Spróbuj jeszcze raz!");
            } else {
                running = false;
            }
        }
        User newUser = new User(newUsername,newPassword);
        service.addUserToUserList(newUser);
        System.out.println("Witaj, " + newUser.getUsername() + ".");
    }

    public void logInScenario() {
        boolean running = true;
        while (running) {
            System.out.print("\nWpisz nick: ");
            String username = reader.nextLine();
            System.out.print("Wpisz hasło: ");
            String password = reader.nextLine();
            boolean doesUserExist = service.doesUserExist(username, password);
            if (doesUserExist) {
                System.out.println("Witaj ponownie, " + username + ".");
                running = false;
            } else {
                System.out.println("Nick lub hasło zostały wpisane niepoprawnie. Spróbuj jeszcze raz.");
            }
        }
    }

    public void problemSolving() {
        boolean running = true;
        while (running) {
            System.out.println("\nDziały: ");
            for (int i = 0;i < service.getAmountCategories();i++) {
                System.out.println(i + 1 + ". " + service.getCategoryByIndex(i).getCategoryName());
            }
            System.out.print("\nWybierz dział wpisując jego numer lub cofnij się do menu głównego wpisując 0: ");
            int answer = reader.nextInt();
            reader.nextLine();

            if (answer == 0) {
                mainMenu();
                running = false;
            } else {
                displayQuestions(answer);
            }
        }
    }

    public void displayQuestions(int answer) {
        Category chosenCategory = service.getCategoryByIndex(answer - 1);
        boolean running1 = true;
        while (running1) {
            System.out.println("\nZadania z działu " + chosenCategory.getCategoryName() + ":");
            for (int i = 0;i < chosenCategory.getProblems().size();i++) {
                System.out.println(i + 1 + ". " + chosenCategory.getProblems().get(i).getTitle());
            }
            System.out.print("\nWybierz zadanie wpisując jego numer lub cofnij się wpisując 0: ");
            int answer2 = reader.nextInt();
            reader.nextLine();
            if (answer2 == 0) {
                running1 = false;
            } else {
                answerQuestion(chosenCategory,answer2);
            }
        }
    }

    public void answerQuestion(Category chosenCategory, int answer) {
        boolean running = true;
        while (running) {
            Problem currentProblem = chosenCategory.getProblems().get(answer - 1);
            System.out.println("\n" + currentProblem.getTitle().toUpperCase(Locale.ROOT) + "\n" + currentProblem.getQuestion());
            System.out.print("\nWpisz odpowiedź lub cofnij się wpisując 0: ");
            String answer3 = reader.nextLine();
            if (!answer3.equals("0")) {
                System.out.println("Odpowiedź została zapisana.");
            }
            running = false;
        }
    }
}
