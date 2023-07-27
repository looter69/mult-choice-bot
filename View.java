import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class View {
    private JFrame frame;
    private JPanel questionPanel, answerPanel;
    private JTextArea questionArea, infoArea;
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
        questionPanel.setBackground(new Color(250, 250, 250));

        answerPanel = new JPanel();
        answerPanel.setBounds(0, 250, 600, 250);
        answerPanel.setLayout(null);
        answerPanel.setBackground(new Color(250, 250, 250));

        /* TextArea Setup */
        questionArea = new JTextArea("Loading");
        questionArea.setBounds(10, 70, 560, 140);
        questionPanel.add(applyStyling(questionArea));

        infoArea = new JTextArea("Info will be displayed here");
        infoArea.setBounds(10, 10, 500, 50);
        questionPanel.add(applyStyling(infoArea));

        /* Button Setup */
        for (int i = 0; i < 4; i++) {
            // Creating Buttons
            buttons[i] = new JButton(Integer.toString(i + 1));
            buttons[i].setLayout(null);
            buttons[i].setBounds(10, 10 + i * 50, 50, 40);
            answerPanel.add(applyStyling(buttons[i]));

            // Action Listener
            String buttonString = Integer.toString(i + 1);
            buttons[i].addActionListener(e -> validateInput(buttonString, currentQuestion));

            // Text Portion Setup
            choiceTexts[i] = new JTextArea();
            choiceTexts[i].setLineWrap(true);
            choiceTexts[i].setBounds(70, 10 + i * 50, 500, 40);
            answerPanel.add(applyStyling(choiceTexts[i]));
        }

        /* Remainin Button Setup */
        saveButton = new JButton("💾");
        saveButton.setBounds(520, 10, 50, 50);
        saveButton.addActionListener(e -> quit());
        questionPanel.add(applyStyling(saveButton));

        /* Finalization */
        frame.add(questionPanel);
        frame.add(answerPanel);
        frame.setVisible(true);

        ask(); // Launch Application
    }

    /**
     * Presenting a Question to the User by getting a random Question from the
     * Quizmaster and updating TextAreas
     */
    public void ask() {
        currentQuestion = qm.getRandomQuestion();
        questionArea.setText(currentQuestion.getQuestion());
        for (int i = 0; i < 4; i++) {
            choiceTexts[i].setText(currentQuestion.getChoices()[i]);
        }
    }

    /**
     * Checks if the User's Input is correct
     * 
     * @param answer | User's response
     * @param q      | Current Question
     */
    public void validateInput(String answer, Question q) {
        Boolean result = qm.validateInput(currentQuestion, answer);
        if (result) {
            infoArea.setText(
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
            infoArea.setText(message);
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

    /**
     * Applies styling to a given Button
     * 
     * @param b | Button
     * @return | styled Button
     */
    private JButton applyStyling(JButton b) {
        if (!b.getText().equals("💾")) {
            b.setFont(new Font("Arial", Font.PLAIN, 14));
        }
        b.setBackground(new Color(51, 153, 255));
        b.setForeground(Color.white);
        b.setBorder(new LineBorder(new Color(51, 153, 255), 2));
        b.setFocusPainted(false);
        return b;
    }

    /**
     * Applies styling to a given TextArea
     * 
     * @param ta | TextArea
     * @return | styled TextArea
     */
    private JTextArea applyStyling(JTextArea ta) {
        ta.setEditable(false);
        ta.setFont(new Font("Arial", Font.PLAIN, 14));
        ta.setBackground(Color.white); // White background
        ta.setForeground(Color.black); // Black text color
        ta.setBorder(new LineBorder(new Color(51, 153, 255), 2)); // Light blue border
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        return ta;
    }
}