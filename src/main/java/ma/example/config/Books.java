package ma.example.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Books")  
public class Books {

	private String title;  
	private String description;  
	private String year; 
	private String by; 
	private String likes;
	
	public void setTitle(String String){
		this.title = String;
	}
	public void setDescription(String String){
		this.description = String;
	}
	
	public void setLikes(String string){
		this.likes = string;
	}
	public void setYear(String String){
		this.year = String;
	}
	public void setBy(String String){
		this.by = String;
	}
	
	@XmlElement  
	public String getTitle(){
		return this.title ;
	}
	@XmlElement  
	public String getDescription(){
		return this.description;
	}
	@XmlElement  
	public String getLikes(){
		return this.likes;
	}
	@XmlElement  
	public String getYear(){
		return this.year ;
	}
	@XmlElement  
	public String getBy(){
		return this.by;
	}
}