
Datasets for Image Filtering
============================

You need to have all the images you want to experiment with on your computer, and then you construct an ARFF file with two attributes: the first attribute should be the filename of an image, and the second its class.

Here’s an example of the first few lines of images.arff:
````
@relation images
@attribute filename string
@attribute class {BEAGLE,GERMAN_SHORTHAIRED}
@data
../data/images/beagle_1.jpg,BEAGLE
../data/images/beagle_10.jpg,BEAGLE
../data/images/beagle_12.jpg,BEAGLE
../data/images/beagle_13.jpg,BEAGLE
../data/images/beagle_14.jpg,BEAGLE
../data/images/beagle_15.jpg,BEAGLE
../data/images/beagle_16.jpg,BEAGLE
../data/images/beagle_17.jpg,BEAGLE
../data/images/beagle_18.jpg,BEAGLE
../data/images/german_shorthaired_50.jpg,GERMAN_SHORTHAIRED
../data/images/german_shorthaired_6.jpg,GERMAN_SHORTHAIRED
../data/images/german_shorthaired_7.jpg,GERMAN_SHORTHAIRED
../data/images/german_shorthaired_8.jpg,GERMAN_SHORTHAIRED
../data/images/german_shorthaired_9.jpg,GERMAN_SHORTHAIRED
````

Image Filtering from CLI
========================

To run the filters on the example images, take a snapshot of the ImageFilter repository, then change directory into the repository.

Next, run the filter depending on which type of features you want to experiment with, e.g. for basic image images:


````
java -cp lib/*:bin/  weka.filters.unsupervised.instance.imagefilter.BasicImageFeatures -i ../data/images.arff -c last -o features.arff
````

and for color layout features:

````
java -cp lib/*:bin/  weka.filters.unsupervised.instance.imagefilter.ColorLayoutImageFeatures -i ../data/images.arff -c last -o features.arff
````

You will get an exception if images.arff does not have a string attribute as the first attribute, or if any of the image files cannot be read.

Next, simply run an experiment from the command line using features.arff as the dataset, e.g.

````
java -cp lib/*:bin/  weka.classifiers.bayes.NaiveBayes -t features.arff 

````


References
==========

LIRE 0.9.3
https://code.google.com/p/lire/

WEKA 3.7.12
http://www.cs.waikato.ac.nz/ml/weka/

The Oxford-IIIT Pet Dataset
http://www.robots.ox.ac.uk/~vgg/data/pets/