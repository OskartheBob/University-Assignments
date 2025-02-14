import javax.swing.*;
import java.lang.*;
import java.util.*;

public class ExpressionHandling {
    private String expression;
    private char[] expressionChar;
    private char currentChar;
    private int operatorValue = 0;

    public void input() {


        while(true) {
            char prevChar = '+';
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
                expressionChar = expression.toCharArray();
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
                if(isValid){
                    break;
                }

            }
        }
    }

    public int operatorValue(char operator){
        switch(operator) {
            case '^':
                return operatorValue = 3;
            case '*', '/':
                return operatorValue = 2;
            case '+', '-':
                return operatorValue = 1;
            default:
                return operatorValue = 0;
        }
    }

    public void postfix(){
        String output = "";
        ArrayStack arrayStack = new ArrayStack();
        char prevChar = '0';
        for(int i = 0 ; i < expression.length(); i++){
            currentChar = expressionChar[i];
            if(Character.isDigit(currentChar)){
                output += currentChar;
            } else if(arrayStack.isEmpty() || operatorValue(currentChar) > operatorValue((char)arrayStack.top()) || (char)arrayStack.top() == '(') {
                arrayStack.push(currentChar);

            }else{
                while(!arrayStack.isEmpty() && operatorValue((char)arrayStack.top()) >= operatorValue(currentChar)) {
                    if ((char) arrayStack.top() == '(' || (char) arrayStack.top() == ')') {
                        break;
                    }
                    output += arrayStack.pop();
                }
                if(currentChar != ')') {
                    arrayStack.push(currentChar);
                }
            }

            if (currentChar == ')') {
                while (!arrayStack.isEmpty() && (char)arrayStack.top() != '(') {
                    output += arrayStack.pop();
                }
                if (!arrayStack.isEmpty() && (char) arrayStack.top() == '(') {
                    arrayStack.pop();
                }
            }
        }
        while (!arrayStack.isEmpty()) {
            output += arrayStack.pop();
        }
        System.out.println(output);
    }
    public void postEval(){
        ArrayStack arrayStack = new ArrayStack();
        for(int i = 0; i < expression.length(); i++){
            currentChar = expressionChar[i];
            if(Character.isDigit(currentChar)){
                arrayStack.push(currentChar);
            }else{
                float temp;
                switch(currentChar){
                    case '+':
                        temp = ((Number)arrayStack.pop()).floatValue();
                        arrayStack.push(temp + ((Number)arrayStack.pop()).floatValue());
                        break;
                    case '-':
                        temp = (Float)arrayStack.pop();
                        arrayStack.push(temp - (float)arrayStack.pop());
                        break;
                    case '*':
                        temp = (float)arrayStack.pop();
                        arrayStack.push(temp * (float)arrayStack.pop());
                        break;
                    case '/':
                        temp = (float)arrayStack.pop();
                        arrayStack.push(temp / (float)arrayStack.pop());
                        break;
                    case '^':
                        temp = (float)arrayStack.pop();
                        arrayStack.push(Math.pow(temp, (float)arrayStack.pop()));
                        break;

                }

            }
        }
        System.out.println(arrayStack.pop());
    }

}
