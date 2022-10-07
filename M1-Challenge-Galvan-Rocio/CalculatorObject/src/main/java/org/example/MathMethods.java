package org.example;

//Point of Exercise 2- method overloading

public class MathMethods {
    // Integers
    public int addition(int a, int b) {
        return a + b;
    }
    public double addition(double a, double b) {
        return a + b;
    }
    public int subtraction(int a, int b) {
        return a - b;
    }
    public double subtraction(double a, double b) {
        return a - b;
    }
    public int multiplication(int a, int b) {
        return a * b;
    }
    public double multiplication(double a, double b) {
        return a * b;
    }
    public int division(int a, int b) {
        return a / b;
    }
    public double division(double a, double b) {
        return a / b;
    }

    public static void main(String[] args) {
//        instantiate the class to create an object
        MathMethods mathCalculations = new MathMethods();
//        calculations:
        int solution1 = mathCalculations.addition(1,1);
        System.out.println("1 + 1 = " + solution1);

        int solution2 = mathCalculations.subtraction(23,52);
        System.out.println("23 - 52 = " + solution2);

        int solution3 = mathCalculations.multiplication(34,2);
        System.out.println("34 * 2 = " + solution3);

        int solution4 = mathCalculations.division(12,3);
        System.out.println("12 / 3 = " + solution4);

//      FIGURE OUT HOW TO RETURN 1.7-TRIED SEVERAL MATH. METHODS BUT DIDN'T WORK
        double solution5 = mathCalculations.division(12,7);
        System.out.println("12 / 7 = " + solution5);

        double solution6 = mathCalculations.addition(3.4,2.3);
        System.out.println("3.4 + 2.3 = " + solution6);

        double solution7 = mathCalculations.multiplication(6.7,4.4);
        System.out.println("6.7 * 4.4 = " + solution7);

        double solution8 = mathCalculations.subtraction(5.5,0.5);
        System.out.println("5.5 - 0.5 = " + solution8);

        double solution9 = mathCalculations.division(10.8,2.2);
        System.out.println("10.8 / 2.2 = " + solution9);

    }
}
