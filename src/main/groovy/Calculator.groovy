class Calculator {

    String multiply = "*"
    String divide = "/"
    String add = "+"
    String subtract = "-"
    String empty = ""
    String space = " "


    def String solveEquation(String input) {
        String equation = input.replace(space, empty)
        String subEquation = equation
        String subEquationWithoutBrackets = subEquation;
        while ((equation.contains(multiply) ||
                equation.contains(divide) ||
                equation.contains(add) ||
                equation.contains(subtract)) &&
                !equation.matches("-.*[*/+-]]")) {

            if (subEquationWithoutBrackets.contains('(') &&
                    subEquationWithoutBrackets.contains(')')) {
                println(equation)
                subEquation = equation.find("\\([^()]*\\)")
                subEquationWithoutBrackets = subEquation.substring(1, subEquation.length() - 1)
            } else {
                int endLeftPart = equation.indexOf(subEquation)
                int startRightPart = equation.indexOf(subEquation) + subEquation.length()
                String solveSubEquation = expandBrackets(subEquationWithoutBrackets)
                equation = equation.substring(0, endLeftPart) + solveSubEquation + equation.substring(startRightPart, equation.length())
                subEquation = equation
                subEquationWithoutBrackets = subEquation
            }
        }
        return equation;
    }

    String expandBrackets(String equation) {
        while (true) {
            if (equation.contains(multiply) | equation.contains(divide) | equation.contains(add) | equation.contains(subtract)) {
                int operatorIndex;
                String operator
                if (equation.contains(multiply) || equation.contains(divide)) {
                    if (equation.contains(multiply)) {
                        operatorIndex = equation.indexOf(multiply)
                        operator = multiply
                    }
                    if (equation.contains(divide)) {
                        if (null == operator || operatorIndex > equation.indexOf(divide)) {
                            operatorIndex = equation.indexOf(divide)
                            operator = divide
                        }
                    }
                } else if (equation.contains(add) || equation.contains(subtract)) {
                    if (equation.contains(add)) {
                        operatorIndex = equation.indexOf(add)
                        operator = add
                    }
                    if (equation.contains(subtract)) {
                        if (null == operator || operatorIndex > equation.indexOf(subtract)) {
                            operatorIndex = equation.indexOf(subtract)
                            operator = subtract
                        }
                    }
                }
                String[] listOfLeftElements = equation.substring(0, operatorIndex).split("[*/+-]");
                String leftValue = listOfLeftElements[listOfLeftElements.size() - 1];
                String[] listOfRightElements = equation.substring(operatorIndex, equation.length()).split("[*/+-]");
                String rightValue = listOfRightElements[listOfRightElements.size() - 1]
                String partEquation = leftValue + operator + rightValue;
                if (leftValue.empty || operator.empty || rightValue.empty) {
                    return equation
                }
                String result = mathOperation(leftValue, operator, rightValue).toString()
                equation = equation.substring(0, equation.indexOf(partEquation)) + result + equation.substring(equation.indexOf(partEquation) + partEquation.length(), equation.length())
            } else {
                return equation
            }
        }
    }

    Double mathOperation(String firstElement, String operator, String secondElement) {
        if (operator.equals(multiply)) {
            return Double.parseDouble(firstElement) * Double.parseDouble(secondElement);
        } else if (operator.equals(divide)) {
            return Double.parseDouble(firstElement) / Double.parseDouble(secondElement);
        } else if (operator.equals(add)) {
            return Double.parseDouble(firstElement) + Double.parseDouble(secondElement);
        } else if (operator.equals("-")) {
            return Double.parseDouble(firstElement) - Double.parseDouble(secondElement);
        } else {
            throw new RuntimeException("Wrong math operation");
        }
    }

}
