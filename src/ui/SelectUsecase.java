package ui;

import help.Collections;
import help.Filter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import ui.addhospitalequipment.AddHopsitalEquipment;
import ui.addhospitalstaff.CreateUser;
import ui.advancetime.AdvanceTimeSuperClass;
import ui.approvediagnosis.ApproveDiagnosis;
import ui.closepatientfile.ClosePatientFile;
import ui.consultpatientfile.ConsultPatientFile;
import ui.dischargepatient.DischargePatient;
import ui.enterdiagnosis.EnderDiagnosis;
import ui.entermedicaltestresult.EnterMedicalTestResult;
import ui.login.IsAllowedToLogin;
import ui.logout.LogOut;
import ui.ordermedicaltest.OrderMedicalTest;
import ui.prescribetreatment.PrescribeTreatment;
import ui.registerpatient.RegisterPatient;
import ui.reviewpatient.ReviewPatient;
import controllers.AddHospitalEquipmentController;
import controllers.ApproveDiagnoseController;
import controllers.AddHospitalStaffController;
import controllers.EnterDiagnoseController;
import controllers.FillStockInWarehouseController;
import controllers.LoginController;
import controllers.OrderMedicalTestController;
import controllers.ConsultPatientFileController;
import controllers.PrescribeTreatmentController;
import controllers.RegisterPatientController;

/**
 * This class represents a menu to select what a user wants to do. You can
 * choose different options that are described in the enumeration below.
 */

public class SelectUsecase extends Usecase
{

	/**
	 * This enumeration is created to simplify the different use cases.
	 * 
	 * Login: a user logs in to the system. RegisterPatient: The nurse logs the
	 * new patient in to the system. Nothing: Returns immediatly to the select
	 * usecase. Logout: a user can log out from the system. Exit System: The
	 * system will be closed.
	 * 
	 * 
	 * 
	 */
	private interface Creator
	{
		Usecase create(UserinterfaceData data) throws Exception;
	}

