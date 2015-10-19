package weka.filters.unsupervised.instance.imagefilter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Vector;

import javax.imageio.ImageIO;

import net.semanticmetadata.lire.imageanalysis.LireFeature;
import weka.core.Attribute;
import weka.core.Capabilities;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Capabilities.Capability;
import weka.core.Option;
import weka.core.Utils;
import weka.filters.SimpleBatchFilter;

public abstract class AbstractImageFilter extends SimpleBatchFilter {

	private static final long serialVersionUID = -407198591679465350L;

	// Directory name containing the images
	private String imageDirectory = System.getProperty("last.dir", System.getProperty("user.dir"));

	// Reference to a LireFeature object that is used to extract features
	// Unfortunately in the current version of LIRE the feature extraction
	// classes are not serializable, so I am manually saving the class name on
	// serialization and recreating the features object on deserialization
	// In the event that a future release of LIRE makes its classes serializable,
	// the feaureClass field, and the writeObject and readObject methods can be
	// removed.
	transient protected LireFeature features;
	private String featuresClass = null;

	private void writeObject(java.io.ObjectOutputStream out)
			throws IOException, ClassNotFoundException {		
		featuresClass = features.getClass().getName();
		if (getDebug())
		System.err
				.println("AbstractImageFilter:: custom writeObject method called, saving features' classname as "
						+ featuresClass);
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		in.defaultReadObject();
		if (featuresClass != null) {
			features = (LireFeature) Class.forName(featuresClass)
					.getConstructor().newInstance();
			if (getDebug())
			System.err
					.println("AbstractImageFilter:: custom readObject method called, restoring features' by instantiating class "
							+ featuresClass);
		}
	}

	
	// Global info method for describing the filter
	public String globalInfo() {
		return "An abstract batch filter for extracting features of any type from images.";
	}

	// Capabilities, indicating that any type of attribute with or without a
	// class is OK
	public Capabilities getCapabilities() {
		Capabilities result = super.getCapabilities();
		result.disableAll();
		result.enable(Capability.STRING_ATTRIBUTES);
		result.enable(Capability.NOMINAL_ATTRIBUTES);
		result.enable(Capability.NUMERIC_ATTRIBUTES);
		result.enable(Capability.NO_CLASS);
		result.enable(Capability.NOMINAL_CLASS);
		result.enable(Capability.NUMERIC_CLASS);
		return result;
	}

	// Method to construct the header for the filtered dataset
	protected Instances determineOutputFormat(Instances inputFormat) {
		Instances result = new Instances(inputFormat, 0);
		int numFeatures = getNumFeatures();
		for (int index = numFeatures - 1; index >= 0; index--) {
			result.insertAttributeAt(new Attribute(getFeatureNamePrefix()
					+ index), 1);
		}
		return result;
	}

	// Method to actually filter the dataset
	protected Instances process(Instances inst) throws Exception {
		// check that first attribute is a string
		if (!inst.attribute(0).isString()) {
			throw new Exception(
					"ImageFilter Exception: dataset's first attribute must be the string filename of an image!");
		}
		// create the new dataset
		Instances result = new Instances(determineOutputFormat(inst), 0);
		// iterate over the instances
		for (int example_index = 0; example_index < inst.numInstances(); example_index++) {
			// load the file from disk into a buffered image
			String filename = inst.instance(example_index).stringValue(0);
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(imageDirectory, filename));
			} catch (IOException e) {
				throw new Exception(
						"ImageFilter Exception: was unable to read file "
								+ filename + "!");
			}
			// extract features from the buffered image
			double[] features=getFeatures(img);
			// create the final feature vector and new instance
			double[] values = new double[result.numAttributes()];
			// copy the features to the values array
			int values_index = 1; // leave first value for the filename
			for (int index = 0; index < features.length; index++)
				values[values_index++] = features[index];
			// copy any additional attributes from the old instance to values
			for (int index = 1; index < inst.numAttributes(); index++)
				values[values_index++] = inst.instance(example_index).value(
						index);
			// create and an instance for the new dataset
			DenseInstance newInst = new DenseInstance(1, values);
			newInst.setDataset(result);
			// add the filename
			newInst.setValue(0, filename);
			// done
			result.add(newInst);
		}
		// done -- return new dataset
		return result;
	}

	// Each subclass *MUST* implement this method so that the number of produced
	// features is known!
	abstract protected int getNumFeatures();

	// Each subclass *MUST* implement this method by returning a unique feature
	// name prefix!
	abstract protected String getFeatureNamePrefix();

	// Each subclass *MUST* implement this method
	abstract protected double[] getFeatures(BufferedImage img);

	// WEKA list/get/set options
	public Enumeration<Option> listOptions() {
		Vector<Option> newVector = new Vector<Option>(2);
		newVector.addElement(new Option(
				"Name of the directory containing the images", "D", 1, "-D"));
		newVector.addElement(new Option(
				"Whether or not to cache image features in memory", "C", 1, "-C"));
		return newVector.elements();
	}

	public String getImageDirectory() {
		return imageDirectory;
	}

	public void setImageDirectory(String imageDirectory) {
		this.imageDirectory = imageDirectory;
		System.setProperty("last.dir", imageDirectory);
	}

	public String[] getOptions() {
		String[] superOptions = super.getOptions();
		String[] options = new String[superOptions.length + 2];
		int current = 0;
		options[current++] = "-D";
		options[current++] = "" + getImageDirectory();
		System.arraycopy(superOptions, 0, options, current, superOptions.length);
		current += superOptions.length;
		while (current < options.length) {
			options[current++] = "";
		}
		return options;
	}

	public void setOptions(String[] options) throws Exception {
		setImageDirectory(Utils.getOption('D', options));
		super.setOptions(options);
	}

}
