import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View {
    private JFrame frame;
    private JPanel questionPanel;
    private JPanel answerPanel;
    private JButton buttons[] = new JButton[4];

    public View (Quizmaster qm) {
        frame = new JFrame("Multiple Choice Bot");
        frame.setDefaultCloseOperation(3);
        frame.setBounds(0, 0, 600, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        questionPanel = new JPanel();
        questionPanel.setBounds(0, 0, 600, 250);
        questionPanel.setLayout(null);
        questionPanel.setBackground(Color.BLACK);
    
        answerPanel = new JPanel();
        answerPanel.setBounds(0,250,600,250);
        answerPanel.setLayout(null);
        answerPanel.setBackground(Color.RED);

        for (int i = 0; i < 4; i++){
            buttons[i] = new JButton();
            buttons[i].setBounds(10, 10 + i * 50 , 560, 40);
            buttons[i].setText(Integer.toString(i));
            answerPanel.add(buttons[i]);
        }

        buttons[0].addActionListener(e -> System.out.println("0"));
        buttons[1].addActionListener(e -> System.out.println("1"));
        buttons[2].addActionListener(e -> System.out.println("2"));
        buttons[3].addActionListener(e -> System.out.println("3"));

        frame.add(questionPanel);
        frame.add(answerPanel);
        frame.setVisible(true);
        
    }

}
