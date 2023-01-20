package prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVRepository implements Repository {
    private static final String PATH_USERS = "src/main/resources/users.csv";

    public String saveUsersToFile(List<User> users) {
        try {
            FileWriter fileWriter = new FileWriter(PATH_USERS,false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (User user:users) {
                printWriter.println(user.getCSVLine());
            }
            printWriter.close();
            return "File saved successfully";
        }
        catch (IOException e) {
            return "An error has occured during saving.";
        }
    }

    public List<User> loadUsersFromFile() {
        List<String> dataList = loadUsersCSV();
        List<User> list = new ArrayList<>();
        for (int i = 0;i < dataList.size();i++) {
            String line = dataList.get(i);
            String[] table = line.split(",");
            if (table.length != 2) {
                continue;
            }
            User user = new User(table[0],table[1]);
            list.add(user);
        }
        return list;
    }

    public List<String> loadUsersCSV() {
        List<String> list = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(PATH_USERS));
            while (reader.hasNextLine()) {
                String user = reader.nextLine();
                list.add(user);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}
