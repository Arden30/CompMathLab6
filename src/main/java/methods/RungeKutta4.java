package methods;

import equations.Equation;
import model.Solution;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class RungeKutta4 implements Method {
    @Override
    public String name() {
        return "Метод Рунге-Кутта 4-го порядка";
    }

    @Override
    public Solution solve(Task task, double h) {
        List<Double> xList = new ArrayList<>();
        xList.add(task.x0());

        List<Double> yList = new ArrayList<>();
        yList.add(task.y0());

        int n = (int) ((task.xn() - task.x0()) / h);
        Equation eq = task.equation();
        for (int i = 0; i < n; i++) {
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
        }

        return new Solution(xList, yList, h);
    }

    @Override
    public int order() {
        return 4;
    }
}
