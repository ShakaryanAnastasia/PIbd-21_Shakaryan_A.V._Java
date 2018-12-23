package Lab_1Java;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
	private Logger log;

	private String logPath = "C:\\Users\\anast\\Desktop\\log.txt";

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
		log = Logger.getLogger(FormParking.class.getName());

		try {
			FileInputStream fis = new FileInputStream("p.properties");
			LogManager.getLogManager().readConfiguration(fis);
			FileHandler fh = null;
			fh = new FileHandler(logPath);
			log.addHandler(fh);
			log.setUseParentHandlers(false);
			fis.close();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 715);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);

		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filesave = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt");
				filesave.setFileFilter(filter);
				if (filesave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = filesave.getSelectedFile();
					if (parking.SaveData(file.getAbsolutePath())) {
						log.log(Level.INFO, "Saved the parking in " + file.getAbsolutePath());
						JOptionPane.showMessageDialog(null, "Saved");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "Save failed", "", 0, null);
					}
				}
			}
		});
		menuFile.add(menuSave);

		JMenuItem menuLoad = new JMenuItem("Load");
		menuLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt");
				fileChooser.setFileFilter(filter);
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						if (parking.load(file.getAbsolutePath())) {
							log.log(Level.INFO, "Loaded the parking from " + file.getAbsolutePath());
							JOptionPane.showMessageDialog(null, "Loaded");
						} else {
							JOptionPane.showMessageDialog(null, "Load failed", "", 0, null);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "", 0, null);
					}
					contentPane.repaint();
				}
			}
		});
		menuFile.add(menuLoad);

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
				if (parking.levelDown())
					log.log(Level.INFO, "Moved to the {0} parking level", parking.getCurrentLevel() + 1);
				list.setSelectedIndex(parking.getCurrentLevel());
				panelParking.repaint();
			}
		});
		btnLevelDown.setBounds(716, 126, 96, 32);
		contentPane.add(btnLevelDown);

		JButton btnLevelUp = new JButton("Up");
		btnLevelUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parking.levelUp())
					log.log(Level.INFO, "Moved to the {0} parking level", parking.getCurrentLevel() + 1);
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
				int numberOfPlace;
				try {
					numberOfPlace = Integer.parseInt(textField.getText());
					bus = parking.getAt(list.getSelectedIndex()).removeTransport(numberOfPlace);
					bus.SetPosition(10, 35, panelTakeTank.getWidth(), panelTakeTank.getHeight());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Wrong format", "Error", 0, null);
					return;
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Empty", "Error", 0, null);
					return;
				} catch (ParkingNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0, null);
					return;
				}
				panelTakeTank.setTransport(bus);
				panelTakeTank.repaint();
				panelParking.repaint();
				log.log(Level.INFO, "Took the bus from parking in place {0}", numberOfPlace);
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
		try {
			if (select.res()) {
				ITransport bus = select.getBus();
				int place = parking.getAt(list.getSelectedIndex()).addTransport(bus);
				contentPane.repaint();
				log.log(Level.INFO, "Added new bus. It's place " + place);
			}
		} catch (ParkingOverflowException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0, null);
			return;
		} catch (ParkingOccupiedPlaceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0, null);
			return;
		}
	}
}