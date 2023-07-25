import java.util.Random;

public class Question {
    private String question;
    private int correctAnswer;
    private String[] choices;

    public Question(String[] input) {
        choices = new String[4];
        question = input[0];
        correctAnswer = Integer.parseInt(input[5].strip());
        for (int i = 1; i < 5; i++) {
            choices[i - 1] = input[i].strip();
        }
    }

    public String getQuestion() {
        return question;
    }

    public Boolean isCorrectAnswer(int index) {
        if (index == correctAnswer)
            return true;
        else
            return false;
    }

    public String[] getChoices() {
        return choices;
    }

    public int getCorrectAnswer () {
        return correctAnswer;
    }

    public void shuffleChoices() {
        String corr = choices[correctAnswer - 1];
        Random random = new Random();

        for (int i = choices.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            // Swap choices[i] and choices[j]
            String temp = choices[i];
            choices[i] = choices[j];
            choices[j] = temp;
        }

        for (int i = 0; i < choices.length; i++){
            if (choices[i].equals(corr)){
                correctAnswer = i+1;
            }
        }
    }
}
