package APITesting.api;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;


public class RequestResponse {
	
		// Variables
		 private static String sFolderName1 = "{ \"name\": \"TestFolder1\"}";
		 private static String sFolderName2 = "{ \"name\": \"TestFolder2\"}";
		// private static String sDocumentName1 = "Document7";
		// private static String sDocumentName2 = "Document8";
		 private static String sDocumentData1 = "{ \"name\": \"Document7\", \"text\": \"Hi ,is it ok  ?\"}";
		 private static String sDocumentData2 = "{ \"name\": \"Document8\", \"text\": \"Hi ,are you ok ?\"}";		
		 public static String sFolderID = "";
		 public static String sDocumentID = "";
		 private static String sFolderURI = "http://192.168.99.100:8080/my-box/v1/folders";
		 private static String sDocumnetURI = "http://192.168.99.100:8080/my-box/v1/documents";
		 private static int nResponseCode = 0;
		 public static boolean bIsItFromFolder = false;
		 
		 //Logger	
		 private static final Logger Log = Logger.getLogger(FolderContents.class.getName());
		 
		
		 
		//Set FolderID
		 public void SetResponseCode(int nRespcode) {  this.nResponseCode = nRespcode; }
		 
		//Get FolderID
		 public static int GetResponseCode() { return nResponseCode; }
		 
		 
		 //Create Folder or Document
		 public static void  Create()
		 {
			Response resp;
			
			if(bIsItFromFolder)
			{
				resp= given().body(sFolderName1).when().contentType(ContentType.JSON).post(sFolderURI);	
				//get ID of the created document from the response		 
				sFolderID = resp.getBody().jsonPath().getString("id");
				Log.info("New Created Folder ID : " + sFolderID);	
			}else {
				resp= given().body(sDocumentData1).when().contentType(ContentType.JSON).post(sDocumnetURI);
				sDocumentID = resp.getBody().jsonPath().getString("id");
				Log.info("New Document Folder ID : " + sDocumentID);	
			}
			
			Log.info("Response code "+ resp.getStatusCode());			
		 }		 
				 
		 //Get a folder or Document
		 public static Boolean GetaFolderorDocumentByID()
		 {
			 Response resp;			
			 if(bIsItFromFolder) {				
				 resp = given().when().get(sFolderURI+"/"+sFolderID);
			 }else {
				 resp = given().when().get(sDocumnetURI+"/"+sDocumentID);
			 }			
			 nResponseCode = resp.getStatusCode();
			 Log.info(resp.asString());		
			 Log.info("Response code: "+ nResponseCode);
			
			 return !CheckFolderorDocerror(resp.asString());
		 }
		 
		//Get list of folders  or Document
	     public static Boolean GetListFolderOrDoc(String sPgNo,String sPgSize)
	     {	 
	    	 Response resp;			 
			 if(bIsItFromFolder) {
				resp = given().param("page", sPgNo).param("per_page",sPgSize ).when().get(sFolderURI);
			 }else {
				resp =  given().param("page", sPgNo).param("per_page",sPgSize ).when().get(sDocumnetURI);
			 }
			  nResponseCode = resp.getStatusCode();
			  Log.info(resp.asString());
			  Log.info("Response code: "+ nResponseCode );
			  return !CheckFolderorDocerror(resp.asString());
		 }
		 
	     //Get list of Document inside the folder
	     public static Boolean GetListDocinFolder(String sPgNo,String sPgSize)
	     {	 
	    	 Response resp;			 
			 if(bIsItFromFolder) {
				resp = given().param("page", sPgNo).param("per_page",sPgSize ).when().
						get(sFolderURI+"/"+sFolderID+"/contents?page="+sPgNo+"&per_page="+sPgSize);
			 }else {
				resp =  given().param("page", sPgNo).param("per_page",sPgSize ).when().
						get(sDocumnetURI+"/"+sDocumentID+"/contents?page="+sPgNo+"&per_page="+sPgSize);
			 }
			  nResponseCode = resp.getStatusCode();
			  Log.info(resp.asString());	
			  return CheckFolderorDocerror(resp.asString());
		 }
		 
	     //Check if folder or Doc as any error
	     public static Boolean CheckFolderorDocerror(String sResponseString)
	     {
	    	 if(sResponseString.length() ==2 || sResponseString.contains("error") ||sResponseString.contains("not found"))//2 to indicate empty array
				 return true;
			 else
				 return false; 	    	
	     }
	     
	     
		 //Modify the existing folder name
		 public static void  ModifyFolderORDoc()
		 {

	    	 Response resp;			 
			 if(bIsItFromFolder) {
				resp = given().body(sFolderName2).when().contentType(ContentType.JSON).put(sFolderURI+"/"+sFolderID);
			 }else {
				resp = given().body(sDocumentData2).when().contentType(ContentType.JSON).put(sDocumnetURI+"/"+sDocumentID);
			 }
			  nResponseCode = resp.getStatusCode();
			  Log.info("Response code: "+nResponseCode);
			  Log.info(resp.asString());
		 }
		 
		 //Move Document into folder name
		 public static void  MoveDocToFolder()
		 {
			 String url = "%s/%s/contents/%s";
	    	 Response resp = given().when().contentType(ContentType.JSON).
			          		 post(String.format(url, sFolderURI, sFolderID, sDocumentID));
			 nResponseCode = resp.getStatusCode();
			 Log.info(resp.asString());
		 }
		 
		 //ReMove Document in folder name
		 public static void  ReMoveDocInFolder()
		 {
			 String url = "%s/%s/contents/%s";
	    	 Response resp = given().when().contentType(ContentType.JSON).
	    			 		 delete(String.format(url, sFolderURI, sFolderID, sDocumentID));
			 nResponseCode = resp.getStatusCode();			
			 Log.info("Response code :" + nResponseCode );			 
		 }
		 
		 //Delete the folder
		 public static void DeleteFolder()
		 {

	    	 Response resp;			 
			 if(bIsItFromFolder) {
				resp = given().when().delete(sFolderURI+"/"+sFolderID);
				Log.info("Delete URI:" +sFolderURI+"/"+sFolderID);
			 }else {
				resp = given().when().delete(sDocumnetURI+"/"+sDocumentID);
			 }
			  nResponseCode = resp.getStatusCode();
			  Log.info("Delete request response code :"+ resp.getStatusCode());			
		 }
		 
		 
		 
		 public static void DetailsToLogFileint()
		 {
			//if(resp.getStatusCode() == 201)-->did not work as per Swagger UI Documentation
			  if(nResponseCode == 200){
				  Log.info("Request Completed Successfully"); 
				 		  
			  }
			  else{
				  if(nResponseCode == 400)
					  Log.info("Request is invalid. See response body for details.");
				  if(nResponseCode == 401)
					  Log.info("Correct credentials were not provided.");
				  if(nResponseCode == 403)
					  Log.info("This action is forbidden.");
				  if(nResponseCode == 404)
					  Log.info("The resource could not be found at this location.");
				  if(nResponseCode == 500)
					  Log.info("An internal error caused the request to fail. Contact support for help");					  
			  }
			 
		 }


}
