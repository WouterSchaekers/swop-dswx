package result;

public class MedicationResult implements Result
{
	public final boolean abnormalReaction_;
	public final String report_;

	public MedicationResult(boolean abnormalReaction, String report) {
		this.abnormalReaction_ = abnormalReaction;
		this.report_ = report;
	}
}