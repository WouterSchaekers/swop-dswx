package machine;

/**
 * A SurgicalEquipmentBuilder is a builder, used to create a Surgical Equipment.
 */
public class SurgicalEquipmentBuilder extends MachineBuilder
{
	/**
	 * The default constructor. Package visible since it should only be used by
	 * the builders.
	 */
	public SurgicalEquipmentBuilder() {
		;
	}

	/**
	 * Builds the Surgical Equipment.
	 */
	@Override
	Machine build() {
		return new SurgicalEquipment(this.serial_, this.loc_, this.location_);
	}

	/**
	 * Returns a new Surgical Equipment Builder.
	 */
	@Override
	MachineBuilder newBuilder() {
		return new SurgicalEquipmentBuilder();
	}

	/**
	 * The textual representation of this Surgical Equipment Builder.
	 */
	@Override
	public String toString() {
		return "Surgical Equipment Builder";
	}
}