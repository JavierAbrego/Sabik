package Services;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import Exception.DBManagerException;
import Exception.VariableManagerException;
import Model.Job;
import Model.Tasker;
import Model.Variable;
import Model.VariableList;
import Model.WebServiceBatch;


public class Services {

    public static void main(String[] args) {
        //Creation of batch of processes
        WebServiceBatch batch =  new WebServiceBatch();
        
        //Creation of a Job
        Job job =  new Job();
        job.setJobName("test Job");
        //Path for Job
        Variable path =  new Variable.VariableBuilder()
									.name("path")
									.value("test.php")
									.build();

        //HEADER
        //definition of parameters
        VariableList parameters = new VariableList();
        parameters.addVariable(path);
        //creation of task header
        Tasker tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.header)
        							.parameters(parameters)
        							.build();
        job.addTasker(tasker);
        
        //BEGIN TRANSACTION
        //definition of parameters
        VariableList parameters2 = new VariableList();
        parameters2.addVariable(path);
        //creation of task header
         tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.beginTransaction)
        							.parameters(parameters2)
        							.build();
        job.addTasker(tasker);
        
        
        //END TRANSACTION
        //definition of parameters
        VariableList parameters3 = new VariableList();
        parameters3.addVariable(path);
        //creation of task header
         tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.endTransaction)
        							.parameters(parameters3)
        							.build();
        job.addTasker(tasker);
        //EOF
        //definition of parameters
         VariableList parameters4 = new VariableList();
        parameters4.addVariable(path);
        Variable test2 =  new Variable.VariableBuilder()
							.name("test2")
							.value("test.php")
							.build();
        parameters4.addVariable(test2);
        //creation of task header
        tasker =  new Tasker.TaskerBuilder()
        							.task(Tasker.Task.EOF)
        							.parameters(parameters4)
        							.build();
        job.addTasker(tasker);
        
        
        //ADD JOBS TO BATCH  and execute
        batch.addJob(job);
        try {
			batch.start();
		} catch (VariableManagerException | DBManagerException e1) {
			e1.printStackTrace();
		}
        
        try {
			JAXBContext jc = JAXBContext.newInstance(WebServiceBatch.class);
			 Marshaller marshaller = jc.createMarshaller();
			 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 marshaller.marshal(batch, System.out);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
