package Model;

import View.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.text.SimpleDateFormat;


public class EmailFolderBean<T> extends TreeItem<String> {

	private boolean topElement  = false;
	private int unreadMessageCount;
	private String name;
	private String completeFolderName;
	private ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();

	
	public EmailFolderBean(String value){
		super(value, ViewFactory.defaultFactory.resolveIcon(value));
		this.name = value;
		this.completeFolderName = value;
		data = null;
		topElement = true;
		this.setExpanded(true);
	}
	
	public EmailFolderBean(String value, String completeFolderName){
		super(value, ViewFactory.defaultFactory.resolveIcon(value));
		this.name = value;
		this.completeFolderName = completeFolderName;
	}
	
	private void updateValue(){
		if(unreadMessageCount > 0){
			this.setValue((String)(name + "(" + unreadMessageCount + ")"));
		}else{
			this.setValue(name);
		}
	}
	
	public void incrementUnreadMessagesCount(int newMessages){
		unreadMessageCount = unreadMessageCount + newMessages;
		updateValue();
	}
	
	public void decrementUnreadMessagesCount(){
		unreadMessageCount--;
		updateValue();
	}
	
	public void addEmail(int index, Message message) throws MessagingException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy , hh.mm aa");
		boolean isRead = message.getFlags().contains(Flags.Flag.SEEN);
		EmailMessageBean emailMessageBean = new EmailMessageBean(
				message.getSubject(),
				message.getFrom()[0].toString(),
				dateFormat.format(message.getReceivedDate()) ,
				message.getSize(),
				isRead,
//				message.hasAttachment(),
				false,
				message);
		if (index < 0){
			data.add(emailMessageBean);
		}
		else {
			data.add(index,emailMessageBean);
		}
		if (!isRead)
			incrementUnreadMessagesCount(1);
	}
	
	public boolean isTopElement(){
		return topElement;
	}

	public Boolean getTopElement() {
		return topElement;
	}

	public int getUnreadMessageCount() {
		return unreadMessageCount;
	}

	public String getCompleteFolderName() {
		return completeFolderName;
	}

	public ObservableList<EmailMessageBean> getData() {
		return data;
	}
}
