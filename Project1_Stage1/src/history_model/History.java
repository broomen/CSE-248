package history_model;

import java.util.Date;

public class History implements Comparable<History> {
	private String trailName;
//	private Date dateHiked;
//	private double distanceHiked;
//	private double durationHiked;
//	private String[] pictures;
//	private double averagePace;

	public History(String trailName) {
		this.trailName = trailName;
	}

	@Override
	public int compareTo(History o) {
		if (trailName.compareTo(o.trailName) == 0) {
			return 0;
		} else if (trailName.compareTo(o.trailName) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	public String getTrailName() {
		return trailName;
	}

	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}

	@Override
	public String toString() {
		return "History [trailName=" + trailName + "]";
	}
	
	

}
