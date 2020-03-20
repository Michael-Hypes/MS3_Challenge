/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ms3_csv_challenge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MS3_CSV_Challenge {

    private static String fname = "";
    
    public static void main(String[] args) {
        try 
        {
            //Unified Filename to prevent mismatch
            fname = "ms3Interview - Jr Challenge 2";      //MIGHT NEED TO CHANGE
            //fname = args[0];
            
            
            //Open the input CSV File
            File inputfile = new File(fname + ".csv");
            Scanner input = new Scanner(inputfile);
            
            //Create the bad output file
            File failedfile = new File(fname + "-bad.csv");
            FileWriter failedwriter = new FileWriter(failedfile);
                
            
            SqliteHandler sh = new SqliteHandler(fname);
            //sh.Connect();
            
            //Match the end of line delimiter to 5 commas
            Pattern p = Pattern.compile("[,]{5}");
            input.useDelimiter(p);
            
            //Throw away useless first line
            input.next();
            //TODO: Check if first line is column names
            
            
            int succeedcount = 0;
            int failedcount = 0;
            
            while (input.hasNext()) 
            {

                String str = input.next();
                
                //Splits on comma not in between double quotation marks
                
                String[] split = str.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                boolean valid = true;

                for (int i = 0; i < split.length; i++) 
                {
                    if (split[i].compareTo("") == 0 || split.length < 10) {
                        valid = false;
                    }
                }
                
                if(valid)
                {
                    //Insert into SQLite
                    //System.out.println(split.length);   Test the size of arrays
                    sh.insert(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7], split[8], split[9]);
                    succeedcount++;
                }
                else if(!valid)
                {
                    //write Invalid Input to File
                    for(int i = 0; i < split.length; i++){
                        failedwriter.write(split[i]);
                    }
                    failedwriter.write(",,,,,");
                    failedcount++;
                }
            }
            
           //Create output log file
            File logfile = new File(fname + ".log");
            FileWriter logwriter = new FileWriter(logfile);
            //Write information to log file
            logwriter.write("Successful inputs = " + succeedcount + "\nFailed inputs = " + 
                    failedcount + "\nTotal inputs = " + (succeedcount+failedcount));
            logwriter.close();
            
        } catch (IOException e) {
            System.out.println("An IOException has occurred!");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
}
