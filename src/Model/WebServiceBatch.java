package Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import Exception.DBManagerException;
import Exception.VariableManagerException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WebServiceBatch {
	private List<Job> job =  new ArrayList<>();

	/**
	 * Describes a batch of jobs, jobs can be added and executed sequentially.
	 */
	public WebServiceBatch(){}
	/**
	 * @return the jobsList
	 */
	public List<Job> getJobsList() {
		return job;
	}

	/**
	 * @param jobsList the jobsList to set
	 */
	public void setJobsList(List<Job> jobsList) {
		this.job = jobsList;
	}
	
	/**
	 * @param jobsList
	 */
	public WebServiceBatch addJob(Job jobsList) {
		this.job.add(jobsList);
		return this;
	}
	
	public WebServiceBatch start() throws VariableManagerException, DBManagerException{
		for (Job jb : job) {
			jb.executeJob();			
		}
		return this;
	}
}
