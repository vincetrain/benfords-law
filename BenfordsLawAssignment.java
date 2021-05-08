/*
* Date: May 12, 2021
* Author: Serena Deng 
* Teacher: Mr.Ho
* Description: Reads "sales.csv" file; writes a digit frequency csv file
**/

import java.util.Scanner;  
import java.io.BufferedReader;    
import java.io.FileReader;
import java.io.IOException;

class BenfordsLawAssignment{
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        String readFile;
        String checkSales;
        // Prompt user: to read, or not to read csv file 
        System.out.print("Do you want to read the total sales amount file? yes, or no: ");
        readFile = reader.nextLine();
        if (readFile.equalsIgnoreCase("yes")){
            readFile();    // call method 
            System.out.print("\nDo you want to check sales for possible fraud? yes, or no: ");
            checkSales = reader.nextLine();
            if (checkSales.equalsIgnoreCase("yes")){
                // method 
            }
            else{
                System.out.println("Program End.");
            }
        }
        else if (readFile.equalsIgnoreCase("no")){
            System.out.print("Do you want to check sales for possible fraud? yes, or no: ");
            checkSales = reader.nextLine();
            if (checkSales.equalsIgnoreCase("yes")){
                // method 
            }
            else{
                System.out.println("Program End.");
            }
        }
        // else 
        reader.close(); 
    }
    /**
     * 
     */
    public static void readFile(){
        try{
            String line;
            String fileName = "sales.csv";      // Assign the csv file to a string called fileName
            BufferedReader br = new BufferedReader(new FileReader(fileName));   // Reads the csv file
            while ((line = br.readLine())!= null){     // Read file line by line until end of file
                System.out.println(line);              
            }
            br.close();
        }
        catch (IOException e) { 
            System.out.println(" ");
        }
    }  
}