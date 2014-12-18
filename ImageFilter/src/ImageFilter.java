import weka.core.*;
import weka.core.Capabilities.*;
import weka.filters.*;

public class ImageFilter extends SimpleBatchFilter {

	private static final long serialVersionUID = 8658527406486139573L;

	public String globalInfo() {
		return "A batch filter for extracting features from images.";
	}

	public Capabilities getCapabilities() {
		Capabilities result = super.getCapabilities();
		result.enable(Capability.STRING_ATTRIBUTES);
		result.enable(Capability.NOMINAL_CLASS);
		result.enable(Capability.NUMERIC_CLASS);
		return result;
	}

	protected Instances determineOutputFormat(Instances inputFormat) {
		Instances result = new Instances(inputFormat, 0);
		result.insertAttributeAt(new Attribute("bla"), result.numAttributes());
		return result;
	}

	protected Instances process(Instances inst) {
		Instances result = new Instances(determineOutputFormat(inst), 0);
		for (int i = 0; i < inst.numInstances(); i++) {
			double[] values = new double[result.numAttributes()];
			for (int n = 0; n < inst.numAttributes(); n++)
				values[n] = inst.instance(i).value(n);
			values[values.length - 1] = i;
			result.add(new DenseInstance(1, values));
		}
		return result;
	}

	public static void main(String[] args) {
		runFilter(new ImageFilter(), args);
	}
}