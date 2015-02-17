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

###4. Run an experiment in the WEKA GUI

###Original sources:
* LIRE 0.9.5 https://code.google.com/p/lire/
* WEKA 3.7.12 http://www.cs.waikato.ac.nz/ml/weka/
* Butterfly & birds images http://www-cvr.ai.uiuc.edu/ponce_grp/data/
* Oxford IIIT Pet Dataset http://www.robots.ox.ac.uk/~vgg/data/pets/
