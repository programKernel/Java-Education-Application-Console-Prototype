package prototype;

import java.util.ArrayList;
import java.util.List;

//Solid - Single Responsibility
public class Service {
    private Repository repository = new CSVRepository();
    private List<Category> categoryList = new ArrayList<>();
    private List<User> users;

    Service() {
        users = repository.loadUsersFromFile();

        List<Problem> basicProblems = basicProblemFactory();
        Category basics = new Category("Podstawy", basicProblems);
        categoryList.add(basics);

        List<Problem> OOPProblems = OOPProblemFactory();
        Category OOP = new Category("Programowanie obiektowe", OOPProblems);
        categoryList.add(OOP);
    }

    public List<Problem> basicProblemFactory() {
        Problem basics1 = new Problem("Wyświetlanie w konsoli",
                "Jak wygląda instrukcja, która drukuje w języku Java?");
        List<Problem> basicProblems = new ArrayList<>();
        basicProblems.add(basics1);
        return basicProblems;
    }

    public List<Problem> OOPProblemFactory() {
        Problem OOP1 = new Problem("Tworzenie obiektu",
                "Jaką instrukcją należy stworzyć obiekt przykładowej klasy Controller?");
        List<Problem> OOPProblems = new ArrayList<>();
        OOPProblems.add(OOP1);
        return OOPProblems;
    }

    public boolean doesNicknameExist(String newUsername) {
        for (User user:users) {
            if (user.getUsername().equals(newUsername)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesUserExist(String username, String password) {
        for (User user:getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void addUserToUserList(User newUser) {
        users.add(newUser);
        repository.saveUsersToFile(users);
    }

    public List<User> getUsers() {
        return users;
    }

    public int getAmountCategories() {
        return categoryList.size();
    }

    public Category getCategoryByIndex(int index) {
        return categoryList.get(index);
    }
}
