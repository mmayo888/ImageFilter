#Image Classification Filter for WEKA

Steps required to perform image classification in WEKA:

###1. Install the Image Filters Package

Run WEKA, and from the GUI menu, select Tools—>Package Manager.

When the Package Manager appears, find the package “imageFilters” in the list and click Install.

Restart WEKA.

###2. Download some images

You need to put all your images in the *same directory*.

For example, if you download the file [butterfly_vs_owl.zip](https://github.com/mmayo888/ImageFilter/raw/master/butterfly_vs_owl.zip),
you will find one directory containing 100 images. 50 of those images depict monarch butterflies; 50 are images of owls.

###3. Create an ARFF file containing the image labels

WEKA needs to have information about the images and their class labels encoded in an ARFF file.

An ARFF file for image data is identical to a normal ARFF file, except that the first attribute *must* be the filename of an image. More attributes may follow the filename, one of which is usually the class label.

For example, here is a portion of the [butterfly_vs_owl.arff](https://github.com/mmayo888/ImageFilter/blob/master/data/butterfly_vs_owl/butterfly_vs_owl.arff)
file in the example directory mentioned above:
````
@relation butterfly_vs_owl
@attribute filename string
@attribute class {BUTTERFLY,OWL}
@data
mno001.jpg,BUTTERFLY
mno002.jpg,BUTTERFLY
mno003.jpg,BUTTERFLY
mno004.jpg,BUTTERFLY
owl001.jpg,OWL
owl002.jpg,OWL
owl003.jpg,OWL
owl004.jpg,OWL
```` 

###4. Filter the images in the WEKA GUI

Open your ARFF file in the WEKA Explorer. If you are using the [butterfly_vs_owl.arff](https://github.com/mmayo888/ImageFilter/blob/master/data/butterfly_vs_owl/butterfly_vs_owl.arff) example, you will see that is contains two attributes: filename and class.

Select an image filter from the Filter Choose button.
The image filters can be found in filters/unsupervised/instance/attribute, and there should be ten of them.
The filter “ColorLayoutFilter” is a good one to start with.

Open the filter’s setting and set the “imageDirectory” field to the directory containing your images.

Click Apply.

If WEKA can find all the image files and there are no other errors, then you should see 34 new attributes (named “MPEG-7 Color Layout1”, “MPEG-7 Color Layout2”, etc) added to the dataset. If you examine the new attributes, you should see that they are all numeric attributes.

Congratulations, you have just filtered an image dataset!

###5. Run an experiment in the WEKA GUI

You can now save the filtered dataset as a normal WEKA ARFF file (preferably with a different name to the original ARFF, e.g. butterfly_vs_owl_features.arff) and run experiments as normal in WEKA.

You will probably need to remove the filename attribute first, however, as string attributes are likely to cause problems for many WEKA classifiers.

A second, harder image classification dataset concerning with distinguishing between two different species of [dogs](https://github.com/mmayo888/ImageFilter/raw/master/dogs.zip) is also available.

###6. Running image filters from the command line

All of the image filters can be run easily from the command line.
You need to provide several things on the command line, namely:
* the class path to your WEKA install
* the ARFF file containing the filenames and image labels, which is specified by the “-i” option
* the directory containing the images, specified by the “-D” option
* an ARFF file that you want the output written to, specified by the “-o” option.

You may also want to use the “RemoveType” filter to delete the filename string attribute after filtering.

For example:
````
java -cp classpath_to_weka weka.filters.unsupervised.instance.imagefilter.PHOGFilter -i dogs/dogs.arff -D /dogs/ -o dogs_features.arff
java -cp classpath_to_weka weka.filters.unsupervised.attribute.RemoveType -T string -i dogs_features.arff -o dogs_features_nostrings.arff
````

###Original sources:
* LIRE 0.9.5 https://code.google.com/p/lire/
* WEKA 3.7.12 http://www.cs.waikato.ac.nz/ml/weka/
* Butterfly & birds images http://www-cvr.ai.uiuc.edu/ponce_grp/data/
* Oxford IIIT Pet Dataset http://www.robots.ox.ac.uk/~vgg/data/pets/
