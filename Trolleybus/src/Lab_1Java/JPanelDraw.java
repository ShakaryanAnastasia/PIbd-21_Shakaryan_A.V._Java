package Lab_1Java;

import javax.swing.*;
import java.awt.*;

public class JPanelDraw extends JPanel {
	private ITransport transport;

	public void setTransport(ITransport transport) {
		this.transport = transport;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (transport != null) {
			transport.DrawBus(g);
		}
	}
}
