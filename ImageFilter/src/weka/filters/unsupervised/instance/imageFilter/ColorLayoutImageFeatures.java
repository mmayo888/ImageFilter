package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.ColorLayout;


public class ColorLayoutImageFeatures extends AbstractImageFeatures {

	private static final long serialVersionUID = 3969180123053302460L;

	protected ColorLayout features;
	
	public ColorLayoutImageFeatures(){
		super();
		features=new ColorLayout();
	}
	
	protected int getNumFeatures(){
		return features.getDoubleHistogram().length;
	}
	
	protected String getFeatureNamePrefix(){
		return features.getFeatureName();
	}
	
	protected double[] getFeatures(BufferedImage img){
		features.extract(img);
		return features.getDoubleHistogram();
	}
	
	public String globalInfo() {
		return "A batch filter for extracting MPEG7 color layout features from images.";
	}

	public static void main(String[] args) {
		runFilter(new ColorLayoutImageFeatures(), args);
	}
	
}
