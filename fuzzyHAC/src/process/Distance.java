package process;

/* euclidean distance */
public class Distance {
    
    private final float[][] posDataMatrix; //Zeile: Datenpunkt; Spalte: Dimensionen
    private final double[][] distDataMatrix;
    private final int anzExamples;
    private final int dimensions;
    
    
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
