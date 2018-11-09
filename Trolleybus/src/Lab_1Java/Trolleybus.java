package Lab_1Java;

import java.awt.Color;
import java.awt.Graphics;

import Lab_1Java.Bus;

public class Trolleybus extends Bus {

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

	public Trolleybus(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean accumulator, boolean horns) {
		super(maxSpeed, weight, mainColor);
		__MaxSpeed = maxSpeed;
		__Weight = weight;
		__MainColor = mainColor;
		__DopColor = dopColor;
		Accumulator = accumulator;
		Horns = horns;
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
}
