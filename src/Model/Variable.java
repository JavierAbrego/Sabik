<<<<<<< HEAD
package Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Variable {
    private String name;
    private String type;
    private List<String> value;
    private boolean isObject;
	private VariableList objectElements;
    
	private Variable(VariableBuilder vb){
		this.name = vb.name;
		this.type = vb.type;
		this.isObject = vb.isObject;
		this.objectElements = vb.objectElements;
		this.value = vb.value;
	}
	
	/**
	 * For creating variables of type object
	 * Example usage:
	 * Variable var =  new Variable.VariableBuilder()
	 * 						    	.name("nme")
	 *						    	.type("type")
	 *						    	.isObject(true)
	 *						    	.addField("field1")
	 *						    	.addField("field2")
	 *						    	.addField("field3")
	 *						    	.build();
     *	
	 */
	public static class VariableBuilder{
		private String name;
	    private String type;
	    private List<String> value =  new ArrayList<>();
	    private boolean isObject = false;
		private VariableList objectElements = new VariableList();
		
		public VariableBuilder name(String val){this.name = val; return this;}
		public VariableBuilder type(String val){this.type = val; return this;}
		public VariableBuilder value(String val){this.value.add(val); return this;}
		public VariableBuilder isObject(boolean val){this.isObject= val; return this;}
		public VariableBuilder addField(Variable var){this.objectElements.addVariable(var);  return this;}
//		public VariableBuilder addField(String name, String type){			
//			this.objectElements.addVariable(name, type);  
//			return this;
//		}
//		public VariableBuilder addField(String name){
//			this.objectElements.addVariable(name);  
//			return this;
//		}
		public Variable build(){
			return new Variable(this);
		}
	}
	
	public Variable(){
    	isObject = false;
    	objectElements =  new VariableList();
    }
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isObject() {
		return isObject;
	}
	public void setObject(boolean isObject) {
		this.isObject = isObject;
	}

	public VariableList getObjectElements() {
		return objectElements;
	}

	public void setObjectElements(VariableList objectElements) {
		this.objectElements = objectElements;
	}

	/**
	 * @return the value
	 */
	public List<String> getValues() {
		return value;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value.get(0);
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value.add(value);
	}

	public VariableList getValuesAsVariableList(){
		VariableList varList =  new VariableList();
		for (String  val : this.value) {
			varList.addVariable(new Variable.VariableBuilder().name(val).value(val).build());
		}
		return varList;
	}
    
    
    
}
=======
package Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Variable {
    private String name;
    private String type;
    private List<String> value;
    private boolean isObject;
	private VariableList objectElements;
    
	private Variable(VariableBuilder vb){
		this.name = vb.name;
		this.type = vb.type;
		this.isObject = vb.isObject;
		this.objectElements = vb.objectElements;
		this.value = vb.value;
	}
	
	/**
	 * For creating variables of type object
	 * Example usage:
	 * Variable var =  new Variable.VariableBuilder()
	 * 						    	.name("nme")
	 *						    	.type("type")
	 *						    	.isObject(true)
	 *						    	.addField("field1")
	 *						    	.addField("field2")
	 *						    	.addField("field3")
	 *						    	.build();
     *	
	 */
	public static class VariableBuilder{
		private String name;
	    private String type;
	    private List<String> value =  new ArrayList<>();
	    private boolean isObject = false;
		private VariableList objectElements = new VariableList();
		
		public VariableBuilder name(String val){this.name = val; return this;}
		public VariableBuilder type(String val){this.type = val; return this;}
		public VariableBuilder value(String val){this.value.add(val); return this;}
		public VariableBuilder isObject(boolean val){this.isObject= val; return this;}
		public VariableBuilder addField(Variable var){this.objectElements.addVariable(var);  return this;}
//		public VariableBuilder addField(String name, String type){			
//			this.objectElements.addVariable(name, type);  
//			return this;
//		}
//		public VariableBuilder addField(String name){
//			this.objectElements.addVariable(name);  
//			return this;
//		}
		public Variable build(){
			return new Variable(this);
		}
	}
	
	public Variable(){
    	isObject = false;
    	objectElements =  new VariableList();
    }
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isObject() {
		return isObject;
	}
	public void setObject(boolean isObject) {
		this.isObject = isObject;
	}

	public VariableList getObjectElements() {
		return objectElements;
	}

	public void setObjectElements(VariableList objectElements) {
		this.objectElements = objectElements;
	}

	/**
	 * @return the value
	 */
	public List<String> getValues() {
		return value;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value.get(0);
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value.add(value);
	}

    
    
    
}
>>>>>>> FETCH_HEAD
