package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.BinaryPatternsPyramid;

public class BinaryPatternsPyramidFilter extends AbstractImageFilter {

	private static final long serialVersionUID = -252542065001092364L;

	private BinaryPatternsPyramid features;

	public String globalInfo() {
		return "A batch filter for extracting a pyramid of local binary pattern histograms from images.";
	}

	public BinaryPatternsPyramidFilter() {
		super();
		features = new BinaryPatternsPyramid();
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
		runFilter(new BinaryPatternsPyramidFilter(), args);
	}
	
}
