package ui.ordermedicaltestchain;

import java.util.ArrayList;
import controllers.MedicalTestController;
import java.util.Arrays;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestTypes;

public class MedicalTestData
{
	ArrayList<MedicalTestTypes> types = new ArrayList<MedicalTestTypes>(Arrays.asList(MedicalTestTypes.values()));
	private MedicalTest m;
	private MedicalTestController mc;
	private String bodypart;
	private int amount;
	private String focus;
	private boolean recVid;
	private boolean recImg;
	private int zoomlevel;
	
	public int getZoomlevel() {
		return this.zoomlevel;
	}
	
	public void setZoomlevel(int zoomlevel) {
		this.zoomlevel = zoomlevel;
	}
	
	public String getBodypart() {
		return this.bodypart;
	}
	
	public void setBodyPart(String bodypart) {
		this.bodypart = bodypart;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}	
	
	public void setMedTestController(MedicalTestController mc) {
		this.mc = mc;
	}
	
	public MedicalTestController getMedTestController() {
		return this.mc;
	}
	
	public void setTest(MedicalTest m) {
		this.m=m;
	}
	
	public MedicalTest getTest() {
		return this.m;
	}
	
	public String getFocus() {
		return this.focus;
	}
	
	public void setFocus(String bodypart) {
		this.bodypart = bodypart;
	}
		

	public void setVid(boolean vid) {
		this.recVid = vid;
	}
	
	public boolean getVid() {
		return this.recVid;
	}
	
	public void setImg(boolean img) {
		this.recImg = img;
	}
	
	public boolean getImg() {
		return this.recImg;
	}
	

}
