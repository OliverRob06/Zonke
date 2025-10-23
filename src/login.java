import java.util.Scanner;

public class login 
{  
	Main M = new Main();
	Scanner s = new Scanner(System.in);
	FriendsList fl = new FriendsList();
	String ans;
	boolean user;
	String username;
	String password;


	//Method for getting user to create or log in to an account
	public boolean existingUser()
	{
		boolean run = true;



		//getting user input
		M.delay(500);
		System.out.println("Are you an existing user?");
		M.delay(500);


		//input validation
		ans = s.nextLine();
		while (!ans.equals("y") && !ans.equals("Yes") && !ans.equals("yes") && !ans.equals("Y") && !ans.equals("N") && !ans.equals("No") && !ans.equals("n") && !ans.equals("no"))
		{
			System.out.println("Please enter yes or no");
			ans = s.nextLine();
		}

		if (ans.equals("y") || ans.equals("Yes") || ans.equals("yes") || ans.equals("Y"))
		{
			M.delay(500);
			//run login method
			run = oldUser();
			return run;
		}

		else
		{
			System.out.println("//User is creating a new account");
			M.delay(500);

			//run create new account
			newUser();
			//display 
			existingUser();
			return run;
		}
	}



	//login method
	public boolean oldUser()
	{
		while (true)
		{
			//getting user input
			System.out.println("Enter your username");
			username = s.nextLine();
			username = inputValidation(username);
			
			boolean check = false;


			//checks if username is in the database
			//returns true if it is
			check = fl.checkForItem(username, 1);


			//if username exists ask for password
			if (check == true)
			{
				while (true)
				{
					System.out.println("Enter your password");
					password = s.nextLine();
					password = inputValidation(password);
					check = fl.checkForItem(password, 2);

					//if password is correct login
					if (check == true)
					{
						System.out.println("You have successfully logged in");
						profile.yourProfile(username);
						System.out.println("");
								
						return true;
						//go to profile page
					}

					//ask user to input password again
					else
					{
						System.out.println("Password incorrect...");
						System.out.println();
					}
				}
			}

			//if username doesnt exist return to start of loop
			else
			{
				System.out.println("Username does not exist");
			}
		}
	}

	//create new account method
	public void newUser()
	{
		System.out.println("Enter the username for your new account");
		username = s.nextLine();
		username = inputValidation(username);
		
		boolean check = false;
		check = fl.checkForItem(username, 1);

		if (check == false)
		{
			while (true)
			{
				System.out.println("Create your password");
				password = s.nextLine();
				password = inputValidation(password);

				//need user to input password twice to confirm it
				System.out.println("Please re-enter your new passsword");
				ans = s.nextLine();
				ans = inputValidation(ans);
				if (password.equals(ans))
				{
					login log = new login();
					//Create new Profile for user
					//Only containing the username and password
					//User should be able to edit other fields inside the profile page
					
					log.addProfileSet(username, password);
					System.out.println("Please login to your new account");
					break;
				}
			}	
		}

		else
		{
			System.out.println("User name already exists");
			System.out.println("Please choose another one");
			newUser();
		}
	}

	//input validation for username and password inputs
	public String inputValidation(String word)
	{
		int length = word.length();
		while (length > 20 || length < 3)
		{
			System.out.println("Your input must be minimum of 3 characters and maximum of 20 characters");
			word = s.nextLine();
			length = word.length();
		}
		return word;
	}



			
	public void addProfileSet(String username, String password) 
	{
		profile.profileSet.add(String.valueOf(profile.getLastID()+1));
		profile.profileSet.add(username);
		profile.profileSet.add(password);
		profile.profileSet.add("No name");
		profile.profileSet.add("No hometown");
		profile.profileSet.add("No workplace");
		profile.profileSet.add("No friends");

		profile.IDSet.add(profile.profileSet);
		//System.out.println(profile.getIDSet());
	}
}
