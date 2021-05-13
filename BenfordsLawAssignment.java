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

import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

class BenfordsLawAssignment{

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        Double frequencyArray[] = {30.1, 17.6, 12.5, 9.7, 7.9, 6.7, 5.8, 5.1, 4.0};
       
        // User input variables
        String userInput;

        // Condition variables
        String continueCondition = "1";
        String exitCondition = "2";

        // Prompts user if not inputted, or input was invalid. Also loops the program, which is nice.
        do {
            printMenu();    // Prints the menu
            reader = new Scanner(System.in);
            userInput = reader.nextLine();
            if (userInput.equals(continueCondition)) {
                do {
                    System.out.print("Input CSV file directory: ");
                    reader = new Scanner(System.in);
                    userInput = reader.nextLine();
                } while (!validFile(userInput));
                readFile(userInput);
            }
        } while(!(userInput.equals(exitCondition)));

        System.exit(0); // Exits the program
    }

    /**
     * Prints the menu using PrintStream and string concatenation.
     * 
     * @author Vincent Tran
     */
    public static void printMenu() {
        System.out.println("Welcome to the Benford's Law Sales Analysis Program.\n"
        .concat("1) Determine sales fraud\n")
        .concat("2) Quit\n"));
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
            String[] salesData = new String[getLength(readFile)];   // new final salesData array, calls getLength to find out the length of csv file
            String[] tempData;
            BufferedReader br = new BufferedReader(new FileReader(readFile));   // FileReader opens csv file
            int j = 0; // used to store index of salesData
            while ((line = br.readLine())!= null){     // read file line by line until end of file
                tempData = line.split(",");  // store data in array, and split strings given comma delimiter
                for (int i = 0; i < tempData.length; i++){ 
                    salesData[j] = tempData[i]; // stores tempData into salesData
                    j++;
                }
                
            }
            System.out.println("\nFinished reading " + readFile + ".\n");
            br.close();

            benfordsLaw(salesData);
        }

        catch (IOException e) { 
            System.out.println("Error reading " + readFile + ".\n");
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

            System.out.println("Finished writing to " + writeFile + ".\n");  
        }

        catch (IOException e){
            System.out.println("Error writing to " + writeFile + ".\n"); 
        }

        /** file ouput example: 
         *   digit  frequency
         *     1      30%
         *     2      15%
         *     3       7%
         *     4       4%
         */
    }
    /**
     * Graphs the first 9 digits using JFreechart API
     * 
     * @author Vincent Tran
     * @params salesData
     */
    public static void generateGraph(String[] salesData) {
        
    }
    /**
     * Calculates fraud within inputted data by comparing frequency of first digits.
     * <p>
     * Uses iteration between for-loops to iterate between indicies in the given salesData array, 
     * and then compares char types with the ascii values of digits between 1 and 9.
     * 
     * @param salesData
     * @author Vincent Tran
     */
    public static void benfordsLaw(String[] salesData) {
        int[] rawFrequencies = new int[9];
        Double[] dataFrequencies = new Double[9]; // array containing 1, 2, 3, 4, 5, 6, 7, 8, 9 place holders
        double sum = 0.0;    // used to keep track oaf sum of all first nums occurances.
        // for loop that iterates through all indicies within salesData
        for (int i = 0; i < salesData.length; i++) {
            // for loop used to check for first-integer frequency
            for (int j = 0; j < 9; j++){
                if (salesData[i].charAt(0) == j+49) {   // compares chars. 49 is added to convert j to ascii code of atleast 1, which when iterated should give values between 1 and 9.
                    rawFrequencies[j]++;   // increments accordingly to keep track of number frequency.
                    sum++; // increments sum everytime a first-number occurs.
                }
            }
        }
        
        // for loop that iterates through digits 0 and 8, to then index and print frequency percentage
        for (int i = 0; i < 9; i++) {
            dataFrequencies[i] = (rawFrequencies[i]/sum)*100;   // gets 
            System.out.format(i + ": %.2f%%\n", dataFrequencies[i]);
        }

        // compares first digit frequency to see if fraudulent.
        if (dataFrequencies[0] > 29 && dataFrequencies[0] < 32) {
            System.out.println("FRAUDULENT DATA: Unlikely\n");
        }
        else {
            System.out.println("FRAUDULENT DATA: Likely\n");
        }

        generateCSVFile(dataFrequencies, "result.csv");
    }

    /**
     * Validates file to see if it exists, or is valid file type.
     * 
     * @author Vincent Tran
     * @param userInput
     * @return
     */
    public static boolean validFile(String userInput) {
        String temp = "";
        try {
            // Iterates through the file extension, using String.lastIndexOf()
            for (int i = userInput.lastIndexOf("."); i < userInput.length(); i++) {
                temp = temp + userInput.charAt(i);
            }
            // Checks if file extension is .csv
            if (temp.equals(".csv")) {
                File file = new File(userInput);
                if (file.exists()) {    // Checks if file exists
                    return true;
                }
            }
        }
        catch (Exception StringIndexOutOfBoundsException) {
            // does nothing
        }
        System.out.println("ERROR: This file does not exist, or is not a CSV.");
        return false;
    }

    /**
     * Gets the maximum length of provided file.
     * 
     * @author Vincent Tran
     * @param readFile
     * @return
     * @throws IOException
     */
    public static int getLength(String readFile) throws IOException {
        int length = 0;
        BufferedReader br = new BufferedReader(new FileReader(readFile));
        while ((br.readLine())!= null){     // read file line by line until end of file
            length++;
        }
        br.close();
        return length*2;
    }
}