package wpi.xojo.g2.project.http;

public class AddAlternativeRequest {
	public String choiceID;
	public String alternativeDesc;
	
	public AddAlternativeRequest(String choiceID, String desc) {
		this.choiceID = choiceID;
		this.alternativeDesc = desc;
	}
	
	public AddAlternativeRequest() {
	}
}
