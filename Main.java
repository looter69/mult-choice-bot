import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Class used for Setup
 */
public class Main {
    static ArrayList<Question> questionList = new ArrayList<Question>(); // Storing all Questions here
    private static Boolean useGui = true;
    private static LanguageModule lm;

    public static void main(String[] args) {
        Language lang = Language.ENGLISH;
        lm = new LanguageModule(lang);
        try {
            // Reading Questions from File
            Scanner sc = new Scanner(new File("Questions.txt"));

            // Line index for debug purposes
            int line = 1;

            // Populating list
            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(":");
                // Checking if question has more than 0 correct guesses remaining
                if (0 != Integer.parseInt(input[6].strip())) {
                    questionList.add(new Question(input, line));
                }else{
                    System.out.println(lm.debugReadFile1 + line + lm.debugReadFile2);
                }
                line++;
            }
            sc.close();
            new Quizmaster(questionList, useGui, lm, lang); // Starting
        } catch (FileNotFoundException e) {
            System.err.println(lm.errorReadFile);
        }
    }
}