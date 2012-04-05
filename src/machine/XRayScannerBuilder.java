package machine;


public class XRayScannerBuilder extends MachineBuilder
{
	XRayScannerBuilder() {
		super();

	}

	public String toString() {
		return "XrayScanner";
	}

	XRayScanner build() {
		return new XRayScanner(serial_, loc_, location_);
	}

	@Override
	MachineBuilder newBuilder() {
		return new XRayScannerBuilder();
	}

	@Override
	boolean sameType(MachineBuilder builder) {
		return builder instanceof XRayScannerBuilder;
	}
}
