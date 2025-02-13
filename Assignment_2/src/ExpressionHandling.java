import javax.swing.*;

public class ExpressionHandling {
    private String expression;
    public void input() {


        while(true) {
            char prevChar = '+';
            char currentChar = '+';
            int noOpen = 0;
            int noClosed = 0;
            boolean isValid = true;

            expression = JOptionPane.showInputDialog("Please enter an infix numerical expression between 3 and 20 characters:");
            if(expression.length() < 3 || expression.length() > 20) {
                JOptionPane.showMessageDialog(null, "The expression needs to be between 3 and 20 characters long", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
            } else if(!expression.matches("[0-9+*/^()-]+")) {
                JOptionPane.showMessageDialog(null, "Only the following characters are valid: +,-,*,/,^,(), and numbers from 0-9", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
            } else if(expression.charAt(0) == ')' || expression.charAt(expression.length() - 1) == '('){
                JOptionPane.showMessageDialog(null, "Invalid expression", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                char[] expressionChar = expression.toCharArray();
                for(int i = 0; i < expression.length(); i++) {
                    currentChar = expressionChar[i];
                    if("+*/^-)".indexOf(currentChar) != -1 && "+*/^-(".indexOf(prevChar) != -1){
                        JOptionPane.showMessageDialog(null, "Invalid expression", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                        isValid = false;
                        break;
                    }
                    if("0123456789".indexOf(currentChar) != -1 && "0123456789".indexOf(prevChar) != -1){
                        JOptionPane.showMessageDialog(null, "Only single digit operations are valid", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                        isValid = false;
                        break;
                    }
                    if(currentChar == '('){
                        noOpen++;
                    }
                    if(currentChar == ')'){
                        noClosed++;
                    }
                    prevChar = currentChar;
                }

                if(noOpen != noClosed){
                    JOptionPane.showMessageDialog(null, "Invalid expression", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                    isValid = false;
                }
                System.out.println(noClosed+","+ noOpen);
                if(isValid){
                    break;
                }

            }
        }
    }

    public void postfix(){

    }
}
