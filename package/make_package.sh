# get rid of any old version of the zip file
rm -f ImageFilter.zip

# create a package directory
mkdir ImageFilter

# create jar file
jar cf ImageFilter/ImageFilter.jar ../ImageFilter/bin/

# add source files
mkdir ImageFilter/src
mkdir ImageFilter/src/main
cp -r ../ImageFilter/src/ ImageFilter/src/main/

# add lib files
cp -r ../ImageFilter/lib ImageFilter/

# add configuration files -- hope they have been updated!
cp Description.props ImageFilter/
cp GenericPropertiesCreator.props ImageFilter/
cp build_package.xml ImageFilter/

# remove unnecessary files from the package
rm ImageFilter/lib/weka.jar
find . -type f -name '.DS_Store' -print -exec rm -rf {} \;

# create zip file
zip -r ImageFilter.zip ImageFilter 

# tidy up
rm -r -f ImageFilter

