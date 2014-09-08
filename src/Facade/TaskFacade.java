<<<<<<< HEAD
package Facade;

import java.util.List;

import Model.Job;
import Model.Tasker;
import Model.Variable;
import Model.VariableList;

public class TaskFacade {
	
	public static enum RequestMethod{GET, POST}
	
	private Job job;
	
	public TaskFacade(Job jobInitialize){
		job = jobInitialize;
	}
	/**
	 * @param pathValue
	 * @return
	 */
	public TaskFacade header(){
		//HEADER
        //definition of parameters
        VariableList parameters = new VariableList();
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.header)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker);
        return this;
	}
	
	
	/**
	 * @param pathValue
	 * @param inputVars
	 * @param requestMethod
	 * @return
	 */
	public TaskFacade getInputVars( List<String> inputVars, RequestMethod requestMethod){
		//HEADER
        //definition of parameters
        VariableList parameters = new VariableList();
        //input variable
        Variable input =  new Variable.VariableBuilder()
        								.name("input").build();
        for (String inputvar: inputVars) {
			input.setValue(inputvar);
		}
        //add parameters
        parameters.addVariable(input);
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.getInputVars)
        							.parameters(parameters)
        							.build();
        if(requestMethod == RequestMethod.POST){
        	tasker.setTask(Tasker.Task.postInputVars);
        }
        
        if(requestMethod == RequestMethod.GET){ 
        	tasker.setTask(Tasker.Task.getInputVars);
        }
        
        job.addTasker(tasker); 
        return this;
	}
	
	/**
	 * @param job
	 * @param path
	 * @param alias
	 */
	public TaskFacade  useDatabase( String alias){
		 //definition of parameters
        VariableList parameters = new VariableList();
        //input variable
        Variable palias =  new Variable.VariableBuilder()
        								.name("alias")
        								.value(alias)
        								.build();
        //add parameters
        parameters.addVariable(palias);
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.useDatabase)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker);
        return this;
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param alias
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 * @param databaseName
	 */
	public TaskFacade addDatabase(	 	String alias,
									String host,
									String port,
									String user,
									String password,
									String databaseName
									){
		 //definition of parameters
        VariableList parameters = new VariableList();
        //input variable
        
        Variable palias =  new Variable.VariableBuilder()
        								.name("alias")
        								.value(alias)
        								.build();
        
        Variable phost =  new Variable.VariableBuilder()
							        .name("host")
							        .value(host)
							        .build();
        
        Variable pport =  new Variable.VariableBuilder()
							        .name("port")
							        .value(port)
							        .build();
        
        Variable puser =  new Variable.VariableBuilder()
							        .name("user")
							        .value(user)
							        .build();
        
        Variable ppassword =  new Variable.VariableBuilder()
							        .name("password")
							        .value(password)
							        .build();
       
        Variable pdatabaseName =  new Variable.VariableBuilder()
							        .name("databaseName")
							        .value(databaseName)
							        .build();
        //add parameters
        parameters.addVariable(palias);
        parameters.addVariable(phost);
        parameters.addVariable(pport);
        parameters.addVariable(puser);
        parameters.addVariable(ppassword);
        parameters.addVariable(pdatabaseName);
        
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.addDatabase)
        							.parameters(parameters)
        							.build();
      
        
        job.addTasker(tasker); 
		return this;
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param sql
	 */
	public TaskFacade executeSqlQuery(String queryValue, List<String> queryParametersValues){
		//definition of parameters
        VariableList parameters = new VariableList();
        Variable query =  new Variable.VariableBuilder()
								        .name("query")
								        .value(queryValue)
								        .build();
        
        Variable queryParameters =  new Variable.VariableBuilder()
								        .name("queryParameters")
								        .build();
        
        for (String parameter : queryParametersValues) {
        	queryParameters.setValue(parameter);
		}
        
        //add parameters
        parameters.addVariable(query);
        parameters.addVariable(queryParameters);
        
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.executeSqlQuery)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
		return this;
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param queryValue
	 * @param queryParametersValues
	 * @param variableNameValue
	 */
	public TaskFacade executeSqlQueryAndGetResultInVariable(String queryValue, List<String> queryParametersValues, String variableNameValue ){
		//definition of parameters
        VariableList parameters = new VariableList();
        
        Variable query =  new Variable.VariableBuilder()
								        .name("query")
								        .value(queryValue)
								        .build();
        Variable queryParameters =  new Variable.VariableBuilder()
        .name("queryParameters")
        .build();

		for (String parameter : queryParametersValues) {
			queryParameters.setValue(parameter);
		}
		
		Variable variableName =  new Variable.VariableBuilder()
									        .name("variableName")
									        .value(variableNameValue)
									        .build();
		
		//add parameters
        parameters.addVariable(query);
        parameters.addVariable(queryParameters);
        parameters.addVariable(variableName);
        
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.executeSqlQueryAndGetResultInVariable)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
        return this;
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param queryValue
	 * @param queryParametersValues
	 * @param variableNameValue
	 */
	public TaskFacade executeSqlUpdateAndGetAffectedRowsNumberIntoVariable(String queryValue, List<String> queryParametersValues, String variableNameValue ){
		//definition of parameters
        VariableList parameters = new VariableList();
        
        Variable query =  new Variable.VariableBuilder()
								        .name("query")
								        .value(queryValue)
								        .build();
        Variable queryParameters =  new Variable.VariableBuilder()
        .name("queryParameters")
        .build();

		for (String parameter : queryParametersValues) {
			queryParameters.setValue(parameter);
		}
		
		Variable variableName =  new Variable.VariableBuilder()
									        .name("resultVariableName")
									        .value(variableNameValue)
									        .build();
		
		//add parameters
        parameters.addVariable(query);
        parameters.addVariable(queryParameters);
        parameters.addVariable(variableName);
        
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.executeSqlUpdateAndGetAffectedRowsNumberIntoVariable)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
        return this;
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param rowsVariableNameValue
	 * @param countResultVariableNameValue
	 */
	public TaskFacade countResultRowsNumberAndGetResultInVariable(String rowsVariableNameValue, String countResultVariableNameValue ){
		//definition of parameters
        VariableList parameters = new VariableList();
        
        Variable rowsVariableName =  new Variable.VariableBuilder()
								        .name("rowsVariableName")
								        .value(rowsVariableNameValue)
								        .build();
        
		
		Variable countResultVariableName =  new Variable.VariableBuilder()
									        .name("countResultVariableName")
									        .value(countResultVariableNameValue)
									        .build();
		
		//add parameters
        parameters.addVariable(rowsVariableName);
        parameters.addVariable(countResultVariableName);

        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.countResultRowsNumberAndGetResultInVariable)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
        return this;
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param resultVariableNameValue
	 */
	public TaskFacade getLastInsertedIdIntoVariable(String resultVariableNameValue){
		//definition of parameters
        VariableList parameters = new VariableList();
        Variable resultVariableName =  new Variable.VariableBuilder()
								        .name("resultVariableName")
								        .value(resultVariableNameValue)
								        .build();
		
		//add parameters
        parameters.addVariable(resultVariableName);

        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.getLastInsertedIdIntoVariable)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
        return this;
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param variableNameValue
	 */
	public TaskFacade printVariableAsJSON( String variableNameValue){
		//definition of parameters
        VariableList parameters = new VariableList();
        
        Variable variableName =  new Variable.VariableBuilder()
								        .name("resultVariableName")
								        .value(variableNameValue)
								        .build();
		
		//add parameters
        parameters.addVariable(variableName);

        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.printVariableAsJSON)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
        return this;
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param variableNameValue
	 */
	public TaskFacade printVariableAsXML(String variableNameValue){
		//definition of parameters
		VariableList parameters = new VariableList();
		
		Variable variableName =  new Variable.VariableBuilder()
		.name("resultVariableName")
		.value(variableNameValue)
		.build();
		
		//add parameters
		parameters.addVariable(variableName);
		
		//creation of task header
		Tasker tasker =  new Tasker.TaskerBuilder()
		.task(Tasker.Task.printVariableAsXML)
						.parameters(parameters)
						.build();
		job.addTasker(tasker); 
		return this;
	}

	
	/**
	 * @param job
	 * @param pathValue
	 * @param arithmeticValue
	 * @param variableListValues
	 * @param resultVariableNameValues
	 */
	public TaskFacade writeArithmeticAndGetResultInVariable(String arithmeticValue, List<String> variableListValues, String resultVariableNameValues){
		//definition of parameters
		VariableList parameters = new VariableList();
		
		Variable arithmetic =  new Variable.VariableBuilder()
											.name("arithmetic")
											.value(arithmeticValue)
											.build();
		Variable resultVariableName =  new Variable.VariableBuilder()
													.name("resultVariableName")
													.value(resultVariableNameValues)
													.build();
		Variable variableList =  new Variable.VariableBuilder()
											.name("variableList")
											.build();
		for (String parameter : variableListValues) {
			variableList.setValue(parameter);
		}
		
		//add parameters
		parameters.addVariable(arithmetic);
		parameters.addVariable(variableList);
		parameters.addVariable(resultVariableName);
		
		//creation of task header
		Tasker tasker =  new Tasker.TaskerBuilder()
									.task(Tasker.Task.writeArithmeticAndGetResultInVariable)
									.parameters(parameters)
									.build();
		job.addTasker(tasker); 
		return this;
	}
	
	/**
	 * @param pathValue
	 * @return
	 */
	public TaskFacade beginTransaction(){
		//definition of parameters
        VariableList parameters = new VariableList();
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.beginTransaction)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker);
        return this;
	}
	/**
	 * @param pathValue
	 * @return
	 */
	public TaskFacade endTransaction(){
		//definition of parameters
		VariableList parameters = new VariableList();
		
		//creation of task header
		Tasker tasker =  new Tasker.TaskerBuilder()
		.task(Tasker.Task.endTransaction)
		.parameters(parameters)
		.build();
		job.addTasker(tasker);
		return this;
	}
	
	/**
	 * @param pathValue
	 * @return
	 */
	public TaskFacade EOF(){
		//definition of parameters
		VariableList parameters = new VariableList();
		//creation of task header
		Tasker tasker =  new Tasker.TaskerBuilder()
		.task(Tasker.Task.EOF)
		.parameters(parameters)
		.build();
		job.addTasker(tasker);
		return this;
	}
	
	
}
=======
package Facade;

