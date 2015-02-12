package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.EdgeHistogram;

public class EdgeHistogramImageFeatures extends AbstractImageFeatures {

	private static final long serialVersionUID = 7560143299904308904L;
	
	private EdgeHistogram features;

	public String globalInfo() {
		return "A batch filter for extracting MPEG7 edge histogram features from images.";
	}

	public EdgeHistogramImageFeatures() {
		super();
		features = new EdgeHistogram();
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
		runFilter(new EdgeHistogramImageFeatures(), args);
	}
	
}