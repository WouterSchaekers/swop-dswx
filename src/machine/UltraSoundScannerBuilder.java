package machine;


public class UltraSoundScannerBuilder extends MachineBuilder
{
	UltraSoundScannerBuilder() {
		super();
	}

	public String toString() {
		return "UltraSoundScanner";

	}

	public UltraSoundScanner build() {
		return new UltraSoundScanner(serial_, loc_, location_);
	}

	@Override
	MachineBuilder newBuilder() {
		return new UltraSoundScannerBuilder();
	}

	@Override
	boolean sameType(MachineBuilder builder) {
		return builder instanceof UltraSoundScannerBuilder;
	}
}
