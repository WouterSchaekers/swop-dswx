package ui.ordermedicaltest;

import medicaltest.XRayScanFactory;
import ui.Usecase;
import ui.UserinterfaceData;

public class OrderXrayScan extends OrderMedicalTestSuperClass
{

	public OrderXrayScan(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		XRayScanFactory fac = (XRayScanFactory) chaindata.getFactory();
		System.out.println("Xray scan:");
		System.out.println("Enter the bodypart that is to be scanned");
		String in = input.nextLine();
		fac.setBodyPart(in);
		System.out.println("Enter zoom level : number between 1 and 3 ( float)");
		float zoom  =-1;
		boolean go = true;
		do{try{
			in = input.nextLine();
			zoom = new Float(in);
			fac.setZoomLevel(zoom);
			go=false;
		}catch(Exception e){
			System.out.println("Invalid number input try againS");
			continue;
			}
		}while(go);
		chaindata.setFactory(fac);
		return new ScheduleMedicalTest(data, chaindata);
	}

}
