import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class that handles posing and handling Questions
 */
public class Quizmaster {
    private static int streak = 0;
    private ArrayList<Question> questionList; // Pot of all Questions
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for the Quizmaster class
     * 
     * @param questions | List of all Questions
     */
    public Quizmaster(ArrayList<Question> questions) {
        questionList = questions;
        ask();
    }

    /**
     * Method that poses a random question and checks the user's answer
     */
    public void ask() {
        // Drawing random question from the questionList
        Collections.shuffle(questionList);
        Question q = questionList.get(0);

        // Randomizing order of choices
        q.shuffleChoices();

        // Posing Question and giving options
        System.out.println(q.getQuestion());
        System.out.println("Please give the correct Answer (1|2|3|4) | '0' quits the program.");
        for (int i = 0; i < q.getChoices().length; i++) {
            System.out.println((i + 1) + " | " + q.getChoices()[i]);
        }

        // Awaiting answer
        int answer = scanner.nextInt();

        // Handling input
        if (answer != 0) {
            if (q.isCorrectAnswer(answer)) {
                correct();
            } else {
                incorrect(q);
            }
            spacer();
            ask(); // Next Round
        }else{
            // Quitting if input is 0
            System.out.println("Quitting. Your streak was: " + streak);
        }
    }

    /**
     * Prints a spacer to the Terminal
     */
    public void spacer() {
        for (int i = 0; i < 20; i++) {
            System.out.println(" ");
        }
    }

    /**
     * Called when the Question is answered correctly
     */
    public static void correct() {
        streak++;
        System.out.println("Correct! Current streak: " + streak);
    }

    /**
     * Called when the Question is answered incorrectly 
     * 
     * @param q | The prior Question
     */
    public static void incorrect(Question q) {
        System.out.println("Not Correct. The correct answer is: " + q.getChoices()[q.getCorrectAnswer() - 1]);
        streak = 0;
    }
}