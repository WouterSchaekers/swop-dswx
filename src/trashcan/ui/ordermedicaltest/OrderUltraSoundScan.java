package ui.ordermedicaltest;

import medicaltest.UltraSoundScanFactory;
import ui.Usecase;
import ui.UserinterfaceData;

public class OrderUltraSoundScan extends OrderMedicalTestSuperClass
{

	public OrderUltraSoundScan(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		System.out.println("Ordering ultrasound scan for :"
				+ data.getPatientFileOpenController().getPatientFile()
						.getName());
		UltraSoundScanFactory fac = (UltraSoundScanFactory) chaindata
				.getFactory();
		System.out.println("Enter extra scan info:");
		String in = input.nextLine();
		fac.setScanInfo(in);
		boolean set = false;
		while (!set) {
			System.out.println("Record images? y/n");
			in = input.nextLine();
			if (in.equalsIgnoreCase("n")) {
				fac.setRecordImages(false);
				set = true;
			}
			if (in.equalsIgnoreCase("y")) {
				fac.setRecordImages(true);
				set = true;
			}
		}
		set = false;
		while (!set) {
			System.out.println("Record vid? y/n");
			in = input.nextLine();
			if (in.equalsIgnoreCase("n")) {
				fac.setRecordVid(false);
				set = true;
			}
			if (in.equalsIgnoreCase("y")) {
				fac.setRecordVid(true);
				set = true;
			}
		}
		chaindata.setFactory(fac);
		return new ScheduleMedicalTest(data, chaindata);

	}

}
