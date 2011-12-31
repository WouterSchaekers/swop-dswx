package system;

import org.junit.Test;
import ui.UserinterfaceData;
import controllers.DataPasser;
import controllers.LoginController;
import exceptions.InvalidTimeLordException;

public class ApproveDiagnoseTest
{
	
	@Test
	public void test() throws InvalidTimeLordException
	{
		StJennySystem_hot t =StJennySystem_hot.instance();
		UserinterfaceData data = new UserinterfaceData(new DataPasser(t.state.userManager,t.state.patientFileManager, t.state.scheduler,t.state.machinePool, t.state.taskManager,t.state.systemTime));
		LoginController controller = new LoginController(data.getDataPasser());
		t.state.userManager.getAllNurses();
	}
}
