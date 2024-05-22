package methods;

import equations.Equation;
import model.Solution;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class ModifiedEuler implements Method {
    @Override
    public String name() {
        return "Усовершенствованный метод Эйлера";
    }

    @Override
    public int order() {
        return 2;
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

            double x_curr = x_prev + h;
            double y_curr = y_prev + h / 2 * (eq.equation(x_prev, y_prev) + eq.equation(x_curr, y_prev + h * eq.equation(x_prev, y_prev)));

            xList.add(x_curr);
            yList.add(y_curr);
        }

        return new Solution(xList, yList, h);
    }
}
