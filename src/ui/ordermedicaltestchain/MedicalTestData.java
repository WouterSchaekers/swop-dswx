package ui.ordermedicaltestchain;

import controllers.MedicalTestController;

public class MedicalTestData
{
		MedicalTestController mtc;
		
		public MedicalTestData(MedicalTestController mtc) {
			this.mtc = mtc;
		}
		
		public MedicalTestController getMedicalTestController() {
			return this.mtc;
		}
}
