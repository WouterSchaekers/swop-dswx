package users;

import scheduler.TimeLord;

public class HospitalUserManager extends UserManager
{
	private TimeLord _tl;
	
	public void createAndAddWarehouseAdmin(String string){
		this._tl = timeLord;
	}
}