# Script to exercise each filter

function test_filter {
    java -cp ImageFilter/lib/*:ImageFilter/bin/ weka.filters.unsupervised.instance.imagefilter.$1 -i data/dogs/dogs.arff -D data/dogs/ -o tmp.arff
    java -cp ImageFilter/lib/weka.jar weka.filters.unsupervised.attribute.RemoveType -T string -i tmp.arff -o tmp_nostrings.arff
    java -cp ImageFilter/lib/weka.jar weka.classifiers.bayes.NaiveBayes -t tmp_nostrings.arff
    rm tmp.arff
    rm tmp_nostrings.arff
}
test_filter ColorLayoutFilter
test_filter EdgeHistogramFilter
test_filter BinaryPatternsPyramidFilter
test_filter GaborFilter
test_filter PHOGFilter
test_filter FuzzyOpponentHistogramFilter
test_filter SimpleColorHistogramFilter
test_filter AutoColorCorrelogramFilter
test_filter FCTHFilter
test_filter JpegCoefficientFilter