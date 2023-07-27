import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class that handles posing and handling Questions
 */
public class Quizmaster {
    private static int streak = 0;
    private static int highscore = 0;
    private static ArrayList<Question> questionList; // Pot of all Questions
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for the Quizmaster class
     * 
     * @param questions | List of all Questions
     */
    public Quizmaster(ArrayList<Question> questions, Boolean gui) {
        questionList = questions;
        if (gui) {
            new View(this);
        } else {
            ask();
        }
    }

    /**
     * Method that poses a random question and checks the user's answer
     */
    public void ask() {
        Question q = getRandomQuestion();

        if (q != null) {
            // Randomizing order of choices
            // q.shuffleChoices();

            // Posing Question and giving options
            System.out.println(q.getQuestion());
            System.out.println("Please give the correct Answer (1|2|3|4) | '0' quits the program.");
            for (int i = 0; i < q.getChoices().length; i++) {
                System.out.println((i + 1) + " | " + q.getChoices()[i]);
            }

            // Awaiting answer
            String answer = scanner.nextLine();

            // Handling input
            if (!answer.equals("0")) {
                if (validateInput(q, answer)) {
                    System.out.println("Correct! Current streak: " + streak);
                } else {
                    System.out.println("Not Correct. The correct answer(s) is/are: " + "\n");
                    if (q.getCorrectAnswers().contains("1"))
                        System.out.println(q.getChoices()[0]);
                    if (q.getCorrectAnswers().contains("2"))
                        System.out.println(q.getChoices()[1]);
                    if (q.getCorrectAnswers().contains("3"))
                        System.out.println(q.getChoices()[2]);
                    if (q.getCorrectAnswers().contains("4"))
                        System.out.println(q.getChoices()[3]);
                }
                spacer();
                ask(); // Next Round
            } else {
                // Quitting if input is 0
                System.out.println("Quitting. Your highscore was: " + highscore);
                saveToFile();
            }
        }
    }

    public Boolean validateInput(Question q, String answer) {
        if (q.isCorrectAnswer(answer)) {
            q.decreaseRemaining();
            if (q.getRemaining() <= 0) {
                questionList.remove(0);
            }
            streak++;
            if (streak > highscore)
                highscore = streak;
            return true;
        } else {

            streak = 0;
            return false;
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

    public void saveToFile() {
        String fileContent = "";
        for (Question q : questionList) {
            fileContent += q.prepareForSave() + "\n";
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Questions.txt"));
            bufferedWriter.write(fileContent);
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Error while saving content to file");
        }
    }

    public Question getRandomQuestion() {
        // Checking if the questionList contains something
        if (!questionList.isEmpty()) {
            // Drawing random question from the questionList
            Collections.shuffle(questionList);
            return questionList.get(0);
        } else {
            System.out.println("Concratulation, you answered each Question correctly three times!");
            return null;
        }
    }

    /**
     * Getter for the current streak
     * 
     * @return | current streak
     */
    public int getStreak() {
        return streak;
    }

    /**
     * Getter for the current highscore
     * 
     * @return | current highscore
     */
    public int getHighscore() {
        return highscore;
    }
}