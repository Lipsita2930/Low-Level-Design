package Pattern.Behavioral.Iterator;

public class InterpreterDemo {
    public static void main(String[] args) {

        Expression num1 = new NumberExpression(5);
        Expression num2 = new NumberExpression(3);

        Expression addition = new AddExpression(num1, num2);
        System.out.println("5 + 3 = " + addition.interpret());
    }
}
