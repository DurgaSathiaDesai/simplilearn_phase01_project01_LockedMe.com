package LockedMe.com.UI;

public class inputValidations 
{
	public static int validateInputMenuOptions(String inputOption,int numOfOptions)
	{
		int choice = 0;
		boolean isOptEmpty = isInputEmpty(inputOption);
		
		if(isOptEmpty==false)
		{
			try
			{
				choice = Integer.parseInt(inputOption);
				
				if(choice<1 || choice>numOfOptions)
				{
					System.out.println("Invalid option selection.Please try again.");
					//printMenu();
				}
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Invalid option selection.Please try again.");
				//printMenu();
			}
		}
		else
		{
			System.out.println("No option entered.Please try again!");
		}
		return choice;	
	}
	
	public static boolean isInputEmpty(String input)
	{
		if(input == null || input.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean validateYesOrNoOption(String userOption)
	{
		//String optTrim = userOption.trim();
		if(userOption.equalsIgnoreCase("yes") || userOption.equalsIgnoreCase("No"))
		{
			return true;
		}
		else
		{
			System.out.println("Invalid Input!");
			return false;
		}
	}
}
