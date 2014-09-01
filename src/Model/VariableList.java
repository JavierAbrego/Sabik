package Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import Exception.VariableManagerException;

@XmlAccessorType(XmlAccessType.FIELD)
public class VariableList {
    private final List<Variable> var =  new ArrayList<>();
    
    /**
	 * @return the vars
	 */
	public List<Variable> getVars() {
		return var;
	}

//	public void addVariable(String name, String type, String value){
//        Variable var =  new Variable.VariableBuilder()
//        							.name(name)
//        							.type(type)
//        							.build();        
//        vars.add(var);
//    }
    
    public void addVariable(Variable variable){    	
    	var.add(variable);
    }
    
//    public void addVariable(String name, String value){
//        addVariable(name, "undefined");
//    }
//    
    public boolean variableExists(String name){
        for (Variable variable : var) {
            if(variable.getName().equals(name))
                return true;
        }
        return false;
    }
    
    public Variable getVariable(String name) throws VariableManagerException{
    	
    	for (Variable variable : var) {
            if(variable.getName().equals(name))
                return variable;
        }
    	
    	throw new VariableManagerException("the variable with alias "+name+" doesn't exists");
    }
    
    public void removeVariable(String name){
        int index = 0;
        for (Variable variable : var) {
            if(variable.getName().equals(name)){
                var.remove(index);
                return;
            }
            index++;
        }
    }
       
}
