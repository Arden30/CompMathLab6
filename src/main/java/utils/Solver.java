package utils;

import methods.Method;
import methods.ModifiedEuler;
import methods.RungeKutta4;
import model.Solution;
import model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static utils.PrettyPrinter.printString;

public class Solver {
    public static Solution solve(Task task, Method method) {
        if (method instanceof ModifiedEuler || task.method() instanceof RungeKutta4) {
            return solveByRunge(task);
        } else return solveByReal(task);
    }

    private static Solution solveByRunge(Task task) {
        double h = task.h();
        Solution solution = task.method().solve(task, h);
        List<Double> listY = solution.yList();
        double y0 = listY.get(listY.size() - 1);
        double y1 = 0;
        int cnt = 0;
        do {
            if (h != task.h()) {
                y0 = y1;
            }
            h /= 2;
            solution = task.method().solve(task, h);
            listY = solution.yList();
            y1 = listY.get(listY.size() - 1);
            cnt++;

            if (cnt > 10000 || Double.isNaN(y1) || Double.isInfinite(y1)) {
                printString("Одношаговый метод не сходится к решению");
                return solution;
            }
        } while (abs(y0 - y1) / (pow(2, task.method().order()) - 1) > task.eps());

        return task.method().solve(task, 2 * h);
    }

    private static Solution solveByReal(Task task) {
        Solution solution;
        double h = task.h();
        List<Double> diff = new ArrayList<>();
        int cnt = 0;
        do {
            diff.clear();
            solution = task.method().solve(task, h);
            List<Double> listY = solution.yList();
            for (double x = task.x0(), i = 0; x <= task.xn(); x += h, i++) {
                diff.add(abs(listY.get((int) i) - task.equation().solution(x, task.x0(), task.y0())));
            }
            h /= 2;
            cnt++;
            if (cnt > 10000 || diff.stream().max(Comparator.naturalOrder()).get().isInfinite() || diff.stream().max(Comparator.naturalOrder()).get().isNaN()) {
                printString("Многошаговый метод не сходится к решению");
                return solution;
            }
        } while (diff.stream().max(Comparator.naturalOrder()).get() > task.eps());

        return solution;
    }
}
