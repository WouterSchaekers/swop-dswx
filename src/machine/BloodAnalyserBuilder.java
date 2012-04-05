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
	 * The textual representation of this blood analyser.
	 */
	@Override
	public String toString() {
		return "Blood Analyser";

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
	 * Checks whether the given builder is a Blood Analyser Builder.
	 */
	@Override
	boolean sameType(MachineBuilder builder) {
		return builder instanceof BloodAnalyserBuilder;
	}
}