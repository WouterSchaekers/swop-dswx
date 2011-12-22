package scheduler.task.unscheduled;

import medicaltest.FactoryInstantiation;
import medicaltest.FactoryTestsMedicalTests;
import medicaltest.MedicalTests;
import medicaltest.XRayScan;
import medicaltest.XRayScanFactory;
import org.junit.Test;
import test.DefaultHospital;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class UnscheduledXRayScanTest
{

	@Test
	public void test() throws InvalidHospitalDateException,
			UserAlreadyExistsException, InvalidNameException,
			InvalidTimeSlotException, InvalidLocationException,
			InvalidSerialException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, FactoryInstantiation, InvalidSchedulingRequestException {
		DefaultHospital dh = new DefaultHospital();
		XRayScanFactory xrf = FactoryTestsMedicalTests.filterXrayScan(new MedicalTests().factories());
		xrf.numberOfNeededImages(1);
		xrf.setBodyPart("head");
		xrf.setZoomLevel(2.5f);
		XRayScan xscan = (XRayScan) xrf.create();
		
		UnscheduledXRayScan uxr = new UnscheduledXRayScan(xscan, dh.s.getCurrentSystemTime(), dh.um, dh.mp);
		System.out.println(dh.s.schedule(uxr));
	}

}
