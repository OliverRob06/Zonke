public class tester 
{
    
    public static void main(String[] args) 
    {

        FriendsList F = new FriendsList();
        
        // Reading file
        fileHanding R = new fileHanding();
        R.readLineFromAFile();

        F.recommendedFriends();
        // F.filterMenu();

    }
    
}



/*
    ---------------- Filter menu --------------------

        System.out.println("Please enter 1 to filer by Home Town");
        System.out.println("or enter 2 to filer by Work Place");
        int choice = 0; 
        While (true)
        {
            try
            {
                
                choice = s.nextInt();
    
                if (choice == 1)
                {
                    System.out.println("Please enter the name of the Home Town you want to filter by");
                    String userFiler = s.nextLine();

                    F.HTfilterFriends(userFiler);       
                                
                } else if (choice == 2) {

                    System.out.println("Please enter the name of the Work Place you want to filter by");
                    String userFiler = s.nextLine();

                    F.WPfilterFriends(userFiler); 
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
     */
