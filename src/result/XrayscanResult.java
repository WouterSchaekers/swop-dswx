package result;

public class XrayscanResult implements Result
{

	public final String abnormalities;
	public final Integer numberOfImages;

	public XrayscanResult(String abnormalities, Integer numberOfImages) {
		this.abnormalities = abnormalities;
		this.numberOfImages = numberOfImages;
	}

}
