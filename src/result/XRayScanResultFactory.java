package result;

import exceptions.FactoryInstantiationException;

public class XRayScanResultFactory implements ResultFactory
{

	private String abnormalities;
	private Integer numberOfImages;

	@Override
	public Result create() throws FactoryInstantiationException {
		if(!isReady())
			throw new FactoryInstantiationException("insufficiently instantiated");
		return new XrayscanResult(abnormalities,numberOfImages);
	}

	private boolean isReady() {
		if(!abnormalitiesReady())
			return false;
		if(!numberOfImagesReady())
			return false;
		return true;
	}

	private boolean numberOfImagesReady() {
		return numberOfImages!=null&&numberOfImages>=0;
	}

	private boolean abnormalitiesReady() {
		return abnormalities!=null;
	}
	public void setAbnormalities(String abString)
	{
		this.abnormalities=abString;
	}
	public void setNumberOfImages(int numberOfImages)
	{
		this.numberOfImages = numberOfImages;
	}
	
}
