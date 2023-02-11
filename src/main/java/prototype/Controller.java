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
            System.out.println("\n1. Zarejestruj się\n2. Zaloguj się\n3. Wyloguj się\n4. Wyjdź");
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
                    if (service.getCurrentUser() == null) {
                        System.out.println("Użytkownik nie jest zalogowany, więc nie może się wylogować.");
                    } else {
                        service.clearCurrentUser();
                        System.out.println("Wylogowano pomyślnie.");
                    }
                    break;
                case "4":
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
        service.setCurrentUser(newUser);
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
                service.setCurrentUser(new User(username, password));
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

    public void displayQuestions(int categoryIndex) {
        Category chosenCategory = service.getCategoryByIndex(categoryIndex - 1);
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
                answerQuestion(chosenCategory, categoryIndex, answer2);
            }
        }
    }

    public void answerQuestion(Category chosenCategory, int categoryIndex, int problemIndex) {
        boolean running = true;
        while (running) {
            Problem currentProblem = chosenCategory.getProblems().get(problemIndex - 1);
            System.out.println("\n" + currentProblem.getTitle().toUpperCase(Locale.ROOT) + "\n" + currentProblem.getQuestion());
            System.out.print("\nWpisz komentarz, wyświetl komentarze wpisująć \"KOM\" lub cofnij się wpisując 0: ");
            String answer3 = reader.nextLine();
            if (answer3.equals("0")) {
                running = false;
            } else if (answer3.equals("KOM")) {
                displayComments(currentProblem);
            } else {
                service.getCategories().get(categoryIndex - 1).getProblems().get(problemIndex - 1).addComment(new Comment(service.getCurrentUser(), answer3));
                System.out.println("Odpowiedź została zapisana.");
                service.updateCategories();
                displayComments(currentProblem);
            }
        }
    }

    public void displayComments(Problem currentProblem) {
        if (currentProblem.getComments().size() != 0) {
            System.out.println("\nKOMENTARZE:\n");
            for (int i = 0;i < currentProblem.getComments().size();i++) {
                System.out.println(currentProblem.getComments().get(i).getSender().getUsername());
                System.out.println(currentProblem.getComments().get(i).getAnswer() + "\n");
            }
        }
    }
}
