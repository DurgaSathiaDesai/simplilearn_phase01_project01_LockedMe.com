package LockedMe.com.BLogic;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import LockedMe.com.UI.mainMenu;

public class fileDeletion 
{
	String dirPath = mainMenu.dirPath;
	
	public boolean deleteFiles(String fileName)
	{
		boolean fileDeleted = false;
		boolean noFiles = false;
		
		try 
		{
			File dir = new File(dirPath);
			
			// Creating a filter to return only files.
		    FileFilter fileFilter = new FileFilter()
		    {
		    	@Override
		        public boolean accept(File file) 
		    	{
		          return !file.isDirectory();
		        }
		     };

			File[] files = dir.listFiles(fileFilter);
			if(files != null && files.length > 0)
		    {
				for(File file : files)
				{
					int result =file.getName().compareTo(fileName);
					if(result == 0)
					{
						fileDeleted = Files.deleteIfExists(Paths.get(dirPath + "\\" + fileName));
						break;
					}
				}
				if(fileDeleted) 
		        {
		            System.out.println("File is deleted successfully!");
		        } 
		        else 
		        {
		            System.out.println("No such file exists in the directory!");
		        }
		    }
			else
			{
				System.out.println("Unable to perform delete operation!The root directory is empty!!");
				noFiles=true;
			}	
	    } 
		catch (IOException e)
		{
			System.out.println("An I/O error occurred while deleting the file! " + e.getMessage());
	    }
		catch (SecurityException e) 
		{
	        System.out.println("No permission to delete file. " + e.getMessage());
	    }
		catch (Exception e)
		{
			System.out.println("An error occurred while deleting the file! " + e.getMessage());
	    }
		return noFiles;
	}
}
