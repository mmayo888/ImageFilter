package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.FuzzyOpponentHistogram;


public class FuzzyOpponentHistogramFilter extends AbstractImageFilter {

	private static final long serialVersionUID = 13532128033291482L;

	public String globalInfo() {
		return "A batch filter for extracting a fuzzy 64-bin histogram, based on the Opponent color space, from images.";
	}	
	public FuzzyOpponentHistogramFilter(){
		super();
		features=new FuzzyOpponentHistogram();
	}
	
	protected int getNumFeatures(){
		//return features.getDoubleHistogram().length;
		return 576; // this is hardcoded due to a null-pointer error in LIRE
		            // if LIRE changes this value then this filter will break
	}
	
	protected String getFeatureNamePrefix(){
		return features.getFeatureName();
	}
	
	protected double[] getFeatures(BufferedImage img){
		features.extract(img);
		return features.getDoubleHistogram();
	}
	

	public static void main(String[] args) {
		runFilter(new FuzzyOpponentHistogramFilter(), args);
	}
	
}
