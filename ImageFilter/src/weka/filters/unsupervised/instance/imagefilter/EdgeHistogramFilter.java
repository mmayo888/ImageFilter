package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.EdgeHistogram;

public class EdgeHistogramFilter extends AbstractImageFilter {

	private static final long serialVersionUID = 7560143299904308904L;
	
	public String globalInfo() {
		return "A batch filter for extracting MPEG7 edge histogram features from images."+
	" Edges are the lines or discontinuities in an image. "+
	" An edge histrogram is therefore a summary of the directions that the edges are going in across an image.";
	}

	public EdgeHistogramFilter() {
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
		runFilter(new EdgeHistogramFilter(), args);
	}
	
}