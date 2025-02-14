import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        ExpressionHandling expression = new ExpressionHandling();
        expression.input();
        expression.postfix();
        expression.postEval();
    }
}
