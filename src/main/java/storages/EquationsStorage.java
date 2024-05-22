package storages;

import equations.Equation;
import equations.FirstEquation;
import equations.SecondEquation;
import equations.ThirdEquation;

import java.util.ArrayList;
import java.util.List;

public class EquationsStorage {
    private final static List<Equation> EQUATIONS = new ArrayList<>();

    public static List<Equation> getEquations() {
        setEquations();

        return EQUATIONS;
    }

    private static void setEquations() {
        EQUATIONS.add(new FirstEquation());
        EQUATIONS.add(new SecondEquation());
        EQUATIONS.add(new ThirdEquation());
    }
}
