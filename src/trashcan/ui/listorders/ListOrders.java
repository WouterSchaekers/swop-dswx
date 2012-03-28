package ui.listorders;

import system.Hospital;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class ListOrders extends ListOrdersSuper
{
	Hospital dataPasser;

	public ListOrders(UserinterfaceData data) {
		super(data);
		dataPasser = data.getDataPasser();
	}

	@Override
	public Usecase Execute() {
		try {
			//TODO: implement
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}
}
