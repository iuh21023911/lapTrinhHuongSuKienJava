package week2ThongTinNhanVien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Databasee {
	public ListEmployee read_NV(String part) throws Exception {
		ListEmployee list = new ListEmployee();
		File f = new File(part);
		if (f.exists()) {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(",");
				Employee emp = new Employee(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]),
						Double.parseDouble(data[5]));
				list.addEmp(emp);
			}
			sc.close();
		} else {
			f.createNewFile();
		}
		return list;
	}

	public void writeNV(String part, ListEmployee list) throws Exception {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(part), true)) {
			for (Employee emp : list.getList()) {
				String data = emp.getId() + "," + emp.getLastName() + "," + emp.getFirstName() + "," + emp.getGender()
						+ "," + emp.getAge() + "," + emp.getSalary();
				out.println(data);
			}
		}
	}

	public void saveFile(String fileName, Object o) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "IO Error!");
			return;
		}
	}

	public Object readFile(String fileName) {
		Object o = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			o = ois.readObject();
			ois.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "IO Error!");
		}
		return o;
	}
}
