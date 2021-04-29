package LockedMe.com.BLogic;

import LockedMe.com.UI.mainMenu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class fileSearch 
{
	String dirPath = mainMenu.dirPath;
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' HH:mm:ss");
	
	public boolean searchFiles(String fileName)
	{
		int fileFound;
		boolean noFiles = false;
		
		try (Stream<Path> walk = Files.walk(Paths.get(dirPath),1)) 
		 {
			 //need to find only regular files
	         TreeSet<String> files = (TreeSet<String>)walk.filter(Files::isRegularFile)
	                               		.map(x -> x.getFileName().toString())                           
	                               		.collect(Collectors.toCollection(TreeSet::new));
	         
	         if(files.isEmpty())
	         {
	        	 System.out.println("Unable to perform Search!The root directory is empty!!");
	        	 noFiles = true;
	         }
	         else
	         {
	        	 // declaration and initialize String Array
	        	 String fileNames[] = new String[files.size()];
	        	 files.toArray(fileNames);
	        	 fileFound=binarySearchFiles(fileNames,fileName);
	        	 if(fileFound == -1)
	        	 {
	        		 System.out.println("File not found in root directory!");
	        	 }
	        	 else
	        	 {
	        		 System.out.println("File exists in root directory!");
	        		 displayFileAttributes(fileName);
	        	 }
	         }
	        	
		 }
		catch (IOException e)
		{
			System.out.println("An I/O error occurred while searching file! " + e.getMessage());
	    }
		catch(Exception e)
		{
			System.out.println("An error occurred while searching file! " + e.getMessage());
		}
		return noFiles;
	}
	
	private int binarySearchFiles(String[] fileNames, String fileName)
    {
        int left = 0, right = fileNames.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
  
            int res = fileName.compareTo(fileNames[middle]);
  
            // Check if filename is present at mid
            if (res == 0)
                return middle;
  
            // If filename greater, ignore left half
            if (res > 0)
            	left = middle + 1;
  
            // If filename is smaller, ignore right half
            else
            	right = middle - 1;
        }
  
        return -1;
    }
	
	private void displayFileAttributes(String fileName)
	{
		try 
		{
			File file = new File(dirPath + "\\" + fileName);
		 
			System.out.println("\tFile Name : "+ file.getName()); 
			System.out.println("\tFile Location : "+file.getAbsolutePath()); 
			System.out.println("\tFile size : " + file.length() + " Bytes"); 
			
			boolean isReadable = file.canRead();
			boolean isWritable = file.canWrite();
			if(isReadable==true&&isWritable==true)
			{
				System.out.println("\tFile is both readable and writable");
			}
			else
			{
				if(isReadable==true)
				{
					System.out.println("\tFile is only Readable");
				}
			}
			
			Path filePath = Paths.get(dirPath + "\\" + fileName);
		
			BasicFileAttributes basicFileAttributes = Files.readAttributes(filePath,BasicFileAttributes.class);
			
			//System.out.println("\tFile created on: " + basicFileAttributes.creationTime());
			FileTime fileCreationTime = basicFileAttributes.creationTime();
			FileTime fileAccessTime = basicFileAttributes.lastAccessTime();
			FileTime fileModifiedTime = basicFileAttributes.lastModifiedTime();
			System.out.println("\tFile created on: " + formatDateTime(fileCreationTime));
			System.out.println("\tFile Last Accessed on : " + formatDateTime(fileAccessTime));
			System.out.println("\tFile Last Modified on: " + formatDateTime(fileModifiedTime));
		} 
		catch (IOException e) 
		{
			System.out.println("An I/O error occurred while searching file! " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("An error occurred while searching file! " + e.getMessage());
		}
	}
	
	public static String formatDateTime(FileTime fileTime) {

        LocalDateTime localDateTime = fileTime
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return localDateTime.format(DATE_FORMATTER);
    }
}
