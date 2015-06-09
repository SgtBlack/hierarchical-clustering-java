/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apporiented.algorithm.clustering.infile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Fabian
 */
public class readFile {
    
    private String fileName;
    private int dimensions;
    private int anzExamples;
    private float[][] dataMatrix;
    
    
    readFile(String file){
        fileName = file;
        
        dimensions = readDimensions();
        
        anzExamples = readAnzExamples();
        
        dataMatrix = new float[anzExamples][dimensions];
    }
    
    public float[][] getData(){
        return dataMatrix;
    }
   
    public void read() {

        // This will reference one line at a time
        String line = null;
        boolean isDataPart = false;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            String[] parts;
            int aktLine = 0;

            while((line = bufferedReader.readLine()) != null) {

                if(isDataPart == true){
                    
                    parts = line.split(",");
                    
                    for(int i = 0; i < parts.length - 1; i++){
                        dataMatrix[aktLine][i] = Float.parseFloat(parts[i]);
                    }
                    aktLine++;
                }
      
                if(line.contains("@DATA"))
                    isDataPart = true;
            }    

            // Always close files.
            bufferedReader.close();            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                   
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }    
    
    private int readDimensions() {

        // This will reference one line at a time
        String line = null;
        boolean isDataPart = false;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            String[] parts;

            while((line = bufferedReader.readLine()) != null) {

                if(isDataPart == true){
                    
                    parts = line.split(",");
                    
                    return ( parts.length - 1 );
                }
      
                if(line.contains("@DATA"))
                    isDataPart = true;
            }    

            // Always close files.
            bufferedReader.close();            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                   
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        return 0;
    }
    
    private int readAnzExamples() {

        // This will reference one line at a time
        String line = null;
        boolean isDataPart = false;
        int count = 0;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                if(isDataPart == true){
                    count++;
                }
      
                if(line.contains("@DATA"))
                    isDataPart = true;
            }    

            // Always close files.
            bufferedReader.close();            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                   
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        return count;
    }
   
}
