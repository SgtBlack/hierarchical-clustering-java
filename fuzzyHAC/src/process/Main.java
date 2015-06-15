package process;

import algorithm.*;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        // read data file
//        ReadFile rf = new ReadFile( "iris.arff" );
        ReadFile rf = new ReadFile( "easy.arff" );
//        ReadFile rf = new ReadFile( "triangles.arff" );
        
        // create object for distance calculation
        Distance d = new Distance( rf.getData() );

        // get the distance matrix from each data point to each data point
        double[][] distances = d.getDistMatrix();
        
        // get the names of the data points (0..n)
        String[] names = new String[distances.length];
        for(int i = 0; i < distances.length; i++){
            names[i] = Integer.toString(i);
        }
        
        // cluster with specified algorithm
        ClusteringAlgorithm alg = new DefaultClusteringAlgorithm();
        
        Cluster cluster_single = alg.performClustering(distances, names, new SingleLinkageStrategy());
        Cluster cluster_average = alg.performClustering(distances, names, new AverageLinkageStrategy());
        
        // write a file with the class labels
        WriteLabelFile labelfile_single = new WriteLabelFile(cluster_single,3);
        labelfile_single.createFile("sol_single");
        
        
        WriteLabelFile labelfile_average = new WriteLabelFile(cluster_average,3);
        labelfile_average.createFile("sol_average");
        
    }
}
