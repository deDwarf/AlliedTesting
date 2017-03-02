package REST.Structures;

import java.util.Date;

public class Author {

	int    id;
	String fullName;
	Date   dateOfBirth;
	String description;

	public Author(String fullName, Date dateOfBirth) {

		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;

		this.description = "Default author description";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
