/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apporiented.algorithm.clustering.infile;

import com.apporiented.algorithm.clustering.AverageLinkageStrategy;
import com.apporiented.algorithm.clustering.Cluster;
import com.apporiented.algorithm.clustering.ClusteringAlgorithm;
import com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm;

/**
 *
 * @author Fabian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //double[][] distances = new double[][] { { 0, 1, 9, 7, 11, 14 }, { 1, 0, 4, 3, 8, 10 }, { 9, 4, 0, 9, 2, 8 },
        //        { 7, 3, 9, 0, 6, 13 }, { 11, 8, 2, 6, 0, 10 }, { 14, 10, 8, 13, 10, 0 } };
        //String[] names = new String[] { "O1", "O2", "O3", "O4", "O5", "O6" };
        //ClusteringAlgorithm alg = new DefaultClusteringAlgorithm();
        //Cluster cluster = alg.performClustering(distances, names, new AverageLinkageStrategy());
        //cluster.toConsole(0);
        
        ReadFile rf = new ReadFile( "example.arff" );
        rf.read();
        
        Distance d = new Distance( rf.getData() );
        
        d.calculate();
        
        double[][] distances = d.getDistMatrix();
        String[] names = new String[distances.length];
        
        
        for(int i = 0; i < distances.length; i++){
            names[i] = Integer.toString(i);
        }
        
        ClusteringAlgorithm alg = new DefaultClusteringAlgorithm();
        Cluster cluster = alg.performClustering(distances, names, new AverageLinkageStrategy());
        cluster.toConsole(0);
        
    }
    
}
