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
        
        //double[][] distances = new double[][] { { 0, 1, 9, 7, 11, 14 }, 
        //                                        { 1, 0, 4, 3, 8, 10 }, 
        //                                        { 9, 4, 0, 9, 2, 8 },
        //                                        { 7, 3, 9, 0, 6, 13 }, 
        //                                        { 11, 8, 2, 6, 0, 10 }, 
        //                                        { 14, 10, 8, 13, 10, 0 } };
        //String[] names = new String[] { "O1", "O2", "O3", "O4", "O5", "O6" };
        
        ReadFile rf = new ReadFile( "example.arff" );
        
        Distance d = new Distance( rf.getData() );

        
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
