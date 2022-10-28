package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CommonUtils {
	
	public FileInputStream readFile(String filePath) {
		File file =    new File(filePath);
		FileInputStream read = null;
		try {
			read =  new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			 new FileNotFoundException("File not found in >>> "+ filePath);
		}
		return read;
	}

}
