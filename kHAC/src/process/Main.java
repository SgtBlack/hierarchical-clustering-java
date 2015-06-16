package process;

import algorithm.*;
import java.io.File;
import java.util.Arrays;


public class Main {

    /**
     * @param args[0] inputFile: typ arff
     * @param args[1] Anzahl der Cluster
     * @param args[2] Linkage Methode
     * @param args[3] K -> Anzahl der einbezogenen Datenpunkte beim Linkage
     */
    
    private final static String[] LINKAGE_STRATEGY = new String[] {"single", "complete", "average"};
    
    public static void main(String[] args) {

        if (args.length == 4) {
            
            // Test Parameters
            
            //input file
            if( !(new File( args[0] ).isFile()) ){
                throw new IllegalArgumentException("File not found!");
            }
            // number of solution clusters
            if( Integer.parseInt( args[1] ) < 1 ){
                throw new IllegalArgumentException("Requires at least one cluster!");
            }
            // linkate strategy
            if( !Arrays.asList(LINKAGE_STRATEGY).contains(args[2]) ){
                throw new IllegalArgumentException("Unknown Linkage Strategy: " + args[2]);
            }
            // k (number of used datapoints)
            if( Integer.parseInt( args[3] ) < 1 ){
                throw new IllegalArgumentException("Requires K > 0!");
            }

            // Get arguments from args[]
            String filename = args[0];
            int anz_cluster = 1;
            if(Integer.parseInt( args[1] ) > 1){
                anz_cluster = Integer.parseInt( args[1] );
            }
            String l_strategy = args[2];
            int k = 1;
            if(Integer.parseInt( args[3] ) > 1){
                k = Integer.parseInt( args[3] );
            }
            
            

            // read data file
            ReadFile rf = new ReadFile( filename );

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
            
            switch( l_strategy ){
                case "single": strategy = new SingleLinkageStrategy(); break;
                case "complete": strategy = new CompleteLinkageStrategy(); break;
                case "average": strategy = new AverageLinkageStrategy(); break;
                default: strategy = new SingleLinkageStrategy(); break;
            }

            Cluster cluster = alg.performClusteringK(distances, names, strategy, k);

            // write the class labels
            WriteLabel labelfile = new WriteLabel(cluster, anz_cluster);
            labelfile.printToConsole();
            //labelfile.createFile("sol_"+"cl"+anz_cluster+"_k"+k+"_"+l_strategy); 
            
        } else {
            throw new IllegalArgumentException("Not enough arguments: 4 arguments needed!");
        }
    }
}
