import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

	public void displayMenu() 
	{
        int choice;
        Scanner s = new Scanner(System.in);
        do 
        {
        
            
            
            while (true)
            {
                try
                {
                    System.out.println("What would you like to do ");
                    System.out.println("1. Add an item");
                    System.out.println("2. Display all the items");
                    System.out.println("3. Search for an item");
                    System.out.println("4. Display total cost for everything in the shop");
                    System.out.println("5. Remove an item ");
                    System.out.println("6. Exit");
                    System.out.print("Enter your choice: ");
                    choice = s.nextInt();

                    if (choice >= 1 && choice <= 6)
                    {
                        break;                   
                    }
                    else
                    {
                        System.out.println("Please enter a valid number");
                    }

                }


                catch(InputMismatchException e)
                {
                    s.nextInt();
                    System.out.println("Please enter a number");
                }

                

            }
            
            switch (choice) 
            {                        
                case 1:
                    
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    System.out.println("You have exited");
                    System.out.println("");

                    break;
            }
        } 
        while (choice != 6);

        s.close();
    }




/**
 * The main method
 * 
 * @param args command line arguments
 */
public static void main(String[] args) 
{
    Main M = new Main();
    M.displayMenu();
}

}
