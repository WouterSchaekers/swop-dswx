package machine;


public class SurgicalEquipmentBuilder extends MachineBuilder
{

	@Override
	Machine build() {
		return new SurgicalEquipment(this.serial_, this.loc_,this.location_);
	}

	@Override
	MachineBuilder newBuilder() {
		return new SurgicalEquipmentBuilder();
	}

	@Override
	boolean sameType(MachineBuilder builder) {
		return builder instanceof SurgicalEquipmentBuilder;
	}

}
