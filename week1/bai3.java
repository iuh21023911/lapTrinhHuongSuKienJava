package week1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class bai3 extends JFrame implements ActionListener {
	JPanel northPn, westPn, centerPn, southPn, pnMathChooser, blueBox, redBox, yellowBox;
	JLabel lblTitle, lblInputA, lblInputB, lblResult;
	JButton calcBtn, clearBtn, exitBtn;
	JTextField txtA, txtB, txtResult;
	JRadioButton plusBtn, minusBtn, multiplyBtn, divineBtn;
	ButtonGroup groupMathBtn;

	bai3() {
		setTitle("Cộng - Trừ - Nhân - Chia");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);

		createGui();
	}

	private void createGui() {
//		North Panel
		add(northPn = new JPanel(), BorderLayout.NORTH);
		northPn.add(lblTitle = new JLabel("Cộng Trừ Nhân Chia"));
		lblTitle.setFont(new Font(null, Font.BOLD, 24));
		lblTitle.setForeground(Color.blue);

//		West Panel
		add(westPn = new JPanel(), BorderLayout.WEST);
		westPn.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));
		westPn.setLayout(new GridLayout(7, 1, 0, 10));
		westPn.setBackground(Color.decode("#c8c4c4"));
		westPn.setPreferredSize(new Dimension(100, 100));
		westPn.add(calcBtn = new JButton("Giải"));
		calcBtn.setMnemonic(KeyEvent.VK_G);
		westPn.add(clearBtn = new JButton("Xóa"));
		clearBtn.setMnemonic(KeyEvent.VK_X);
		westPn.add(exitBtn = new JButton("Thoát"));
		exitBtn.setMnemonic(KeyEvent.VK_T);

//		Center Panel
		add(centerPn = new JPanel(), BorderLayout.CENTER);
		centerPn.setLayout(null);
		centerPn.setBorder(BorderFactory.createTitledBorder("Tính Toán"));

		int x = 50, y = 20, width = 250, height = 30;
		centerPn.add(lblInputA = new JLabel("Nhập a:"));
		lblInputA.setBounds(x, y, width, height);
		y += 40;
		centerPn.add(lblInputB = new JLabel("Nhập b:"));
		lblInputB.setBounds(x, y, width, height);

		x += 50;
		y = 20;
		centerPn.add(txtA = new JTextField());
		txtA.setBounds(x, y, width, height);
		y += 40;
		centerPn.add(txtB = new JTextField());
		txtB.setBounds(x, y, width, height);

		centerPn.add(pnMathChooser = new JPanel());
		pnMathChooser.setBorder(BorderFactory.createTitledBorder("Phép toán"));
		y += 40;
		pnMathChooser.setBounds(x, y, width, 100);

		pnMathChooser.add(plusBtn = new JRadioButton("Cộng"));
		pnMathChooser.add(minusBtn = new JRadioButton("Trừ"));
		pnMathChooser.add(multiplyBtn = new JRadioButton("Nhân"));
		pnMathChooser.add(divineBtn = new JRadioButton("Chia"));
		pnMathChooser.setLayout(new GridLayout(2, 2));
		groupMathBtn = new ButtonGroup();
		groupMathBtn.add(plusBtn);
		groupMathBtn.add(minusBtn);
		groupMathBtn.add(multiplyBtn);
		groupMathBtn.add(divineBtn);

		centerPn.add(lblResult = new JLabel("Kết quả:"));
		x -= 50;
		y += 120;
		lblResult.setBounds(x, y, width, height);
		centerPn.add(txtResult = new JTextField());
		x += 50;
		txtResult.setEditable(false);
		txtResult.setBounds(x, y, width, height);

//		South Panel
		add(southPn = new JPanel(), BorderLayout.SOUTH);
		southPn.setBackground(Color.decode("#ffacac"));
		southPn.setPreferredSize(new Dimension(100, 50));
		
		southPn.add(blueBox = new JPanel());
		blueBox.setBackground(Color.blue);
		blueBox.setPreferredSize(new Dimension(30,20));
		southPn.add(redBox = new JPanel());
		redBox.setBackground(Color.red);
		redBox.setPreferredSize(new Dimension(30,20));
		southPn.add(yellowBox = new JPanel());
		yellowBox.setBackground(Color.yellow);
		yellowBox.setPreferredSize(new Dimension(30,20));
		
//		Action listener
		calcBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		exitBtn.addActionListener(this);
	}

	public static void main(String[] args) {
		new CongTruNhanChia().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == exitBtn) {
			System.exit(0);
		} else if (o == clearBtn) {
			txtA.setText("");
			txtB.setText("");
			groupMathBtn.clearSelection();
			txtResult.setText("");
			txtA.requestFocus();
		} else if (o == calcBtn) {
			if (!isNum(txtA)) {
				focus(txtA);
			} else if (!isNum(txtB)) {
				focus(txtB);
			} else {
				int a = Integer.parseInt(txtA.getText());
				int b = Integer.parseInt(txtB.getText());
				if (plusBtn.isSelected()) {
					txtResult.setText("" + (a + b));
				} else if (minusBtn.isSelected()) {
					txtResult.setText("" + (a - b));
				} else if (multiplyBtn.isSelected()) {
					txtResult.setText("" + (a * b));
				} else if (divineBtn.isSelected()) {
					txtResult.setText("" + ((double) a / b));
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
		JOptionPane.showMessageDialog(null, "Lỗi nhập liệu.");
		txt.selectAll();
		txt.requestFocus();
	}
}
