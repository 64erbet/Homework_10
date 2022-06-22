import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadNumbers {
    private static String line;

    public static void main(String[] args) {
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
}