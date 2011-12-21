package medicaltest;


public class BloodAnalysisFactory extends MedicalTestFactory
{
	
	public void setFocus(String focus) {
		if(inValidFocus(focus))
			throw new IllegalArgumentException("invalid focus value");
		this.focus = focus;
	}

	private boolean inValidFocus(String focus2) {
		return focus!=null;
	}
	
	public void setNumberOfAnalysis(int numberOfAnalysis) {
		if(inValidNumberOfAnalysis(numberOfAnalysis))
			throw new IllegalArgumentException("Illegal amount of analysis");
		this.numberOfAnalysis = numberOfAnalysis;
	}
	
	private boolean inValidNumberOfAnalysis(int numberOfAnalysis2) {
		return numberOfAnalysis2>0;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	private String focus;
	private int numberOfAnalysis;
	private int duration;
	@Override
	public MedicalTest create() {
		return null;
	}
}
