package utility;

import java.util.HashMap;
import java.util.Map;

public class VariableContainer {
    private static final Map<String, Object> variable = new HashMap<>();

    public void setVar(String varName, Object var) {
        variable.put(varName, var);
    }
    public <T> T getVar(String varName) {
        return (T) variable.get(varName);
    }
}
