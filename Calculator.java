import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    // Declare all components
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton sqrtButton, expButton, eqButton, clrButton, delButton;
    private JPanel panel;

    private Font font;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        // Initialize frame
        setTitle("Calculator");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Initialize display
        display = new JTextField();
        display.setBounds(50, 25, 300, 50);
        display.setEditable(false);
        add(display);

        // Initialize buttons
        font = new Font("Ink Free", Font.BOLD, 30);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");

        sqrtButton = new JButton("√");
        expButton = new JButton("^");
        eqButton = new JButton("=");
        clrButton = new JButton("C");
        delButton = new JButton("DEL");

        functionButtons = new JButton[] {
                addButton, subButton, mulButton, divButton,
                sqrtButton, expButton, eqButton, clrButton, delButton
        };

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setFont(font);
            button.setFocusable(false);
        }

        // Add buttons to panel
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(5, 4, 10, 10)); // Adjusted the GridLayout

        // Add number buttons to panel in correct order
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(clrButton);
        panel.add(numberButtons[0]);
        panel.add(eqButton);
        panel.add(divButton);
        panel.add(sqrtButton);
        panel.add(expButton);
        panel.add(delButton);

        add(panel);

        clrButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(250, 430, 100, 50);

        add(clrButton);
        add(delButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == clrButton) {
            display.setText("");
        }

        if (e.getSource() == delButton) {
            String string = display.getText();
            display.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                display.setText(display.getText() + string.charAt(i));
            }
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '+';
            display.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '-';
            display.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '*';
            display.setText("");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '/';
            display.setText("");
        }

        if (e.getSource() == sqrtButton) {
            num1 = Double.parseDouble(display.getText());
            display.setText(String.valueOf(Math.sqrt(num1)));
        }

        if (e.getSource() == expButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '^';
            display.setText("");
        }

        if (e.getSource() == eqButton) {
            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
            }

            display.setText(String.valueOf(result));
            num1 = result;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}