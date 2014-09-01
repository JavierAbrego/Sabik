package WriterImplementation;

import Model.Variable;
import Model.VariableList;
import WriterInterface.VariableManagerInterface;

import java.util.HashMap;
import java.util.Map;

import Exception.VariableManagerException;

public class VariableManagerImpl implements VariableManagerInterface {

    private static VariableManagerImpl _variableManagerImpl;
    private VariableManagerImpl() {
        if( _variableManagerImpl != null ) {
            throw new InstantiationError( "More instances of this object cannot be created." );
        }
    }
    
    private synchronized  static void createInstance(){
        if(_variableManagerImpl==null){
            _variableManagerImpl  = new VariableManagerImpl();
        }
    }        
    
    public static  VariableManagerImpl getInstace(){
        if(_variableManagerImpl==null) createInstance();
        return _variableManagerImpl;
    }
    
    private Map<String, VariableList> _variableMap =  new HashMap<>();
    
    private boolean pathHasList(String path){
       return _variableMap.containsKey(path);                 
    }
        
    private VariableList getVariableList(String path){
        if(pathHasList(path)){
            return _variableMap.get(path);
        }else{
            return new VariableList();
        }
    }
    
    @Override
    public boolean addVariable(String variableName, String path) throws VariableManagerException{        
        VariableList variableList = getVariableList(path);                
        if(variableList.variableExists(variableName)){
             throw new VariableManagerException("The variable with the name \""+variableName+"\" already exists.");   
        }else{
        	Variable var = new Variable.VariableBuilder().name(variableName).type("undefined").value("none").build();
            variableList.addVariable(var);
        }        
        return true;        
    }

    @Override
    public void deleteVariable(String variableName, String path) {
        getVariableList(path).removeVariable(variableName);        
    }

    @Override
    public boolean existVariable(String variableName, String path) {
        return getVariableList(path).variableExists(variableName);
    }
    
}
