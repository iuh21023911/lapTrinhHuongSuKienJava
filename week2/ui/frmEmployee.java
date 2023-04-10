package ui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.Employee;

public class frmEmployee extends JFrame {
	private ArrayList<Employee> list;
	private JTable table;
	private DefaultTableModel tableModel;

	public frmEmployee(ArrayList<Employee> listEmp, String title) {
		setTitle(title);
		setSize(600,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.list = listEmp;
		String column[] = {"Mã","Họ","Tên","Phong ban","Tuổi","Tiền lương"};
		table = new JTable(tableModel = new DefaultTableModel(column,0));
		
		for(int i = 0; i < list.size();i++) {
			Employee emp = listEmp.get(i);
			String []row= {emp.getId(),emp.getLastName(),emp.getFirstName(),emp.getOffice()+"", emp.getAge()+"",emp.getSalary()+""};
			tableModel.addRow(row);
		}
		table.setRowHeight(30);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
}
