package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.BinaryPatternsPyramid;

public class BinaryPatternsPyramidFilter extends AbstractImageFilter {

	private static final long serialVersionUID = -252542065001092364L;

	public String globalInfo() {
		return "A batch filter for extracting a pyramid of rotation-invariant local binary pattern histograms from images."+
	" Each local binary pattern represents an intensity pattern (e.g. an edge or a corner) around a point."+
	" A histogram of local binary patterns therefore encodes the larger scale patterns that occur across regions of images."+
	" Local binary patterns are useful for texture and face recognition.";
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
