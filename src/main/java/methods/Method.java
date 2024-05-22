package methods;

import model.Solution;
import model.Task;

public interface Method {
    String name();
    Solution solve(Task task, double h);
    int order();
}
