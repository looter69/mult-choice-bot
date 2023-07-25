import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Class used for Setup
 */
public class Main {
    static ArrayList<Question> questionList = new ArrayList<Question>(); // Storing all Questions here

    public static void main(String[] args) {
        try {
            // Reading Questions from File
            Scanner sc = new Scanner(new File("Questions.txt"));
            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(":");
                questionList.add(new Question(input));
            }
            sc.close();
            new Quizmaster(questionList); // Starting
        } catch (FileNotFoundException e) {
            System.err.println("Error loading File!");
        }
    }
}