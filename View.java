import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View {
    private JFrame frame;
    private JPanel questionPanel, answerPanel;
    private JTextField questionField, infoField;
    private JButton buttons[] = new JButton[4];
    private JButton quitButton;
    private Question currentQuestion;
    private Quizmaster qm;

    public View(Quizmaster q) {
        qm = q;
        frame = new JFrame("Multiple Choice Bot");
        frame.setDefaultCloseOperation(3);
        frame.setBounds(0, 0, 600, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        questionPanel = new JPanel();
        questionPanel.setBounds(0, 00, 600, 250);
        questionPanel.setLayout(null);

        questionField = new JTextField("Loading");
        questionField.setBounds(10, 60, 560, 150);
        questionField.setEditable(false);
        questionPanel.add(questionField);

        infoField = new JTextField("Info will be displayed here");
        infoField.setBounds(10, 10, 500, 30);
        infoField.setEditable(false);
        questionPanel.add(infoField);

        answerPanel = new JPanel();
        answerPanel.setBounds(0, 250, 600, 250);
        answerPanel.setLayout(null);

        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            buttons[i].setBounds(10, 10 + i * 50, 560, 40);
            buttons[i].setText(Integer.toString(i));
            answerPanel.add(buttons[i]);
        }

        quitButton = new JButton("❌");
        quitButton.setBounds(530, 0, 50, 50);
        quitButton.addActionListener(e -> quit());
        questionPanel.add(quitButton);

        buttons[0].addActionListener(e -> validateInput("1"));
        buttons[1].addActionListener(e -> validateInput("2"));
        buttons[2].addActionListener(e -> validateInput("3"));
        buttons[3].addActionListener(e -> validateInput("4"));

        frame.add(questionPanel);
        frame.add(answerPanel);
        frame.setVisible(true);

        ask();
    }

    public void ask() {
        currentQuestion = qm.getRandomQuestion();

        questionField.setText(currentQuestion.getQuestion());

        for (int i = 0; i < 4; i++) {
            buttons[i].setText(currentQuestion.getChoices()[i]);
        }

    }

    public void validateInput(String answer) {
        Boolean result = qm.validateInput(currentQuestion, answer);
        if (result) {
            infoField.setText("Correct");
        }else{
            infoField.setText("False");
        }
        ask();
    }

    public void quit () {
        frame.dispose();
        qm.saveToFile();
    }
}
