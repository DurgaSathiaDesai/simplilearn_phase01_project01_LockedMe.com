package LockedMe.com.BLogic;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validations 
{
	public static boolean validateDirPath(String dirPath)
	{
		try
		{
			Path path = Paths.get(dirPath);
	        boolean isDir = Files.isDirectory(path);
	        return isDir;
		}
		catch(InvalidPathException e)
		{
			return false;
		}
		
	}
}
