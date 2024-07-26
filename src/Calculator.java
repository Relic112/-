import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculator extends JFrame {
    private JTextField inputField;
    private JTextField outputField;
    private JTextArea historyArea;

    public Calculator() {
        setTitle("Calculator");
        setSize(600, 371);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(2, 1));
        inputField = new JTextField();
        outputField = new JTextField();
        outputField.setEditable(false);
        Font font = new Font("Arial", Font.PLAIN, 32); // 你可以根据需要调整字体大小和类型
        inputField.setFont(font);
        outputField.setFont(font);

        panel.add(inputField);
        panel.add(outputField);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        Font fontt = new Font("Arial", Font.PLAIN, 16);
        historyArea.setFont(fontt);
        scrollPane.setPreferredSize(new Dimension(200, 309));

        add(panel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.EAST);

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateResult();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    inputField.setText(outputField.getText());
                    outputField.setText("");
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    inputField.setText("");
                }
            }
        });

        setVisible(true);
    }

    private void calculateResult() {
        try {
            String input = inputField.getText();
            double result = eval(input);
            outputField.setText(String.valueOf(result));
            updateHistory(input + " = " + result);
        } catch (Exception e) {
            outputField.setText("Error");
            updateHistory(inputField.getText() + " = Error");
        }
    }

    private void updateHistory(String record) {
        historyArea.append(record + "\n");
    }

    public double eval(final String str) {
        class Parser {
            int pos = -1, c;

            void nextChar() {
                c = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (c == ' ') nextChar();
                if (c == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) c);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((c >= '0' && c <= '9') || c == '.') {
                    while ((c >= '0' && c <= '9') || c == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) c);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }
        return new Parser().parse();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
