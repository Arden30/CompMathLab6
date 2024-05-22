package utils;

import equations.Equation;
import methods.Method;
import model.Solution;
import model.Task;

import java.util.List;

public class PrettyPrinter {
    public static void printString(String s) {
        System.out.println(s);
    }

    public static void printEquations(List<Equation> list) {
        printString("Выберите одно из уравнений для численного решения (введите её номер):");
        for (int i = 0; i < list.size(); i++) {
            printString(i + 1 + ": " + list.get(i).name());
        }
    }

    public static void printMethods(List<Method> list) {
        printString("Выберите один из методов численного решения (введите его номер):");
        for (int i = 0; i < list.size(); i++) {
            printString(i + 1 + ": " + list.get(i).name());
        }
    }

    public static void printSolution(Solution solution, Task task) {
        printString("Шаг" + String.format("%" + 15 + "s", "") + "X" + String.format("%" + 15 + "s", "") + "Приближенное решение" + String.format("%" + 15 + "s", "") + "Точное решение" + String.format("%" + 15 + "s", ""));
        for (int i = 0; i < solution.yList().size(); i++) {
            printString(i + String.format("%" + 15 + "s", "") + String.format("%.2f", solution.xList().get(i)) + String.format("%" + 20 + "s", "") + String.format("%.8f", solution.yList().get(i)) + String.format("%" + 20 + "s", "") + String.format("%.8f", task.equation().solution(solution.xList().get(i), task.x0(), task.y0())));
        }
    }
}
