package Services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import Exception.DBManagerException;
import Exception.VariableManagerException;
import Facade.TaskFacade;
import Model.Job;
import Model.WebServiceBatch;


public class Services {

    public static void main(String[] args) {
        //CREATE BATCH OF PROCESSES
        WebServiceBatch batch =  new WebServiceBatch();
        
        //CREATE JOB
        Job job =  new Job();
        job.setJobName("test Job");
        //Path for Job
        String path =  "test.php";
        
        
        //add tasks for jobs
        TaskFacade.header(job, path);
        TaskFacade.beginTransaction(job, path);
        @SuppressWarnings("serial")
		List<String> inputVars = new ArrayList<String>() {{ add("input"); add("action"); }};
        TaskFacade.getInputVars(job, path, inputVars, TaskFacade.RequestMethod.GET);
        TaskFacade.getInputVars(job, path, inputVars, TaskFacade.RequestMethod.POST);
        TaskFacade.addDatabase(job, path, "alias", "host", "port", "user", "password", "databaseName");
        TaskFacade.useDatabase(job, path, "alias");
        TaskFacade.endTransaction(job, path);
        TaskFacade.EOF(job, path);
        
        //ADD JOBS TO BATCH  
        batch.addJob(job);
        //EXECUTE BATCH
        try {
			batch.start();
		} catch (VariableManagerException | DBManagerException e1) {
			e1.printStackTrace();
		}
        
        //PRINT BATCH OBJECT TO XML
        try {
			JAXBContext jc = JAXBContext.newInstance(WebServiceBatch.class);
			 Marshaller marshaller = jc.createMarshaller();
			 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 marshaller.marshal(batch, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
    }
    
}
