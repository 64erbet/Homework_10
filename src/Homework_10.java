import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Homework_10 {
    public static void main(String[] args) {
        Homework_10 homework_10 = new Homework_10();

        homework_10.readNumbers();

        homework_10.createJson();

        homework_10.countWords();
    }
//************************ countWords() ***************************************
    public void countWords() {
        try {
            InputStream inputStream = new FileInputStream("words.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            String wholeString = "";
            while (line != null) {
                wholeString += line + " ";
                line = bufferedReader.readLine();
            }
            wholeString = wholeString.trim();
//            System.out.println(wholeString);
            String[] stringMas = wholeString.split(" ");

            //************ Будем считать колличесво повторяющихся слов в строке
            HashMap<String, Integer> rezultHM = new HashMap<String, Integer>()
            {
                @Override
                public String toString() {
                    String collectToString = this.entrySet()
                            .stream()
                            .map(entry -> entry.getKey() + " " + entry.getValue())
                            .collect(Collectors.joining("\n", "", ""));
                    return collectToString;
                }
            };

            String slovo = "";
            for(int i=0; i<stringMas.length; i++) {
                slovo = stringMas[i];
                int kol = 0;
                for (int j=i; j<stringMas.length; j++) {
                    if (stringMas[j].equals(slovo) && !rezultHM.keySet().contains(slovo)) {
                        kol++;
                    }
                }
                if (!rezultHM.keySet().contains(slovo)) {
                    rezultHM.put(slovo, kol);
                }
            }
            System.out.println(rezultHM);
            //*****************************************************************

        } catch (Exception e) {
            System.out.println("!!! Поймали Exception");
            e.printStackTrace();
        }
    }
//*****************************************************************************
//************************ createJson() ***************************************
    public static String nameFirstColum = "";
    public static String nameSecondColum = "";
    public void createJson() {
        try {
            InputStream inputStream = new FileInputStream("file_2.txt");
            BufferedReader samBR = new BufferedReader(new InputStreamReader(inputStream));
            String line = samBR.readLine();
            String[] masFirstString = line.split(" ");
            nameFirstColum = masFirstString[0];
            nameSecondColum = masFirstString[1];
            List<User> users = new ArrayList<User>() {
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
    class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "\n{\n" +
                    "\"" + Homework_10.nameFirstColum + "\":" + "\"" + name + "\"" + "\n" +
                    "\"" + Homework_10.nameSecondColum + "\":" + age +
                    "\n}";
        }
    }
//*****************************************************************************
//************************ readNumbers() **************************************
    public void readNumbers() {
        try {
            InputStream inputStream = new FileInputStream("file_1.txt");
            BufferedReader samBR = new BufferedReader(new InputStreamReader(inputStream));
            String line = samBR.readLine();

            while (line != null) {

                //*************** Предполагаем, что "валидный" номер телефона - это
                // строка в одном из двух форматов: (xxx) xxx-xxxx или xxx-xxx-xxxx
                // (х обозначает цифру).

                Pattern pattern_1 = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
                Matcher matcher_1 = pattern_1.matcher(line);
                Pattern pattern_2 = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}");
                Matcher matcher_2 = pattern_2.matcher(line);

                while (matcher_1.find() || matcher_2.find()){
                    System.out.println(line);
                }
                line = samBR.readLine();
            }
        } catch (Exception e) {
            System.out.println("!!! Поймали Exception: ");
            e.printStackTrace();
        }
    }
//*****************************************************************************
}
