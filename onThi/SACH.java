package laMinhTam_21023911;

import java.io.Serializable;
import java.util.Objects;

public class SACH implements Serializable{
	private int ma;
	private String ten;
	private int soTrang;
	private String theLoai;
	private String nxb;

	public SACH(int ma, String ten, int soTrang, String theLoai, String nxb) {
		this.ma = ma;
		this.ten = ten;
		this.soTrang = soTrang;
		this.theLoai = theLoai;
		this.nxb = nxb;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ma);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SACH other = (SACH) obj;
		return ma == other.ma;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}
	
	public String getNxb() {
		return nxb;
	}

	public void setNxb(String nxb) {
		this.nxb = nxb;
	}

}
