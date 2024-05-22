package utils;

import equations.Equation;
import methods.Method;
import model.Task;
import storages.EquationsStorage;
import storages.MethodsStorage;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static utils.PrettyPrinter.*;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Equation> equations = EquationsStorage.getEquations();
    private final List<Method> methods = MethodsStorage.getMethods();

    public Task read() {
        Equation equation = readEquation();
        Method method = readMethod();
        double x0 = readX0();
        double y0 = readY0();
        double xn = readXn(x0);
        double h = readH(x0, xn);
        double eps = readAccuracy();

        return new Task(method, equation, x0, xn, y0, h, eps);
    }

    private Equation readEquation() {
        while (true) {
            try {
                printEquations(equations);

                return equations.get(scanner.nextInt() - 1);
            } catch (InputMismatchException e) {
                printString("Ошибка ввода номера уравнения, попробуйте ещё раз");
                scanner.next();
            } catch (IndexOutOfBoundsException e) {
                printString("Такого номера нет, попробуйте ещё раз");
            }
        }
    }

    private Method readMethod() {
        while (true) {
            try {
                printMethods(methods);

                return methods.get(scanner.nextInt() - 1);
            } catch (InputMismatchException e) {
                printString("Ошибка ввода номера метода, попробуйте ещё раз");
                scanner.next();
            } catch (IndexOutOfBoundsException e) {
                printString("Такого номера нет, попробуйте ещё раз");
            }
        }
    }

    private double readX0() {
        while (true) {
            try {
                printString("Введите x0:");

                return scanner.nextDouble();
            } catch (Exception e) {
                printString("Ошибка ввода x0, попробуйте ещё раз");
                scanner.next();
            }
        }
    }

    private double readY0() {
        while (true) {
            try {
                printString("Введите y0:");

                return scanner.nextDouble();
            } catch (Exception e) {
                printString("Ошибка ввода y0, попробуйте ещё раз");
                scanner.next();
            }
        }
    }

    private double readXn(double x0) {
        while (true) {
            try {
                printString("Введите правую границу интервала поиска решений (нижняя была введена " + x0 + "):");
                double xn = scanner.nextDouble();
                if (xn < x0) {
                    printString("Граница xn должна быть больше x0, введите ещё раз");
                } else {
                    return xn;
                }
            } catch (Exception e) {
                printString("Ошибка ввода границы xn, попробуйте ещё раз");
                scanner.next();
            }
        }
    }

    private double readH(double x0, double xn) {
        while (true) {
            try {
                printString("Введите шаг h:");
                double h = scanner.nextDouble();
                if (h > xn - x0 || h <= 0) {
                    printString("Шаг должен быть положительным числом, не выходящим за границы выбранного интервала, введите ещё раз");
                } else {
                    return h;
                }
            } catch (Exception e) {
                printString("Ошибка ввода шага h, попробуйте ещё раз");
                scanner.next();
            }
        }
    }

    private double readAccuracy() {
        while (true) {
            try {
                printString("Введите точность: ");
                double accuracy = scanner.nextDouble();
                if (accuracy <= 0) {
                    printString("Точность должна быть неотрицательным числом");
                } else {
                    return accuracy;
                }
            } catch (Exception e) {
                printString("Ошибка ввода точности, попробуйте ещё раз");
                scanner.next();
            }
        }
    }
}
