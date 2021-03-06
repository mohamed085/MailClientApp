package Model;

public class EmailAccountFactory implements EmailAccountAbstractFactory<EmailAccountBean>{
    private int emailAccountID;
    private String emailAddress;
    private String emailPassword;
    private String type;
    private int UserID;

    public EmailAccountFactory(String emailAddress, String emailPassword, String type) {
        this.emailAddress = emailAddress;
        this.emailPassword = emailPassword;
        this.type = type;
        System.out.println("EmailAccountFactory");
    }

    @Override
    public String toString() {
        return "EmailAccountFactory{" +
                "emailAddress='" + emailAddress + '\'' +
                ", emailPassword='" + emailPassword + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public EmailAccountBean createEmailAccount() {
        if (type.equalsIgnoreCase("gmail")){
            System.out.println("Create Gmail Account");
            return new GmailAccount(emailAddress,emailPassword);
        }
        else if(type.equalsIgnoreCase("outlook"))
            return new OutlookAccount(emailAddress,emailPassword);
        else
            return null;
    }
}
