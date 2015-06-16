package process;

import algorithm.*;
import java.io.File;
import java.util.Arrays;


public class Main {

    /**
     * @param args the command line arguments
     * args[0] inputFile
     * args[1] Anzahl der Cluster
     * args[2] Linkage Methode
     * args[3] K -> Anzahl der einbezogenen Datenpunkte beim Linkage
     */
    
    private final static String[] LinkageStrategies = new String[] {"single", "complete", "average"};
    
    public static void main(String[] args) {

        if (args.length == 4) {
            
            // Exception handling
            if( !(new File( args[0] ).isFile()) ){
                throw new IllegalArgumentException("File not found!");
            }
            
            if( Integer.parseInt( args[1] ) < 1 ){
                throw new IllegalArgumentException("Requires at least one cluster!");
            }
            
            if( Arrays.asList(LinkageStrategies).contains(args[2]) ){
                throw new IllegalArgumentException("Unknown Linkage Strategy!");
            }
            
            if( Integer.parseInt( args[3] ) < 1 ){
                throw new IllegalArgumentException("Requires K > 0!");
            }

            // Get arguments from args[]
            int anz_cluster = 1;
            
            if(Integer.parseInt( args[1] ) > 1){
                anz_cluster = Integer.parseInt( args[1] );
            }
            
            int k = 1;
            
            if(Integer.parseInt( args[3] ) > 1){
                k = Integer.parseInt( args[3] );
            }

            // read data file
            ReadFile rf = new ReadFile( args[0] );

            // create object for distance calculation
            Distance d = new Distance( rf.getData() );

            // get the distance matrix from each data point to each data point
            double[][] distances = d.getDistMatrix();

            // get the names of the data points (0...n)
            String[] names = new String[distances.length];
            for(int i = 0; i < distances.length; i++){
                names[i] = Integer.toString(i);
            }

            // cluster with specified algorithm
            ClusteringAlgorithm alg = new DefaultClusteringAlgorithm();
            
            LinkageStrategy strategy;
            
            switch( args[2] ){
                case "single": strategy = new SingleLinkageStrategy(); break;
                case "complete": strategy = new CompleteLinkageStrategy(); break;
                case "average": strategy = new AverageLinkageStrategy(); break;
                default: strategy = new SingleLinkageStrategy(); break;
            }

            Cluster cluster = alg.performClusteringK(distances, names, strategy, k);

            // write a file with the class labels
            WriteLabelFile labelfile = new WriteLabelFile(cluster, anz_cluster);
            labelfile.createFile("sol_k_linkage"); 
            
        } else {
            throw new IllegalArgumentException("Not enough arguments: 4 arguments needed!");
        }
    }
}
