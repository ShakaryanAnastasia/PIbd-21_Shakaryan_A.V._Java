package Lab_1Java;

import java.awt.Color;
import java.awt.Graphics;

public interface ITransport {
	void SetPosition(int x, int y, int width, int height);

	void MoveTransport(Direction direction);

	void DrawBus(Graphics g);

	void setMainColor(Color color);
}
