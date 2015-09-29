package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.SimpleColorHistogram;

public class SimpleColorHistogramFilter extends AbstractImageFilter {

	private static final long serialVersionUID = -7390749464085428280L;
	
	public String globalInfo() {
		return "A batch filter for extracting color histogram feature from images. "+
	" This is the most basic color feature that can be computed; essentially, it three histograms "+
	" (one for red, one for green, one for blue) each of which has 32 bins. "+
	" Each bin contains a count of the pixels in the image that fall into that bin.";
	}

	public SimpleColorHistogramFilter() {
		super();
		features = new SimpleColorHistogram();
	}

	protected int getNumFeatures() {
		return features.getDoubleHistogram().length;
	}

	protected String getFeatureNamePrefix() {
		return features.getFeatureName();
	}

	protected double[] getFeatures(BufferedImage img) {
		features.extract(img);
		return features.getDoubleHistogram();
	}

	public static void main(String[] args) {
		runFilter(new SimpleColorHistogramFilter(), args);
	}
	
}
