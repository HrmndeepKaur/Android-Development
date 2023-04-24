package com.example.calculator;

import java.util.ArrayList;

public class calculatorBrain  {
    //ArrayList to store numbers and operators
    private ArrayList<String> numbers;


    //Constructor
    public calculatorBrain(){
        numbers = new ArrayList<>();
    }

    // adding string to the list
    public void push(String s) {
        numbers.add(s);
    }

    // clear Method to clean the arrayList for next calculation
    public void clear() {
        numbers.clear();
    }
    public String calculate(){
        String result = "";
        if (validatenumbers()) {
            int operatorIndex = getOperatorIndex();

            ArrayList<String> firstPart = new ArrayList<>(numbers.subList(0, operatorIndex));
            ArrayList<String> secondPart = new ArrayList<>(numbers.subList(operatorIndex + 1, numbers.size()));

            int firstPartInt = Integer.parseInt(String.join("", firstPart));
            int secondPartInt = Integer.parseInt(String.join("", secondPart));

            String operator = numbers.get(operatorIndex);

            switch (operator) {
                case "+":
                    result = Integer.toString(firstPartInt + secondPartInt);
                    break;
                case "-":
                    result = Integer.toString(firstPartInt - secondPartInt);
                    break;
                case "*":
                    result = Integer.toString(firstPartInt * secondPartInt);
                    break;
                case "/":
                    result = Integer.toString(firstPartInt / secondPartInt);
                    break;
            }
        }
        else {
            result = "failed";
        }
return result;
    }

    private int getOperatorIndex() {
        int index = -1;
        for (int i = 0; i < numbers.size(); i++) {
            String currentValue = numbers.get(i);
            if (currentValue.equals("+") || currentValue.equals("-") || currentValue.equals("*") || currentValue.equals("/")) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean validatenumbers() {
        boolean isValid = false;
        if (numbers.size() >= 3) {
            int operatorIndex = getOperatorIndex();
            if (operatorIndex > 0 && operatorIndex < numbers.size() - 1) {
                isValid = true;
            }
        }
        return isValid;
    }
}