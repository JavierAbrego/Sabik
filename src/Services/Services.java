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
import Model.Tasker.Language;
import Model.WebServiceBatch;


public class Services {
	
	@SuppressWarnings("serial")
    public static void main(String[] args) throws VariableManagerException, DBManagerException {
        //create parameters for jobs
        List<String> inputVars = new ArrayList<String>() {{ add("numberA"); add("numberB"); }};
        List<String> operationVars = new ArrayList<String>() {{ add("numberA"); add("numberB"); add("numberA"); }};
        //CREATE JOB
        Job job =  new Job();
        //add tasks for jobs
        job.setJobName("test Job")
	    	.setPath("test.php")
	    	.setLanguage(Language.PHP5_1)
	        .addTasker()
        	.header()
        	.getInputVars(inputVars, TaskFacade.RequestMethod.GET)
        	.getInputVars(inputVars, TaskFacade.RequestMethod.POST)
        	.writeArithmeticAndGetResultInVariable("(?+?)*?", operationVars, "result")
        	.printVariableAsJSON("result")
        	.EOF();
        //CREATE BATCH OF PROCESSES
        WebServiceBatch batch =  new WebServiceBatch().addJob(job).start();
        printBatchToXML(batch);
    }
	
	
	private static void printBatchToXML(WebServiceBatch batch){
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
