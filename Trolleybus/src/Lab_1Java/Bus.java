package Lab_1Java;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

public class Bus extends Vehicle implements Serializable, Comparable<Bus> {

	protected final int carWidth = 100;

	protected final int carHeight = 60;

	public Bus(int maxSpeed, int weight, Color mainColor) {
		__MaxSpeed = maxSpeed;
		__Weight = weight;
		__MainColor = mainColor;
	}

	public Bus(String info) {
		String[] str = info.split(";");
		if (str.length == 5) {
			__MaxSpeed = Integer.parseInt(str[0]);
			__Weight = Integer.parseInt(str[1]);
			__MainColor = new Color(Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]));
		}
	}

	@Override
	public void MoveTransport(Direction direction) {
		float step = getMaxSpeed() * 100 / getWeight();
		switch (direction) {
		case Right:
			if (_startPosX + step < _pictureWidth - carWidth) {
				_startPosX += step;
			}
			break;
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		case Up:
			if (_startPosY - step > 0) {
				_startPosY -= step;
			}
			break;
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

	@Override
	public String getInfo() {
		return __MaxSpeed + ";" + __Weight + ";" + __MainColor.getRed() + ";" + __MainColor.getGreen() + ";"
				+ __MainColor.getBlue();
	}

	@Override
	public int compareTo(Bus another) {
		if (another == null) {
			return 1;
		}
		if (__MaxSpeed != another.__MaxSpeed) {
			return Integer.valueOf(__MaxSpeed).compareTo(another.__MaxSpeed);
		}
		if (__Weight != another.__Weight) {
			return Integer.valueOf(__Weight).compareTo(another.__Weight);
		}
		if (__MainColor != another.__MainColor) {
			return Integer.valueOf(__MainColor.getRGB()).compareTo(another.__MainColor.getRGB());
		}
		return 0;
	}

	@Override
	public boolean equals(Object another) {
		if (another == null) {
			return false;
		}
		if (!(another instanceof Bus)) {
			return false;
		}
		Bus tank = (Bus) another;
		return equals(tank);
	}

	public boolean equals(Bus another) {
		if (another == null) {
			return false;
		}
		if (__MaxSpeed != another.__MaxSpeed) {
			return false;
		}
		if (__Weight != another.__Weight) {
			return false;
		}
		if (__MainColor != another.__MainColor) {
			return false;
		}
		return true;
	}
}