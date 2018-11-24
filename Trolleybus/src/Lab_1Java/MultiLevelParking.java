package Lab_1Java;

import java.util.ArrayList;

public class MultiLevelParking {

	ArrayList<Parking<ITransport>> parkingStages;

	private final int countPlaces = 15;
	int currentLevel;

	public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight) {
		parkingStages = new ArrayList<Parking<ITransport>>();
		for (int i = 0; i < countStages; ++i) {
			parkingStages.add(new Parking<ITransport>(countPlaces, pictureWidth, pictureHeight));
		}
	}

	public Parking<ITransport> getAt(int index) {
		if (index > -1 && index < parkingStages.size()) {
			return parkingStages.get(index);
		}
		return null;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void levelUp() {
		if (currentLevel + 1 < parkingStages.size())
			currentLevel++;
	}

	public void levelDown() {
		if (currentLevel > 0)
			currentLevel--;
	}
}
