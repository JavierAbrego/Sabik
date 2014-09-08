<<<<<<< HEAD
package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import Exception.DBManagerException;
import Exception.VariableManagerException;
import WriterImplementation.PHPGenericWriterImpl;
import WriterInterface.GenericWriterInterface;

@XmlAccessorType(XmlAccessType.FIELD)
public class Tasker {
	
	public enum Task{
		header, getInputVars, postInputVars, useDatabase, addDatabase,
		beginTransaction, endTransaction, executeSqlQuery, executeSqlQueryAndGetResultInVariable,
		countResultRowsNumberAndGetResultInVariable, executeSqlUpdateAndGetAffectedRowsNumberIntoVariable,
		getLastInsertedIdIntoVariable, writeArithmeticAndGetResultInVariable, 
		printVariableAsJSON, printVariableAsXML, EOF
	}
	
	public enum Language{PHP5_1, JAVAEE6, CSharp}
	
	private int id;
	private Task task;
	/**
	 * Variables used in this tasker
	 */
	private VariableList parameters;
	
	private static int autoIncrementId = 0;

	public static class TaskerBuilder{
		private Task task;
		private VariableList parameters;
		public TaskerBuilder task(Task val){this.task=val; return this;}
		public TaskerBuilder parameters(VariableList val){this.parameters=val; return this;}
		public Tasker build(){
			return new Tasker(this);
		}
	}
	
	public Tasker(TaskerBuilder tb){
		this.task = tb.task;
		this.parameters =  tb.parameters;
		autoIncrementId();	
	}
		
	public Tasker(){
		autoIncrementId();
	}
	
	private void autoIncrementId(){
		this.id = autoIncrementId;
		autoIncrementId++;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}
	/**
	 * @return the parameters
	 */
	public VariableList getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(VariableList parameters) {
		this.parameters = parameters;
	}
	
	public void executeTask(String pathValue, Language language) throws VariableManagerException, DBManagerException{
		Variable path = new Variable.VariableBuilder().value(pathValue).name("path").build();
		GenericWriterInterface genericWriter = initializeGenericWriter(language);
		switch (task) {
		case header:
			header(path, genericWriter);
			break;
		case getInputVars:
			getInputVars(path, genericWriter);
			break;
		case postInputVars:
			postInputVars(path, genericWriter);
			break;
		case useDatabase:
			useDatabase(path, genericWriter);
			break;
		case addDatabase:
			addDatabase(path, genericWriter);
			break;
		case beginTransaction:
			beginTransaction(path, genericWriter);
			break;
		case endTransaction:
			endTransaction(path, genericWriter);
			break;
		case executeSqlQuery:
			executeSqlQuery(path, genericWriter);
			break;
		case executeSqlQueryAndGetResultInVariable:
			executeSqlQueryAndGetResultInVariable(path, genericWriter);
			break;
		case executeSqlUpdateAndGetAffectedRowsNumberIntoVariable:
			executeSqlUpdateAndGetAffectedRowsNumberIntoVariable(path, genericWriter);
			break;
		case countResultRowsNumberAndGetResultInVariable:
			countResultRowsNumberAndGetResultInVariable(path, genericWriter);
			break;
		case getLastInsertedIdIntoVariable:
			getLastInsertedIdIntoVariable(path, genericWriter);
			break;
		case printVariableAsJSON:
			printVariableAsJSON(path, genericWriter);
			break;
		case printVariableAsXML:
			printVariableAsXML(path, genericWriter);
			break;
		case writeArithmeticAndGetResultInVariable:
			writeArithmeticAndGetResultInVariable(path, genericWriter);
			break;
		case EOF:
			EOF(path, genericWriter);
			break;

		default:
			break;
		}
	}
	
	private GenericWriterInterface initializeGenericWriter(Language language){
		switch (language) {
		case PHP5_1:
			return PHPGenericWriterImpl.getInstace();

		default:
			return PHPGenericWriterImpl.getInstace();
		}
	}
	private void header(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		genericWriter.header(path.getValue());
		
	}
	
	private void getInputVars(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable input = parameters.getVariable("input");
		genericWriter.getInputVars(path.getValue(), input.getValues());;
	}
	
	private void postInputVars(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable input = parameters.getVariable("input");
		genericWriter.postInputVars(path.getValue(), input.getValues());;
	}
	
	private void useDatabase(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable alias = parameters.getVariable("alias");
		genericWriter.useDatabase(alias.getValue(), path.getValue());
	}
	
