// TestCode.java - Test code for grades assignment
// Author: Zachary Klausner
// Date: 1/30/2017
// Class: CS165
// Email: zachklau@rams.colostate.edu

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestCode {

    public static void main(String[] args) {

        // Instantiate object
        ProcessGrades process = new ProcessGrades();

        // Check usage
        if (args.length != 2) {
            System.out.println("usages: java ProcessGrades <roster file> <grades file>");
            System.exit(-1);
        }

        // Process arguments
        String rosterName = args[0];
        String gradesName = args[1];

        // Read roster file
        ArrayList<Interface.Entry> entries = process.readRoster(rosterName);

        // Assertion for post-condition for readRoster
        // Post-condition: ArrayList contains all data from roster file
        // YOUR CODE HERE
        File rosterFile = new File(args[0]);
        long fileLength = rosterFile.length();
        long dataLength = findSize(entries);
        
        assert dataLength == fileLength: "File has " + fileLength + " bytes, arraylist has " + dataLength + "!";
        
        // Read grades file
        int numberMatched = process.readGrades(gradesName, entries);

        // Assertion for post-condition for readGrades
        // Post-condition: All students in roster are matched with grades
        // YOUR CODE HERE
        int numberStudents = entries.size();
        
        assert numberMatched == numberStudents: "Students in roster: " + numberStudents + ", grades matched: " + numberMatched;
        
        // Compute grades
        process.computeGrades(entries);

        // Assertion for post-condition for computeGrades
        // Post-condition: All grades are in the range zero to 100
        // YOUR CODE HERE
        boolean gradeStatus = checkGrades(entries);
        
        assert gradeStatus: "One or more grades are out of range!";
        
        // Write upload file
        process.writeUpload("upload.csv", entries);

        // Assertion for post-condition for writeUpload
        // Post-condition: Upload file format is correct
        ArrayList<String> contents = readFile("upload.csv");
        // YOUR CODE HERE
        boolean isValid = checkUpload(contents);
        
        assert isValid: "Upload file has incorrect format!";
    }

    // Test code to read file
    private static ArrayList<String> readFile(String filename) {

        ArrayList<String> contents = new ArrayList<>();
        try {

            Scanner s = new Scanner(new File(filename));
            while (s.hasNextLine())
                contents.add(s.nextLine());
            s.close();

        } catch (IOException e) {
            System.out.println("readGrades cannot read: " + filename);
        }
        return contents;
    }

    public static long findSize(ArrayList<Interface.Entry> entries) {
    	
    	long listSize = 0;
    	
    	for (Interface.Entry entry: entries) {
    		listSize += entry.first.length();
    		listSize += entry.last.length();
    		listSize += entry.eid.length();
    		listSize += 3;
    	}
    	
    	return listSize;
    	
    }

    public static boolean checkGrades(ArrayList<Interface.Entry> entries){
		
    	boolean status = true;
	
    	for (Interface.Entry entry: entries){
    		if (checkRange(entry.overallAverage)) {
    			status = false;
    		}
    		else if (checkRange(entry.examAverage)){
    			status = false;
    		}
    		else if (checkRange(entry.assignmentAverage)) {
    			status = false;
    		}
    		else if (checkRange(entry.quizAverage)) {
    			status = false;
    		}
    		else if (checkRange(entry.labAverage)) {
    			status = false;
    		}
    		else if (checkRange(entry.peerAverage)) {
    			status = false;
    		}
    	}
    
    	return status;
    	
    }

    public static boolean checkUpload(ArrayList<String> contents) {
    	
    	boolean status = true;
    	
    	for (String line: contents) {
    		String[] fields = line.split(",");
    		if (fields.length != 10) {
    			status = false;
    		}
    	}
    	
    	return status;
    	
    }
    
    public static boolean checkRange(double value) {
    	
    	if (value > 100 || value < 0){
    		return true;	
    	}
    	return false;
    } 
}
