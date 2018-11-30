package Lab_1Java;

import java.util.*;
import java.awt.Color;
import java.awt.Graphics;

public class Parking<T extends ITransport> {

	HashMap<Integer, T> _places;

	private int _pictureWidth;

	private int _pictureHeight;

	private int _placeSizeWidth = 210;

	private int _placeSizeHeight = 110;

	private int _maxCount;

	public Parking(int sizes, int pictureWidth, int pictureHeight) {
		_maxCount = sizes;
		_places = new HashMap<Integer, T>(sizes);
		this._pictureWidth = pictureWidth;
		this._pictureHeight = pictureHeight;
	}

	public int addTransport(T transport) {
		if (_places.size() == _maxCount) {
			return -1;
		}
		for (int i = 0; i < _maxCount; i++) {
			if (checkFreePlace(i)) {
				_places.put(i, transport);
				_places.get(i).SetPosition(10 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 40,
						_pictureWidth, _pictureHeight);
				return i;
			}
		}
		return -1;
	}

	public T removeTransport(int index) {
		if (_places.get(index) != null) {
			T bus = _places.get(index);
			_places.remove(index);
			return bus;
		}
		return null;
	}

	public T getBus(int index) {
		if (_places.get(index) != null) {
			return _places.get(index);
		} else {
			return null;
		}
	}

	private boolean checkFreePlace(int index) {
		if (_places.get(index) == null) {
			return true;
		}
		return false;
	}

	public void Draw(Graphics g) {
		DrawMarking(g);
		for (T i : _places.values()) {
			i.DrawBus(g);
		}
	}

	private void DrawMarking(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, (_maxCount / 5) * _placeSizeWidth, 630);
		for (int i = 0; i < _maxCount / 5; i++) {
			for (int j = 0; j < 6; ++j) {
				g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + 110, j * _placeSizeHeight);
			}
			g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 550);
		}
	}
}