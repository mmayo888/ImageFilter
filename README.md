
Usage Examples from CLI
=======================

To run the filters on the example images, take a snapshot of the ImageFilter repository, then change directory into the repository.

Next, run the filter depending on which type of features you want to experiment with, e.g. for basic image images:


````
java -cp lib/*:bin/  weka.filters.unsupervised.instance.imagefilter.BasicImageFeatures -i ../data/images.arff -c last -o ~/Desktop/features.arff

````

and for color layout features:

````
java -cp lib/*:bin/  weka.filters.unsupervised.instance.imagefilter.ColorLayoutImageFeatures -i ../data/images.arff -c last -o ~/Desktop/features.arff
````

You will get an exception if any of the image files cannot be read.

Next, simply run an experiment from the command line using features.arff as the dataset:




References
==========

LIRE 0.9.3
https://code.google.com/p/lire/

WEKA 3.7.12
http://www.cs.waikato.ac.nz/ml/weka/

The Oxford-IIIT Pet Dataset
http://www.robots.ox.ac.uk/~vgg/data/pets/