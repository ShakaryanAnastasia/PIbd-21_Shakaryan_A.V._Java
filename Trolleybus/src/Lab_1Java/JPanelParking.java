package Lab_1Java;

import javax.swing.*;
import java.awt.*;

public class JPanelParking extends JPanel {

	private MultiLevelParking parking;

	private JList list;

	public void setParking(MultiLevelParking parking) {
		this.parking = parking;
	}

	public void setList(JList list) {
		this.list = list;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			if (parking != null) {
				if (list.getSelectedIndex() != -1) {
					parking.getAt(list.getSelectedIndex()).Draw(g);
				}

			}
		} catch (Exception ex) {
		}
	}
}
