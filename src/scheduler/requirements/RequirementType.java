package scheduler.requirements;

import scheduler.HospitalDate;
import system.Location;
import warehouse.Warehouse;

/**
 * Class representing a DiagnoseCondition for a description.
 */
public class RequirementType<T extends Requirable> implements Requirement
{
	private Class<T> type_;
	private boolean backToBack_;
	private int amount_;
	
	public RequirementType(Class<T> type, boolean backToBack, int amount) {
		this.type_ = type;
		this.backToBack_ = backToBack;
		this.amount_ = amount;
	}

	@Override
	public boolean isMetBy(Requirable requirable) {
		return requirable.getClass().equals(type_);
	}
	
	@Override
	public boolean isCrucial(){
		return true;
	}

	@Override
	public boolean isMetOn(HospitalDate hospitalDate, Location location) {
		return false;
	}

	@Override
	public void collect(Warehouse warehouse) {
		;
	}

	@Override
	public boolean backToBack() {
		return backToBack_;
	}
	
	public int getAmount(){
		return this.amount_;
	}

	@Override
	public boolean isMarkedForDeletion() {
		return false;
	}
}