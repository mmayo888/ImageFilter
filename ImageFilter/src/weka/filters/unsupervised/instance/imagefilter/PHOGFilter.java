package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.PHOG;

public class PHOGFilter extends AbstractImageFilter {

	private static final long serialVersionUID = 9144122420251148032L;

	private PHOG features;

	public String globalInfo() {
		return "A batch filter for extracting PHOG features from images.";
	}

	public PHOGFilter() {
		super();
		features = new PHOG();
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
		runFilter(new PHOGFilter(), args);
	}
	
}
