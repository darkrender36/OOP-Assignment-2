package classes;

public class Participant {
	
	String names;
	int shareNumber;
	
	public Participant(String names, int shareNumber) {
		this.names = names;
		this.shareNumber = shareNumber;
	}
	
	public void updateNumber(int shareNumber) {
		this.shareNumber = shareNumber;
	}
	
}
