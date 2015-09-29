package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.JpegCoefficientHistogram;

public class JpegCoefficientFilter extends AbstractImageFilter {

	private static final long serialVersionUID = 3673433053794040922L;

	public String globalInfo() {
		return "A batch filter for extracting JPEG coefficients from images. "
				+" Converting an image to the JPEG file format discards information that it imperceptible to humans "
				+" and in the process produces a sequences of quantized coefficients that are part of the compressed "
				+" representation of the image."
				+" These coefficients are the features computed by this filter.";
	}

	public JpegCoefficientFilter() {
		super();
		features = new JpegCoefficientHistogram();
	}

	protected int getNumFeatures() {
		//return features.getDoubleHistogram().length;
		return 192; // this is hardcoded due to a null-pointer error in LIRE
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
		runFilter(new JpegCoefficientFilter(), args);
	}
	
}
