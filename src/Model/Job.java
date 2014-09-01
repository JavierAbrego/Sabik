package Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import Exception.DBManagerException;
import Exception.VariableManagerException;

@XmlAccessorType(XmlAccessType.FIELD)
public class Job {
	private int id;
	private String jobName;
	private List<Tasker> tasker = new ArrayList<>();
	
	
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
			tsk.executeTask();
		}
	}

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
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
}
