import java.util.Random;

/**
 * Data Structure to store Questions, Choices and correct answer
 */
public class Question {
    private String question;
    private int correctAnswer;
    private String[] choices;

    /**
     * Constructor for the Question.
     * Takes an Array of format
     * "Q : A1 : A2 : A3 : A4 : i"
     * where: Q is the Question, A1-A4 are possible choices and i is the index of
     * the only correct answer
     * 
     * @param input | formatted question-answer combo
     */
    public Question(String[] input, int line) {
        try {
            choices = new String[4];
            question = input[0];
            correctAnswer = Integer.parseInt(input[5].strip());
            for (int i = 1; i < 5; i++) {
                choices[i - 1] = input[i].strip();
            }
        } catch (Exception e) {
            System.err.println("Error in this Line: " + line);
        }

    }

    /**
     * Getter for the Question
     * 
     * @return | Question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Checks if the User's input is correct
     * 
     * @param index | User Input
     * @return | Bool if User Input matches the correct answer index
     */
    public Boolean isCorrectAnswer(int index) {
        if (index == correctAnswer)
            return true;
        else
            return false;
    }

    /**
     * Getter for the choices
     * 
     * @return | Array of Choices
     */
    public String[] getChoices() {
        return choices;
    }

    /**
     * Getter for the Index of the correct answer
     * 
     * @return | Index of the correct answer
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Method to shuffle the order of the choices
     */
    public void shuffleChoices() {
        // Storing the correct choice temporarily
        String corr = choices[correctAnswer - 1];
        Random random = new Random();

        // Shuffling
        for (int i = choices.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            String temp = choices[i];
            choices[i] = choices[j];
            choices[j] = temp;
        }

        // Searching for the correct answer and updating the correctAnswer int
        for (int i = 0; i < choices.length; i++) {
            if (choices[i].equals(corr)) {
                correctAnswer = i + 1;
            }
        }
    }
}