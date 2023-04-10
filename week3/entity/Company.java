package entity;

import java.util.ArrayList;

public class Company {
	private ArrayList<Office> listOffice;

	public Company() {
		listOffice = new ArrayList<Office>();
	}
	
	public void addOffice(Office newOffice) {
		listOffice.add(newOffice);
	}

	public ArrayList<Office> getListOffice() {
		return listOffice;
	}
	
}
