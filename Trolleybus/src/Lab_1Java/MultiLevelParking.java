package Lab_1Java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MultiLevelParking {

	ArrayList<Parking<ITransport>> parkingStages;

	int countStages = 5;

	private int pictureWidth;

	private int pictureHeight;

	private final int countPlaces = 15;
	int currentLevel;

	public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight) {
		parkingStages = new ArrayList<Parking<ITransport>>(countStages);
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

	public boolean levelUp() {
		if (currentLevel + 1 < parkingStages.size()) {
			currentLevel++;
			return true;
		}
		return false;
	}

	public boolean levelDown() {
		if (currentLevel > 0) {
			currentLevel--;
			return true;
		}
		return false;
	}

	public boolean SaveData(String filename) {
		File file = new File(filename);
		if (file.exists())
			file.delete();
		try (FileOutputStream fileStream = new FileOutputStream(file)) {
			try (BufferedOutputStream bs = new BufferedOutputStream(fileStream)) {
				String str = "CountLeveles:" + parkingStages.size() + System.lineSeparator();
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				for (int i = 0; i < str.length(); i++) {
					byteOut.write(str.charAt(i));
				}
				byte[] info = byteOut.toByteArray();
				fileStream.write(info, 0, info.length);
				for (Parking<ITransport> level : parkingStages) {
					byteOut = new ByteArrayOutputStream();
					str = "Level" + System.lineSeparator();
					for (int i = 0; i < str.length(); i++) {
						byteOut.write(str.charAt(i));
					}
					info = byteOut.toByteArray();
					fileStream.write(info, 0, info.length);
					for (int i = 0; i < countPlaces; i++) {
						ITransport bus = level.getBus(i);
						if (bus != null) {
							byteOut = new ByteArrayOutputStream();
							String busInfo = bus.getClass().getName() + ":" + bus.getInfo() + System.lineSeparator();
							busInfo = busInfo.substring(10);
							for (int j = 0; j < busInfo.length(); j++) {
								byteOut.write(busInfo.charAt(j));
							}
							info = byteOut.toByteArray();
							fileStream.write(info, 0, info.length);
						}
					}
				}
			}
			fileStream.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean load(String filename) throws ParkingOverflowException, ParkingOccupiedPlaceException, ParkingAlreadyHaveException  {
		File file = new File(filename);
		if (!file.exists()) {
			return false;
		}
		try (FileInputStream fileStream = new FileInputStream(filename)) {
			String s = "";
			try (BufferedInputStream bs = new BufferedInputStream(fileStream)) {
				Path path = Paths.get(file.getAbsolutePath());
				byte[] b = new byte[fileStream.available()];
				b = Files.readAllBytes(path);
				ByteArrayInputStream bos = new ByteArrayInputStream(b);
				String value = new String(b, StandardCharsets.UTF_8);
				while (bos.read(b, 0, b.length) > 0) {
					s += value;
				}
				s = s.replace("\r", "");
				String[] strs = s.split("\n");
				if (strs[0].contains("CountLeveles")) {
					if (parkingStages != null) {
						parkingStages.clear();
					}
					parkingStages = new ArrayList<Parking<ITransport>>();
				} else {
					return false;
				}
				int counter = -1;
				for (int i = 0; i < strs.length; i++) {
					if (strs[i].startsWith("Level")) {
						counter++;
						parkingStages.add(new Parking<ITransport>(countPlaces, pictureWidth, pictureHeight));
					} else if (strs[i].startsWith("Bus")) {
						ITransport bus = new Bus(strs[i].split(":")[1]);
						int number = parkingStages.get(counter).addTransport(bus);
						if (number == -1) {
							return false;
						}
					} else if (strs[i].startsWith("Trolleybus")) {
						ITransport bus = new Trolleybus(strs[i].split(":")[1]);
						int number = parkingStages.get(counter).addTransport(bus);
						if (number == -1) {
							return false;
						}
					}
				}
			}
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
	public void Sort() {
		parkingStages.sort(null);
	}
}