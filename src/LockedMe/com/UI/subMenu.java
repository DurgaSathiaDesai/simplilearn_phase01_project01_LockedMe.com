package LockedMe.com.UI;

import java.util.Scanner;
import LockedMe.com.BLogic.fileCreation;
import LockedMe.com.BLogic.fileDeletion;
import LockedMe.com.BLogic.fileSearch;

public class subMenu 
{
	boolean exitSubMenu;
	Scanner input;
	
	public void runSubMenu()
	{
		while(!exitSubMenu)
		{
			printSubMenu();
			int option = getSubInputOption();
			doSubOperations(option);
		}
	}
	
	private void printSubMenu()
	{
		   System.out.println("\n\t\tSub Operations");
		   System.out.println("\t\t---------------");
		   System.out.println("\t\t1. Add a file");
		   System.out.println("\t\t2. Delete a file");
		   System.out.println("\t\t3. Search a file");
		   System.out.println("\t\t4. Back to Main Operations");
	}
	
	private int getSubInputOption()
	{
		int choice = 0;
		String inputOption = null;
		   
		input = new Scanner(System.in);
		do
		{
			System.out.print("\nPlease enter your option : ");
			inputOption = input.nextLine();
			inputOption = inputOption.trim();
			choice = inputValidations.validateInputMenuOptions(inputOption,4);
		}while((choice<1) || (choice>4));
		   
		return choice;
	}
	
	private void doSubOperations(int option)
	{
		switch(option)
		{
		   case 1:
			   callAddFiles();
			   break;
		   case 2:
			   callDeleteFiles();
			   break;
		   case 3:
			   callSearchFiles();
			   break;
		   case 4:
			   exitSubMenu = true;
			   break;
		 }
	}
	
	private void callAddFiles()
	{
		boolean fileAdd = false;
		boolean continueAdd;
		String opt;
		
		fileCreation fcObj = new fileCreation();
		while(fileAdd == false)
		{
			System.out.print("Please enter the name of the file you want to add : ");
			String fileName = input.nextLine();
			fileName = fileName.trim();
			boolean isNameEmpty = inputValidations.isInputEmpty(fileName);
			if(isNameEmpty==true)
			{
				System.out.println("No filename entered.Please try again!");
			}
			else
			{
				fcObj.addFiles(fileName);
				continueAdd = true;
				while(continueAdd==true)
				{
					System.out.print("Do you want to add another file? Enter Yes or No :");
					opt = input.nextLine();
					opt = opt.trim();
					boolean isOptionEmpty = inputValidations.isInputEmpty(opt);
					
					if(isOptionEmpty==true)
					{
						System.out.println("No Input entered!!");
					}
					else
					{
						boolean isuserOptionValid = inputValidations.validateYesOrNoOption(opt);
						if(isuserOptionValid==true)
						{
							if(opt.equalsIgnoreCase("yes"))
							{
								fileAdd = false;
								continueAdd = false;
							}
							else
							{
								fileAdd = true;
								continueAdd = false;
							}
						}
					}
				}
			}
		}
	}
	
	private void callDeleteFiles()
	{
		boolean fileDelete = false;
		boolean continueDeletion;
		boolean deleteResult;
		String opt;
		
		fileDeletion fdObj = new fileDeletion();
		while(fileDelete == false)
		{
			System.out.print("Please enter the name of the file you want to delete : ");
			String fileName = input.nextLine();
			fileName = fileName.trim();
			boolean isNameEmpty = inputValidations.isInputEmpty(fileName);
			
			if(isNameEmpty==true)
			{
				System.out.println("No filename entered.Please try again!");
			}
			else
			{
				deleteResult = fdObj.deleteFiles(fileName);
				if(deleteResult == false)
				{
					continueDeletion = true;
					while(continueDeletion==true)
					{
						System.out.print("Do you want to delete any other file? Enter Yes or No :");
						opt = input.nextLine();
						opt = opt.trim();
						boolean isOptionEmpty = inputValidations.isInputEmpty(opt);
						
						if(isOptionEmpty==true)
						{
							System.out.println("No Input entered!!");
						}
						else
						{
							boolean isuserOptionValid = inputValidations.validateYesOrNoOption(opt);
							if(isuserOptionValid==true)
							{
								if(opt.equalsIgnoreCase("yes"))
								{
									fileDelete = false;
									continueDeletion = false;
								}
								else
								{
									fileDelete = true;
									continueDeletion = false;
								}
							}
						}
					}
				}
				else
				{
					fileDelete = true;
				}		
			}
		}
	}
	
	private void callSearchFiles()
	{
		boolean fileSearch = false;
		String fileName;
		String opt;
		boolean continueSearch;
		boolean searchResult;
		
		fileSearch fsObj = new fileSearch();
		
		while(fileSearch == false)
		{
			System.out.print("Please enter the name of the file you want to search : ");
		    fileName = input.nextLine();
		    fileName = fileName.trim();
		    boolean isNameEmpty = inputValidations.isInputEmpty(fileName);
			if(isNameEmpty==true)
			{
				System.out.println("No filename entered.Please try again!");
			}
			else
			{
				searchResult = fsObj.searchFiles(fileName);
				if(searchResult == false)
				{
					continueSearch = true;
					while(continueSearch==true)
					{
						System.out.print("Do you want to search any other file? Enter Yes or No :");
						opt = input.nextLine();
						opt = opt.trim();
						boolean isOptionEmpty = inputValidations.isInputEmpty(opt);
						
						if(isOptionEmpty==true)
						{
							System.out.println("No Input entered!!");
						}
						else
						{
							boolean isuserOptionValid = inputValidations.validateYesOrNoOption(opt);
							if(isuserOptionValid==true)
							{
								if(opt.equalsIgnoreCase("yes"))
								{
									fileSearch = false;
									continueSearch = false;
								}
								else
								{
									fileSearch = true;
									continueSearch = false;
								}
							}
						}
					}
				}
				else
				{
					fileSearch = true;
				}
			}
		}
	}
}
