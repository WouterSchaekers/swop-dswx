package system;

import machine.MachinePoolBuilder;
import warehouse.NormalWarehouseBuilder;

/**
 * Class for building a Campus.
 */
public class CampusBuilder
{
	private String campusName_;
	private Hospital hospital_;

	/**
	 * Default constructor.
	 * 
	 * @param string
	 *            The name of the Campus.
	 * @param hospital
	 *            The Hospital where the Campus belongs too.
	 */
	public CampusBuilder(String string, Hospital hospital) {
		this.campusName_ = string;
		this.hospital_ = hospital;
	}

	/**
	 * @return A Campus made from the given information.
	 */
	public Campus create() {
		Campus campus = new Campus(this.campusName_, this.hospital_, new NormalWarehouseBuilder(),
				new MachinePoolBuilder().create());
		return campus;
	}
}