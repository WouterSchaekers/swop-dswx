package machine;

/**
 * A XRayScannerBuilder is a builder, used to create a XRay Scanner.
 */
public class XRayScannerBuilder extends MachineBuilder
{
	
	/**
	 * The default constructor. Package visible since it should only be used by
	 * the builders.
	 */
	XRayScannerBuilder() {
		;
	}

	/**
	 * Builds the XRay Scanner.
	 */
	XRayScanner build() {
		return new XRayScanner(serial_, loc_, location_);
	}

	/**
	 * Returns a new XRay Scanner Builder.
	 */
	@Override
	MachineBuilder newBuilder() {
		return new XRayScannerBuilder();
	}
	
	/**
	 * The textual representation of this XRay Scanner Builder.
	 */
	@Override
	public String toString() {
		return "XRay Scanner Builder";
	}
}