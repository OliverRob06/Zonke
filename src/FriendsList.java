import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FriendsList
{ 
	Scanner s = new Scanner(System.in);

	fileHanding H = new fileHanding();
	
	//Adds a new friend to your friends list
	public void addFriend()
	{
		System.out.println("Enter the username of the person you want to add");
		String username = s.nextLine();
	
		//Checks if the user is trying to add themselves
		while (username.equals(profile.getProfilePos(profile.userID, 1)))
		{
			System.out.println("You can't add yourself as a friend");
			System.out.println("Enter the username of the person you want to add");
			username = s.nextLine();
		}
		
		parseIntoFriendsSet(profile.userID);

		// searchs for the user 
		if (checkForItem(username, 1))
		{
			int IDToAdd;

			//gets the ID of the user and sets it to IDToAdd
			String StringID = profile.getProfilePos(profile.getID(username), 0);
			IDToAdd = Integer.parseInt(StringID);

			// Add to friendsSet
			profile.friendsSet.add(IDToAdd); 
		}
		// Error handling
		else
		{
			System.out.println("User not found");
		}

		parseOutFriendsSet();
	}

	//gets the friends list from the profileSet and puts it into the friendsSset
	public void parseIntoFriendsSet(int profileID) 
	{
		// clears the tempory friend set
		profile.friendsSet.clear();

		//sets currentFriendsString to the list of your friends IDs
		String currentFriendsString = profile.getProfilePos(profileID, 6);

		//removes the brackets from the string
		String cleaned = currentFriendsString.substring(1, currentFriendsString.length() - 1); 

		// splits the string into an array of strings
		String[] stringArray = cleaned.split("\\.");

		//checks if the string is empty and if it is not empty it adds the ID to the friendsSet
		for (String element : stringArray) 
		{
			try {
				if (!element.isEmpty()) 
				{ // Check for empty strings
					int number = Integer.parseInt(element); 
					profile.friendsSet.add(number); 
				}
			} 
			// Error handling
			catch (NumberFormatException e) 
			{
				System.out.println("Skipping invalid input: " + element); // Skip non-numeric values
			}
		}
	}
	
	//gets the friends list from the profileSet and puts it into the friendsFriendsSset
	public void parseIntoFriendsFriendsSet(int profileID) 
	{
		// clears the tempory friend set
		profile.friendsFriendsSet.clear();

		//sets currentFriendsString to the list of your friends IDs
		String currentFriendsString = profile.getProfilePos(profileID, 6);

		//removes the brackets from the string
		String cleaned = currentFriendsString.substring(1, currentFriendsString.length() - 1);

		// splits the string into an array of strings
		String[] stringArray = cleaned.split("\\.");

		//checks if the string is empty and if it is not empty it adds the ID to the friendsSet
		for (String element : stringArray) 
		{
			try {
				// Check for empty strings
				if (!element.isEmpty()) 
				{ 
					int number = Integer.parseInt(element); 
					profile.friendsFriendsSet.add(number); 
				}
			} 
			// Error
			catch (NumberFormatException e) 
			{
				System.out.println("Skipping invalid input: " + element); 
			}
		}
	}
	
	//adds the friends list from the friendsSet to the profileSet
	public void parseOutFriendsSet() 
	{
		//creates an int array of the friends list
		Integer[] intIDArray = profile.friendsSet.toArray(new Integer[0]);
		//creates an array of strings
		String[] IDArray = new String[intIDArray.length];
			
		//loops through the int array and converts it to IDArray
		for (int index = 0; index < intIDArray.length; index++) 
		{
			IDArray[index] = Integer.toString(intIDArray[index]);
		}
		// joins the array of strings into one string
		String str = "[" + String.join(".", IDArray) + "]";

		//sets str to the friends list in the profileSet
		profile.setAnyPosition(6, str);		
	}

	// checks if item is in the profile
	public boolean checkForItem(String item, int position)
	{
		boolean found = false;
		// for the amount of profiles in IDSet check if item is equal to that in the profile
		for (int i = 0; i < profile.IDSet.size(); i++) {
			if (item.equals(profile.getProfilePos(i, position)))
			{
				found = true;
				return found;
			} 
		}
		return false;
	}

	//displays the friends list of a user
	public void displayFriends(int profileID)
	{
		parseIntoFriendsSet(profileID);
		//loops through the friends list and prints the names of the friends
		for (int friends : profile.friendsSet) 
		{
			System.out.print(profile.getProfilePos(friends,1)+ ", ");
		}
	}

	//menu to choose for what to filter by (workplace or hometown)
	public void filterMenu() {

		int choice = 0; 
		
		while (choice != 3)
		{
			// this displays the menu options 
			System.out.println();
			System.out.println("Enter a number to filter by ");
			System.out.println("1) Home town");
			System.out.println("2) Work place");
			System.out.println("3) Exit Menu");

		
			try
			{
				// gets the users menu choice and stores it in a variable
				choice = s.nextInt(); 
				System.out.println();

				// filters by hometown
				if (choice == 1)
				{
					// clears inputs
					s.nextLine();
					
					// user input to filter by hometown
					System.out.println("Please enter the name of the hometown you want to filter by: ");
					String userFiler = s.nextLine();
					System.out.println();

					// calls the hometown filter method
					HTfilterFriends(userFiler);

				// filters by workplace		
				} else if (choice == 2) {

					// clears inputs
					s.nextLine();

					// user input to filter by workplace
					System.out.println("Please enter the name of the workplace you want to filter by: ");
					String userFiler = s.nextLine();
					System.out.println();

					// calls the hometown filter method 
					WPfilterFriends(userFiler); 
				}
				// exit loop
				else if (choice == 3) {
					System.out.println("You have exited...");
					System.out.println();
				}

				// input validation
				else {
					System.out.println("Please enter a valid number");
					System.out.println();
				}
			}
			// Error handling
			catch(InputMismatchException e)
			{
				s.next();
				System.out.println("Please enter a number");
				System.out.println();
			}
		}
	}

	// Workplace filter method
	public void WPfilterFriends(String workplaceFilter)
	{
		// get friends list
		parseIntoFriendsSet(profile.userID);

		// initilises tempory set to store the names of found friends
		Set<String> friendName = new LinkedHashSet<>(); 

		// for each friend in the friends set add if the friend has the same workplace as entered
		for (int i : profile.friendsSet)
		{
			if (profile.getProfilePos(i, 5).equals(workplaceFilter)) {
				
				friendName.add(profile.getProfilePos(i, 1));
			}
		}
		
		// check if there are friends the tempory set
		if (friendName.size() == 0){
			System.out.println("There are no people with the workplace: " + workplaceFilter + " in your friends list");

		} else {
			// display tempory friends set
			System.out.println("All of the people with the workplace " + workplaceFilter + " that are in your friends list are: ");
			System.out.println(friendName);
		}
		
		// the temp set is then cleared to eliminate errors 
		friendName.clear();
	}

	// Hometsown filter method
	public void HTfilterFriends(String homeTown)
	{
		// get friends list
		parseIntoFriendsSet(profile.userID);

		// initilises variable to store the names of found friends
		Set<String> friendName = new LinkedHashSet<>(); 

		// for each friend in the friends set add if the friend has the same hometown as entered
		for (int i : profile.friendsSet)
		{
			if (profile.getProfilePos(i, 4).equals(homeTown)) {
				
				friendName.add(profile.getProfilePos(i, 1));
			}
		}
		
		// check if there are friends the tempory set
		if (friendName.size() == 0){
			System.out.println("There are no people with the hometown: " + homeTown + " in your friends list.");

		} else {
			//display tempory friends set
			System.out.println("All of the people with the hometown: " + homeTown + " that are in your friends list are: ");
			System.out.println(friendName);
		}
		
		// the temp set is then cleared to eliminate errors
		friendName.clear();
	}

	// finds friends to recommend that have the same hometown or workplace as you
	public void recommendedFriends() {
		// parses in the friends set
		parseIntoFriendsSet(profile.userID);
		
		// initilises variable to store the names of found friends

		// friend = [[username, connecitno], [user2, connection]....]

		Set<List<String>> connectionsSet = new HashSet<>();
		
		for (int i = 0; i < profile.IDSet.size(); i++) {
            // Skip if it's the current user's profile or one of their friends
            if (i == profile.userID || profile.friendsSet.contains(i)) {
                continue;
            }

            // Get user details
            String userName = profile.getProfilePos(i, 1);
			String workplace = profile.getProfilePos(i, 4);
            String hometown = profile.getProfilePos(i, 5);
            String currentUserWorkplace = profile.getProfilePos(profile.userID, 4);
            String currentUserHometown = profile.getProfilePos(profile.userID, 5);

            // Check workplace or hometown compatibility if it is add it to the connection set
            if (workplace.equals(currentUserWorkplace)) {
                connectionsSet.add(Arrays.asList(userName, workplace));
            } else if (hometown.equals(currentUserHometown)) {
                connectionsSet.add(Arrays.asList(userName, hometown));
            }

		}


		if (connectionsSet.size() == 0)
		{
			System.out.println("There are no people to recommend.");

		} else {
			System.out.println();
			System.out.println("We recommend you add: ");
			// System.out.println();
			for (List<String> connection : connectionsSet) {
				System.out.println(connection.get(0) + " - " + connection.get(1));
			}
		}

		// clears the sets after use to eliminate errors
		connectionsSet.clear();		
	}

	//Displays the common friends of the user and on of their friends
	public void friendsOfFriends()
	{
		System.out.println("Enter the username of the person you want to check mutual friends");
		String username = s.nextLine();
		
		//Checks if the user is trying to check common friends with themselves
		while (username.equals(profile.getProfilePos(profile.userID, 1)))
		{
			System.out.println("You can't check what for mutual friends with yourself");
			System.out.println("Enter the username of the person you want to check mutual friends");
			username = s.nextLine();
		}
		
		//Checks if the user is trying to check common friends with someone not in their friends list
		while (!profile.friendsSet.contains(profile.getID(username)))
		{
			System.out.println("You must enter a username of someone in your friends list");
			System.out.println("Enter the username of one of your friends");
			username = s.nextLine();
		}
		
		parseIntoFriendsSet(profile.userID);
		parseIntoFriendsFriendsSet(profile.getID(username));


		//Creates a copy of the friends list and removes all the friends that are not mutual friends
		Set<Integer> copySet = new LinkedHashSet<>(profile.friendsSet);
		copySet.retainAll(profile.friendsFriendsSet);
		
		System.out.println(copySet);
		
		for (int friends : copySet)
		{
			System.out.println(profile.getProfilePos(friends, 1));
		}
		copySet.clear();

	}

	//crashes when username is not found
	public void viewProfile()
	{
		String ans;

		login log = new login();


		while(true)
		{
			System.out.println("Enter the username of the profile you want to view");
			ans = s.nextLine();
			ans = log.inputValidation(ans);
			
			if (checkForItem(ans, 1))
			{
				profile.displayOtherProfile(profile.getID(ans));
				System.out.println();
				break;
			}

			else{
				System.out.println("User does not exist");
			}
		}	
	}

	//adds a friend of a friend to your friends list
	public void addFriendsOfFriends(int profileID) 
	{
		parseIntoFriendsSet(profileID);
		System.out.println("Enter the username of one of your friends");
		String username = s.nextLine();
		//Checks if the user is trying to add themselves
		while (username.equals(profile.getProfilePos(profile.userID, 1)))
		{
			System.out.println("You can't add yourself as a friend");
			System.out.println("Enter the username of the person you want to add");
			username = s.nextLine();
		}
		//Checks if the user is trying to add someone not in their friends list
		while (!profile.friendsSet.contains(profile.getID(username)))
		{
			System.out.println("You must enter a username of someone in your friends list");
			System.out.println("Enter the username of one of your friends");
			username = s.nextLine();
		}

		login log = new login();
		username = log.inputValidation(username);

		parseIntoFriendsFriendsSet(profile.getID(username));

		//Displays the friends of the friend
		for (int friends : profile.friendsFriendsSet) 
		{
			System.out.print(profile.getProfilePos(friends,1)+ ", ");
		}

		System.out.println();
		System.out.println("Enter the username of the person you want to add");
		String username2 = s.nextLine();


		//Checks if the user is trying to add themselves
		while (username2.equals(profile.getProfilePos(profile.userID, 1)))
		{
			System.out.println("You can't add yourself as a friend");
			System.out.println("Enter the username of the person you want to add");
			username2 = s.nextLine();
		}
		//Checks if the user is trying to add someone not in their friends friends list
		while (!profile.friendsFriendsSet.contains(profile.getID(username2)))
		{
			System.out.println("You must enter a username of someone in their friends list");
			System.out.println("Enter the username of the person you want to add");
			username2 = s.nextLine();
		}
		//searches for the user to add then adds then to the friends list
		if (checkForItem(username2, 1))
		{
			int IDToAdd;
			String StringID = profile.getProfilePos(profile.getID(username2), 0);
			IDToAdd = Integer.parseInt(StringID);
			profile.friendsSet.add(IDToAdd);
			System.out.println("Friend added");
		}
		else
		{
			System.out.println("User not found");
		}
			
		parseOutFriendsSet();
		
	}
}
