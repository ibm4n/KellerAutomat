package pushdown.automata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.err;

public class Automaton {


    private PushDown stack = new PushDown();
    private String result = null;
    public final static char EPSILON = 'e';
    State currentState = State.q0;



    public String runAutomate(String word) {
        currentState = State.q0;
        char inputSymbol;
        boolean automataFinished = false;
        int i = 0;

        while (!automataFinished) {

            if (i < word.length()) {
                inputSymbol = word.charAt(i);
            } else {
                inputSymbol = 'e';
            }
            i++;

            switch (currentState) {
                case q0:
                    stateQ0Handler(inputSymbol);
                    break;
                case q1:
                    stateQ1Handler(inputSymbol);
                    break;
                case q2:
                    stateQ2Handler(inputSymbol);
                    break;
                case q3:
                    automataFinished = true;
                    break;
                case failed:
                    result = "Böööp";
                    automataFinished = true;
                    break;
                default:
                    automataFinished = true;
                    err.println("supposedly unreachable State");
                    break;
            }
        }
        return result;
    }

    private void stateQ0Handler (char inputSymbol) {
        if (Character.isDigit(inputSymbol)) {
            stack.push(String.valueOf(inputSymbol));
        } else if (inputSymbol == '+' || inputSymbol == '*') {
            try {
                stack.push((calculate(inputSymbol)));
                currentState = State.q1;
            } catch (EmptyStackException e) {
                currentState = State.failed;
            }
        } else {
            currentState = State.failed;
        }
    }

    private void stateQ1Handler (char inputSymbol){
        if (Character.isDigit(inputSymbol)) {
            stack.push(String.valueOf(inputSymbol));
            currentState = State.q0;
        } else if (inputSymbol == '+' || inputSymbol == '*') {
            try {
                stack.push((calculate(inputSymbol)));
                currentState = State.q1;
            } catch (EmptyStackException e) {
                currentState = State.failed;
            }
        } else if (inputSymbol == 'e') {
            result = stack.popAndReturn();
            currentState = State.q2;
        } else {
            currentState = State.failed;
        }
    }

    private void stateQ2Handler(char inputSymbol) {
        if (inputSymbol == 'e' && stack.popAndReturn().equals("$")) {
            currentState = State.q3;
        } else {
            currentState = State.failed;
        }
    }

    private int calculate(char inputSymbol) throws EmptyStackException {
        String firstString = stack.popAndReturn();
        String secondString = stack.popAndReturn();
        if(firstString.equals("$") || secondString.equals("$")){
            throw new EmptyStackException();
        }

        int firstNumber = Integer.parseInt(firstString);
        int secondNumber = Integer.parseInt(secondString);

        int result = 0;

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
            if(word.equals("quit")){
                running = false;
            } else {
                String result = automaton.runAutomate(word);
                System.out.println(result);
                automaton.stack = new PushDown();
            }
        }
    }

    enum State {
        q0, q1, q2, q3, failed
    }
}
