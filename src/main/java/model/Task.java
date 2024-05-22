package model;

import equations.Equation;
import methods.Method;

public record Task(Method method, Equation equation, double x0, double xn, double y0, double h, double eps) {
}
