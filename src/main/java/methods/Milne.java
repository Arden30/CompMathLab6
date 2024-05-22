package methods;

import equations.Equation;
import model.Solution;
import model.Task;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Milne implements Method {
    @Override
    public String name() {
        return "Метод Милна";
    }

    @Override
    public Solution solve(Task task, double h) {
        List<Double> xList = new ArrayList<>();
        xList.add(task.x0());

        List<Double> yList = new ArrayList<>();
        yList.add(task.y0());

        int n = (int) ((task.xn() - task.x0()) / h);
        Equation eq = task.equation();

        List<Double> fList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            double x_prev = xList.get(i);
            double y_prev = yList.get(i);

            double k1 = h * eq.equation(x_prev, y_prev);
            double k2 = h * eq.equation(x_prev + h / 2, y_prev + k1 / 2);
            double k3 = h * eq.equation(x_prev + h / 2, y_prev + k2 / 2);
            double k4 = h * eq.equation(x_prev + h, y_prev + k3);

            double x_curr = x_prev + h;
            double y_curr = y_prev + (k1 + 2 * k2 + 2 * k3 + k4) / 6;

            xList.add(x_curr);
            yList.add(y_curr);
            fList.add(task.equation().equation(x_curr, y_curr));
        }

        for (int i = 3; i < n; i++) {
            double x_curr = xList.get(i) + h;
            double y_pred = yList.get(i - 3) + 4 * h / 3 * (2 * fList.get(i - 3) - fList.get(i - 2) + 2 * fList.get(i - 1));
            fList.add(eq.equation(x_curr, y_pred));
            double y_corr = yList.get(i - 1) + h / 3 * (fList.get(i - 2) + 4 * fList.get(i - 1) + fList.get(i));

            while (abs(y_corr - y_pred) > task.eps()) {
                y_pred = y_corr;
                fList.set(i, eq.equation(x_curr, y_pred));
                y_corr = yList.get(i - 1) + h / 3 * (fList.get(i - 2) + 4 * fList.get(i - 1) + fList.get(i));
            }

            xList.add(x_curr);
            yList.add(y_corr);
        }

        return new Solution(xList, yList, h);
    }

    @Override
    public int order() {
        return 4;
    }
}
