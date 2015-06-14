package process;

import algorithm.AverageLinkageStrategy;
import algorithm.Cluster;
import algorithm.ClusteringAlgorithm;
import algorithm.DefaultClusteringAlgorithm;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // read data file
//        ReadFile rf = new ReadFile( "iris.arff" );
        ReadFile rf = new ReadFile( "easy.arff" );
        
        // create object for distance calculation
        Distance d = new Distance( rf.getData() );

        // get the distance matrix from each data point to each data point
        double[][] distances = d.getDistMatrix();
        
        // get the names of the data points (0..n)
        String[] names = new String[distances.length];
        for(int i = 0; i < distances.length; i++){
            names[i] = Integer.toString(i);
        }
        
        // 
        ClusteringAlgorithm alg = new DefaultClusteringAlgorithm();
        Cluster cluster = alg.performClustering(distances, names, new AverageLinkageStrategy());
        cluster.toConsole(0);
        
    }
    
}
