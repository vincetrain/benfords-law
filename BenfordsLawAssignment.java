/*
* Date: May 12, 2021
* Author: Serena Deng 
* Teacher: Mr.Ho
* Description: Reads "sales.csv" file; writes a digit frequency csv file
**/

import java.util.Scanner;    
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

class BenfordsLawAssignment{
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        // declare variables
        String readFile;
        String checkSales;
        // assign a variable to the file name
        String readCSVFile = "sales.csv";  
        String writeCSVFile = "results.csv";   
        // the array contains the digit frequencies 
        Double frequencyArray[] = {30.1, 17.6, 12.5, 9.7, 7.9, 6.7, 5.8, 5.1, 4.0};
       
        // prompt: to read, or not to read sales file 
        System.out.print("Do you want to read the total sales amount file? (yes, or no): ");
        readFile = reader.nextLine();
        if (readFile.equalsIgnoreCase("yes")){
            readFile(readCSVFile);    // call method 
            // prompt: to analyze, or not to analyze sales file 
            System.out.print("\nDo you want to analyze sales for possible fraud? (yes, or no): ");
            checkSales = reader.nextLine();
            if (checkSales.equalsIgnoreCase("yes")){
                // method for benfords law; graph 
                // after the validation/graph,
                // call:
                generateCSVFile(frequencyArray, writeCSVFile);  
            }
            else{
                System.out.println("Program End.");
            }
        }
        else{
            System.out.print("Do you want to analyze sales for possible fraud? (yes, or no): ");
            checkSales = reader.nextLine();
            if (checkSales.equalsIgnoreCase("yes")){
                // method for benfords law 
                // after the validation,
                // call:
                generateCSVFile(frequencyArray, writeCSVFile);
            }
            else{
                System.out.println("Program End.");
            }
        }
        reader.close(); 
    }
    /**
     * Description - to read sales csv file: open the file; split its data; print data 
     * using an array  
     * 
     * @author - Serena Deng 
     * @param readFile - passes the file name --> sales.csv
     */
    public static void readFile(String readFile){
        try{
            String line;
            BufferedReader br = new BufferedReader(new FileReader(readFile));   // FileReader opens csv file
            while ((line = br.readLine())!= null){     // read file line by line until end of file
                String[] salesData = line.split(",");  // store data in array, and split strings given comma delimiter
                for (int i = 0; i < salesData.length; i++ ){ 
                    System.out.println(salesData[i]);
                }
                
            }
            System.out.println("\nFinished reading file.");
            br.close();
            
        }

        catch (IOException e) { 
            System.out.println("Error reading file.");
        }
    } 
    /**
     * Description - to write results csv file: 
     * 
     * @author - Serena Deng 
     * @param arr - used to convert from double to string array; used in a for-each loop to write csv file
     * @param writeFile - passes the file name --> results.csv
     */
    public static void generateCSVFile(Double[] arr, String writeFile){
        // counter: fabricates the 'digit' column of the file 
        int countDigit = 0;
        // create and write csv file: 
        try {  
            BufferedWriter br = new BufferedWriter(new FileWriter(writeFile));   // create file 
            StringBuilder sb = new StringBuilder();   // StringBuilder add (builds) strings to file
            sb.append("digit" + "," + "frequency\n"); // this line is displayed once, hence outside of for loop
            // the for loop iterates through the elements of the converted string array, executing code tailored to each element 
            for (int i = 0; i < arr.length; i++){
                countDigit = countDigit+1;
                sb.append(countDigit + "," + arr[i] + "\n"); // append strings 
            }

            // print data to csv file 
            br.write(sb.toString());  // write data to file  
            br.close();              // close writer 

            System.out.println("Finished writing to file.");  
        }

        catch (IOException e){
            System.out.println("Error writing to file."); 
        }

        /** file ouput example: 
         *   digit  frequency
         *     1      30%
         *     2      15%
         *     3       7%
         *     4       4%
         */
    }
}