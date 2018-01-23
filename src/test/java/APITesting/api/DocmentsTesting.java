package APITesting.api;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.Assert;


public class DocmentsTesting {	
		 

	//Logger
	private static final Logger Log = Logger.getLogger(DocmentsTesting.class.getName());

	 @BeforeTest
	 private void SetCallfromFolder() {
		 RequestResponse.bIsItFromFolder = false;
	 }
	 
	//1).Test Case to Create Document
	 @Test(priority =1)
	 public void CreateDocument_01()
	 {
			  
		 Log.info("Executing test case 1- Creating Document");	
		
		  //Create new Document
		  RequestResponse.Create();		  
		
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();		  
		  Assert.assertEquals(RequestResponse.GetResponseCode(), 201);
	 }
		  
		  
	//2).Test Case to Get Document by ID
	@Test(priority = 2)
	public void GetDocumentbyID_02()
	{
		 Log.info("Executing test case 2- Get Document by ID");			
			  
		  //Get newly created folder by ID		  
		  Boolean bGotData = RequestResponse.GetaFolderorDocumentByID(); 		  
		  
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();
		  //bGotData must be false as it contains data
		  if(bGotData == true && RequestResponse.GetResponseCode() == 200)		  
			  Assert.assertTrue(bGotData);		  //PASS
		  else
			  Assert.assertTrue(bGotData);	 	 //FAIL  
	}  
		  
		 
	 //3)Test Case to Get list of Documents
	 @Test(priority = 3)
	 public void GetDocumentList_03()
	 {
				  
		 Log.info("Executing test case 3- Getting list of Document from 0 to 5");	
		  
		//Get list of folders
		  Boolean bGotData =RequestResponse.GetListFolderOrDoc("0", "5");   
			  
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();
		  //bGotData must be false as it contains data
		  if(bGotData == true && RequestResponse.GetResponseCode() == 200)		  
			  Assert.assertTrue(bGotData);		  
		  else
			  Assert.assertTrue(bGotData);	
		 
	 }
	 
	//4)Test Case to Get list of Documents
	@Test(priority = 4)
	 public void GetDocumentList_04()
	 {
					  
		 Log.info("Executing test case 4- Getting list of Document from 1 to 5");	
			  
		  //Get list of folders
		  Boolean bGotData =RequestResponse.GetListFolderOrDoc("1", "5");   
			  
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();
		  if(bGotData == true && RequestResponse.GetResponseCode() == 200)		  
			  Assert.assertTrue(bGotData);		  
		  else
			  Assert.assertTrue(bGotData);
			 
	 }
		 
	//5)Test Case to Get list of Documents
	@Test(priority = 5)
	public void GetDocumentList_05()
	{
						  
	    Log.info("Executing test case 5- Getting list of Document from -1 to 5");	
				  
	    //Get list of folders
		  Boolean bGotData =RequestResponse.GetListFolderOrDoc("-1", "5");   
			  
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();
		  //bGotData must be false as it contains data
		  if(bGotData == false && RequestResponse.GetResponseCode() == 400)		  
			  Assert.assertFalse(bGotData);		  
		  else
			  Assert.assertFalse(bGotData);	
				 
	 }
	
	//6)Test Case to Get list of Documents
	@Test(priority = 6)
	public void GetDocumentList_06()
	{
							  
	    Log.info("Executing test case 6- Getting list of Document from '' to ''");	
				  
		//Get list of folders
	    RequestResponse.GetListFolderOrDoc(" ", " ");   
						  
		//write to LogFile
		 RequestResponse.DetailsToLogFileint();
		 Assert.assertEquals(RequestResponse.GetResponseCode(), 400);
					 
	}		  
			  
	//Test Case to modify Document by ID
	@Test(priority = 7)
	public void MoidfyDocumentbyID_07()
	{
				  
	      Log.info("Executing test case 7- Modify Document by ID");			  		 
		  
		  //Modify Doucument
		  RequestResponse.ModifyFolderORDoc(); 
				  
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();		  
		  Assert.assertEquals(RequestResponse.GetResponseCode(), 200);			  
	}
			  
			  
	 //8).Test Case to Delete Document by ID---> is not working
	@Test(priority = 8)
     public void DeleteDocumentbyID_08()
	 {
				  
		  Log.info("Executing test case 8- Delete Document by ID");		  
				  
		  //Delete newly created Document
		  RequestResponse.DeleteFolder(); 
		  
		  int nResponseCode = RequestResponse.GetResponseCode();
		  
		  //Check if folder or document did get deleted
		  Boolean bDocDeleted = RequestResponse.GetaFolderorDocumentByID();
						  
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();
		  //checked with false as the folder would have got delete already
		  if(bDocDeleted == false && nResponseCode == 410)
			  Assert.assertFalse(bDocDeleted);
		  else
			  Assert.assertFalse(bDocDeleted);		  
			
	 }
	 
	 //9).Test Case to Delete Document by ID---> is not working
	 @Test(priority = 9)
     public void DeleteDocumentbyID_09()
	 {
				  
		  Log.info("Executing test case 9- Delete Document by ID which was already deleted");		  
				  
		  //Delete newly created Document
		  RequestResponse.DeleteFolder(); 
		  
		  int nResponseCode = RequestResponse.GetResponseCode();
		  
		  //Check if folder or document did get deleted
		  Boolean bDocDeleted = RequestResponse.GetaFolderorDocumentByID();
						  
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();
		  
		  //checked with false as the folder would have got delete already
		  if(bDocDeleted == false && nResponseCode == 410)
			  Assert.assertFalse(bDocDeleted);
		  else
			  Assert.assertFalse(bDocDeleted);	  
			
	 }
		  
}
