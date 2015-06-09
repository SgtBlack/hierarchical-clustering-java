/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apporiented.algorithm.clustering.infile;

/**
 *
 * @author Fabian
 */
public class Distance {
    
    private float[][] posDataMatrix; //Zeile: Datenpunkt; Spalte: Dimensionen
    private double[][] distDataMatrix;
    private int anzExamples;
    private int dimensions;
    
    
    public Distance(float[][] matrix){
        posDataMatrix = matrix;
        
        anzExamples = posDataMatrix.length;
        
        dimensions = posDataMatrix[0].length;
        
        distDataMatrix = new double[anzExamples][anzExamples];
        
        calculate();
    }
    
    public double[][] getDistMatrix(){
        return distDataMatrix;
    }
    
    private void calculate(){
    
        double dist;
        float sum;
        
        // Diagonale auf Null setzen
        for(int i = 0; i < anzExamples; i++){
            distDataMatrix[i][i] = 0;
        }
        
        // Distanzen berechnen
        for(int i = 0; i < anzExamples; i++){
            
            dist = 0;
            sum = 0;
            
            for(int j = i + 1; j < anzExamples; j++){
                 
                for(int k = 0; k < dimensions; k++){
                    sum += Math.pow(posDataMatrix[i][k] - posDataMatrix[j][k], 2);
                }
                
                dist = Math.sqrt( sum );
                
                distDataMatrix[i][j] = dist;
                distDataMatrix[j][i] = dist;
            }
        }
    }
    
}
