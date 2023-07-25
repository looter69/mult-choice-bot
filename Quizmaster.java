import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Quizmaster {
    private static int streak = 0;
    private ArrayList<Question> questionList;
    private Scanner scanner = new Scanner(System.in);

    public Quizmaster(ArrayList<Question> questions) {
        questionList = questions;
        ask();
    }

    public void ask() {
        // Prep work
        Collections.shuffle(questionList);
        Question q = questionList.get(0);
        q.shuffleChoices();

        // Posing Question and giving options
        System.out.println(q.getQuestion());
        System.out.println("Please give the correct Answer (1|2|3|4) | '0' quits the program.");
        for (int i = 0; i < q.getChoices().length; i++) {
            System.out.println((i + 1) + " | " + q.getChoices()[i]);
        }

        // Awaiting answer
        int answer = scanner.nextInt();

        if (answer != 0) {
            if (q.isCorrectAnswer(answer)) {
                correct();
            } else {
                incorrect(q);
            }
            spacer();
            ask();
        }else{
            System.out.println("Quitting. Your streak was: " + streak);
        }
    }

    public void spacer() {
        for (int i = 0; i < 10; i++) {
            System.out.println(" ");
        }
    }

    public static void correct() {
        streak++;
        System.out.println("Correct! Current streak: " + streak);
    }

    public static void incorrect(Question q) {
        System.out.println("Not Correct. The correct answer is: " + q.getChoices()[q.getCorrectAnswer() - 1]);
    }
}
