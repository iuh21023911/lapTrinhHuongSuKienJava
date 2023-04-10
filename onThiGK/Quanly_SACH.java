package laMinhTam_21023911;

import java.io.Serializable;
import java.util.ArrayList;

public class Quanly_SACH implements Serializable{
	private ArrayList<SACH> list;
	
	public Quanly_SACH() {
		this.list = new ArrayList<SACH>();
	}

	public ArrayList<SACH> getList() {
		return list;
	}

	public void setList(ArrayList<SACH> list) {
		this.list = list;
	}
	
	public boolean them(SACH s) {
		if(list.contains(s)) {
			return false;
		}
		list.add(s);
		return true;
	}
	public boolean xoa(int index) {
		return list.remove(list.get(index));
	}
	
	public int tim(int index) {
		return list.indexOf(new SACH(index, "", 0, "", ""));
	}
}
