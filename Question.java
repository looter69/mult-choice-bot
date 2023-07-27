/**
 * Data Structure to store Questions, Choices and correct answer
 */
public class Question {
    private String question;
    private String correctAnswers;
    private String[] choices;
    private int remaining;

    /**
     * Constructor for the Question.
     * Takes an Array of format
     * "Q : A1 : A2 : A3 : A4 : i : j"
     * where: Q is the Question, A1-A4 are possible choices, i is the index of
     * the only correct answer and j is the remaining counter
     * 
     * @param input | formatted question-answer combo
     * @param line  | corresponding Line from source file
     */
    public Question(String[] input, int line) {
        try {
            choices = new String[4];
            question = input[0];
            remaining = Integer.parseInt(input[6].strip());
            correctAnswers = input[5].strip();
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
     * @param userInput | User Input
     * @return | Bool if User Input matches the correct answer index
     */
    public Boolean isCorrectAnswer(String userInput) {
        if (correctAnswers.contains(userInput)) {
            if (userInput.length() < 1) {
                // handling empty input
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
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
     * Getter for the String that contains the correct answer(s)
     * 
     * @return | Index of the correct answer
     */
    public String getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * Decreases the remaining value until it reaches 0
     */
    public void decreaseRemaining() {
        remaining--;
        if (remaining < 0)
            remaining = 0;
    }

    /**
     * Getter of the number of remaining correct answers
     * 
     * @return number of remaining successful tries
     */
    public int getRemaining() {
        return remaining;
    }

    /**
     * Method to shuffle the order of the choices
     */
    // INFO: Disabled shuffling for now to finalize support for multiple correct
    // answers
    /*
     * public void shuffleChoices() {
     * // Storing the correct choice temporarily
     * String corr = choices[correctAnswers - 1];
     * Random random = new Random();
     * 
     * // Shuffling
     * for (int i = choices.length - 1; i > 0; i--) {
     * int j = random.nextInt(i + 1);
     * 
     * String temp = choices[i];
     * choices[i] = choices[j];
     * choices[j] = temp;
     * }
     * 
     * // Searching for the correct answer and updating the correctAnswer int
     * for (int i = 0; i < choices.length; i++) {
     * if (choices[i].equals(corr)) {
     * correctAnswers = i + 1;
     * }
     * }
     * }
     */

    /**
     * Returns a String to save a Question to File in the format
     * Q : A1 : A2 : A3 : A4 : i : j
     * 
     * @return
     */
    public String prepareForSave() {
        return question.strip() + " : " + choices[0] + " : " + choices[1] + " : " + choices[2] + " : " + choices[3] + " : "
                + correctAnswers + " : " + Integer.toString(remaining);
    }
}