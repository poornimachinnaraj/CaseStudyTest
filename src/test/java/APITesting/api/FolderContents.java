package APITesting.api;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

public class FolderContents {
	
	 //Logger	
	 private static final Logger Log = Logger.getLogger(FolderContents.class.getName());
	 	
	  //1).Test Case to Add a document to Folder
	 @Test(priority =1 )
	 public void AddDocToFolder_01()
	 {		  
		  Log.info("Executing test case 1- Add Document to a folder");		
		  
		  //Create new folder
		  RequestResponse.bIsItFromFolder = true;
		  RequestResponse.Create();	
		  
		  //Create new Document
		  RequestResponse.bIsItFromFolder = false;
		  RequestResponse.Create();	
 
		  //Move document to folder
		  RequestResponse.MoveDocToFolder();
		   
		  //write to LogFile
		  RequestResponse.DetailsToLogFileint();		  
		  Assert.assertEquals(RequestResponse.GetResponseCode(), 200); 	  
	
	  }
	  
	  //Test Case to Get the contents of the folder
	  @Test(priority =2)
	  public void GetFolderContent_02()
	  {
			  
			  Log.info("Executing test case 2- Get folder Content"); 
			 			
			  //Get folder content
			  RequestResponse.bIsItFromFolder = true;
			  RequestResponse.GetListDocinFolder("0","5");
			  
			  //write to LogFile
			  RequestResponse.DetailsToLogFileint();		  
			  Assert.assertEquals(RequestResponse.GetResponseCode(), 200); 			
			  
	 }
		  
	//Test Case to Remove a document from folder
	@Test(priority = 3)
	public void RemoveDocumnetFromFolder_03() 
	{
				  
			Log.info("Executing test case 3- Remove Document from Folder");	
				
			//Remove Folder content
			RequestResponse.ReMoveDocInFolder();
			
			//confirm the deletion
			Boolean bDeleted = RequestResponse.GetListDocinFolder("0","5");
			
			//write to LogFile
			RequestResponse.DetailsToLogFileint();		  
			Assert.assertTrue(bDeleted); 	
	}
	
}
