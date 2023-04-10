package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Office {
	private String id;
	private String name;
	private ArrayList<Employee> list;

	public Office(String maPhong, String tenPhong) {
		this.id = maPhong;
		this.name = tenPhong;
		list = new ArrayList<Employee>();
	}

	public void addNhanVien(String id, String firstName, String lastName, boolean gender, int age, long salary) {
		Employee emp = new Employee(id, lastName, firstName, age, salary, this);
		list.add(emp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Office other = (Office) obj;
		return Objects.equals(name, other.name);
	}

	public ArrayList<Employee> getList() {
		return list;
	}

	public void setList(ArrayList<Employee> list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("%s", name);
	}

}