	private void addDatabase(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException, DBManagerException{
		String alias = parameters.getVariable("alias").getValue();
		String host = parameters.getVariable("host").getValue();
		String port = parameters.getVariable("port").getValue();
		String user = parameters.getVariable("user").getValue();
		String passsword = parameters.getVariable("password").getValue();
		String databaseName = parameters.getVariable("databaseName").getValue();
		genericWriter.addDatabase(alias, host, port, user, passsword, databaseName);
	}
	
	private void beginTransaction(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		genericWriter.beginTransaction(path.getValue());
	}
	
	private void endTransaction(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		genericWriter.endTransaction(path.getValue());
	}
	
	private void executeSqlQuery(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable query = parameters.getVariable("query");
		Variable queryParameters = parameters.getVariable("queryParameters");
		genericWriter.executeSqlQuery(path.getValue(), query.getValue(), queryParameters.getObjectElements().getVars());
	}
	
	private void executeSqlQueryAndGetResultInVariable(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable query = parameters.getVariable("query");
		Variable queryParameters = parameters.getVariable("queryParameters");
		Variable variableName = parameters.getVariable("variableName");
		genericWriter.executeSqlQueryAndGetResultInVariable(path.getValue(), query.getValue(), queryParameters.getObjectElements().getVars(), variableName.getValue());
	}
	
	private void executeSqlUpdateAndGetAffectedRowsNumberIntoVariable(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable query = parameters.getVariable("query");
		Variable queryParameters = parameters.getVariable("queryParameters");
		Variable resultVariableName = parameters.getVariable("resultVariableName");
		genericWriter.executeSqlUpdateAndGetAffectedRowsNumberIntoVariable(path.getValue(), query.getValue(), queryParameters.getObjectElements().getVars(), resultVariableName.getValue());
	}
	
	private void countResultRowsNumberAndGetResultInVariable(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable rowsVariableName = parameters.getVariable("rowsVariableName");
		Variable countResultVariableName = parameters.getVariable("countResultVariableName");
		genericWriter.countResultRowsNumberAndGetResultInVariable(path.getValue(), rowsVariableName.getValue(), countResultVariableName.getValue());
	}
	
	private void getLastInsertedIdIntoVariable(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable resultVariableName = parameters.getVariable("resultVariableName");
		genericWriter.getLastInsertedIdIntoVariable(path.getValue(), resultVariableName.getValue());
	}
	
	private void printVariableAsJSON(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable variableName = parameters.getVariable("resultVariableName");
		genericWriter.printVariableAsJSON(path.getValue(), variableName.getValue());
	}
	
	private void printVariableAsXML(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable variableName = parameters.getVariable("resultVariableName");
		genericWriter.printVariableAsXML(path.getValue(), variableName.getValue());
	}
	
	private void writeArithmeticAndGetResultInVariable(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		Variable arithmetic = parameters.getVariable("arithmetic");
		Variable variableList = parameters.getVariable("variableList");
		Variable resultVariableName = parameters.getVariable("resultVariableName");
		genericWriter.writeArithmeticAndGetResultInVariable(path.getValue(), arithmetic.getValue(), variableList.getValuesAsVariableList().getVars(), resultVariableName.getValue());
	}
	
	private void EOF(Variable path, GenericWriterInterface genericWriter) throws VariableManagerException{
		genericWriter.EOF(path.getValue());
	}
	
}
=======
package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import Exception.DBManagerException;
import Exception.VariableManagerException;
import WriterImplementation.PHPGenericWriterImpl;

@XmlAccessorType(XmlAccessType.FIELD)
public class Tasker {
	
	public enum Task{
		header, getInputVars, postInputVars, useDatabase, addDatabase,
		beginTransaction, endTransaction, executeSqlQuery, executeSqlQueryAndGetResultInVariable,
		countResultRowsNumberAndGetResultInVariable, executeSqlUpdateAndGetAffectedRowsNumberIntoVariable,
		getLastInsertedIdIntoVariable, writeArithmeticAndGetResultInVariable, 
		printVariableAsJSON, printVariableAsXML, EOF
	}
	
	private int id;
	private Task task;
	/**
	 * Variables used in this tasker
	 */
	private VariableList parameters;
	
	private static int autoIncrementId = 0;

	public static class TaskerBuilder{
		private Task task;
		private VariableList parameters;
		public TaskerBuilder task(Task val){this.task=val; return this;}
		public TaskerBuilder parameters(VariableList val){this.parameters=val; return this;}
		public Tasker build(){
			return new Tasker(this);
		}
	}
	
	public Tasker(TaskerBuilder tb){
		this.task = tb.task;
		this.parameters =  tb.parameters;
		autoIncrementId();	
	}
		
	public Tasker(){
		autoIncrementId();
	}
	
	private void autoIncrementId(){
		this.id = autoIncrementId;
		autoIncrementId++;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}
	/**
	 * @return the parameters
	 */
	public VariableList getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(VariableList parameters) {
		this.parameters = parameters;
	}
	
	public void executeTask() throws VariableManagerException, DBManagerException{
		switch (task) {
		case header:
			header();
			break;
		case getInputVars:
			getInputVars();
			break;
		case postInputVars:
			postInputVars();
			break;
		case useDatabase:
			useDatabase();
			break;
		case addDatabase:
			addDatabase();
			break;
		case beginTransaction:
			beginTransaction();
			break;
		case endTransaction:
			endTransaction();
			break;
		case executeSqlQuery:
			executeSqlQuery();
			break;
		case executeSqlQueryAndGetResultInVariable:
			executeSqlQueryAndGetResultInVariable();
			break;
		case executeSqlUpdateAndGetAffectedRowsNumberIntoVariable:
			executeSqlUpdateAndGetAffectedRowsNumberIntoVariable();
			break;
		case countResultRowsNumberAndGetResultInVariable:
			countResultRowsNumberAndGetResultInVariable();
			break;
		case getLastInsertedIdIntoVariable:
			getLastInsertedIdIntoVariable();
			break;
		case printVariableAsJSON:
			printVariableAsJSON();
			break;
		case printVariableAsXML:
			printVariableAsXML();
			break;
		case writeArithmeticAndGetResultInVariable:
			writeArithmeticAndGetResultInVariable();
			break;
		case EOF:
			EOF();
			break;

		default:
			break;
		}
	}
	
