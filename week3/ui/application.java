package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import entity.Company;
import entity.Employee;
import entity.Office;

public class application extends JFrame {
	private DefaultMutableTreeNode root;
	private JTree tree;
	private ArrayList<Office> listOffice;

	public application(ArrayList<Office> listOffice) {
		setTitle("^-^");
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(800, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.listOffice = listOffice;

		root = new DefaultMutableTreeNode("Danh sách phòng ban");
		tree = new JTree(root);
		tree.setShowsRootHandles(true);

		for (int i = 0; i < listOffice.size(); i++) {
			Office tempOffice = listOffice.get(i);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(tempOffice);
			root.add(node);
		}

		add(new JScrollPane(tree), BorderLayout.CENTER);

		tree.addMouseListener(new MouseListener() {

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
				if (e.getClickCount() == 2) {
					DefaultMutableTreeNode x = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
					if (x != null) {
						Object gt = x.getUserObject();
						if (gt instanceof Office) {
							Office Office = (Office) x.getUserObject();
							ArrayList<Employee> listEmp = Office.getList();

							frmEmployee gd = new frmEmployee(listEmp, Office.getName());
							gd.show();
						}
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		Company company = new Company();
		Office tc, kt, ns, dn, bh, bv;
		company.addOffice(tc = new Office("TC", "Phòng Tổ Chức"));
		company.addOffice(kt = new Office("KT", "Phòng Kỹ Thuật"));
		company.addOffice(ns = new Office("NS", "Nhân Sự"));
		company.addOffice(dn = new Office("DN", "Đối Ngoại"));
		company.addOffice(bh = new Office("BH", "Bán Hàng"));
		company.addOffice(bv = new Office("BV", "Bảo Vệ"));
		
		tc.addNhanVien("001", "Nguyễn", "An", true, 25, 30000);
		kt.addNhanVien("002", "Lê", "Tính", true, 27, 70000);
		ns.addNhanVien("003", "Trần", "Văn", true, 35, 20000);
		dn.addNhanVien("004", "Hoàng", "Quý", false, 45, 100000);
		bh.addNhanVien("005", "Tô", "Sỹ", false, 55, 56000);
		bv.addNhanVien("006", "Phạm", "Anh", true, 20, 12300);
		
		ArrayList<Office> listOffice= company.getListOffice();
		new application(listOffice).setVisible(true);
		
	}
}
