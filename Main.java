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

    public static void main(String[] args) {
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
                    System.out.println("Skipped Question in line " + line + " because it has been answered correctly enough times already!");
                }
                line++;
            }
            sc.close();
            new Quizmaster(questionList, useGui); // Starting
        } catch (FileNotFoundException e) {
            System.err.println("Error loading File!");
        }
    }
}