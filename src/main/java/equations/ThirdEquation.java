package equations;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ThirdEquation implements Equation {
    @Override
    public String name() {
        return "y' = cos(x)";
    }

    @Override
    public double equation(double x, double y) {
        return cos(x);
    }

    @Override
    public double solution(double x, double x0, double y0) {
        double c = y0 - sin(x0);

        return sin(x) + c;
    }
}
