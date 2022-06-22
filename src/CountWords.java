import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CountWords {
    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("words.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            String wholeString = "";
            while (line != null) {
                wholeString += line;
            }
        }

        wholeString.toString();
    }
}
