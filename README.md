Image Classification Filter for WEKA
====================================

Preparing the datasets:
—————————

Datasets should contain a minimum of two attributes: a string (which stores the filenames of the images) and a class, in an ARFF file.
Additional attributes can also be added and they will be copied unchanged by the filter.

Example, this is dogs.arff from the data folder:
````
@relation dogs
@attribute filename string
@attribute class {BEAGLE,GERMAN_SHORTHAIRED}
@data
img_beagle_1.jpg,BEAGLE
img_beagle_10.jpg,BEAGLE
img_beagle_12.jpg,BEAGLE
img_beagle_13.jpg,BEAGLE
img_beagle_14.jpg,BEAGLE
img_beagle_15.jpg,BEAGLE
img_german_shorthaired_42.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_43.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_44.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_45.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_46.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_47.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_48.jpg,GERMAN_SHORTHAIRED
img_german_shorthaired_49.jpg,GERMAN_SHORTHAIRED

````

Using the filters from the command line:
————————————————————
Run the image filter, specifying your ARFF file with the image filenames and the class index as the input. The output should be the name of the file where you want the images stored, e.g.:
````
java -cp classpath_to_weka weka.filters.unsupervised.instance.imagefilter.BasicImageFeatures -i dogs.arff -c 2 -o dogs_features.arff

````

Performing an image classification experiment from the command line:
——————————————————————————————————
Use the feature-extracted ARFF file to run experiments, e.g.:

````
java -cp classpath_to_weka weka.classifiers.functions.SMO -t ~/Desktop/dogs_features.arff
````


