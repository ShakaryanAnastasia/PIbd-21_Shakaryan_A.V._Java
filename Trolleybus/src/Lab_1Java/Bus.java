package Lab_1Java;

import java.awt.Color;
import java.awt.Graphics;

public class Bus extends Vehicle {

	protected final int carWidth = 100;

	protected final int carHeight = 60;

	public Bus(int maxSpeed, float weight, Color mainColor) {
		__MaxSpeed = maxSpeed;
		__Weight = weight;
		__MainColor = mainColor;
	}

	@Override
	public void MoveTransport(Direction direction) {
		float step = getMaxSpeed() * 100 / getWeight();
		switch (direction) {
		// вправо
		case Right:
			if (_startPosX + step < _pictureWidth - carWidth) {
				_startPosX += step;
			}
			break;
		// влево
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		// вверх
		case Up:
			if (_startPosY - step > 0) {
				_startPosY -= step;
			}
			break;
		// вниз
		case Down:
			if (_startPosY + step < _pictureHeight - carHeight) {
				_startPosY += step;
			}
			break;
		}
	}

	@Override
	public void DrawBus(Graphics g) {
		g.setColor(getMainColor());
		g.fillRect((int) _startPosX, (int) _startPosY, carWidth, carHeight - 20);
		g.setColor(Color.BLACK);
		g.fillOval((int) _startPosX + 10, (int) _startPosY + 40, 20, 20);
		g.fillOval((int) _startPosX + 70, (int) _startPosY + 40, 20, 20);
		g.fillRect((int) _startPosX + 40, (int) _startPosY + 10, 20, 30);
		g.setColor(Color.GRAY);
		g.fillRect((int) _startPosX + 70, (int) _startPosY, 30, 30);
	}
}