import java.util.List;

import Model.Job;
import Model.Tasker;
import Model.Variable;
import Model.VariableList;

public class TaskFacade {
	
	public static enum RequestMethod{GET, POST}
	
	/**
	 * @param pathValue
	 * @return
	 */
	public static void  header(Job job, String pathValue){
		//HEADER
        //definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        parameters.addVariable(path);
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.header)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker);
	}
	
	
	/**
	 * @param pathValue
	 * @param inputVars
	 * @param requestMethod
	 * @return
	 */
	public static void getInputVars(Job job, String pathValue, List<String> inputVars, RequestMethod requestMethod){
		//HEADER
        //definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        //input variable
        Variable input =  new Variable.VariableBuilder()
        								.name("input").build();
        for (String inputvar: inputVars) {
			input.setValue(inputvar);
		}
        //add parameters
        parameters.addVariable(path);
        parameters.addVariable(input);
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.getInputVars)
        							.parameters(parameters)
        							.build();
        if(requestMethod == RequestMethod.POST){
        	tasker.setTask(Tasker.Task.postInputVars);
        }
        
        if(requestMethod == RequestMethod.GET){ 
        	tasker.setTask(Tasker.Task.getInputVars);
        }
        
        job.addTasker(tasker); 
	}
	
	/**
	 * @param job
	 * @param path
	 * @param alias
	 */
	public static void useDatabase(Job job, String pathValue, String alias){
		 //definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        //input variable
        Variable palias =  new Variable.VariableBuilder()
        								.name("alias")
        								.value(alias)
        								.build();
        //add parameters
        parameters.addVariable(path);
        parameters.addVariable(palias);
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.useDatabase)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker);
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param alias
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 * @param databaseName
	 */
	public static void addDatabase(	Job job, 
									String pathValue,
									String alias,
									String host,
									String port,
									String user,
									String password,
									String databaseName
									){
		 //definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        //input variable
        
        Variable palias =  new Variable.VariableBuilder()
        								.name("alias")
        								.value(alias)
        								.build();
        
        Variable phost =  new Variable.VariableBuilder()
							        .name("host")
							        .value(host)
							        .build();
        
        Variable pport =  new Variable.VariableBuilder()
							        .name("port")
							        .value(port)
							        .build();
        
        Variable puser =  new Variable.VariableBuilder()
							        .name("user")
							        .value(user)
							        .build();
        
        Variable ppassword =  new Variable.VariableBuilder()
							        .name("password")
							        .value(password)
							        .build();
       
        Variable pdatabaseName =  new Variable.VariableBuilder()
							        .name("databaseName")
							        .value(databaseName)
							        .build();
        //add parameters
        parameters.addVariable(path);
        parameters.addVariable(palias);
        parameters.addVariable(phost);
        parameters.addVariable(pport);
        parameters.addVariable(puser);
        parameters.addVariable(ppassword);
        parameters.addVariable(pdatabaseName);
        
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.addDatabase)
        							.parameters(parameters)
        							.build();
      
        
        job.addTasker(tasker); 
		
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param sql
	 */
	public static void executeSqlQuery(Job job, String pathValue, String queryValue, List<String> queryParametersValues){
		//definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        
        Variable query =  new Variable.VariableBuilder()
								        .name("query")
								        .value(queryValue)
								        .build();
        
        Variable queryParameters =  new Variable.VariableBuilder()
								        .name("queryParameters")
								        .build();
        
        for (String parameter : queryParametersValues) {
        	queryParameters.setValue(parameter);
		}
        
        //add parameters
        parameters.addVariable(path);
        parameters.addVariable(query);
        parameters.addVariable(queryParameters);
        
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.executeSqlQuery)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
		
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param queryValue
	 * @param queryParametersValues
	 * @param variableNameValue
	 */
	public static void executeSqlQueryAndGetResultInVariable(Job job, String pathValue, String queryValue, List<String> queryParametersValues, String variableNameValue ){
		//definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        
        Variable query =  new Variable.VariableBuilder()
								        .name("query")
								        .value(queryValue)
								        .build();
        Variable queryParameters =  new Variable.VariableBuilder()
        .name("queryParameters")
        .build();

		for (String parameter : queryParametersValues) {
			queryParameters.setValue(parameter);
		}
		
		Variable variableName =  new Variable.VariableBuilder()
									        .name("variableName")
									        .value(variableNameValue)
									        .build();
		
		//add parameters
        parameters.addVariable(path);
        parameters.addVariable(query);
        parameters.addVariable(queryParameters);
        parameters.addVariable(variableName);
        
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.executeSqlQueryAndGetResultInVariable)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
        
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param queryValue
	 * @param queryParametersValues
	 * @param variableNameValue
	 */
	public static void executeSqlUpdateAndGetAffectedRowsNumberIntoVariable(Job job, String pathValue, String queryValue, List<String> queryParametersValues, String variableNameValue ){
		//definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        
        Variable query =  new Variable.VariableBuilder()
								        .name("query")
								        .value(queryValue)
								        .build();
        Variable queryParameters =  new Variable.VariableBuilder()
        .name("queryParameters")
        .build();

		for (String parameter : queryParametersValues) {
			queryParameters.setValue(parameter);
		}
		
		Variable variableName =  new Variable.VariableBuilder()
									        .name("resultVariableName")
									        .value(variableNameValue)
									        .build();
		
		//add parameters
        parameters.addVariable(path);
        parameters.addVariable(query);
        parameters.addVariable(queryParameters);
        parameters.addVariable(variableName);
        
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.executeSqlUpdateAndGetAffectedRowsNumberIntoVariable)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
        
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param rowsVariableNameValue
	 * @param countResultVariableNameValue
	 */
	public static void countResultRowsNumberAndGetResultInVariable(Job job, String pathValue, String rowsVariableNameValue, String countResultVariableNameValue ){
		//definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        
        Variable rowsVariableName =  new Variable.VariableBuilder()
								        .name("rowsVariableName")
								        .value(rowsVariableNameValue)
								        .build();
        
		
		Variable countResultVariableName =  new Variable.VariableBuilder()
									        .name("countResultVariableName")
									        .value(countResultVariableNameValue)
									        .build();
		
		//add parameters
        parameters.addVariable(path);
        parameters.addVariable(rowsVariableName);
        parameters.addVariable(countResultVariableName);

        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.countResultRowsNumberAndGetResultInVariable)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
        
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param resultVariableNameValue
	 */
	public static void getLastInsertedIdIntoVariable(Job job, String pathValue, String resultVariableNameValue){
		//definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        
        Variable resultVariableName =  new Variable.VariableBuilder()
								        .name("resultVariableName")
								        .value(resultVariableNameValue)
								        .build();
		
		//add parameters
        parameters.addVariable(path);
        parameters.addVariable(resultVariableName);

        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.getLastInsertedIdIntoVariable)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param variableNameValue
	 */
	public static void printVariableAsJSON(Job job, String pathValue, String variableNameValue){
		//definition of parameters
        VariableList parameters = new VariableList();
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        
        Variable variableName =  new Variable.VariableBuilder()
								        .name("resultVariableName")
								        .value(variableNameValue)
								        .build();
		
		//add parameters
        parameters.addVariable(path);
        parameters.addVariable(variableName);

        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.printVariableAsJSON)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker); 
	}
	
	/**
	 * @param job
	 * @param pathValue
	 * @param variableNameValue
	 */
	public static void printVariableAsXML(Job job, String pathValue, String variableNameValue){
		//definition of parameters
		VariableList parameters = new VariableList();
		//Path for Job
		Variable path =  new Variable.VariableBuilder()
		.name("path")
		.value(pathValue)
		.build();
		
		Variable variableName =  new Variable.VariableBuilder()
		.name("resultVariableName")
		.value(variableNameValue)
		.build();
		
		//add parameters
		parameters.addVariable(path);
		parameters.addVariable(variableName);
		
		//creation of task header
		Tasker tasker =  new Tasker.TaskerBuilder()
		.task(Tasker.Task.printVariableAsXML)
						.parameters(parameters)
						.build();
		job.addTasker(tasker); 
	}

	
	/**
	 * @param job
	 * @param pathValue
	 * @param arithmeticValue
	 * @param variableListValues
	 * @param resultVariableNameValues
	 */
	public static void writeArithmeticAndGetResultInVariable(Job job, String pathValue, String arithmeticValue, List<String> variableListValues, String resultVariableNameValues){
		//definition of parameters
		VariableList parameters = new VariableList();
		//Path for Job
		Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
		
		Variable arithmetic =  new Variable.VariableBuilder()
											.name("arithmetic")
											.value(arithmeticValue)
											.build();
		Variable resultVariableName =  new Variable.VariableBuilder()
													.name("resultVariableName")
													.value(resultVariableNameValues)
													.build();
		Variable variableList =  new Variable.VariableBuilder()
											.name("variableList")
											.build();
		for (String parameter : variableListValues) {
			variableList.setValue(parameter);
		}
		
		//add parameters
		parameters.addVariable(path);
		parameters.addVariable(arithmetic);
		parameters.addVariable(variableList);
		parameters.addVariable(resultVariableName);
		
		//creation of task header
		Tasker tasker =  new Tasker.TaskerBuilder()
									.task(Tasker.Task.writeArithmeticAndGetResultInVariable)
									.parameters(parameters)
									.build();
		job.addTasker(tasker); 
	}
	
	/**
	 * @param pathValue
	 * @return
	 */
	public static void beginTransaction(Job job, String pathValue){
		//definition of parameters
        VariableList parameters = new VariableList();
        
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value(pathValue)
									.build();
        parameters.addVariable(path);
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.beginTransaction)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker);
	}
	/**
	 * @param pathValue
	 * @return
	 */
	public static void endTransaction(Job job, String pathValue){
		//definition of parameters
		VariableList parameters = new VariableList();
		
		//Path for Job
		Variable path =  new Variable.VariableBuilder()
		.name("path")
		.value(pathValue)
		.build();
		parameters.addVariable(path);
		//creation of task header
		Tasker tasker =  new Tasker.TaskerBuilder()
		.task(Tasker.Task.endTransaction)
		.parameters(parameters)
		.build();
		job.addTasker(tasker);
	}
	
	/**
	 * @param pathValue
	 * @return
	 */
	public static void EOF(Job job, String pathValue){
		//definition of parameters
		VariableList parameters = new VariableList();
		
		//Path for Job
		Variable path =  new Variable.VariableBuilder()
		.name("path")
		.value(pathValue)
		.build();
		parameters.addVariable(path);
		//creation of task header
		Tasker tasker =  new Tasker.TaskerBuilder()
		.task(Tasker.Task.EOF)
		.parameters(parameters)
		.build();
		job.addTasker(tasker);
	}
	
	
}
>>>>>>> FETCH_HEAD
