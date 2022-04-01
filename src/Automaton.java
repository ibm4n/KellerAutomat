import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Automaton {


    private PushDown stack = new PushDown();
    public final static char EPSILON = 'e';
    State currentState = State.q0;


    public boolean runAutomate(String word) {
        //valid Word 4 3 +
        currentState = State.q0;

        for (int i = 0; i < word.length(); i++) {
            char inputSymbol = word.charAt(i);
            switch (currentState) {
                case q0:
                    if (Character.isDigit(inputSymbol)) {
                        stack.push(inputSymbol);
                    } else if (inputSymbol == '+' || inputSymbol == '*') {
                        stack.push(calculate(inputSymbol));
                        currentState = State.q1;
                    }
                    break;
                case q1:

                    break;
                case q2:
                    break;
                case q3:
                    break;
                case q4:
                    break;
                case failed:
                    break;
            }


        }


        return false;
    }

    private int calculate(char inputSymbol) {
        int firstNumber = stack.popAndReturn();
        int secondNumber = stack.popAndReturn();
        int result;

        if(inputSymbol == '+'){
            result = firstNumber + secondNumber;
        }
        if(inputSymbol == '*'){
            result = firstNumber * secondNumber;
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Automaton automaton = new Automaton();
        boolean running = true;
        while (running) {
            String word = reader.readLine();
            automaton.runAutomate(word);
            //     System.out.println((automaton.check(word) ? "bingooo" : "böööp"));
        }
    }


    enum State {
        q0, q1, q2, q3, q4, failed
    }
}
