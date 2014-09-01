package Model;

public class Tasker {
	public enum Task{
		header, getInputVars, postInputVars, useDatabase, addDatabase,
		beginTransaction, endTransaction, executeSqlQuery, executeSqlQueryAndGetResultInVariable,
		countResultRowsNumberAndGetResultInVariable, executeSqlUpdateAndGetAffectedRowsNumberIntoVariable,
		getLastInsertedIdIntoVariable, writeArithmeticAndGetResultInVariable, 
		printVariableAsJSON, printVariableAsXML, EOF
	}
	
	private Task task;
	private VariableList parameters;
	private int id;
	private static int autoIncrementId = 0;
	
	public Tasker(){
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
	
	public void executeTask(){
		switch (task) {
		case header:
			break;
		case getInputVars:
			break;
		case postInputVars:
			break;
		case useDatabase:
			break;
		case addDatabase:
			break;
		case beginTransaction:
			break;
		case endTransaction:
			break;
		case executeSqlQuery:
			break;
		case executeSqlQueryAndGetResultInVariable:
			break;
		case executeSqlUpdateAndGetAffectedRowsNumberIntoVariable:
			break;
		case countResultRowsNumberAndGetResultInVariable:
			break;
		case getLastInsertedIdIntoVariable:
			break;
		case printVariableAsJSON:
			break;
		case printVariableAsXML:
			break;
		case writeArithmeticAndGetResultInVariable:
			break;
		case EOF:
			break;

		default:
			break;
		}
	}
	
}
