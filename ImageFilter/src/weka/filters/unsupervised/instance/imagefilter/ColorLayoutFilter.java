package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;

import net.semanticmetadata.lire.imageanalysis.ColorLayout;


public class ColorLayoutFilter extends AbstractImageFilter {

	private static final long serialVersionUID = 3969180123053302460L;

	public String globalInfo() {
		return "A batch filter for extracting MPEG7 color layout features from images.";
	}	
	public ColorLayoutFilter(){
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
	

	public static void main(String[] args) {
		runFilter(new ColorLayoutFilter(), args);
	}
	
}
