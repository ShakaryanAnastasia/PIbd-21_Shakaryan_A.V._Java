package Lab_1Java;

import java.awt.Graphics;

public interface ITransport {
	void SetPosition(int x, int y, int width, int height);

	void MoveTransport(Direction direction);

	void DrawBus(Graphics g);
}
