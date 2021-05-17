package classes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	Scanner scan = new Scanner(System.in);
	ArrayList<Participant> participants = new ArrayList<>();
	ArrayList<Integer> shuffleNumber = new ArrayList<>();
	
	boolean checkParticipantName(String name) {
		for(Participant p : participants) {
			if(p.names.equals(name)) {
				System.out.print("username has already been taken");
				scan.nextLine();
				return false;
			}
		}
		
		if(name.length() < 5 || name.length() > 20) {
			return false;
		}
		
		return true;
	}
	
	void startSharing() {
		int num = 0;
		do {
			try {
				System.out.print("Input a number [0-100]: ");
				num = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				scan.nextLine();
				System.out.print("input must be a numeric");
				scan.nextLine();
			}
			
		} while (num < 0 || num > 100);
		
		String name;
		do {
			System.out.print("Could you give me your username [5 - 20 characters]? ");
			name = scan.nextLine();
		} while (!checkParticipantName(name));
		
		Participant newParticipant = new Participant(name, num);
		participants.add(newParticipant);
		
		System.out.printf("You are the %d that joins the game\n", participants.size());
		System.out.printf("You share number : %d\n", num);
	}
	
	void displayList() {
		System.out.println("+=======================================+");
		System.out.println("+ Share List                            +");
		System.out.println("+=======================================+");
		for(int i = 0; i < participants.size(); i++) {
			System.out.printf("| %-4d| %-21s| %-9d|\n", i + 1, participants.get(i).names, participants.get(i).shareNumber);
		}
		System.out.println("+=======================================+");
		
	}
	
	void updateParticipant() {
		
		if(participants.size() == 0) {
			System.out.print("There is no participants");
			scan.nextLine();
			return;
		}
		
		int idx = -1;
		displayList();
		do {
			try {
				System.out.print("Which participant you would like to update [1 - " + participants.size() + "][0 to exit]? ");
				idx = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				scan.nextLine();
				System.out.print("input must be a numeric");
				scan.nextLine();
			}
		} while (idx < 0 || idx > participants.size());
		
		if(idx == 0) {
			return;
		} else {
			int num = -1;
			do {
				try {
					System.out.print("Input a number [0-100]: ");
					num = scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					scan.nextLine();
					System.out.print("input must be a numeric");
					scan.nextLine();
				}
				
			} while (num < 0 || num > 100);
			
			participants.get(idx - 1).updateNumber(num);
			System.out.println("Update Successful!");
		}
		
	}
	
	void deleteParticipant() {
		
		if(participants.size() == 0) {
			System.out.print("There is no participants");
			scan.nextLine();
			return;
		}
		
		displayList();
		int idx = -1;
		do {
			try {
				System.out.print("Which participant you would like to delete [1 - " + participants.size() + "][0 to exit]? ");
				idx = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				scan.nextLine();
				System.out.print("input must be a numeric");
				scan.nextLine();
			}
		} while (idx < 0 || idx > participants.size());
		
		if(idx == 0) {
			return;
		} else {
			participants.remove(idx - 1);
			System.out.print("Participant successfully removed from event");
			scan.nextLine();
		}
	}
	
	void printShareList() {
		System.out.println("Here's the completed share list");
		System.out.println("Remember, sharing is caring, happy sharing :D");
		System.out.println("+==============================================+");
		System.out.println("+ Share List                                   +");
		System.out.println("+==============================================+");
		System.out.println("+ Username                 | Before  | After   +");
		System.out.println("+==============================================+");
		for(int i = 0; i < participants.size(); i++) {
			System.out.printf("| %-25s| %-8d| %-8d|\n", participants.get(i).names, participants.get(i).shareNumber, shuffleNumber.get(i));
		}
		System.out.println("+==============================================+");
	}
	
	void closeApp() {
		Collections.sort(participants, (p1, p2) -> p1.names.compareTo(p2.names));
		
		for(Participant x : participants) {
			shuffleNumber.add(x.shareNumber);
		}
		
		Collections.shuffle(shuffleNumber);
		Collections.reverse(shuffleNumber);
		printShareList();
	}
	
	void option() {
		int end = 0, choice = 0;
		do {
			do {
				System.out.println("Bj - Share");
				System.out.println("+=====================+");
				System.out.println("+Options              +");
				System.out.println("+=====================+");
				System.out.println("1. Start Sharing      +");
				System.out.println("2. Update Participant +");
				System.out.println("3. Delete Participant +");
				System.out.println("4. Close App          +");
				System.out.println("+=====================+");
				System.out.print("Choice >> ");
				choice = scan.nextInt();
				scan.nextLine();
				
				if(choice < 1 || choice > 4) {
					System.out.print("Input must between 1 and 4");
					scan.nextLine();
				}

			} while (choice < 1 || choice > 4);
			
			switch(choice) {
			case 1:
				startSharing();
				break;
				
			case 2: 
				updateParticipant();
				break;
				 
			case 3:
				deleteParticipant();
				break;
				
			case 4:
				closeApp();
				end = 1;
				break;
			}
		} while (end == 0);
	}
	
	public Main() {
		option();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
