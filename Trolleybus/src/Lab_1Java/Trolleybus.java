package Lab_1Java;

import java.awt.Color;
import java.awt.Graphics;

public class Trolleybus {
	/// <summary>
	/// ����� ���������� ��������� ����������
	/// </summary>
	private float _startPosX;
	/// <summary>
	/// ������ ���������� ��������� ����������
	/// </summary>
	private float _startPosY;
	/// <summary>
	/// ������ ���� ���������
	/// </summary>
	private int _pictureWidth;
	// ������ ���� ���������
	private int _pictureHeight;
	/// <summary>
	/// ������ ��������� ����������
	/// </summary>
	private final static int carWidth = 100;
	/// <summary>
	/// ������ ��������� ����������
	/// </summary>
	private final static int carHeight = 60;
	/// <summary>
	/// ������������ ��������
	private int MaxSpeed;

	public int getMaxSpeed() {
		return MaxSpeed;
	}

	public void setMaxSpeed(int value) {
		MaxSpeed = value;
	}

	/// ��� ����������
	private float Weight;

	public float getWeight() {
		return Weight;
	}

	public void setWeight(float value) {
		Weight = value;
	}

	/// �������� ���� ������
	private Color MainColor;

	public Color getMainColor() {
		return MainColor;
	}

	public void setMainColor(Color value) {
		MainColor = value;
	}

	private Color DopColor;

	public Color getDopColor() {
		return DopColor;
	}

	public void setDopColor(Color value) {
		DopColor = value;
	}

	public Trolleybus(int maxSpeed, float weight, Color mainColor, Color dopColor) throws Exception {
		setMaxSpeed(maxSpeed);
		setWeight(weight);
		setMainColor(mainColor);
		setDopColor(dopColor);

	}

	public void setPosition(int x, int y, int width, int height) throws Exception {
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}

	public void moveMonorail(Direction direction) throws Exception {
		float step = getMaxSpeed() * 100 / getWeight();
		switch (direction) { /// ������
		case Right:
			if (_startPosX + step < _pictureWidth - carWidth) {
				_startPosX += step;
			}

			break;
		/// �����
		case Left:
			if (_startPosX - step > 5) {
				_startPosX -= step;
			}

			break;
		/// �����
		case Up:
			if (_startPosY - step > 30) {
				_startPosY -= step;
			}

			break;
		/// ����
		case Down:
			if (_startPosY + step < _pictureHeight - carHeight) {
				_startPosY += step;
			}

			break;

		}
	}

	public void drawMonorail(Graphics g) throws Exception {

		g.setColor(MainColor);
		g.fillRect((int) _startPosX, (int) _startPosY, carWidth, carHeight - 20);
		g.setColor(DopColor);
		g.fillOval((int) _startPosX + 10, (int) _startPosY + 40, 20, 20);
		g.fillOval((int) _startPosX + 70, (int) _startPosY + 40, 20, 20);
		g.fillRect((int) _startPosX + 40, (int) _startPosY + 10, 20, 30);
		g.setColor(Color.GRAY);
		g.fillRect((int) _startPosX + 70, (int) _startPosY, 30, 30);
	}

}
