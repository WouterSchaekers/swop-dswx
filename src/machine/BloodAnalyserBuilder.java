package machine;

/**
 * A BloodAnalyserBuilder is a builder, used to create a Blood Analyser.
 */
public class BloodAnalyserBuilder extends MachineBuilder
{

	/**
	 * The default constructor. Package visible since it should only be used by
	 * the builders.
	 */
	BloodAnalyserBuilder() {
		;
	}

	/**
	 * Builds the Blood Analyser.
	 */
	@Override
	Machine build() {
		return new BloodAnalyser(this.serial_, this.loc_, this.location_);
	}

	/**
	 * Returns a new Blood Analyser Builder.
	 */
	@Override
	MachineBuilder newBuilder() {
		return new BloodAnalyserBuilder();
	}
	
	/**
	 * The textual representation of this Blood Analyser Builder.
	 */
	@Override
	public String toString() {
		return "Blood Analyser Builder";
	}
}