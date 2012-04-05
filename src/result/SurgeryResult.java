package result;

public class SurgeryResult implements Result
{
	public final String report_;
	public final String afterCare_;

	public SurgeryResult(String report, String afterCare) {
		this.report_ = report;
		this.afterCare_ = afterCare;
	}
}