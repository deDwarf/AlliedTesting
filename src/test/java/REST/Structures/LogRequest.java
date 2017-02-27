package REST.Structures;

import REST.REST;

public class LogRequest {
	
	private int    id;
	private long   timestamp;
	private String event;
	private String levelString;
	private String description;
	private String username;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getLevelString() {
		return levelString;
	}
	public void setLevelString(String levelString) {
		this.levelString = levelString;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	

}
