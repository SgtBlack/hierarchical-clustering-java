package process;

import algorithm.*;


public class Main {

    /**
     * @param args the command line arguments
     * args[0] the inputFile
     * args[1] Anzahl der Cluster
     * args[2] Linkage Methode
     * args[3] K -> Anzahl der einbezogenen Datenpunkte beim Linkage
     */
    public static void main(String[] args) {

        if (args.length == 4) {

            int anz_cluster = Integer.parseInt( args[1] );
            int k = Integer.parseInt( args[3] );
            
            
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
                default: strategy = new SingleLinkageStrategy(); break;
            }

            Cluster cluster = alg.performClusteringK(distances, names, strategy, k);

            // write a file with the class labels
            WriteLabelFile labelfile = new WriteLabelFile(cluster, anz_cluster);
            labelfile.createFile("sol_k_linkage"); 
        }
    }
}
