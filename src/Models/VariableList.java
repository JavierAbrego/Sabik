package Models;

import java.util.ArrayList;
import java.util.List;


public class VariableList {
    private List<Variable> vars =  new ArrayList<>();
    public void addVariable(String name, String type){
    
    }
    
    public void addVariable(String name){
        addVariable(name, "undefined");
    }
    
    public boolean variableExists(String name){
        return false;
    }
    
    public void removeVariable(String name){
    
    }
    
    
}
