package REST.Structures;

import java.util.Date;
import java.util.List;

public class Book {

	private int           id;
	private String        title;
	private String        language;
	private Category      category;
	private Date          yearPublished;
	private String        summary;
	private boolean       active;
	private int           numberOfItems;
	private boolean       availability;
	private String        cover;
	private List<Comment> comments;
	private List<Author>  authors;

	public Book(){}
	public Book(String title, String language, Category category,
				Date yearPublished, int numberOfItems, List<Author> authors) {

		this.title = title;
		this.language = language;
		this.category = category;
		this.yearPublished = yearPublished;
		this.numberOfItems = numberOfItems;
		this.authors = authors;

		this.cover = null;
		this.summary = "Default Summary";
		this.availability = false;
		this.active = true;
		this.comments = null;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Date getYearPublished() {
		return yearPublished;
	}
	public void setYearPublished(Date yearPublished) {
		this.yearPublished = yearPublished;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getNumberOfItems() {
		return numberOfItems;
	}
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	
	
}


