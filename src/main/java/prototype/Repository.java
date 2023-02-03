package prototype;

import java.util.List;

public interface Repository {
    public abstract String saveUsersToFile(List<User> users);

    public abstract List<User> loadUsersFromFile();

    public abstract String updateCategoriesToFile(List<Category> categories);

    public abstract List<Category> loadCategoriesFromFile();
}
