package machine;

/**
 * A UltraSoundScannerBuilder is a builder, used to create a UltraSound Scanner.
 */
public class UltraSoundScannerBuilder extends MachineBuilder
{

	/**
	 * The default constructor. Package visible since it should only be used by
	 * the builders.
	 */
	UltraSoundScannerBuilder() {
		;
	}

	/**
	 * Builds the UltraSound Scanner.
	 */
	public UltraSoundScanner build() {
		return new UltraSoundScanner(serial_, loc_, location_);
	}

	/**
	 * Returns a new UltraSound Scanner Builder.
	 */
	@Override
	MachineBuilder newBuilder() {
		return new UltraSoundScannerBuilder();
	}

	/**
	 * The textual representation of this UltraSound Scanner Builder.
	 */
	@Override
	public String toString() {
		return "UltraSound Scanner Builder";
	}
}