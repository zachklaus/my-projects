// ProcessGrades.java - Solution for grades assignment
// Author: Zachary Klausner
// Date: 1/30/2017
// Class: CS165
// Email: zachklau@rams.colostate.edu

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessGrades implements Interface {

    // Method to read roster file
    // Post-condition: ArrayList contains all data from roster file 
    public ArrayList<Entry> readRoster(String filename) {
        
        ArrayList<Entry> entries = new ArrayList<>();
        
        // YOUR CODE HERE
        try {
        
        	Scanner scan = new Scanner(new File(filename));
        	//System.out.println("Roster contents: ");
        	while (scan.hasNextLine()) {
        		String line = scan.nextLine();
        		//System.out.println(line);
        		String[] contents = line.split(",");
        		Entry entry = new Entry(contents[2], contents[0], contents[1]);
        		entries.add(entry);
        	}
        	
        	scan.close();
        	
        } catch(IOException e) {
        	System.err.println("Cannot read file: " + filename);
        }
        
        
        return entries;
    }

    // Method to read grades file, returns number of students matched
    // Post-condition: All students in roster are matched with grades
    public int readGrades(String filename, ArrayList<Entry> entries) {
        
        int numberMatches = 0;
        
        // YOUR CODE HERE
        //System.out.println("grades.csv contents: ");
        try {
        	
        	Scanner scan = new Scanner(new File(filename));
        	
        	while (scan.hasNextLine()) {
        		String line = scan.nextLine();
        		//System.out.println(line);
        		String[] contents = line.split(",");
        		String ID = contents[0];
        		//System.out.println("ID: " + ID);
        		for (Entry entry: entries) {
        			if (entry.eid.equals(ID)) {
        				entry.examAverage = Integer.parseInt(contents[1]);
        				entry.assignmentAverage = Integer.parseInt(contents[2]);
        				entry.quizAverage = Integer.parseInt(contents[3]);
        				entry.labAverage = Integer.parseInt(contents[4]);
        				entry.peerAverage = Integer.parseInt(contents[5]);
        				numberMatches++;
        			}
        		}
        	}
        	//System.out.println("numberMatches = " + numberMatches);
        	scan.close();
        	
        } catch (IOException e) {
        	System.out.println("Cannot read file: " + filename);
        }
        
        return numberMatches;
    }

    // Method to compute grades
    // Post-condition: All grades are in the range zero to 100
    public void computeGrades(ArrayList<Entry> entries) {

    	//System.out.println("Average grades: ");
    	
        for (Entry entry : entries) {
            
        	//System.out.println(entry.assignmentAverage);
    	
            // YOUR CODE HERE
        	entry.overallAverage = (entry.examAverage * 0.50) +
                    (entry.assignmentAverage * 0.20) +
                    (entry.quizAverage * 0.10) +
                    (entry.labAverage * 0.10) +
                    (entry.peerAverage * 0.10);
        	
        	double grade = entry.overallAverage;
        	char letter;
        	
        	if (grade >= 90.0) {
        		letter = 'A';
        	}
        	else if (grade >= 80.0) {
        		letter = 'B';
        	}
        	else if (grade >= 70.0) {
        		letter = 'C';
        	}
        	else if (grade >= 60.0) {
        		letter = 'D';
        	}
        	else {
        		letter = 'F';
        	}
        	
        	entry.letterGrade = letter;
        }
    }
    
    // Method to write upload file
    // Post-condition: Upload file format and size are correct 
    public void writeUpload(String filename, ArrayList<Entry> entries) {
        
        // YOUR CODE HERE
    	try {
    		PrintWriter writer = new PrintWriter(new File(filename));
    		writer.println("EID,First Name,Last Name,Exams,Assignments,Quizzes,Labs,Peer,Overall,Grade");
    		for (Entry entry: entries) {
    			writer.println(entry.toString());
    		}
    		writer.close();
    	}catch(IOException e) {
    		System.out.println("Cannot write to file: " + filename);
    	}
    }
}

