package Model;

import java.util.ArrayList;
import java.util.List;


public class VariableList {
    private final List<Variable> vars =  new ArrayList<>();
    
    public void addVariable(String name, String type){
        Variable var =  new Variable();
        var.setName(name);
        var.setType(type);
        vars.add(var);
    }
    
    public void addVariable(String name){
        addVariable(name, "undefined");
    }
    
    public boolean variableExists(String name){
        for (Variable variable : vars) {
            if(variable.getName().equals(name))
                return true;
        }
        return false;
    }
    
    public void removeVariable(String name){
        int index = 0;
        for (Variable variable : vars) {
            if(variable.getName().equals(name)){
                vars.remove(index);
                return;
            }
            index++;
        }
    }
    
    
}
