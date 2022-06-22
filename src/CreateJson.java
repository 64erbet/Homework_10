import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CreateJson {
    public static String nameFirstColum = "";
    public static String nameSecondColum = "";
    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("file_2.txt");
            BufferedReader samBR = new BufferedReader(new InputStreamReader(inputStream));
            String line = samBR.readLine();
            String[] masFirstString = line.split(" ");
            nameFirstColum = masFirstString[0];
            nameSecondColum = masFirstString[1];
            List<User> users = new ArrayList<User>()
            {
                @Override
                public String toString() {
                    String rez = "[";
                    for(int i=0; i<this.size(); i++) {
                        if (i != this.size()-1) {
                            rez += this.get(i) + ", ";
                        } else {
                            rez += this.get(i);
                        }
                    }
                    return (rez + " \n]");
                }
            };
            FileWriter fileWriter = new FileWriter("file_2.json");

            line = samBR.readLine();
            while (line != null) {
                String[] masLine = line.split(" ");
                User newUser = new User(masLine[0], Integer.parseInt(masLine[1]));
                users.add(newUser);

                line = samBR.readLine();
            }
            fileWriter.write(users.toString());
            fileWriter.close();
            System.out.println(users);

        } catch (Exception e) {
            System.out.println("!!! Поймали Exception");
            e.printStackTrace();
        }

    }
    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "\n{\n" +
                    "\"" + CreateJson.nameFirstColum + "\":" + "\"" + name + "\"" + "\n" +
                    "\"" + CreateJson.nameSecondColum + "\":" + age +
                    "\n}";
        }
    }
}
