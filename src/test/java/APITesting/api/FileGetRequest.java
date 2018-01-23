package APITesting.api;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class FileGetRequest {
	
		 
	 //Logger	
	static Logger Log = Logger.getLogger(FileGetRequest.class.getName());
     
	 @BeforeTest
	 private void SetCallfromFolder() {
		 RequestResponse.bIsItFromFolder = true;
	 }
	 
	//1).Test Case to Create folder
	 @Test(priority = 1)
	  public void CreateFolder_01()
	 {
		  
		  Log.info("Executing test case 1- Creating folder");	

		  //Create new folder
		  RequestResponse.Create();		  
		
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();		  
		  Assert.assertEquals(RequestResponse.GetResponseCode(), 201);
	  }
	  
	  
	//2).Test Case to Get Folder by ID
	 @Test(priority =2)
	  public void GetFolderbyID_02() 
	 {
		  
		  Log.info("Executing test case 2- Get folder by ID");
		  
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
	 
	  //3).Test Case to Get list folder	 
	 @Test(priority = 3)
	  public void GetListofFolder_03() 
	  {
			  
		  Log.info("Executing test case 3- Getting list of folder from 0 to 5");	
			  
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
	  
	  //4).Test Case to Get list folder	  
	  @Test(priority = 4 )
	  public void GetListofFolder_04() 
	  {
				  
			  Log.info("Executing test case 4- Getting list of folder from 1 to 5");	
				  
			  //Get list of folders
			  Boolean bGotData =RequestResponse.GetListFolderOrDoc("1", "5");   
				  
			  //write to LogFile
			  RequestResponse.DetailsToLogFileint();
			  if(bGotData == true && RequestResponse.GetResponseCode() == 200)		  
				  Assert.assertTrue(bGotData);		  
			  else
				  Assert.assertTrue(bGotData);	
	  }
	  
	  //5).Test Case to Get list folder	  
	  @Test(priority = 5 )
	  public void GetListofFolder_05() 
	  {
				  
			  Log.info("Executing test case 5- Getting list of folder from -1 to 5");	
				  
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
		  
	  //6).Test Case to Get list folder	  
	  @Test(priority = 6)
	  public void GetListofFolder_06() 
	  {
				  
			  Log.info("Executing test case 6- Getting list of folder empty page numbers");	
				  
			  //Get list of folders
			  Boolean bGotData =RequestResponse.GetListFolderOrDoc("", "");   
				  
			  //write to LogFile
			  RequestResponse.DetailsToLogFileint();
			  //bGotData must be false as it contains data
			  if(bGotData == false && RequestResponse.GetResponseCode() == 400)		  
				  Assert.assertFalse(bGotData);		  
			  else
				  Assert.assertFalse(bGotData);	
	   }
		  
	 //7).Test Case to modify Folder by ID
	// @Test(priority = 7)
	 public void MoidfyFolderbyID_07() 
	 {
		  Log.info("Executing test case 7- Modify folder by ID");			  		 
		  
		  //Get list of folders
		  RequestResponse.ModifyFolderORDoc(); 
				  
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();
		  Assert.assertEquals(RequestResponse.GetResponseCode(), 200);			  
	 }
		  
		  
	//8).Test Case to Delete Folder by ID
	@Test(priority =8)
	public void DeleteFolderbyID_08()
	{
			  
		  Log.info("Executing test case 8- Delete folder by ID");		  
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
	
	//9).Test Case to Folder by ID
	@Test(priority = 9)
	public void DeleteFolderbyID_09() 
	{
		Log.info("Executing test case 09- Get folder details which has already been deleted");		  
		//Delete newly created Document
		RequestResponse.GetaFolderorDocumentByID(); 
								  							  
		//write to LogFile
        RequestResponse.DetailsToLogFileint();
		Assert.assertEquals(RequestResponse.GetResponseCode(), 404);
	} 
	
	//10).Test Case to Delete Folder by ID
	@Test(priority = 10)
	public void DeleteFolderbyID_10() 
	{
		  Log.info("Executing test case 10- Delete folder by using empty ID");
		  
		  //Set DcoumentID to ""
		  RequestResponse.sFolderID = "";
		  
		  //Delete newly created Document
		  RequestResponse.DeleteFolder();		  
		 	  
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();
		  Assert.assertEquals(RequestResponse.GetResponseCode(), 405);
	} 		
	
  
}
