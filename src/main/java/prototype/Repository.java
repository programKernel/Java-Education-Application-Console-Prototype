package prototype;

import java.util.List;

public interface Repository {
    public abstract String saveUsersToFile(List<User> users);

    public abstract List<User> loadUsersFromFile();
}
