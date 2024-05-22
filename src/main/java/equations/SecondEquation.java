package equations;

import static java.lang.Math.exp;

public class SecondEquation implements Equation {
    @Override
    public String name() {
        return "y' = x + y";
    }

    @Override
    public double equation(double x, double y) {
        return x + y;
    }

    @Override
    public double solution(double x, double x0, double y0) {
        double c = (y0 + x0 + 1) / exp(x0);

        return c * exp(x) - x - 1;
    }
}
