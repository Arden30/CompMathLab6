package equations;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class FirstEquation implements Equation {
    @Override
    public String name() {
        return "y' = y + (1 + x) * y^2";
    }

    @Override
    public double equation(double x, double y) {
        return y + (1 + x) * pow(y, 2);
    }

    @Override
    public double solution(double x, double x0, double y0) {
        double c = -exp(x0) * (1 + x0 * y0) / y0;

        return -exp(x) / (x * exp(x) + c);
    }
}
