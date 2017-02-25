package REST;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import REST.Structures.*;
import com.google.gson.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/* TODO:
 * 		- Test methods  
 */

public class REST {
	
	private static HttpClient httpclient = null;
	private static Gson   gson           = null;
	private static String host           = "81.180.75.144:8080";
	
	public REST() {

		if (httpclient == null) {
			httpclient     = HttpClientBuilder.create().build();
			
			GsonBuilder gb = new GsonBuilder();
			gb.setPrettyPrinting();
			gb.setDateFormat("yyyy-MM-dd");
			gb.registerTypeAdapter(Date.class, new DateDeserializer());
			gb.setPrettyPrinting();
			gson       = gb.create();
		}
	}
	
	private String getResponseData(HttpResponse response) throws Exception
	{
		return EntityUtils.toString(response.getEntity());
	}
	
	
	public void prettyPrint(Object  obj)
	{
		System.out.println(gson.toJson(obj));
	}

	private HttpResponse getRequest(String url) throws Exception
	{
		return httpclient.execute(new HttpGet(url));
	}

	private HttpResponse createRequest(Object obj, String url) throws Exception
	{
		HttpPost request     = new HttpPost(url);
		request.setHeader("Content-type", "application/json");
		request.setHeader("Accept", "application/json");
		StringEntity payload = new StringEntity(gson.toJson(obj));
		request.setEntity(payload);
		return httpclient.execute(request);
	}

	private HttpResponse updateRequest(Object obj, String url) throws Exception
	{
		HttpPut  request     = new HttpPut(url);
		
		request.setHeader("Content-type", "application/json");
		StringEntity payload = new StringEntity(gson.toJson(obj));
		request.setEntity(payload);

		return httpclient.execute(request);
	}

	private HttpResponse removeRequest(String url) throws Exception
	{
		return httpclient.execute(new HttpDelete(url));
	}


	/** Book Resource **/
	/*******************/
	
	/* GET-  http://{host}/elibraryws/books/{bookId} */
	public Book getBook(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/books/" + id;
		HttpResponse response = getRequest(url);
		
		return gson.fromJson(getResponseData(response), Book.class);
	}

	
	/* POST - http://{host}/elibraryws/books */
	public Book createBook(Book book) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/books";
		HttpResponse response = createRequest(book, url);
		
