package laMinhTam_21023911;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ThongTinSach extends JFrame implements ActionListener {
	private Databasee db;
	private Quanly_SACH list;
	JPanel pnBorder, pnNorth, pnCenter, pnSouth;
	JLabel lblTieuDe, lblMa, lblTen, lblSoTrang, lblTheLoai, lblNXB, lblTim;
	JComboBox cbTheLoai;
	JTextField txtMa, txtTen, txtSoTrang, txtNXB, txtTim;
	JButton timBtn, themBtn, xoaTrangBtn, xoaBtn, luuBtn;
	DefaultTableModel model;
	JTable table;

	public ThongTinSach() {
		list = new Quanly_SACH();
		db = new Databasee();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		createGui();
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createGui() {
		add(pnBorder = new JPanel());
		pnBorder.setLayout(new BorderLayout());
		// North
		pnBorder.add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.add(lblTieuDe = new JLabel("THÔNG TIN SÁCH"));
		lblTieuDe.setFont(new Font("Arial", Font.PLAIN, 24));
		lblTieuDe.setForeground(Color.BLUE);
		// Center
		pnBorder.add(pnCenter = new JPanel(), BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(5));
		pnCenter.add(b);
		pnCenter.add(Box.createVerticalStrut(10));
		b1.add(lblMa = new JLabel("Mã Sách"));
		lblMa.setPreferredSize(new Dimension(80, 20));
		b1.add(txtMa = new JTextField("111111"));
		b2.add(lblTen = new JLabel("Tên Sách:"));
		lblTen.setPreferredSize(lblMa.getPreferredSize());
		b2.add(txtTen = new JTextField("Lập trình hướng sự kiện Java"));
		b2.add(lblSoTrang = new JLabel("Số Trang"));
		lblSoTrang.setPreferredSize(lblMa.getPreferredSize());
		b2.add(txtSoTrang = new JTextField("156"));
		b3.add(lblTheLoai = new JLabel("Thể Loại:"));
		lblTheLoai.setPreferredSize(lblMa.getPreferredSize());
		b3.add(cbTheLoai = new JComboBox());
		cbTheLoai.addItem("Toán");
		cbTheLoai.addItem("Tin Học");
		b3.add(lblNXB = new JLabel("Nhà Xuất Bản"));
		lblNXB.setPreferredSize(lblMa.getPreferredSize());
		b3.add(txtNXB = new JTextField("Khoa Công nghệ Thông tin IUH"));
		// Table
		createTable();
		// South
		pnBorder.add(pnSouth = new JPanel(), BorderLayout.SOUTH);
		pnSouth.add(lblTim = new JLabel("Nhập mã sách cần tìm:"));
		pnSouth.add(txtTim = new JTextField(20));
		pnSouth.add(timBtn = new JButton("Tìm"));
		pnSouth.add(themBtn = new JButton("Thêm"));
		pnSouth.add(xoaTrangBtn = new JButton("Xóa trắng"));
		pnSouth.add(xoaBtn = new JButton("Xóa"));
		pnSouth.add(luuBtn = new JButton("Lưu"));
		timBtn.addActionListener(this);
		themBtn.addActionListener(this);
		xoaTrangBtn.addActionListener(this);
		xoaBtn.addActionListener(this);
		luuBtn.addActionListener(this);
	}

	public void createTable() {
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã Sách");
		model.addColumn("Tên Sách");
		model.addColumn("Số Trang");
		model.addColumn("Thể Loại");
		model.addColumn("Nhà Xuất Bản");
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnCenter.add(sp);
	}

	public void loadData() {
		list = (Quanly_SACH) db.readFile("Sach.dat");
		if (list == null) {
			list = new Quanly_SACH();
		} else {
			for (SACH s : list.getList()) {
				String[] row = { s.getMa() + "", s.getTen(), s.getSoTrang() + "", s.getTheLoai(), s.getNxb() };
				model.addRow(row);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(luuBtn)) {
			JOptionPane.showMessageDialog(null, "Bạn đã lưu thành công");
			db.saveFile("Sach.dat", list);
		} else if (o.equals(xoaTrangBtn)) {
			xoaTrang();
		} else if (o.equals(themBtn)) {
			if (!txtSoTrang.getText().matches("\\d*")) {
				JOptionPane.showMessageDialog(null, "Số trang phải là số nguyên dương");
			} else if (isEmpty(txtMa) || isEmpty(txtTen) || isEmpty(txtSoTrang) || isEmpty(txtNXB)) {
				JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin");
			} else {
				them();
			}
		} else if (o.equals(xoaBtn)) {
			xoa();
		} else if (o.equals(timBtn)) {
			if (txtTim.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn chưa nhập vào mã sách cần tìm");
			} else {
				tim();
			}
		}
	}

	public void xoaTrang() {
		txtMa.setText("");
		txtTen.setText("");
		txtSoTrang.setText("");
		txtNXB.setText("");
		cbTheLoai.setSelectedIndex(0);
		txtMa.requestFocus();
	}

	public boolean isEmpty(JTextField txt) {
		if (txt.getText().trim().equals("")) {
			txt.selectAll();
			txt.requestFocus();
			return true;
		}
		return false;
	}

	public void them() {
		String ma = txtMa.getText();
		String ten = txtTen.getText();
		String soTrang = txtSoTrang.getText();
		String theLoai = cbTheLoai.getSelectedItem().toString();
		String nxb = txtNXB.getText();
		SACH s = new SACH(Integer.parseInt(ma), ten, Integer.parseInt(soTrang), theLoai, nxb);
		if (list.them(s)) {
			String[] row = { ma, ten, soTrang, theLoai, nxb };
			model.addRow(row);
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		}
	}

	public void xoa() {
		int index = table.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng nào để xóa");
		} else {
			int choose = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa dòng này", "Xóa dòng",
					JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.YES_OPTION) {
				list.xoa(index);
				model.removeRow(index);
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			}
		}
	}

	public void tim() {
		int pos = list.tim(Integer.parseInt(txtTim.getText()));
		if (pos == -1) {
			JOptionPane.showMessageDialog(null, "Không tồn tại mã sách này");
		} else {
			table.setRowSelectionInterval(pos, pos);
		}
	}
}
