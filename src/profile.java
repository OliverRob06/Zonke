import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

// we can keep the set tho

public class profile
{
	public static Set<Set<String>> IDSet = new LinkedHashSet<>();
	public static Set<String> profileSet = new LinkedHashSet<>();
	public static Set<Integer> friendsSet = new LinkedHashSet<>();
	public static Set<Integer> friendsFriendsSet = new LinkedHashSet<>();

	public static int userID = 0;

	static Scanner s = new Scanner(System.in);
	static FriendsList F = new FriendsList();

	// returns the profile set at position
	public static Set<String> getIDPos(int position) {
		int index = 0;
		// For each profile in IDSet
		for (Set<String> innerSet : IDSet) {
			// If index is the position print the profile
			if (index == position) {
				return innerSet;
			}
			index++;
		}
		// ErrorHandling
		throw new IndexOutOfBoundsException("Position " + position + " is out of bounds!");		
	}
		
	// displays the element at position in the profile set
	public static String getProfilePos(int idpos, int position) {
		// Get the profile from other method
		Set<String> setAtPosition = profile.getIDPos(idpos);
		int index = 0;
		// For each element in the profile
		for (String string : setAtPosition) {
			// If index is the position print the element 
			if (index == position) {
				return string;
			}
			index++;
		}
		// Error handling
		throw new IndexOutOfBoundsException("Position " + position + " is out of bounds!");
	}

	// changes the value in the set to the value specified 
	public static void setAnyPosition(int position, String change) {

		// Create new set to hold the profile set
		Set<String> profileToChange = new LinkedHashSet<>(profile.getIDPos(userID));

		// Get the element of the set to change
		String target = profile.getProfilePos(userID, position);

		// Create temporry set to change the original set to
		Set<String> updatedProfile = new LinkedHashSet<>();

		// For each element in the temp set
		for (String string : profileToChange) {
			if (string.equals(target)) {
				// Add the changed string to tempory set
				updatedProfile.add(change);
			} else {
				// Add the normal string to the set
				updatedProfile.add(string);
			}
		}

		// Create new list of sets and copy the IDSet into it
		List<Set<String>> IDSetList = new ArrayList<>(IDSet);

		// sets the position of the original set in the IDSet
		int index = IDSetList.indexOf(profileToChange);
		
		// update set at the index
		if (index !=-1) {
			IDSetList.set(index, updatedProfile);
		}

		// replace IDSet with the updated set
		IDSet.clear();
		IDSet.addAll(IDSetList);

		// clear tempory sets
		profileToChange.clear(); 
		IDSetList.clear();

	}
	
	// returns the ID of the profile from specified username
	public static int getID(String username) {
		// For the size of IDSet
		for (int i = 0; i < IDSet.size(); i++) {
			// If username is in the IDset then return the ID of that profile 
			if (getProfilePos(i,1).equals(username)) {
				return Integer.parseInt(getProfilePos(i, 0));
			}
		}
		// Error handling
		throw new IndexOutOfBoundsException("Username doesnt exit");
	}
	
	// gets the last ID and returns it
	public static int getLastID() {
		return Integer.parseInt(getProfilePos((IDSet.size()-1), 0));
	}

	// displays logged in profile messages
	public static void yourProfile(String username) {
		userID = getID(username);
		System.out.println();
		System.out.println("Welcome to your profile");
		System.out.println("Here you can find your personal information:");
		profile.displayYourProfile(userID);
	}
	
	// menu for editing specific ascepts of a users profile
	public static void editProfileMenu() {
		fileHanding R = new fileHanding();
		int menuChoice = 0;
		// menu for editing profile information
		while (menuChoice !=6) {
			System.out.println();
			profile.yourProfile(profile.getProfilePos(profile.userID, 1));
			System.out.println();

			profile.information();
			menuChoice = profile.userInput();
			System.out.println();
				
			switch (menuChoice) {
				case 1:
					System.out.println();
					System.out.println("Editing Username");
					System.out.println("Enter the new username");
					editItem(menuChoice);
					break;
	
				case 2:
					System.out.println();
					System.out.println("Editing Password");
					System.out.println("Enter the new password");
					editItem(menuChoice);
					break;

				case 3:
					System.out.println();
					System.out.println("Editing Name");
					System.out.println("Enter the new name");
					editItem(menuChoice);
					break;
	
				case 4:
					System.out.println();
					System.out.println("Editing Hometown");
					System.out.println("Enter the new hometown");
					editItem(menuChoice);
					break;
					
				case 5:
					System.out.println();
					System.out.println("Editing Workplace");
					System.out.println("Enter the new workplace");
					editItem(menuChoice);
					break;
	
				case 6:
					System.out.println();
					System.out.println("Exiting...");
					System.out.println();
					R.writeToFile();
					break;
						
				default:
					System.out.println("Error");
					break;
	
			}
		}
	}
	
	// get and returns menu input
	public static int userInput() {
		int num;
		System.out.println("Enter a number between 1-6");
		Main main = new Main();
		num = main.menuChoices();
		return num;
	}
	
	// context for menu
	public static void information() {
		System.out.println("1) Edit Username");
		System.out.println("2) Edit Password");
		System.out.println("3) Edit Name");
		System.out.println("4) Edit Hometown");
		System.out.println("5) Edit Workplace");
		System.out.println("6) Exit");
	}

	// edits item at position
	public static void editItem(int position) {
		boolean same = false;
		System.out.println();
		String userInput = s.nextLine();
		login log = new login();
		userInput = log.inputValidation(userInput);


		while (!same)
		{
		same = profile.editValidation(position, userInput);
		}


		if (same) {
			profile.setAnyPosition(position, userInput);
			System.out.println();
			displayYourProfile(profile.userID);
			System.out.println();
		}		
	}
	
	// Validation for editItem
	public static boolean editValidation(int position, String item) {
		
		if (profile.getIDPos(profile.userID).contains(item)) {
			return false;
		} else {
			return true;
		}

	}

	// Display profile with showing the ID and the password and displays the friends list as their usernames.
	public static void displayOtherProfile(int profileID) {
		System.out.println();
		System.out.print("[");
		for (int i = 1; i < 5; i++) {
			if (i == 2) {
				continue;
			}
			System.out.print(profile.getProfilePos(profileID, i));
			System.out.print(", ");
		}
		System.out.print(profile.getProfilePos(profileID, 5) + "]");
		System.out.println();
		System.out.println("Friends List:");
		F.displayFriends(profileID);
	}

	// Display your profile without the ID and displays the friends list as their names
	public static void displayYourProfile(int profileID) {
		System.out.println();
		System.out.print("[");
		for (int i = 1; i < 5; i++) {
			System.out.print(profile.getProfilePos(profileID, i));
			System.out.print(", ");
		}
		System.out.print(profile.getProfilePos(profileID, 5) + "]");
		System.out.println();
		System.out.println("Friends List:");
		F.displayFriends(profileID);
	}

}