package Lab_1Java;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FormParking extends JFrame {

	JFrame frame;

	private JPanel contentPane;

	private JTextField textField;

	private static ITransport bus;

	private static JList list;

	private static MultiLevelParking parking;

	private final int countLevel = 5;

	private static JPanelParking panelParking;

	BusConfig select;
	private String[] elements = new String[6];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormParking frame = new FormParking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public FormParking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 692);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanelParking panelParking = new JPanelParking();
		panelParking.setBounds(10, 11, 634, 632);
		contentPane.add(panelParking);

		list = new JList(elements);
		list.setBounds(665, 8, 206, 107);
		contentPane.add(list);

		JButton btnLevelDown = new JButton("Down");
		btnLevelDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parking.levelDown();
				list.setSelectedIndex(parking.getCurrentLevel());
				panelParking.repaint();
			}
		});
		btnLevelDown.setBounds(716, 126, 96, 32);
		contentPane.add(btnLevelDown);

		JButton btnLevelUp = new JButton("Up");
		btnLevelUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parking.levelUp();
				list.setSelectedIndex(parking.getCurrentLevel());
				panelParking.repaint();
			}
		});
		btnLevelUp.setBounds(716, 163, 96, 34);
		contentPane.add(btnLevelUp);

		for (int i = 0; i < 5; i++) {
			elements[i] = "Level " + (i + 1);
		}
		parking = new MultiLevelParking(countLevel, panelParking.getWidth(), panelParking.getHeight());
		panelParking.setParking(parking);
		panelParking.setList(list);

		JPanel panelGroupElements = new JPanel();
		panelGroupElements.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGroupElements.setBackground(new Color(250, 235, 215));
		panelGroupElements.setBounds(665, 453, 206, 190);
		contentPane.add(panelGroupElements);
		panelGroupElements.setLayout(null);

		JLabel lblNewLabel = new JLabel("Place");
		lblNewLabel.setBounds(10, 14, 50, 14);
		panelGroupElements.add(lblNewLabel);

		JPanelDraw panelTakeTank = new JPanelDraw();
		panelTakeTank.setBounds(20, 73, 165, 103);
		panelGroupElements.add(panelTakeTank);

		JButton buttonTakeTank = new JButton("Take");
		buttonTakeTank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedIndex() == -1) {
					return;
				}
				int numberOfPlace = 0;
				try {
					numberOfPlace = Integer.parseInt(textField.getText());
				} catch (Exception ex) {
					textField.setText("Invalid input");
					return;
				}
				if (numberOfPlace >= parking.getAt(list.getSelectedIndex())._places.size() || numberOfPlace < 0) {
					textField.setText("Invalid input");
					return;
				}
				bus = parking.getAt(list.getSelectedIndex()).removeTransport(numberOfPlace);
				if (bus != null) {
					bus.SetPosition(10, 35, panelTakeTank.getWidth(), panelTakeTank.getHeight());
				}
				panelTakeTank.setTransport(bus);
				panelTakeTank.repaint();
				panelParking.repaint();
			}
		});
		buttonTakeTank.setBounds(20, 39, 176, 23);
		panelGroupElements.add(buttonTakeTank);

		textField = new JTextField();
		textField.setBounds(60, 11, 136, 20);
		panelGroupElements.add(textField);
		textField.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getBus();
			}
		});
		btnAdd.setBounds(665, 390, 206, 38);
		contentPane.add(btnAdd);
	}

	public void getBus() {
		select = new BusConfig(frame);
		if (select.res()) {
			ITransport bus = select.getBus();
			if (bus != null) {
				int place = parking.getAt(list.getSelectedIndex()).addTransport(bus);
				if (place < 0) {
					JOptionPane.showMessageDialog(null, "No free places");
				}
			}
			contentPane.repaint();
		}
	}
}