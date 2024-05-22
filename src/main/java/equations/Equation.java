package equations;
public interface Equation {
    String name();
    double equation(double x, double y);
    double solution(double x, double x0, double y0);
}
