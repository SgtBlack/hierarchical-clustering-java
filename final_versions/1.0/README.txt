###########################################
 README
###########################################


*******************************************
1. Run the program
*******************************************

[command]
    java -jar "kHAC_1.0.jar" [filename] [clusters] [method] [k]

[example]
    java -jar "kHAC_1.0.jar" data/iris.arff 3 single 2

*******************************************
2. Parameters
*******************************************

[filename]
  - file name / path of the data (arff) you want to cluster, for instance iris.arff

[clusters]
  - number of final clusters, that the algorithm should produce (more than 1)

[method]
  - linkage method you want to use (single, complete or average)

[k]
  - number of datapoints you want to use with the linkage method
  - if k is 1 you will use the standard implementation of the HAC methods