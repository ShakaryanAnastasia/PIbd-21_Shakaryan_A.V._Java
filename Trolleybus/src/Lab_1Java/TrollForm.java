package Lab_1Java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TrollForm extends JFrame {

	private JPanel contentPane;
	private ITransport bus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrollForm frame = new TrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			if (bus != null) {
				bus.DrawBus(g);
			}
		} catch (Exception ex) {

		}
	}

	public void moveButton(JButton sender) {
		try {
			String name = sender.getToolTipText();
			switch (name) {
			case "Up":
				bus.MoveTransport(Direction.Up);
				break;
			case "Down":
				bus.MoveTransport(Direction.Down);
				break;
			case "Left":
				bus.MoveTransport(Direction.Left);
				break;
			case "Right":
				bus.MoveTransport(Direction.Right);
				break;
			}
			this.repaint();
		} catch (Exception ex) {
			System.out.print("Click on the button <Create @something@>! \n");
		}
	}

	/**
	 * Create the frame.
	 */
	public TrollForm() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton buttonCreate = new JButton("Create Trolleybus");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bus = new Trolleybus(100 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000),
							Color.YELLOW, Color.PINK, true, true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					bus.SetPosition(70 + (int) (Math.random() * 160), 70 + (int) (Math.random() * 160),
							TrollForm.this.getWidth(), TrollForm.this.getHeight());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				TrollForm.this.repaint();

			}
		});
		buttonCreate.setBounds(10, 11, 150, 42);
		contentPane.add(buttonCreate);

		JButton buttonCreateBase = new JButton("Create Bus");
		buttonCreateBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					bus = new Bus(100 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000), Color.YELLOW);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					bus.SetPosition(70 + (int) (Math.random() * 160), 70 + (int) (Math.random() * 160),
							TrollForm.this.getWidth(), TrollForm.this.getHeight());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				TrollForm.this.repaint();
			}
		});
		buttonCreateBase.setBounds(170, 11, 150, 42);
		contentPane.add(buttonCreateBase);

		JButton buttonUp = new JButton("");
		buttonUp.setToolTipText("Up");
		buttonUp.setIcon(new ImageIcon(TrollForm.class.getResource("/up.png")));
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonUp);
			}
		});
		buttonUp.setBounds(515, 215, 65, 65);
		contentPane.add(buttonUp);

		JButton buttonLeft = new JButton("");
		buttonLeft.setToolTipText("Left");
		buttonLeft.setIcon(new ImageIcon(TrollForm.class.getResource("/left.png")));
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonLeft);
			}
		});
		buttonLeft.setBounds(440, 291, 65, 65);
		contentPane.add(buttonLeft);

		JButton buttonDown = new JButton("");
		buttonDown.setToolTipText("Down");
		buttonDown.setIcon(new ImageIcon(TrollForm.class.getResource("/down.png")));
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonDown);
			}
		});
		buttonDown.setBounds(515, 291, 65, 65);
		contentPane.add(buttonDown);

		JButton buttonRight = new JButton("");
		buttonRight.setToolTipText("Right");
		buttonRight.setIcon(new ImageIcon(TrollForm.class.getResource("/right.png")));
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonRight);
			}
		});
		buttonRight.setBounds(590, 291, 65, 65);
		contentPane.add(buttonRight);
	}

}
