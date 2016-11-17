package md.utm.fi131;

public class Message {
	public Message(String type, String command, String message) {
		this.type=type;
		this.command=command;
		this.message=message;
	}
	
	public Message() {
	}

	public String type;
	public String command;
    public String message;
    
}
