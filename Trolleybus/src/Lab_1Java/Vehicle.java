package Lab_1Java;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Vehicle implements ITransport {
	/// <summary>
	/// ����� ���������� ��������� ����������
	/// </summary>
	protected float _startPosX;
	/// <summary>
	/// ������ ���������� ��������� ����������
	/// </summary>
	protected float _startPosY;
	/// <summary>
	/// ������ ���� ���������
	/// </summary>
	protected int _pictureWidth;
	// ������ ���� ���������
	protected int _pictureHeight;
	/// <summary>
	/// ������������ ��������
	/// </summary>

	public int __MaxSpeed;

	public int getMaxSpeed() {
		return __MaxSpeed;
	}

	public void setMaxSpeed(int value) {
		__MaxSpeed = value;
	}

	public int __Weight;

	public int getWeight() {
		return __Weight;
	}

	public void setWeight(int value) {
		__Weight = value;
	}

	transient public Color __MainColor;

	public Color getMainColor() {
		return __MainColor;
	}

	public void setMainColor(Color value) {
		__MainColor = value;
	}

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}

	public abstract void DrawBus(Graphics g);

	public abstract void MoveTransport(Direction direction);

	public abstract String getInfo();
}
