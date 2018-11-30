package Lab_1Java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class BusConfig extends JDialog {

	ITransport bus;
	JPanel panel;
	boolean r;

	Color color;
	Color dopColor;
	int maxSpeed;

	public BusConfig(JFrame parent) {
		super(parent, true);
		initialize();
	}

	public boolean res() {
		setVisible(true);
		return r;
	}

	private void initialize() {
		this.getContentPane().setBackground(SystemColor.controlHighlight);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel lblBus = new JLabel("Bus");
		lblBus.setHorizontalAlignment(SwingConstants.CENTER);
		lblBus.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblBus.setBounds(10, 29, 77, 25);
		this.getContentPane().add(lblBus);

		JLabel lblTrolleybus = new JLabel("Trolleybus");
		lblTrolleybus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrolleybus.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTrolleybus.setBounds(10, 64, 77, 25);
		this.getContentPane().add(lblTrolleybus);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBounds(144, 10, 120, 112);
		this.getContentPane().add(panel);

		MouseListener mouseL = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				JComponent jc = (JComponent) e.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, e, TransferHandler.COPY);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		};

		lblBus.addMouseListener(mouseL);
		lblTrolleybus.addMouseListener(mouseL);
		lblTrolleybus.setTransferHandler(new TransferHandler("text"));
		lblBus.setTransferHandler(new TransferHandler("text"));

		panel.setDropTarget(new DropTarget() {

			public void drop(DropTargetDropEvent e) {

				try {

					for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
						if (e.getTransferable().getTransferData(df) == "Bus") {
							bus = new Bus(100 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000),
									Color.WHITE);
						} else if (e.getTransferable().getTransferData(df) == "Trolleybus") {
							bus = new Trolleybus(100 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000),
									Color.WHITE, Color.BLACK, true, true);
						}
						draw(panel, bus);
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JLabel lblMainColor = new JLabel("Main Color");
		lblMainColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMainColor.setBounds(154, 132, 90, 25);
		this.getContentPane().add(lblMainColor);

		JLabel lblDopColor = new JLabel("Dop Color");
		lblDopColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDopColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDopColor.setBounds(154, 167, 90, 23);
		this.getContentPane().add(lblDopColor);

		lblMainColor.setDropTarget(new DropTarget() {

			public void drop(DropTargetDropEvent e) {
				if (bus != null) {
					try {
						for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
							bus.setMainColor((selectColor(e.getTransferable().getTransferData(df).toString())));
							draw(panel, bus);
						}
					} catch (Exception ex) {
						System.out.println(ex + "FF");
					}
				}
			}

			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (UnsupportedFlavorException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		lblDopColor.setDropTarget(new DropTarget() {

			public void drop(DropTargetDropEvent e) {
				if (bus != null) {
					try {

						for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
							((Trolleybus) bus)
									.setDopColor((selectColor(e.getTransferable().getTransferData(df).toString())));
							draw(panel, bus);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}

			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (UnsupportedFlavorException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel panelYellow = new JPanel();
		panelYellow.setName("yellow");
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(289, 29, 46, 39);
		this.getContentPane().add(panelYellow);

		JPanel panelBlue = new JPanel();
		panelBlue.setName("blue");
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(357, 29, 46, 39);
		this.getContentPane().add(panelBlue);

		JPanel panelRed = new JPanel();
		panelRed.setName("red");
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(289, 83, 46, 39);
		this.getContentPane().add(panelRed);

		JPanel panelGreen = new JPanel();
		panelGreen.setName("green");
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(357, 83, 46, 39);
		this.getContentPane().add(panelGreen);

		JPanel panelBlack = new JPanel();
		panelBlack.setName("black");
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(289, 133, 46, 39);
		this.getContentPane().add(panelBlack);

		JPanel panelPink = new JPanel();
		panelPink.setName("pink");
		panelPink.setBackground(Color.PINK);
		panelPink.setBounds(357, 133, 46, 39);
		this.getContentPane().add(panelPink);

		JPanel panelMagenta = new JPanel();
		panelMagenta.setName("magenta");
		panelMagenta.setBackground(Color.MAGENTA);
		panelMagenta.setBounds(289, 187, 46, 39);
		this.getContentPane().add(panelMagenta);

		JPanel panelCyan = new JPanel();
		panelCyan.setName("cyan");
		panelCyan.setBackground(Color.CYAN);
		panelCyan.setBounds(357, 187, 46, 39);
		this.getContentPane().add(panelCyan);

		panelYellow.addMouseListener(mouseL);
		panelYellow.setTransferHandler(new TransferHandler("name"));

		panelBlue.addMouseListener(mouseL);
		panelBlue.setTransferHandler(new TransferHandler("name"));

		panelRed.addMouseListener(mouseL);
		panelRed.setTransferHandler(new TransferHandler("name"));

		panelGreen.addMouseListener(mouseL);
		panelGreen.setTransferHandler(new TransferHandler("name"));

		panelBlack.addMouseListener(mouseL);
		panelBlack.setTransferHandler(new TransferHandler("name"));

		panelPink.addMouseListener(mouseL);
		panelPink.setTransferHandler(new TransferHandler("name"));

		panelMagenta.addMouseListener(mouseL);
		panelMagenta.setTransferHandler(new TransferHandler("name"));

		panelCyan.addMouseListener(mouseL);
		panelCyan.setTransferHandler(new TransferHandler("name"));

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				r = true;
				dispose();
			}
		});
		btnAdd.setBounds(27, 133, 96, 23);
		this.getContentPane().add(btnAdd);

		JButton btnNO = new JButton("Cancel");
		btnNO.setBounds(27, 187, 96, 23);
		this.getContentPane().add(btnNO);
		btnNO.addActionListener((ActionEvent e) -> {
			r = false;
			dispose();
		});
	}

	public ITransport getBus() {
		return bus;
	}

	public void draw(JPanel panel, ITransport bus) {
		if (bus != null) {
			Graphics gr = panel.getGraphics();
			gr.clearRect(0, 0, panel.getWidth(), panel.getHeight());
			bus.SetPosition(10, 35, panel.getWidth(), panel.getHeight());
			bus.DrawBus(gr);
		}
	}

	public Color selectColor(String s) {
		switch (s) {
		case "yellow":
			return Color.yellow;
		case "blue":
			return Color.blue;
		case "red":
			return Color.red;
		case "green":
			return Color.green;
		case "black":
			return Color.black;
		case "pink":
			return Color.pink;
		case "magenta":
			return Color.magenta;
		case "cyan":
			return Color.cyan;
		}

		return null;
	}
}