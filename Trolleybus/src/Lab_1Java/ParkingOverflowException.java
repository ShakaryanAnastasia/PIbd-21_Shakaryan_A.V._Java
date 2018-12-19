package Lab_1Java;

public class ParkingOverflowException extends Exception{
	public ParkingOverflowException() {
		super("No free places");
	}
}
