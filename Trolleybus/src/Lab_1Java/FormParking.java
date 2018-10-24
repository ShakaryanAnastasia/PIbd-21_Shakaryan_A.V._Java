package Lab_1Java;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormParking extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private ITransport bus;
	private Parking<ITransport> parking;

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
	public FormParking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanelParking panelParking = new JPanelParking();
		panelParking.setBounds(33, 21, 682, 644);
		contentPane.add(panelParking);
		
		parking = new Parking<ITransport>(15, panelParking.getWidth(), panelParking.getHeight());
		panelParking.setParking(parking);
		
		JButton buttonSetTroll = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0442\u0440\u043E\u043B\u043B\u0435\u0439\u0431\u0443\u0441");
		buttonSetTroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color firstColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
				Color secondColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
				bus = new Trolleybus(100 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000), firstColor,
						secondColor, true, true);
				parking.addTransport(bus);
				panelParking.repaint();
			}
		});
		buttonSetTroll.setBounds(738, 119, 206, 68);
		contentPane.add(buttonSetTroll);
		
		JButton buttonSetBus = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0430\u0432\u0442\u043E\u0431\u0443\u0441");
		buttonSetBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color firstColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
				bus = new Bus(100 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000), firstColor);
				parking.addTransport(bus);
				panelParking.repaint();
			}
		});
		buttonSetBus.setBounds(738, 25, 206, 68);
		contentPane.add(buttonSetBus);
		
		JPanel panelGroupElements = new JPanel();
		panelGroupElements.setBounds(738, 391, 206, 253);
		contentPane.add(panelGroupElements);
		panelGroupElements.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u041C\u0435\u0441\u0442\u043E");
		lblNewLabel.setBounds(10, 14, 40, 14);
		panelGroupElements.add(lblNewLabel);
		
		JPanelDraw panelTakeTransport = new JPanelDraw();
		panelTakeTransport.setBounds(10, 72, 186, 171);
		panelGroupElements.add(panelTakeTransport);
		
		JButton buttonTakeTransport = new JButton(
				"\u0417\u0430\u0431\u0440\u0430\u0442\u044C \u0442\u0440\u0430\u043D\u0441\u043F\u043E\u0440\u0442");
		buttonTakeTransport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numberOfPlace = 0;
				try {
					numberOfPlace = Integer.parseInt(textField.getText());
				} catch (Exception ex) {
					textField.setText("Invalid input");
					return;
				}
				if (numberOfPlace >= parking._places.size() || numberOfPlace < 0) {
					textField.setText("Invalid input");
					return;
				}
				bus = parking.removeTransport(numberOfPlace);
				if (bus != null) {
					bus.SetPosition(5, 25, panelTakeTransport.getWidth(), panelTakeTransport.getHeight());
				}
				panelTakeTransport.setTransport(bus);
				panelTakeTransport.repaint();
				panelParking.repaint();
			}
		});
		buttonTakeTransport.setBounds(10, 39, 186, 23);
		panelGroupElements.add(buttonTakeTransport);
		
		textField = new JTextField();
		textField.setBounds(60, 11, 136, 20);
		panelGroupElements.add(textField);
		textField.setColumns(10);
	}

}
