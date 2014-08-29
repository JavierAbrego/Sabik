package Model;

import java.util.ArrayList;
import java.util.List;

import Exception.VariableManagerException;


public class VariableList {
    private final List<Variable> vars =  new ArrayList<>();
    
    public void addVariable(String name, String type){
        Variable var =  new Variable.VariableBuilder()
        							.name(name)
        							.type(type)
        							.build();        
        vars.add(var);
    }
    
    public void addVariable(Variable var){    	
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
    
    public Variable getVariable(String name) throws VariableManagerException{
    	
    	for (Variable variable : vars) {
            if(variable.getName().equals(name))
                return variable;
        }
    	
    	throw new VariableManagerException("the variable with alias "+name+" doesn't exists");
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
