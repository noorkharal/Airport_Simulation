package finalfinal;

public class Destination {
	protected int startingPoint;
	protected int endingPoint;
	protected double totalCost;
	protected Time start;
	protected Time end;
	
	public Destination() {
		
	}
	public Destination(int sp, int ep, Time s, Time e) {
		startingPoint = sp;
		endingPoint = ep;
		start= s;
		end = e;
		
	}
}