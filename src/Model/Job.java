package Model;

import java.util.ArrayList;
import java.util.List;

public class Job {
	private List<Tasker> taskers = new ArrayList<>();
	
	public void addTasker(Tasker tasker){
		
	}
	
	public void deleteTasker(int id){
		
	}
	
	public void executeJob(){
		for (Tasker tasker : taskers) {
			tasker.executeTask();
		}
	}
}
