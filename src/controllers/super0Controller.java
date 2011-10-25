package controllers;

public class super0Controller extends superController
{
	private ConsultPatientFileController cpf;

	public super0Controller(LoginController lc, ConsultPatientFileController cpf)
			throws IllegalArgumentException {
		super(lc);
		this.cpf = cpf;
	}

	protected ConsultPatientFileController getConsultPatientFileController() {
		return cpf;
	}

}
