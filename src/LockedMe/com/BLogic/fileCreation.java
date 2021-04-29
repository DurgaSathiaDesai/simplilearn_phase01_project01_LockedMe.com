package LockedMe.com.BLogic;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import LockedMe.com.UI.mainMenu;

public class fileCreation 
{
	String dirPath = mainMenu.dirPath;
	
	public void addFiles(String fileName)
	{
		 Path filePath = Paths.get(dirPath + "\\" + fileName);
		 try 
		 {
			 
			 // Create a file at the specified file path
	         Files.createFile(filePath);
	         System.out.println("File created successfully!");
	     } 
		 catch (FileAlreadyExistsException e) 
		 {
			 System.out.println("File already exists!");
	     } 
		 catch (IOException e) 
		 {
	         System.out.println("An I/O error occurred while creating file : " + e.getMessage());
	     } 
		 catch (SecurityException e) 
		 {
	         System.out.println("No permission to create file: " + e.getMessage());
	     }
		 catch (Exception e) 
		 {
	         System.out.println("An error occurred while creating file : " + e.getMessage());
	     } 
	}
}
