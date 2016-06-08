package valueobject;

public final class RoundCount {
	
	private static int roundCount = 1;
	
	public static int getRoundCount(){
		return roundCount++;
	}

}
