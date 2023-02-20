package week1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class bai2 extends JFrame {
	JTextField txtN;
	JButton generateBtn;
	JTextArea taPrimes;
	JScrollPane scroll;

	bai2() {
		setTitle("Primes");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);

		createGui();
	}

	private void createGui() {
		setLayout(null);
		add(txtN = new JTextField());
		txtN.setBounds(50, 10, 170, 20);
		txtN.setToolTipText("Nhập số nguyên tố cần hiển thị.");

		add(generateBtn = new JButton("Generate"));
		generateBtn.setBounds(225, 8, 90, 25);

		add(scroll = new JScrollPane(taPrimes = new JTextArea()));
		scroll.setBounds(50, 40, 300, 150);
		taPrimes.setToolTipText("Danh sách các số nguyên tố.");
		taPrimes.setEditable(false);

		generateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				taPrimes.setText("");
				try {
					int n = Integer.parseInt(txtN.getText());
					if (n > 0) {
						for (int i = 2; i < Integer.MAX_VALUE; i++) {
							if (isPrime(i)) {
								n--;
								taPrimes.append(i + "\n");
							}
							if(n == 0) {
								break;
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "nhập vào số nguyên dương");
						focus(txtN);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "lỗi nhập liệu.");
					focus(txtN);
				}
			}

			private void focus(JTextField txtN) {
				txtN.requestFocus();
				txtN.selectAll();
				return ;
			}

			private boolean isPrime(int n) {
				for (int i = 2; i <= Math.sqrt(n); i++) {
					if (n % i == 0) {
						return false;
					}
				}
				return true;
			}
		});
	}

	public static void main(String[] args) {
		new SoNguyenTo().setVisible(true);
	}
}
