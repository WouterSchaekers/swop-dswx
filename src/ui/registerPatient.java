package ui;

public class registerPatient extends usecase
{

	public registerPatient(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		// TODO Auto-generated method stub
		return new CreateRegisterController(data);
	}

}
