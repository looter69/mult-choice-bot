import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class View {
    private JFrame frame;
    private JPanel questionPanel, answerPanel;
    private JTextArea questionField, infoField;
    private JTextArea[] choiceTexts = new JTextArea[4];
    private JButton buttons[] = new JButton[4];
    private JButton saveButton;
    private Question currentQuestion;
    private Quizmaster qm;

    public View(Quizmaster q) {
        /* Frame Setup */
        qm = q;
        frame = new JFrame("Multiple Choice Bot");
        frame.setDefaultCloseOperation(3);
        frame.setBounds(0, 0, 600, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        /* Panel Setup */
        questionPanel = new JPanel();
        questionPanel.setBounds(0, 0, 600, 250);
        questionPanel.setLayout(null);

        answerPanel = new JPanel();
        answerPanel.setBounds(0, 250, 600, 250);
        answerPanel.setLayout(null);

        /* TextArea Setup */

        questionField = new JTextArea("Loading");
        questionField.setBounds(10, 70, 560, 140);
        questionField.setEditable(false);
        questionField.setLineWrap(true);
        questionPanel.add(questionField);

        infoField = new JTextArea("Info will be displayed here");
        infoField.setBounds(10, 10, 500, 50);
        infoField.setEditable(false);
        questionPanel.add(infoField);

        /* Button Setup */
        for (int i = 0; i < 4; i++) {
            // Creating Buttons
            buttons[i] = new JButton(Integer.toString(i + 1));
            buttons[i].setLayout(null);
            buttons[i].setBounds(10, 10 + i * 50, 50, 40);
            answerPanel.add(buttons[i]);

            // Action Listener
            String buttonString = Integer.toString(i + 1);
            buttons[i].addActionListener(e -> validateInput(buttonString, currentQuestion));
            
            // Text Portion Setup
            choiceTexts[i] = new JTextArea();
            choiceTexts[i].setLineWrap(true);
            choiceTexts[i].setBounds(70, 10 + i * 50, 500, 40);
            answerPanel.add(choiceTexts[i]);
        }

        /* Remainin Button Setup */
        saveButton = new JButton("💾");
        saveButton.setBounds(520, 10, 50, 50);
        saveButton.addActionListener(e -> quit());
        questionPanel.add(saveButton);

        /* Finalization */
        frame.add(questionPanel);
        frame.add(answerPanel);
        frame.setVisible(true);

        ask(); // Launch Application
    }

    /**
     * Presenting a Question to the User by getting a random Question from the Quizmaster and updating TextAreas
     */
    public void ask() {
        currentQuestion = qm.getRandomQuestion();
        questionField.setText(currentQuestion.getQuestion());
        for (int i = 0; i < 4; i++) {
            choiceTexts[i].setText(currentQuestion.getChoices()[i]);
        }
    }

    /**
     * Checks if the User's Input is correct
     * 
     * @param answer | User's response
     * @param q | Current Question
     */
    public void validateInput(String answer, Question q) {
        Boolean result = qm.validateInput(currentQuestion, answer);
        if (result) {
            infoField.setText(
                    "✅ Correct ✅" + "\n" + "Streak: " + qm.getStreak() + "\n" + "Highscore: " + qm.getHighscore());
        } else {
            // Presenting correct answer(s)
            String message = "❌" + " Wrong. The correct answer(s) is/was: " + "❌" + "\n";
            if (q.getCorrectAnswers().contains("1"))
                message += (q.getChoices()[0]) + "\n";
            if (q.getCorrectAnswers().contains("2"))
                message += (q.getChoices()[1]) + "\n";
            if (q.getCorrectAnswers().contains("3"))
                message += (q.getChoices()[2]) + "\n";
            if (q.getCorrectAnswers().contains("4"))
                message += (q.getChoices()[3]) + "\n";
            infoField.setText(message);
        }
        ask();
    }

    /**
     * Quitting the program
     */
    public void quit() {
        frame.dispose();
        qm.saveToFile();
    }
}
