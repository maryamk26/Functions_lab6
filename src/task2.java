import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class task2 {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("C:\\2ND SEM. CODES\\smstr2.lab6\\src\\LAB6WORDS");
        BufferedReader BUFFER = new BufferedReader(fileReader);

        String[] W = new String[5];
        int i = 0;
        try {
            String LINE;
            while ((LINE = BUFFER.readLine()) != null) {
                W[i] = LINE;
                i++;
            }
            BUFFER.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int FIVELW = 0;
        int PALI = 0;
        int WW = 0;
        for (String WORD : W) {
            if (WORD.length() == 5) {
                FIVELW++;
            }
            if (WORD.charAt(0) == 'w' | WORD.charAt(0) == 'W') {
                WW++;
            }
            if (isPalindromes(WORD)) {
                PALI++;
            }
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("THE WORDS THAT HAVE 5 LETTERS : " + FIVELW);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("PALINDROME WORDS : " + PALI);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("THE WORDS THAT START WITH THE LETTER W : " + WW);
        System.out.println("-----------------------------------------------------------------------");

    }

    public static boolean isPalindromes(String WORD) {
        boolean KEY = true;
        char[] letters = new char[WORD.length()];
        for (int i = 0; i < WORD.length(); i++) {
            letters[i] = WORD.charAt(i);
        }

        int s = WORD.length() -1;
        for (int i = 0; i < WORD.length(); i++) {
            if (i > s) {
                break;
            }
            if (letters[i] != letters[s]) {
                KEY = false;
            }
            s--;
        }
        return KEY;
    }
}

