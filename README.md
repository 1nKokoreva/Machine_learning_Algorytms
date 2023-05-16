# MACHINE LEARNING ALGORYTMS

# Knn Algorithm

Implement the knn algorithm

The Program should take arguments k, train_file, test_file, where:

k-number of nearest neighbors
train_file-path to the training file
test file-path to the file containing the test file

The Program should write out each observation from the test set (input vector and correct class) and the Kals provided by the algorithm for this observation.
In the last line, the program should display the accuracy, that is, the percentage of correct answers.

-------------------------
-------------------------

Example

1.2, 2.2, 3.1, 4.1, iris-virginica predicted: iris-virginica
1.2, 0.2, 3.1, 0.0, iris-setosa predicted: iris-virginica
0.3, 0.1, 1.1, 2.0, iris-setosa predicted: iris-setosa
0.3, 0.1, 1.3, 2.1, iris-setosa predicted: iris-setosa

Accuracy: 75.0 %

--------------------------------
--------------------------------

# K-means Algorithm

Implement the k-mean algorithm.
The Program should work for any number of attributes that are recognized automatically from the data file.

The Program should be tested on tiris data from the knn project(you can combine a test and training set). Labels from the dataset should be omitted from k-means calculations and used only for cluster purity calculations.
The number of clusters k is selected by the user.
After each iteration of the k-means algorithm, the program should write:
- sum of distances between observations and centroids of assigned clusters (the sum is calculated as one value for all clusters)
- purity of each cluster, i.e. what percentage of labels are in each cluster
