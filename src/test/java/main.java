/*
 * TODO: 15.11.2014 
 * 	  - Test Engine Framework?
 * 	  - httpclient - > Send request so server etc. SET, PUT etc. No JS:
 * 			->     make it singleton ( if  == NIL => create, ruby's |= )
 * 			->     make a RestAPI class 
 * 			->     send requests -> get JSON's. (GSON lib)  to_gson, from_gson methods check.
 * 			->     make a class for each entity
 * 			->     Authors make a List<Author> .... etc
 * 			->     date use as Date?:
 * 			-> Homework
 * 			To write login, logout
 * 			RESTAPI work class (httpclient)
 * 			Classes entities. experiment with GSON.
 * 
 */

/*	
 * TODO: 22.11.2014 
 * 	    TestNG:
 * 			DataProvider - > prepare data for tests
 * 	 		Checkout maven build
 * 
 * 
 */

import REST.*;

public class main {

	public static void main(String[] args) throws Exception {
	    REST API = new REST();
	    String filter = "{\"categoryId\":1,\"authorId\":1}";


	    API.prettyPrint(API.getBook(1));
	}

}
	