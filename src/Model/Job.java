package Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import Exception.DBManagerException;
import Exception.VariableManagerException;
import Facade.TaskFacade;

@XmlAccessorType(XmlAccessType.FIELD)
public class Job {
	private int id;
	private String jobName;
	private String path;
	private Tasker.Language language;
	private List<Tasker> tasker = new ArrayList<>();
	
	/**
	 * Describes the tasks that will be executed by the Service, and the path where it will write those task
	 */
	public Job(){
		
	}
	
	public TaskFacade addTasker(){
		return new TaskFacade(this);
	}
	
	public void addTasker(Tasker tsk){
		this.tasker.add(tsk);
	}
	
	public void deleteTasker(int id){
		int index = 0;
		for (Tasker tsk : tasker) {
			if(tsk.getId()==id){
				tasker.remove(index);
				return;
			}
			index++;
		}
	}
	
	public void executeJob() throws VariableManagerException, DBManagerException{
		for (Tasker tsk : tasker) {
			tsk.executeTask(path, language);
		}
	}

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * Sets an alias for the job
	 * @param jobName the jobName to set
	 */
	public Job setJobName(String jobName) {
		this.jobName = jobName;
		return this;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public Job setPath(String path) {
		this.path = path;
		return this;
	}

	/**
	 * @return the language
	 */
	public Tasker.Language getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public Job setLanguage(Tasker.Language language) {
		this.language = language;
		return this;
	}
}
