package Lab_1Java;

import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Parking<T extends ITransport> implements Serializable, Comparable<Parking<T>>, Iterable<T>, Iterator<T> {

	HashMap<Integer, T> _places;

	private int _pictureWidth;

	private int _pictureHeight;

	private int _placeSizeWidth = 210;

	private int _placeSizeHeight = 110;

	private int _maxCount;

	private int currentIndex;

	public Parking(int sizes, int pictureWidth, int pictureHeight) {
		_maxCount = sizes;
		_places = new HashMap<Integer, T>(sizes);
		this._pictureWidth = pictureWidth;
		this._pictureHeight = pictureHeight;
	}

	public int addTransport(T transport)
			throws ParkingOverflowException, ParkingOccupiedPlaceException, ParkingAlreadyHaveException {
		if (this._places.size() == this._maxCount) {
			throw new ParkingOverflowException();
		}

		int index = _places.size();
		for (int i = 0; i <= _places.size(); i++) {
			if (checkFreePlace(i)) {
				index = i;
			}
			if (_places.containsValue(transport)) {
				throw new ParkingAlreadyHaveException();
			}
		}
		if (index != _places.size()) {
			_places.put(index, transport);
			_places.get(index).SetPosition(10 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 40,
					_pictureWidth, _pictureHeight);
			return index;
		}
		this._places.put(this._places.size(), transport);
		_places.get(index).SetPosition(10 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 40,
				_pictureWidth, _pictureHeight);
		return this._places.size() - 1;
	}

	public T removeTransport(int index) throws ParkingNotFoundException {
		if (_places.get(index) != null) {
			T bus = _places.get(index);
			_places.remove(index);
			return bus;
		}
		throw new ParkingNotFoundException();
	}

	public T getBus(int index) {
		if (_places.get(index) != null) {
			return _places.get(index);
		} else {
			return null;
		}
	}

	private boolean checkFreePlace(int index) {
		return !_places.containsKey(index);
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

	@Override
	public int compareTo(Parking<T> other) {
		if (this._places.size() > other._places.size()) {
			return -1;
		} else if (this._places.size() < other._places.size()) {
			return 1;
		} else {
			Integer[] thisKeys = this._places.keySet().toArray(new Integer[this._places.size()]);
			Integer[] otherKeys = other._places.keySet().toArray(new Integer[other._places.size()]);
			for (int i = 0; i < this._places.size(); i++) {
				if (this._places.get(thisKeys[i]).getClass().equals(Bus.class)
						&& other._places.get(otherKeys[i]).getClass().equals(Trolleybus.class)) {
					return 1;
				}
				if (this._places.get(thisKeys[i]).getClass().equals(Trolleybus.class)
						&& other._places.get(otherKeys[i]).getClass().equals(Bus.class)) {
					return -1;
				}
				if (this._places.get(thisKeys[i]).getClass().equals(Bus.class)
						&& other._places.get(otherKeys[i]).getClass().equals(Bus.class)) {
					return ((Bus) this._places.get(thisKeys[i])).compareTo((Bus) other._places.get(otherKeys[i]));
				}
				if (this._places.get(thisKeys[i]).getClass().equals(Trolleybus.class)
						&& other._places.get(otherKeys[i]).getClass().equals(Trolleybus.class)) {
					return ((Trolleybus) this._places.get(thisKeys[i]))
							.compareTo((Trolleybus) other._places.get(otherKeys[i]));
				}
			}
		}
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		if (currentIndex + 1 >= _places.size()) {
			currentIndex = -1;
			return false;
		}
		currentIndex++;
		return true;
	}

	@Override
	public T next() {
		return (T) _places.get(currentIndex);
	}

	private void reset() {
		currentIndex = -1;
	}
}