		return gson.fromJson(getResponseData(response), Book.class);
	} 
	
	/* PUT - http://{host}/elibraryws/books/{bookId} */
	public void updateBook(int id, Book book) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/books/" + id;
		HttpResponse response = updateRequest(book, url);
		
		System.out.println(getResponseData(response));		
	}
	
	/* DELETE - http://{host}/elibraryws/books/{bookId} */
	public void removeBook(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/books/" + id;
		HttpResponse response = removeRequest(url);
		
		System.out.println("Removing Book with id : " + id);
		System.out.println(getResponseData(response));
	}
	
	/* POST - http://{host}/elibraryws/books/get-filtered-data-count */
	public int getBooksCount(String filter) throws Exception {
		
		String url           = "http://" + host + "/elibraryws/books/get-filtered-data-count";
		HttpPost request     = new HttpPost(url);
		request.setHeader("Content-type", "application/json");
		StringEntity payload = new StringEntity(filter);
		request.setEntity(payload);
		HttpResponse response = httpclient.execute(request);

		return Integer.valueOf(getResponseData(response));
	}
	
	/* POST - http://{host}/elibraryws/books/get-data/{offset}/{limit}/{sort_by}/{sort_dir} */
	@SuppressWarnings("unchecked")
	public List<Book> getBooks(String filter, int offset, int limit,
			String sort_by, String sort_dir) throws Exception {
		
		String url           = "http://" + host + "/elibraryws/books/get-data" +
						"/" + offset + "/" + limit + "/" + sort_by + "/" + sort_dir;
		
		HttpPost request     = new HttpPost(url);
		StringEntity payload = new StringEntity(filter);
		
		request.setHeader("Content-type", "application/json");
		request.setEntity(payload);
		HttpResponse response = httpclient.execute(request);
		
		return gson.fromJson(getResponseData(response), List.class);
	}

	
	/** Author Resource **/
	/*********************/
	
	/* GET - http://{host}/elibraryws/authors/{authorId} */
	public Author getAuthor(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/authors/" + id;
		HttpResponse response = getRequest(url);
		
		return gson.fromJson(getResponseData(response), Author.class);
	}
	
	/* GET - http://{host}/elibraryws/authors/count */
	public int getAuthorsCount() throws Exception {
		
		String url            = "http://" + host + "/elibraryws/authors/count";
		HttpGet request       = new HttpGet(url);
		HttpResponse response = httpclient.execute(request);
	
		return Integer.valueOf(getResponseData(response));
	}
	
	/* GET - http://{host}/elibraryws/authors */
	@SuppressWarnings("unchecked")
	public List<Author> getAuthors() throws Exception {
		
		String url            = "http://" + host + "/elibraryws/authors";
		HttpGet request       = new HttpGet(url);
		HttpResponse response = httpclient.execute(request);
		
		return gson.fromJson(getResponseData(response), List.class);
	}
	
	/* DELETE - http://localhost:8080/elibraryws/authors/{authorId} */
	public void removeAuthor(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/authors/" + id;
		HttpResponse response = removeRequest(url);
		
		System.out.println("Removing Author with id : " + id);
		System.out.println(getResponseData(response));
	}
	
	/* POST - http://localhost:8080/elibraryws/authors */
	public Author createAuthor(Author author) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/authors";
		HttpResponse response = createRequest(author, url);
		Author res = gson.fromJson(getResponseData(response),Author.class);
		return res;
	}
	
	/* PUT-http://localhost:8080/elibraryws/authors */
	public void updateAuthor(Author author) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/authors";
		HttpResponse response = updateRequest(author, url);
		
		System.out.println(getResponseData(response));
	}
	

	/** Category Resource **/
	/***********************/
	
	/* GET - http://{host}/elibraryws/categories/{categoryId} */
	public Category getCategory(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/categories/" + id;
		HttpResponse response = getRequest(url);
		
		return gson.fromJson(getResponseData(response), Category.class);
	}
	
	/* GET - http://{host}/elibraryws/categories/count */
	public int getCategoriesCount() throws Exception {
		
		String url            = "http://" + host + "/elibraryws/categories/count";
		HttpGet request       = new HttpGet(url);
		HttpResponse response = httpclient.execute(request);
	
		return Integer.valueOf(getResponseData(response));
	}
	
	/* GET - http://{host}/elibraryws/categories */
	@SuppressWarnings("unchecked")
	public List<Category> getCategories() throws Exception {
		
		String url            = "http://" + host + "/elibraryws/categories";
		HttpGet request       = new HttpGet(url);
		HttpResponse response = httpclient.execute(request);
		
		return gson.fromJson(getResponseData(response), List.class);
	}
	
	/* DELETE - http://localhost:8080/elibraryws/categories/{categoryId} */
	public void removeCategory(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/categories/" + id;
		HttpResponse response = removeRequest(url);
		
		System.out.println("Removing Category with id : " + id);
		System.out.println(getResponseData(response));
	}
	
	/* POST - http://localhost:8080/elibraryws/categories */
	public void createCategory(Category category) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/categories";
		HttpResponse response = createRequest(category, url);
		
		System.out.println(getResponseData(response));
	}
	
	/* PUT-http://localhost:8080/elibraryws/categories */
	public void updateCategory(Category category) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/categories";
		HttpResponse response = updateRequest(category, url);
		
		System.out.println(getResponseData(response));
	}
	
	/** User Role Resource **/
	/************************/
	
	/* GET - http://{host}/elibraryws/roles/{roleId} */
	public UserRole getUserRole(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/roles/" + id;
		HttpResponse response = getRequest(url);
		
		return gson.fromJson(getResponseData(response), UserRole.class);
	}
	
	/* GET - http://{host}/elibraryws/roles/count */
	public int getUserRolesCount() throws Exception {
		
		String url            = "http://" + host + "/elibraryws/roles/count";
		HttpGet request       = new HttpGet(url);
		HttpResponse response = httpclient.execute(request);
	
		return Integer.valueOf(getResponseData(response));
	}
	
	/* GET - http://{host}/elibraryws/roles */
	@SuppressWarnings("unchecked")
	public List<UserRole> getUserRoles() throws Exception {
		
		String url            = "http://" + host + "/elibraryws/roles";
		HttpGet request       = new HttpGet(url);
		HttpResponse response = httpclient.execute(request);
		
		return gson.fromJson(getResponseData(response), List.class);
	}
	
	/* DELETE - http://localhost:8080/elibraryws/roles/{roleId} */
	public void removeUserRole(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/roles/" + id;
		HttpResponse response = removeRequest(url);
		
		System.out.println("Removing UserRole with id : " + id);
		System.out.println(getResponseData(response));
	}
	
	/* POST - http://localhost:8080/elibraryws/roles */
	public void createUserRole(UserRole userRole) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/roles";
		HttpResponse response = createRequest(userRole, url);
		
		System.out.println(getResponseData(response));
	}
	
	/* PUT-http: //localhost:8080/elibraryws/roles */
	public void updateUserRoles(UserRole userRole) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/roles";
		HttpResponse response = updateRequest(userRole, url);
		
		System.out.println(getResponseData(response));
	}
	
	/** Book Request Resource **/
	/***************************/
	
	/* GET- http://{host}/elibraryws/ book-requests/{bookRequestId} */
	public BookRequest getBookRequest(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/book-requests/" + id;
		HttpResponse response = getRequest(url);
		
		return gson.fromJson(getResponseData(response), BookRequest.class);
	}
	
	/* POST - http://{host}/elibraryws/book-requests */
	public void createBookRequest(BookRequest bookRequest) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/book-requests";
		HttpResponse response = createRequest(bookRequest, url);
		
		System.out.println(getResponseData(response));
	}
	
	/* PUT - http://{host}/elibraryws/book-requests/{bookRequestId} */
	public void updateBookRequest(int id, BookRequest bookRequest) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/book-requests/" + id;
		HttpResponse response = updateRequest(bookRequest, url);
		
		System.out.println(getResponseData(response));
	}
	

	/* DELETE - http://{host}/elibraryws/book-requests/{bookRequestId} */
	public void removeBookRequest(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/book-requests/" + id;
		HttpResponse response = removeRequest(url);
		
		System.out.println("Removing REST.BookRequest with id : " + id);
		System.out.println(getResponseData(response));
	}
	
	/* POST - http://{host}/elibraryws/book-requests/get-filtered-data-count */
	public int getBookRequestsCount(String filter) throws Exception {
		
		String url           = "http://" + host + "/elibraryws/book-requests/get-filtered-data-count";
		HttpPost request     = new HttpPost(url);
		request.setHeader("Content-type", "application/json");
		StringEntity payload = new StringEntity(filter);
		request.setEntity(payload);
		HttpResponse response = httpclient.execute(request);

		return Integer.valueOf(getResponseData(response));
	}
	
	/* POST - http://{host}/elibraryws/book-requests/get-data/{offset}/{limit}/{sort_by}/{sort_dir} */
	@SuppressWarnings("unchecked")
	public List<BookRequest> getBookRequests(String filter, int offset, int limit,
                                             String sort_by, String sort_dir) throws Exception {
		
		String url           = "http://" + host + "/elibraryws/book-requests/get-data" +
						"/" + offset + "/" + limit + "/" + sort_by + "/" + sort_dir;
		
		HttpPost request     = new HttpPost(url);
		StringEntity payload = new StringEntity(filter);
		
		request.setHeader("Content-type", "application/json");
		request.setEntity(payload);
		HttpResponse response = httpclient.execute(request);
		
		return gson.fromJson(getResponseData(response), List.class);
	}

	/** Log Request Resource **/
	/**************************/

	/* POST - http://{host}/elibraryws/logs/get-filtered-data-count */
	public int getLogsCount(String filter) throws Exception {

		String url           = "http://" + host + "/elibraryws/logs/get-filtered-data-count";
		HttpPost request     = new HttpPost(url);
		request.setHeader("Content-type", "application/json");
		StringEntity payload = new StringEntity(filter);
		request.setEntity(payload);
		HttpResponse response = httpclient.execute(request);

		return Integer.valueOf(getResponseData(response));
	}
	
	/* POST - http://{host}/elibraryws/logs/get-data/{offset}/{limit}/{sort_by}/{sort_dir} */
	@SuppressWarnings("unchecked")
	public List<LogRequest> getLogs(String filter, int offset, int limit,
                                    String sort_by, String sort_dir) throws Exception {

		String url           = "http://" + host + "/elibraryws/logs/get-data" +
						"/" + offset + "/" + limit + "/" + sort_by + "/" + sort_dir;
		
		HttpPost request     = new HttpPost(url);
		StringEntity payload = new StringEntity(filter);
		
		request.setHeader("Content-type", "application/json");
		request.setEntity(payload);
		HttpResponse response = httpclient.execute(request);
		
		return gson.fromJson(getResponseData(response), List.class);
	}
	
	/** Registration Request Resource **/
	/***********************************/

	/* GET- http://{host}/elibraryws/ registration/{registrationRequestId} */
	public RegistrationRequest getRegistrationRequest(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/registration/" + id;
		HttpResponse response = getRequest(url);
		
		return gson.fromJson(getResponseData(response), RegistrationRequest.class);
	}
	
	/* POST - http://{host}/elibraryws/registration */
	public void createRegistrationRequest(RegistrationRequest request) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/registration";
		HttpResponse response = createRequest(request, url);
		
		System.out.println(getResponseData(response));
	}
	
	/* DELETE - http://{host}/elibraryws/registration/{registrationRequestId} */
	public void removeRegistrationRequest(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/registration/" + id;
		HttpResponse response = removeRequest(url);
		
		System.out.println("Removing Registration with id : " + id);
		System.out.println(getResponseData(response));
	}
	
	/* PUT - http://{host}/elibraryws/registration */
	public void updateRegistrationRequest(RegistrationRequest request) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/registration";
		HttpResponse response = updateRequest(request, url);
		
		System.out.println(getResponseData(response));
	}
	
	/* GET - http://{host}/elibraryws/registration/get-data-count */
	public int  getRegistrationRequestsCount() throws Exception {
		
		String url            = "http://" + host + "/elibraryws/registration/get-data-count";
		HttpGet request       = new HttpGet(url);
		HttpResponse response = httpclient.execute(request);
	
		return Integer.valueOf(getResponseData(response));
	}
	
	/* GET - http://{host}/elibraryws/regsitration/get-data/{offset}/{limit}/{sort_by}/{sort_dir} */
	@SuppressWarnings("unchecked")
	public List<RegistrationRequest> getRegistratinRequests(int offset, int limit,
                                                            String sort_by, String sort_dir) throws Exception {

		String url           = "http://" + host + "/elibraryws/registration/get-data" +
						"/" + offset + "/" + limit + "/" + sort_by + "/" + sort_dir;
		
		HttpResponse response = getRequest(url);
		
		return gson.fromJson(getResponseData(response), List.class);
	}
	
	/* GET - http://{host}/elibraryws/registration/register/{registrationRequestId} */
	public void approveRegistrationRequest(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/registration/register/" + id;
		HttpResponse response = getRequest(url);
		
		System.out.println("Approving registration for id : " + id);
		System.out.println(getResponseData(response));
	}
	
	/* GET - http://{host}/elibraryws/registration/decline/{registrationRequestId} */
	public void declineRegistrationRequest(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/registration/decline/" + id;
		HttpResponse response = getRequest(url);
		
		System.out.println("Declining registration for id : " + id);
		System.out.println(getResponseData(response));
	}
	
	/** User Resource **/
	/*******************/
	/*  GET- http://{host}/elibraryws/ users/{userId} */
	public User getUser(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/users/" + id;
		HttpResponse response = getRequest(url);
		
		return gson.fromJson(getResponseData(response), User.class);
	}

	/* GET- http://{host}/elibraryws/ users/login/{username} */
	public User getUser(String login) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/users/" + login;
		HttpResponse response = getRequest(url);
		
		return gson.fromJson(getResponseData(response), User.class);
	}

	/* POST - http://{host}/elibraryws/users */
	public void createUser(User user) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/users";
		HttpResponse response = createRequest(user, url);
		
		System.out.println(getResponseData(response));
	}

	/* DELETE - http://{host}/elibraryws/users/{userId} */
	public void removeUser(int id) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/users/" + id;
		HttpResponse response = removeRequest(url);
		
		System.out.println("Removing User with id : " + id);
		System.out.println(getResponseData(response));
	}

	/* PUT - http://{host}/elibraryws/users */
	public void updateUser(int id, User user) throws Exception {
		
		String url            = "http://" + host + "/elibraryws/users";
		HttpResponse response = updateRequest(user, url);
		
		System.out.println(getResponseData(response));
	}

	/* GET - http://{host}/elibraryws/users/get-filtered-data-count  */
	public int getUsersCount() throws Exception {
		
		String url            = "http://" + host + "/elibraryws/users/get-filtered-data-count";
		HttpGet request       = new HttpGet(url);
		HttpResponse response = httpclient.execute(request);
	
		return Integer.valueOf(getResponseData(response));
	}

	/* GET - http://{host}/elibraryws/users/get-data/{offset}/{limit}/{sort_by}/{sort_dir} */
	@SuppressWarnings("unchecked")
	public List<User> getUsers(String filter, int offset, int limit, 
			String sort_by, String sort_dir) throws Exception {

		String url           = "http://" + host + "/elibraryws/users/get-data" +
						"/" + offset + "/" + limit + "/" + sort_by + "/" + sort_dir;
		
		HttpPost request     = new HttpPost(url);
		StringEntity payload = new StringEntity(filter);
		
		request.setHeader("Content-type", "application/json");
		request.setEntity(payload);
		HttpResponse response = httpclient.execute(request);
		
		return gson.fromJson(getResponseData(response), List.class);
	}
}

class DateDeserializer implements JsonDeserializer<Date> {

	public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		long l = element.getAsLong();
			return new Date(l);

	}
}
