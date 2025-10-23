import java.util.InputMismatchException;
import java.util.Scanner;


public class Main 
{
	Scanner s = new Scanner(System.in);
	FriendsList F = new FriendsList();
	

	//menu for calling other methods
	public void displayMenu() 
	{

	int choice = 0;
		
		while (choice != 6)
		{
			delay(1500);
			information();
			System.out.println("");
			choice = menuChoices();
		   
			switch (choice) 
			{						
				case 1:
					System.out.println();
					profile.editProfileMenu();
					break;

				case 2:
					System.out.println();
					F.recommendedFriends();
					System.out.println();
					F.addFriend();
					break;
				
				case 3:
					System.out.println();
					System.out.println("Your friends list:");
					F.displayFriends(profile.userID);
					F.filterMenu();
					break;
					
				case 4:
					System.out.println();
					F.viewProfile();
					break;

				case 5:
					Main m = new Main();
					System.out.println();
					System.out.println("You have logged out");
					m.test();


				case 6:
					System.out.println();
					System.out.println("You have exited");
					break;

				default:
					System.out.println("HOW DID YOU GET HERE");
			} 
		}
	} 
		
	public void information()
	{
		System.out.println();
		System.out.println("What would you like to do ");
		System.out.println("1. EDIT PROFILE");
		System.out.println("2. ADD FRIEND");
		System.out.println("3. DISPLAY FRIENDS LIST");
		System.out.println("4. View profile");
		System.out.println("5. LogOut");
		System.out.println("6. Exit");
		System.out.print("Enter your choice: ");
		System.out.println();
		//friends of friends
		//add friends of friends
	}
	
	public int menuChoices()
	{
		int choice = 0;
	
		while (true)
		{
			try
			{
				choice = s.nextInt();
	
				if (choice >= 1 && choice <= 7)
				{
					return choice;			   
				}
				
				else
				{
					System.out.println("Please enter a valid number");
					System.out.println();
				}
			}
	
			catch(InputMismatchException e)
			{
				s.next();
				System.out.println("Please enter a number");
				System.out.println();
			}  
		}
	}

	public void delay(int num)
	{
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void test()
	{
		boolean run = false;
		Main M = new Main();

		// Reading file
		fileHanding R = new fileHanding();
		R.readLineFromAFile();

	   // System.out.println(profile.getIDSet());

		// Welcome Message
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("Welcome to Zonke");
		delay(500);
		
		// Run login method
		login log = new login();
		delay(500);
		run = log.existingUser();

		if (run == true)
		{
			M.displayMenu();
		}


		
	}

    public static void main(String[] args) {
        Main m = new Main();
		m.test();
    }


}