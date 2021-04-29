package LockedMe.com.UI;

import java.util.Scanner;

import LockedMe.com.BLogic.Validations;
import LockedMe.com.BLogic.filesDisplay;

public class mainMenu 
{
	public static String dirPath;
	boolean exitMenu;
	Scanner input;
	  
	public static void main(String[] args) 
	{
		welcomeScreen.printScreen();
		
		mainMenu menuObjref = new mainMenu();
		menuObjref.runMenu();
	}
	
	public void runMenu()
	{
		while(!exitMenu)
		{
			printMenu();
			int option = getInputOption();
			if(option!=3)
				getDirectoryPath();
			doOperations(option);
		}
	}
	
	// method to print Main_menu
	private void printMenu()
	{
		System.out.println("\nFile Management Operations");
		System.out.println("----------------------------");
		System.out.println("1. Display files and folders");
		System.out.println("2. File operations(Add/Search/Delete)");
		System.out.println("3. Exit Application");
	}
	
	//method to get Main_menu input option from user
	private int getInputOption()
	{
		int choice = 0;
		String inputOption = null;
		   
		input = new Scanner(System.in);
		do
		{
			System.out.print("\nPlease enter your option : ");
			inputOption = input.nextLine();
			inputOption = inputOption.trim();
			choice = inputValidations.validateInputMenuOptions(inputOption,3);
		}while((choice<1) || (choice>3));
		   
		return choice;
	}
	
	private void getDirectoryPathFromUser()
	{
		boolean isValid = false;
		String dirPathFromUser=null;
		while(isValid==false)
		{
			System.out.print("\nPlease enter the directory path in which you want to perform operations : ");
			dirPathFromUser = input.nextLine();
			dirPathFromUser = dirPathFromUser.trim();
			boolean isDirPathEmpty = inputValidations.isInputEmpty(dirPathFromUser);
			
			if(isDirPathEmpty==true)
			{
				System.out.println("No Path entered.Please try again!");
			}
			else
			{
				isValid = Validations.validateDirPath(dirPathFromUser);
				if(isValid==false)
				{
					System.out.println("Invalid Directory Path!!");
					
				}
				else 
				{
					dirPath=dirPathFromUser;
				}
			}
		}
	}
	
	private void getDirectoryPath()
	{
		String userOption;
		boolean continueOp = false;
		if(dirPath!=null)
		{
			System.out.println("Currently you are performing operations in " + dirPath);
			while(continueOp==false)
			{
				System.out.print("Do you want to change the directory path?Enter yes or No :");
				userOption = input.nextLine();
				userOption = userOption.trim();
				boolean isOptionEmpty = inputValidations.isInputEmpty(userOption);
				
				if(isOptionEmpty==true)
				{
					System.out.println("No Input entered!!");
				}
				else
				{
					boolean isuserOptionValid = inputValidations.validateYesOrNoOption(userOption);
					if(isuserOptionValid==true)
					{
						if(userOption.equalsIgnoreCase("yes"))
						{
							getDirectoryPathFromUser();
							
						}
						continueOp=true;
					}
					else
					{
						continueOp = false;
					}
			    }
			}	
		}
		else
		{
			getDirectoryPathFromUser();
		}
	}
	
	private void doOperations(int option)
	{
		switch(option)
		{
			case 1:
				callSortAndDisplayFiles();
			    break;
		    case 2:
			    callSubMenu();
			    break;
		    case 3:
			    exitMenu = true;
			    System.out.println("Thank you for using our Application");
			    input.close();
			    break;
		}
	}
	
	private void callSubMenu()
	{
		subMenu sObj = new subMenu();
		sObj.runSubMenu();
	}
	
	private void callSortAndDisplayFiles()
	{
		filesDisplay fObj = new filesDisplay();
		fObj.sortAndDisplayFiles();	   
	}
	   
}

