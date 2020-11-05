import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    JPanel panel1;

    public MainForm() {
        setTitle("CALCULATOR");
        CalcPanel panel = new CalcPanel();
        add(panel);
        setSize(340, 480);
    }
}

class CalcPanel extends JPanel implements ActionListener {
    private final JButton display;
    private final JPanel panel;
    private String first;
    private String second;
    private String operand;


    public CalcPanel() {
        setLayout(new BorderLayout());

        first = second = operand = "";
        display = new JButton("0");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);
        JButton button = new JButton("=");
        button.addActionListener(this);
        button.setBackground(Color.cyan);
        add(button, BorderLayout.SOUTH);


        panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(5, 4));

        addButton("7", Color.gray);
        addButton("8", Color.gray);
        addButton("9", Color.gray);
        addButton("/", Color.darkGray);

        addButton("4", Color.gray);
        addButton("5", Color.gray);
        addButton("6", Color.gray);
        addButton("*", Color.darkGray);

        addButton("1", Color.gray);
        addButton("2", Color.gray);
        addButton("3", Color.gray);
        addButton("-", Color.darkGray);

        addButton("0", Color.gray);
        addButton(".", Color.gray);
        addButton("%", Color.darkGray);
        addButton("+", Color.darkGray);
        addButton("C", Color.white);

        add(panel, BorderLayout.CENTER);
    }
    private void addButton(String label,Color color) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setBackground(color);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
            if (!operand.equals("")) {
                second += s;
            } else {
                first += s;
            }
            display.setText(first + operand + second);
        }
        else if (s.charAt(0) == 'C') {
            first = operand = second = "";

            display.setText(first + operand + second);
        }
        else if (s.charAt(0) == '=') {
            double res;
            switch(operand){
                case "+":
                    res = Double.parseDouble(first) + Double.parseDouble(second);
                    break;
                case "-":
                    res = Double.parseDouble(first) - Double.parseDouble(second);
                    break;
                case "/":
                    res = Double.parseDouble(first) / Double.parseDouble(second);
                    break;
                default :
                    res = Double.parseDouble(first) * Double.parseDouble(second);
                    break;
            }

            display.setText(first + operand + second + "=" + res);

            first = Double.toString(res);

            operand = second = "";

        } else if (s.charAt(0) == '%') 
            double res;
            switch(operand){
                case "+":
                    res = (Double.parseDouble(first) + (Double.parseDouble(first)) / 100 * Double.parseDouble(second));
                    break;
                case "-":
                    res = (Double.parseDouble(first) - (Double.parseDouble(first)) / 100 * Double.parseDouble(second));
                    break;
                case "/":
                    res = ((Double.parseDouble(first) / (Double.parseDouble(second) / 100)));
                    break;
                default:
                    res = (Double.parseDouble(first) * Double.parseDouble(second) / 100);
                    break;
            }

            display.setText(first + operand + second + "% =" + res);

            first = Double.toString(res);

            operand = second = "";
        }
        else {
            if (operand.equals("") || second.equals(""))
                if (first.isEmpty()) {
                    first = s;
                } else {
                    operand = s;
                }
            else {
                double te;
                switch (operand){
                    case "+":
                        te = Double.parseDouble(first) + Double.parseDouble(second);
                        break;
                    case "-":
                        te = Double.parseDouble(first) - Double.parseDouble(second);
                        break;
                    case "/":
                        te = Double.parseDouble(first) / Double.parseDouble(second);
                        break;
                    default:
                        te = Double.parseDouble(first) * Double.parseDouble(second);
                        break;
                }


                first = Double.toString(te);

                operand = s;

                second = "";
            }

            display.setText(first + operand + second);
        }
    }
}
