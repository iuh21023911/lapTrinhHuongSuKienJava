package week2ThongTinNhanVien;

import java.io.Serializable;
import java.util.ArrayList;

public class ListEmployee implements Serializable {
	ArrayList<Employee> list;

	public ListEmployee() {
		this.list = new ArrayList<Employee>();
	}

	public ArrayList<Employee> getList() {
		return list;
	}

	public void setList(ArrayList<Employee> list) {
		this.list = list;
	}

	public boolean addEmp(Employee newEmp) {
		if (list.contains(newEmp)) {
			return false;
		} else {
			list.add(newEmp);
			return true;
		}
	}

	public boolean deleteIndex(int index) {
		return list.remove(list.get(index));
	}

	public boolean updateEmp(String id, String lastName, String firstName, String gender, int age, double salary) {
		Employee tempEmp = new Employee(id, lastName, firstName, gender, age, salary);
		if (list.contains(tempEmp)) {
			tempEmp.setId(id);
			tempEmp.setLastName(lastName);
			tempEmp.setFirstName(firstName);
			tempEmp.setGender(gender);
			tempEmp.setAge(age);
			tempEmp.setSalary(salary);
			return true;
		} else {
			return false;
		}
	}

	public int findEmp(String id) {
		return list.indexOf(new Employee(id, "", "", "", 0, 0));
	}

}
