package utils;

import model.Solution;
import model.Task;

import javax.swing.*;
import java.util.Scanner;

import static utils.PrettyPrinter.printSolution;

public class ProgramStarter {
    private final Scanner scanner = new Scanner(System.in);
    private final Reader reader = new Reader();

    public void start() {
        while (true) {
            try {
                Task task = reader.read();
                Solution solution = Solver.solve(task, task.method());
                printSolution(solution, task);

                SwingUtilities.invokeLater(() -> new ChartsBuilder(solution, task));
            } catch (Exception e) {
                PrettyPrinter.printString(e.getMessage());
                scanner.next();
            }
        }
    }
}
