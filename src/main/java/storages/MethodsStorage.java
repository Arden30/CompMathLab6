package storages;

import methods.Method;
import methods.Milne;
import methods.ModifiedEuler;
import methods.RungeKutta4;

import java.util.ArrayList;
import java.util.List;

public class MethodsStorage {
    private final static List<Method> METHODS = new ArrayList<>();

    public static List<Method> getMethods() {
        setMethods();

        return METHODS;
    }

    private static void setMethods() {
        METHODS.add(new ModifiedEuler());
        METHODS.add(new RungeKutta4());
        METHODS.add(new Milne());
    }
}
