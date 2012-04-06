package controllers.tests;

import static org.junit.Assert.*;
import java.util.Collection;
import machine.Machine;
import machine.MachineBuilder;
import org.junit.Before;
import org.junit.Test;
import system.Campus;
import system.ExtendedStandardHospitalBuilder;
import system.Hospital;
import system.Location;
import users.HospitalAdmin;
import users.User;
import controllers.AddHospitalEquipmentController;
import controllers.LoginController;
import controllers.interfaces.LocationIN;
import controllers.interfaces.MachineIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidSerialException;

public class AddHospitalEquipmentControllerTest
{
	AddHospitalEquipmentController controller;
	Hospital hospital;
	Campus campus;
	@Before
	public void init() throws InvalidHospitalException, InvalidLoginControllerException
	{
		hospital=new ExtendedStandardHospitalBuilder().build();
		campus=hospital.getAllCampuses().iterator().next();
		LoginController loginc = new LoginController(hospital);
		loginc.logIn(select(loginc.getAllUsers(),HospitalAdmin.class,"Thibault Leemans"), campus);
		controller = new AddHospitalEquipmentController(loginc);
	}
	@Test
	public void createAllTheMachines() throws InvalidLocationException, InvalidSerialException
	{
		int number =10;
		for(MachineBuilder builder:controller.getAllMachineBuilders())
			for(LocationIN location :controller.getAllLocations())
			{
				builder.setLocation((Location) location);
				builder.setLocationWithinCampus("Thuis"+number);
				builder.setSerial(number);
				MachineIN machine = controller.createMachine(builder);
				assertTrue(machine.getSerial()==number);
				assertTrue(machine.getLocationWithinCampus().equals("Thuis"+(number)));
				number++;
			}
	}
	private UserIN select(Collection<UserIN> allUsers, Class<? extends User> class1, String string) {
		for(UserIN user:allUsers)
			if(user.getClass().equals(class1)&&user.getName().equals(string))
				return user;
		return null;
	}
}
