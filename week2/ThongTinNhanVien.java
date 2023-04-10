package week2ThongTinNhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class ThongTinNhanVien extends JFrame implements ActionListener {
	JPanel pnBorder, pnNorth, pnCenter, pnFind, pnFeature;
	JLabel lblTitle, lblId, lblLastName, lblFirstName, lblAge, lblGender, lblSalary, lblFind;
	JTextField txtId, txtLastName, txtFirstName, txtAge, txtSalary, txtFindId;
	JRadioButton maleRad, femaleRad;
	JButton findBtn, addBtn, clearBtn, deleteBtn, saveBtn;
	DefaultTableModel model;
	JTable table;
	private ListEmployee list;
	private Databasee database;

	public ThongTinNhanVien() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 500);
		database = new Databasee();
		list = new ListEmployee();
		createGui();
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createGui() {
		add(pnBorder = new JPanel());
		pnBorder.setLayout(new BorderLayout());
		// North
		pnBorder.add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.add(lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitle.setForeground(Color.blue);
		// Center
		pnBorder.add(pnCenter = new JPanel(), BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(5));
		b.add(b4);
		b.add(Box.createVerticalStrut(5));
		pnCenter.add(b);
		pnCenter.add(Box.createVerticalStrut(10));
		b1.add(lblId = new JLabel("Mã nhân viên:"));
		b1.add(txtId = new JTextField("A02"));
		b2.add(lblLastName = new JLabel("Họ:"));
		b2.add(txtLastName = new JTextField("La"));
		b2.add(lblFirstName = new JLabel("Tên nhân viên:"));
		lblLastName.setPreferredSize(lblId.getPreferredSize());
		b2.add(txtFirstName = new JTextField("Minh Tâm"));
		b3.add(lblAge = new JLabel("Tuổi:"));
		lblAge.setPreferredSize(lblId.getPreferredSize());
		b3.add(txtAge = new JTextField("20"));
		b3.add(lblGender = new JLabel("Phái:"));
		maleRad = new JRadioButton("Nam");
		femaleRad = new JRadioButton("Nữ");
		ButtonGroup radGroup = new ButtonGroup();
		radGroup.add(maleRad);
		radGroup.add(femaleRad);
		maleRad.setSelected(true);
		b3.add(maleRad);
		b3.add(femaleRad);
		b4.add(lblSalary = new JLabel("Tiền lương:"));
		lblSalary.setPreferredSize(lblId.getPreferredSize());
		b4.add(txtSalary = new JTextField("200000"));
		// Table
		createTable();
		// South
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		pnBorder.add(split, BorderLayout.SOUTH);

		split.add(pnFind = new JPanel());
		pnFind.add(lblFind = new JLabel("Nhập số cần tìm:"));
		pnFind.add(txtFindId = new JTextField(10));
		pnFind.add(findBtn = new JButton("Tìm"));

		split.add(pnFeature = new JPanel());
		pnFeature.add(addBtn = new JButton("Thêm"));
		pnFeature.add(clearBtn = new JButton("Xóa trắng"));
		pnFeature.add(deleteBtn = new JButton("Xóa"));
		pnFeature.add(saveBtn = new JButton("Lưu"));

		addBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		findBtn.addActionListener(this);
	}

	private void createTable() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã NV");
		model.addColumn("Họ");
		model.addColumn("Tên");
		model.addColumn("Phái");
		model.addColumn("Tuồi");
		model.addColumn("Tiền lương");
		TableColumn genderColumn = table.getColumnModel().getColumn(3);
		JComboBox genderComboBox = new JComboBox();
		genderComboBox.addItem("Nam");
		genderComboBox.addItem("Nữ");
		genderColumn.setCellEditor(new DefaultCellEditor(genderComboBox));
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setPreferredSize(new Dimension(600, 250));
		pnCenter.add(sp);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtId.setText(model.getValueAt(row, 0).toString());
				txtLastName.setText(model.getValueAt(row, 1).toString());
				txtFirstName.setText(model.getValueAt(row, 2).toString());
				if (model.getValueAt(row, 3).toString().equals("Nam")) {
					maleRad.setSelected(true);
				} else {
					femaleRad.setSelected(true);
				}
				txtAge.setText(model.getValueAt(row, 4).toString());
				txtSalary.setText(model.getValueAt(row, 5).toString());
			}
		});
	}

	public void loadData() throws Exception {
		list = database.read_NV("Nhanvien.txt");
//		list = (ListEmployee) database.readFile("Nhanvien.dat");
		if (list == null) {
			list = new ListEmployee();
		} else {
			for (Employee emp : list.getList()) {
				String[] row = { emp.getId(), emp.getLastName(), emp.getFirstName(), emp.getGender(), emp.getAge() + "",
						emp.getSalary() + "" };
				model.addRow(row);
			}
		}
	}

	public static void main(String[] args) {
		new ThongTinNhanVien().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(addBtn)) {
			switchBtnMode();
		} else if (o.equals(saveBtn)) {
			if (txtId.getText().equals("") || txtLastName.getText().equals("") || txtFirstName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin");
			} else {
				try {
					save();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} else if (o.equals(deleteBtn)) {
			try {
				deleteRow();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (o.equals(clearBtn)) {
			clear();
		} else if (o.equals(findBtn)) {
			int pos = list.findEmp(this.txtFindId.getText());
			System.out.println(pos);
			if (pos == -1) {
				JOptionPane.showMessageDialog(this, "Không tôn tại nhân viên có mã nãy!");
			} else {
				table.setRowSelectionInterval(pos, pos);
			}
		}
	}

	public void save() throws Exception {
		if (validData()) {
			String id = txtId.getText();
			String lastName = txtLastName.getText();
			String firstName = txtFirstName.getText();
			String gender = "";
			if (maleRad.isSelected()) {
				gender = maleRad.getText();
			} else {
				gender = femaleRad.getText();
			}
			String age = txtAge.getText();
			String salary = txtSalary.getText();

			Employee emp = new Employee(id, lastName, firstName, gender, Integer.parseInt(age),
					Double.parseDouble(salary));
			if (list.addEmp(emp)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				String[] row = { id, lastName, firstName, gender, age, salary };
				model.addRow(row);
				database.writeNV("Nhanvien.txt", list);
//				database.saveFile("Nhanvien.dat", list);
			} else {
				JOptionPane.showMessageDialog(this, "Id này đã tồn tại");
				txtId.setText("");
			}
		}
	}

	public void switchBtnMode() {
		if (addBtn.getText().equals("Thêm")) {
			clear();
			this.txtId.requestFocus();
			deleteBtn.setEnabled(false);
			addBtn.setText("Hủy");
		} else {
			deleteBtn.setEnabled(true);
			addBtn.setText("Thêm");
		}
	}

	public void clear() {
		txtId.setText("");
		txtLastName.setText("");
		txtFirstName.setText("");
		maleRad.setSelected(false);
		femaleRad.setSelected(false);
		txtAge.setText("");
		txtSalary.setText("");
		txtId.requestFocus();
	}

	public void deleteRow() throws Exception {
		int rowIndex = table.getSelectedRow();
		if (rowIndex != -1) {
			int notify = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa dòng này không?", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (notify == JOptionPane.YES_OPTION) {
				list.deleteIndex(rowIndex);
				model.removeRow(rowIndex);
				clear();
				database.writeNV("Nhanvien.txt", list);
//				database.saveFile("Nhanvien.dat", list);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
		}
	}

	private boolean validData() {
		String id = txtId.getText().trim();
		String lastName = txtLastName.getText().trim();
		String firstName = txtFirstName.getText().trim();
		int age = Integer.parseInt(txtAge.getText());
		String gender = "";
		if (maleRad.isSelected()) {
			gender = maleRad.getText();
		} else {
			gender = femaleRad.getText();
		}
		double salary = Double.parseDouble(txtSalary.getText());

		if (!(id.length() > 0 && id.matches("(NV)\\d{3}"))) {
			JOptionPane.showMessageDialog(null, "Error: Mã sách theo mẫu: \"[NV]\\\\d{3}\"");
			return false;
		}
		if (!(lastName.length() > 0 && lastName.matches(".[A-Za-z0-9]+"))) {
			JOptionPane.showMessageDialog(null, "Error: Họ theo mẫu: \".[A-Za-z0-9]+\"");
			txtLastName.requestFocus();
			return false;
		}
		if (!(firstName.length() > 0 && firstName.matches(".[\\D\\s]++"))) {
			JOptionPane.showMessageDialog(null, "Error: Tên theo mẫu: \".[\\D\\s]+\"");
			txtFirstName.requestFocus();
			return false;
		}
		if (age >= 18 && age <= 60) {
			try {
				int x = Integer.parseInt(txtAge.getText());
				if (x <= 0) {
					JOptionPane.showMessageDialog(null, "Error: Số tuổi phải nhập số nguyên dương");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: Số tuổi phải nhập số nguyên dương");
				return false;
			}
		}
		if (salary >= 0) {
			try {
				double x = Double.parseDouble(txtSalary.getText());
				if (x <= 0) {
					JOptionPane.showMessageDialog(null, "Error: Số tiền phải nhập số lớn hơn 0");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error: Số tiền phải nhập số lớn hơn 0");
				return false;
			}
		}
		return true;

	}
}
