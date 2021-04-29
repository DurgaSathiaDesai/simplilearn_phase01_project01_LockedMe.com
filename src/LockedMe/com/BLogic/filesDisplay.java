package LockedMe.com.BLogic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import LockedMe.com.UI.mainMenu;

public class filesDisplay 
{
	String dirPath = mainMenu.dirPath;
	
	public void sortAndDisplayFiles()
	{
		try 
	    {
			Path path = Paths.get(dirPath);
			
			if (!Files.list(path).findAny().isPresent()) 
			{
				System.out.println("\nRoot Directory is empty!");
			} 
			else 
			{
				fetchAndSortFiles();
				fetchAndSortSubDir();
			}
		} 
	    catch (IOException e) 
	    {
	    	System.out.println("Error in displaying the files!" + e.getMessage());
		}
	    catch (Exception e) 
		{
	    	System.out.println("Error in displaying the files!" + e.getMessage());
		}
	}
	
	private void fetchAndSortSubDir()
	{
		 List<String> subdirs;
		 try (Stream<Path> list = Files.list(Paths.get(dirPath)))
		 {
			 subdirs = list.filter(Files::isDirectory)
					  .map(x -> x.getFileName().toString())
					  .sorted()
	                  .collect(Collectors.toList());
			 if(subdirs.isEmpty())
			 {
				 System.out.println("\nNo folders present in the root directory!");
			 }
			 else
			 {
				 System.out.println("\n\tList of folders in root directory");
				 System.out.println("\t----------------------------------");
				 
				 Iterator<String> iterator = subdirs.iterator();
			        while (iterator.hasNext()) {
			            System.out.println("\t" + iterator.next());
			        }
			 }	 
	     } 
		 catch (IOException e) 
		 {
			 System.out.println("Error in displaying the files!" + e.getMessage());
		 }
		 catch (Exception e) 
		 {
		    System.out.println("Error in displaying the files!" + e.getMessage());
		 }
	}
	
	private void fetchAndSortFiles()
	{
		 try (Stream<Path> walk = Files.walk(Paths.get(dirPath),1)) 
		 {
			 // We want to find only regular files
	         ArrayList<String> files = (ArrayList<String>) walk.filter(Files::isRegularFile)
	                               .map(x -> x.getFileName().toString())
	                               .collect(Collectors.toList());
	         if(files.isEmpty())
	         {
	        	 System.out.println("\nNo files present in the root directory!");
	         }
	         else
	         {
	        	// declaration and initialize String Array
		         String filenames[] = new String[files.size()];
		   
		         // ArrayList to Array Conversion
		         for (int count = 0; count < files.size(); count++) {
		   
		             // Assign each value to String array
		        	 filenames[count] = files.get(count);
		         }
		         String[] sortedArray = mergeSortStr(filenames);
		         System.out.println("\n\tList of files in root directory");
				 System.out.println("\t--------------------------------");
		         for (int i = 0; i < sortedArray.length; i++) 
		         {
		        	 System.out.println("\t" +sortedArray[i] + " ");
		         }
	         } 
	     } 
		 catch (IOException e)
		 {
			 System.out.println("Error in displaying the files!" + e.getMessage());
	     }
		 catch (Exception e) 
		 {
		    System.out.println("Error in displaying the files!" + e.getMessage());
		 }
	}
	
	private static String[] mergeSortStr(String[] list) 
	{
        String [] sorted = new String[list.length];
        if (list.length == 1) {
            sorted = list;
        } else {
            int mid = list.length/2;
            String[] left = null; 
            String[] right = null;
            if ((list.length % 2) == 0) {
                left = new String[list.length/2];
                right = new String[list.length/2];
            } else { 
                left = new String[list.length/2];
                right = new String[(list.length/2)+1];
            }
            int x=0;
            int y=0;
            for ( ; x < mid; x++) {
                left[x] = list[x];
            }
            for ( ; x < list.length; x++) {
                right[y++] = list[x];
            }
            left = mergeSortStr(left);
            right = mergeSortStr(right);
            sorted = mergeArray(left,right);
        }

        return sorted;
    }

    private static String[] mergeArray(String[] left, String[] right) 
    {
        String[] merged = new String[left.length+right.length];
        int lIndex = 0;
        int rIndex = 0;
        int mIndex = 0;
        int comp = 0;
        while (lIndex < left.length || rIndex < right.length) {
            if (lIndex == left.length) {
                merged[mIndex++] = right[rIndex++];
            } else if (rIndex == right.length) {
                merged[mIndex++] = left[lIndex++];
            } else {  
                comp = left[lIndex].compareTo(right[rIndex]);
                if (comp > 0) {
                    merged[mIndex++] = right[rIndex++];
                } else if (comp < 0) {
                    merged[mIndex++] = left[lIndex++];
                } else { 
                    merged[mIndex++] = left[lIndex++];
                }
            }   
        }
        return merged;
    }
	
}