	enum usecases
	{
		login("login", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				new LoginController(data.getDataPasser());
				if (data.getLoginController() != null)
					throw new Exception();
				return new IsAllowedToLogin(data);
			}
		}), logout("logout", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				if (data.getLoginController() == null)
					throw new Exception();
				return new LogOut(data);
			}
		}), RegisterPatient("register patient", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				new RegisterPatientController(data.getLoginController(),
						data.getDataPasser());
				return new RegisterPatient(data);
			}
		}), orderMedicalTest("order medical test", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				new OrderMedicalTestController(data.getLoginController(),
						data.getPatientFileOpenController(),
						data.getDataPasser());
				return new OrderMedicalTest(data);
			}
		}), addHospitalEquipment("add hospital equipment", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				new AddHospitalEquipmentController(data.getLoginController(),
						data.getDataPasser());
				return new AddHopsitalEquipment(data);
			}
		}), addhospitalstaff("add hospital staff", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				new AddHospitalStaffController(data.getLoginController(),
						data.getDataPasser());
				return new CreateUser(data);
			}
		}), advanceTime("advance time", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				// TODO new AdvanceTimeController(data.getLoginController(),
				// data.getDataPasser());
				return new AdvanceTimeSuperClass(data);
			}
		}), enterDiagnose("enter diagnose", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				new EnterDiagnoseController(null, data.getLoginController(),
						data.getPatientFileOpenController());
				return new EnderDiagnosis(data);
			}
		}), approveDiagnose("approve diagnose", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				new ApproveDiagnoseController(data.getDataPasser(),
						data.getLoginController());
				return new ApproveDiagnosis(data);
			}
		}), consultpatientfile("consult patient file", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				if (data.getPatientFileOpenController() != null)
					throw new Exception();
				new ConsultPatientFileController(data.getDataPasser(),
						data.getLoginController());

				return new ConsultPatientFile(data);
			}
		}), reviewPatienfFile("Review patient file", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				if (data.getPatientFileOpenController() == null)
					throw new Exception();
				return new ReviewPatient(data);
			}

		}), dischargePatient("Discharge patient", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				// TODO: new
				// DischargePatientController(data.getLoginController(),
				// data.getPatientFileOpenController());
				return new DischargePatient(data);
			}
		}), closePatientFile("Close patient file,", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				if (data.getPatientFileOpenController() == null
						|| !data.getPatientFileOpenController()
								.isValidLoginController(
										data.getLoginController()))
					throw new Exception();
				return new ClosePatientFile(data);

			}

		}), enterMedicalTestResult("Enter medtest result:", new Creator()
		{
			public Usecase create(UserinterfaceData data) throws Exception {
				// TODO new
				// EnterMedicaltestResultController(data.getLoginController(),data.getDataPasser());
				return new EnterMedicalTestResult(data);

			}
		}), fillStockInWarehouse("Fill stock in warehouse", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				new FillStockInWarehouseController(data.getDataPasser(),
						data.getLoginController());
				return null;
			}
		}), prescribeTreatment("Prescribe treatment", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				new PrescribeTreatmentController(data.getLoginController(),
						data.getPatientFileOpenController());
				return new PrescribeTreatment(data);
			}
		}), exitSystem("exit system", new Creator()
		{

			@Override
			public Usecase create(UserinterfaceData data) throws Exception {
				return null;
			}
		});
		String description;
		Creator creator;

		boolean canbeactivated(UserinterfaceData data) {
			try {
				this.creator.create(data);
			} catch (Exception e) {
				return false;
			}
			return true;
		}

		/**
		 * Gives a description of each different use case
		 */
		private usecases(String descr, Creator create) {
			this.description = descr;
			this.creator = create;
		}

		/**
		 * 
		 * @param i
		 *            Represents the number associated with the use case.
		 * @return The use case analogue to his number. If a wrong number is
		 *         given the system does nothing
		 */
		public static usecases fromInt(int i) {
			for (usecases u : usecases.values())
				if (u.ordinal() == i)
					return u;
			return null;
		}
	}

	/**
	 * Initiates a new use case with data given.
	 * 
	 * @param data
	 *            Data is the information that the user interface must past,
	 *            requires to create controllers and retains state information.
	 */

	public SelectUsecase(UserinterfaceData data) {
		super(data);
	}

	/**
	 * In this method the initial chain is started. You can choose the different
	 * use cases.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Usecase Execute() {
		Collection<usecases> executableUsecases = Collections.filter(
				Arrays.asList(usecases.values()), new Filter()
				{

					@Override
					public <T> boolean allows(T arg) {
						return ((usecases) arg).canbeactivated(data);
					}
				});
		Map<String, usecases> menuOptions = new HashMap<String, SelectUsecase.usecases>();
		for (usecases usecase : executableUsecases) {
			menuOptions.put(usecase.description, usecase);
		}

		int i = 0;
		Collection<Entry<String, usecases>> t = menuOptions.entrySet();
		Object[] array = t.toArray();
		Arrays.sort(array, new Comparator<Object>()
		{

			@Override
			public int compare(Object o1, Object o2) {
				return new Integer(((Entry<String, usecases>) o1).getValue()
						.ordinal()).compareTo(new Integer(
						((Entry<String, usecases>) o2).getValue().ordinal()));

			}
		});
		Map<Integer, String> selectionOptions = new HashMap<Integer, String>();
		i = 0;
		for (Object entry : array) {
			selectionOptions.put(++i,
					((Entry<String, usecases>) entry).getKey());
		}

		System.out.println("Select what you would like to do: ");
		System.out.println("type the number of the new usecase");
		i = 0;
		for (Object entry : array) {
			System.out.println(++i + ": "
					+ ((Entry<String, usecases>) entry).getKey());
		}
		String in = input.nextLine();
		try {
			i = new Integer(in);
		} catch (NumberFormatException num) {
			System.out.println(in + " is not a valid number");
			return this;
		}
		if (!selectionOptions.keySet().contains(i))
			return this;
		try {
			return menuOptions.get(selectionOptions.get(i)).creator
					.create(data);
		} catch (Exception e) {
		}
		return this;
	}

}
