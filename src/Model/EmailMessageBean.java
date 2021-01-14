package Model;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class EmailMessageBean extends AbstractTableItem{
	
	public static Map<String, Integer> formattedValues = new HashMap<String, Integer>();
	
	private SimpleStringProperty from;
	private SimpleStringProperty subject;
	private SimpleStringProperty size;
	private String content;
	
	public EmailMessageBean(String Subject, String from, int size, String Content, boolean isRead){
		super(isRead);
		this.subject = new SimpleStringProperty(Subject);
		this.from = new SimpleStringProperty(from);
		this.size = new SimpleStringProperty(formatSize(size));
		this.content = Content;
	}
	
	public String getFrom(){
		return from.get();
	}
	public String getSubject(){
		return subject.get();
	}
	public String getSize(){
		return size.get();
	}
	public String getContent(){
		return content;
	}
	
	private String formatSize(int size){
		String returnValue;
		if(size<= 0){
			returnValue =  "0";}
		
		else if(size<1024){
			returnValue = size + " B";
		}
		else if(size < 1048576){
			returnValue = size/1024 + " kB";
		}else{
			returnValue = size/1048576 + " MB";
		}
		formattedValues.put(returnValue, size);
		return returnValue;
		
	}

	@Override
	public String toString() {
		return "EmailMessageBean{" +
				"sender=" + from +
				", subject=" + subject +
				", size=" + size +
				", content='" + content + '\'' +
				'}';
	}
}