	private void header() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		PHPGenericWriterImpl.getInstace().header(path.getValue());
	}
	
	private void getInputVars() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable input = parameters.getVariable("input");
		PHPGenericWriterImpl.getInstace().getInputVars(path.getValue(), input.getValues());;
	}
	
	private void postInputVars() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable input = parameters.getVariable("input");
		PHPGenericWriterImpl.getInstace().postInputVars(path.getValue(), input.getValues());;
	}
	
	private void useDatabase() throws VariableManagerException{
		Variable alias = parameters.getVariable("alias");
		Variable path = parameters.getVariable("path");
		PHPGenericWriterImpl.getInstace().useDatabase(alias.getValue(), path.getValue());
	}
	
	private void addDatabase() throws VariableManagerException, DBManagerException{
		String alias = parameters.getVariable("alias").getValue();
		String host = parameters.getVariable("host").getValue();
		String port = parameters.getVariable("port").getValue();
		String user = parameters.getVariable("user").getValue();
		String passsword = parameters.getVariable("password").getValue();
		String databaseName = parameters.getVariable("databaseName").getValue();
		PHPGenericWriterImpl.getInstace().addDatabase(alias, host, port, user, passsword, databaseName);
	}
	
	private void beginTransaction() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		PHPGenericWriterImpl.getInstace().beginTransaction(path.getValue());
	}
	
	private void endTransaction() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		PHPGenericWriterImpl.getInstace().endTransaction(path.getValue());
	}
	
	private void executeSqlQuery() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable query = parameters.getVariable("query");
		Variable queryParameters = parameters.getVariable("queryParameters");
		PHPGenericWriterImpl.getInstace().executeSqlQuery(path.getValue(), query.getValue(), queryParameters.getObjectElements().getVars());
	}
	
	private void executeSqlQueryAndGetResultInVariable() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable query = parameters.getVariable("query");
		Variable queryParameters = parameters.getVariable("queryParameters");
		Variable variableName = parameters.getVariable("variableName");
		PHPGenericWriterImpl.getInstace().executeSqlQueryAndGetResultInVariable(path.getValue(), query.getValue(), queryParameters.getObjectElements().getVars(), variableName.getValue());
	}
	
	private void executeSqlUpdateAndGetAffectedRowsNumberIntoVariable() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable query = parameters.getVariable("query");
		Variable queryParameters = parameters.getVariable("queryParameters");
		Variable resultVariableName = parameters.getVariable("resultVariableName");
		PHPGenericWriterImpl.getInstace().executeSqlUpdateAndGetAffectedRowsNumberIntoVariable(path.getValue(), query.getValue(), queryParameters.getObjectElements().getVars(), resultVariableName.getValue());
	}
	
	private void countResultRowsNumberAndGetResultInVariable() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable rowsVariableName = parameters.getVariable("rowsVariableName");
		Variable countResultVariableName = parameters.getVariable("countResultVariableName");
		PHPGenericWriterImpl.getInstace().countResultRowsNumberAndGetResultInVariable(path.getValue(), rowsVariableName.getValue(), countResultVariableName.getValue());
	}
	
	private void getLastInsertedIdIntoVariable() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable resultVariableName = parameters.getVariable("resultVariableName");
		PHPGenericWriterImpl.getInstace().getLastInsertedIdIntoVariable(path.getValue(), resultVariableName.getValue());
	}
	
	private void printVariableAsJSON() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable variableName = parameters.getVariable("variableName");
		PHPGenericWriterImpl.getInstace().printVariableAsJSON(path.getValue(), variableName.getValue());
	}
	
	private void printVariableAsXML() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable variableName = parameters.getVariable("variableName");
		PHPGenericWriterImpl.getInstace().printVariableAsXML(path.getValue(), variableName.getValue());
	}
	
	private void writeArithmeticAndGetResultInVariable() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		Variable arithmetic = parameters.getVariable("arithmetic");
		Variable variableList = parameters.getVariable("variableList");
		Variable resultVariableName = parameters.getVariable("resultVariableName");
		PHPGenericWriterImpl.getInstace().writeArithmeticAndGetResultInVariable(path.getValue(), arithmetic.getValue(), variableList.getObjectElements(), resultVariableName.getValue());
	}
	
	private void EOF() throws VariableManagerException{
		Variable path = parameters.getVariable("path");
		PHPGenericWriterImpl.getInstace().EOF(path.getValue());
	}
	
}
>>>>>>> FETCH_HEAD
