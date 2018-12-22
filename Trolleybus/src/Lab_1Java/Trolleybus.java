package Lab_1Java;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import Lab_1Java.Bus;

public class Trolleybus extends Bus implements Serializable {

	private static final int carWidth = 100;

	private static final int carHeight = 60;

	private Color __DopColor;

	public Color getDopColor() {
		return __DopColor;
	}

	public void setDopColor(Color value) {
		__DopColor = value;
	}

	private boolean Accumulator;

	public boolean getAccumulator() {
		return Accumulator;
	}

	public void setAccumulator(boolean value) {
		Accumulator = value;
	}

	private boolean Horns;

	public boolean getHorns() {
		return Horns;
	}

	public void setHorns(boolean value) {
		Horns = value;
	}

	public Trolleybus(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean accumulator, boolean horns) {
		super(maxSpeed, weight, mainColor);
		__DopColor = dopColor;
		Accumulator = accumulator;
		Horns = horns;
	}

	public Trolleybus(String info) {
		super(info);
		String[] str = info.split(";");
		if (str.length == 10) {
			__MaxSpeed = Integer.parseInt(str[0]);
			__Weight = Integer.parseInt(str[1]);
			__MainColor = new Color(Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]));
			__DopColor = new Color(Integer.parseInt(str[5]), Integer.parseInt(str[6]), Integer.parseInt(str[7]));
			Accumulator = Boolean.parseBoolean(str[8]);
			Horns = Boolean.parseBoolean(str[9]);

		}
	}

	@Override
	public void DrawBus(Graphics g) {
		super.DrawBus(g);
		if (Horns) {
			g.setColor(getDopColor());
			g.drawLine((int) _startPosX + 50, (int) _startPosY, (int) _startPosX + 35, (int) _startPosY - 30);
			g.drawLine((int) _startPosX + 40, (int) _startPosY, (int) _startPosX + 25, (int) _startPosY - 30);
		}
		if (Accumulator) {
			g.setColor(getDopColor());
			g.fillRect((int) _startPosX - 5, (int) _startPosY + 10, 5, 25);
		}
	}

	@Override
	public String getInfo() {
		return __MaxSpeed + ";" + __Weight + ";" + __MainColor.getRed() + ";" + __MainColor.getGreen() + ";"
				+ __MainColor.getBlue() + ";" + __DopColor.getRed() + ";" + __DopColor.getGreen() + ";"
				+ __DopColor.getBlue() + ";" + Accumulator + ";" + Horns;
	}

	public int compareTo(Trolleybus another) {
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
		if (__DopColor != another.__DopColor) {
			return Integer.valueOf(__DopColor.getRGB()).compareTo(another.__DopColor.getRGB());
		}
		if (Accumulator != another.Accumulator) {
			return Boolean.valueOf(Accumulator).compareTo(another.Accumulator);
		}
		if (Horns != another.Horns) {
			return Boolean.valueOf(Horns).compareTo(another.Horns);
		}
		return 0;
	}

	@Override
	public boolean equals(Object another) {
		if (another == null) {
			return false;
		}
		if (!(another instanceof Trolleybus)) {
			return false;
		}
		Trolleybus tank = (Trolleybus) another;
		return equals(tank);
	}

	public boolean equals(Trolleybus another) {
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
		if (__DopColor != another.__DopColor) {
			return false;
		}
		if (Accumulator != another.Accumulator) {
			return false;
		}
		if (Horns != another.Horns) {
			return false;
		}
		return true;
	}
}