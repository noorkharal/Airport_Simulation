package finalfinal;

public class Details {
	protected int minsBoarding; //to show how many minutes before departure should the boarding start
	protected int minsTaxiway; // to show how many minutes before departure should plane be in taxiway
	protected int minsRunway; //to show how many minutes before departure should plane be in runway
	protected int minsTakeOff;	// to show how many minutes after takeoff should plane be deleted or how many
								// mins it will take for plane to land on runway
	
	public Details() {
		minsBoarding = 3;
		minsTaxiway = 2;
		minsRunway = 1;
		minsTakeOff = 1;
	}
	
	public void changeDetails(int minsB, int minsT, int minsR, int minsTO) {
		minsBoarding = minsB;
		minsTaxiway = minsT;
		minsRunway = minsR;
		minsTakeOff = minsTO;
	}
	
}
