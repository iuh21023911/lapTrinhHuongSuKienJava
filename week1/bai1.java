package week1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class bai1 extends JFrame implements ActionListener {
	JPanel northPn, centerPn, southPn;
	JLabel title, heSo1, heSo2, heSo3, ketQua;
	JButton giaiBtn, xoaBtn, thoatBtn;
	JTextField txtA, txtB, txtC, txtKQ;

	bai1() {
		setTitle("^-^");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		createGUI();
	}

	private void createGUI() {
		northPn = new JPanel();
		add(northPn, BorderLayout.NORTH);
		title = new JLabel("GIẢI PHƯƠNG TRÌNH BẬC HAI");
		title.setFont(new Font(null, Font.BOLD, 20));
		northPn.add(title);
		northPn.setBackground(Color.CYAN);

		centerPn = new JPanel();
		add(centerPn, BorderLayout.CENTER);

		heSo1 = new JLabel("Nhập a:");
		heSo2 = new JLabel("Nhập b:");
		heSo3 = new JLabel("Nhập c:");
		ketQua = new JLabel("Kết quả:");
		int x = 40, y = 30, width = 100, height = 30;
		heSo1.setBounds(x, y, width, height);
		centerPn.add(heSo1);
		y += 50;
		heSo2.setBounds(x, y, width, height);
		centerPn.add(heSo2);
		y += 50;
		heSo3.setBounds(x, y, width, height);
		centerPn.add(heSo3);
		y += 50;
		ketQua.setBounds(x, y, width, height);
		centerPn.add(ketQua);

		txtA = new JTextField();
		txtB = new JTextField();
		txtC = new JTextField();
		txtKQ = new JTextField();
		x = 140;
		y = 30;
		width = 300;
		txtA.setBounds(x, y, width, height);
		centerPn.add(txtA);
		y += 50;
		txtB.setBounds(x, y, width, height);
		centerPn.add(txtB);
		y += 50;
		txtC.setBounds(x, y, width, height);
		centerPn.add(txtC);
		y += 50;
		txtKQ.setEditable(false);
		txtKQ.setBounds(x, y, width, height);
		centerPn.add(txtKQ);
		centerPn.setLayout(null);

		southPn = new JPanel();
		add(southPn, BorderLayout.SOUTH);
		southPn.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		giaiBtn = new JButton("Giải");
		xoaBtn = new JButton("Xóa rỗng");
		thoatBtn = new JButton("Thoát");
		southPn.add(giaiBtn);
		southPn.add(xoaBtn);
		southPn.add(thoatBtn);
		giaiBtn.addActionListener(this);
		xoaBtn.addActionListener(this);
		thoatBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		double x1, x2, delta;
		if (o.equals(thoatBtn)) {
			System.exit(0);
		} else if (o.equals(xoaBtn)) {
			txtA.setText("");
			txtB.setText("");
			txtC.setText("");
			txtKQ.setText("");
			txtA.requestFocus();
		} else if (o.equals(giaiBtn)) {
			if (!isNum(txtA)) {
				focus(txtA);
			} else if (!isNum(txtB)) {
				focus(txtB);
			} else if (!isNum(txtC)) {
				focus(txtC);
			} else {
				int a = Integer.parseInt(txtA.getText());
				int b = Integer.parseInt(txtB.getText());
				int c = Integer.parseInt(txtC.getText());
				if (a == 0) {
					giaiPtBac1(b, c);
				} else {
					delta = b * b - 4 * a * c;
					if (delta > 0) {
						x1 = (-b + Math.sqrt(delta)) / (2 * a);
						x2 = (-b - Math.sqrt(delta)) / (2 * a);
						txtKQ.setText("Phương trình có 2 nghiệm x1 = " + x1 + ", x2 = " + x2);
					} else if (delta == 0) {
						txtKQ.setText("Phương trình có nghiệm kép: x1 = x2 = " + (-b / 2 * (double) a));
					} else {
						txtKQ.setText("Phương trình vô nghiệm");
					}
				}
			}
		}
	}

	private boolean isNum(JTextField text) {
		try {
			Integer.parseInt(text.getText());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void focus(JTextField txt) {
		JOptionPane.showMessageDialog(null, "lỗi nhập liệu. Hãy nhập vào giá trị số");
		txt.selectAll();
		txt.requestFocus();
	}

	private void giaiPtBac1(int b, int c) {
		if (b == 0) {
			if (c == 0) {
				txtKQ.setText("Phương trình vô số nghiệm");
			} else {
				txtKQ.setText("Phương trình vô nghiệm");
			}
		} else {
			if (c == 0) {
				txtKQ.setText("Phương trình vô số nghiệm");
			} else {
				txtKQ.setText("Phương trình có nghiệm x = " + (-b / (double) c));
			}
		}
	}

	public static void main(String[] args) {
		new PhuongTrinhBacHai().setVisible(true);
	}
}
