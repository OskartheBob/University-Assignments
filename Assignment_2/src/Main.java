import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        input();
        postFix();
    }

    public static void input() {
        while(true) {
            String expression = JOptionPane.showInputDialog("Please enter an infix numerical expression between 3 and 20 characters:");
            if (expression.length() < 3 || expression.length() > 20) {
                JOptionPane.showMessageDialog(null, "The expression needs to be between 3 and 20 characters long", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
            } else if (!expression.matches("[0-9+*/^()-]+")) {
                JOptionPane.showMessageDialog(null, "Only the following characters are valid: +,-,*,/,^,(), and numbers from 0-9", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                break;
            }
        }
    }

    public static void postFix() {

    }
}
