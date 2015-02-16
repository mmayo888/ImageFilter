package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.AutoColorCorrelogram;

public class AutoColorCorrelogramFilter extends AbstractImageFilter {

	private static final long serialVersionUID = 4083293550806621676L;
	
	public String globalInfo() {
		return "A batch filter for extracting the autocorrelogram based on color feature from images.";
	}

	public AutoColorCorrelogramFilter() {
		super();
		features = new AutoColorCorrelogram();
	}

	protected int getNumFeatures() {
		//return features.getDoubleHistogram().length;
		return 1024; // this is hardcoded due to a null-pointer error in LIRE
		             // if LIRE changes this value then this filter will break
	}

	protected String getFeatureNamePrefix() {
		return features.getFeatureName();
	}

	protected double[] getFeatures(BufferedImage img) {
		features.extract(img);
		return features.getDoubleHistogram();
	}

	public static void main(String[] args) {
		runFilter(new AutoColorCorrelogramFilter(), args);
	}
	
}
