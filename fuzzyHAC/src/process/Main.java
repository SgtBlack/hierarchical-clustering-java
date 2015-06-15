package process;

import algorithm.*;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int k = 10;
        int anz_cluster = 3;
        
        
        // read data file
        ReadFile rf = new ReadFile( "iris.arff" );
//        ReadFile rf = new ReadFile( "easy.arff" );
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
        Cluster cluster_single_k = alg.performClusteringK(distances, names, new SingleLinkageStrategy(),k);
        
        Cluster cluster_complete = alg.performClustering(distances, names, new CompleteLinkageStrategy());
        Cluster cluster_complete_k = alg.performClusteringK(distances, names, new CompleteLinkageStrategy(),k);
        
        
        // write a file with the class labels
        WriteLabelFile labelfile_single = new WriteLabelFile(cluster_single,anz_cluster);
        labelfile_single.createFile("sol_single");
        
        WriteLabelFile labelfile_single_k = new WriteLabelFile(cluster_single_k,anz_cluster);
        labelfile_single_k.createFile("sol_single_k");
        
        WriteLabelFile labelfile_complete = new WriteLabelFile(cluster_complete,anz_cluster);
        labelfile_complete.createFile("sol_complete");
        
        WriteLabelFile labelfile_complete_k = new WriteLabelFile(cluster_complete_k,anz_cluster);
        labelfile_complete_k.createFile("sol_complete_k");
        
    }
}
