package history_model;

import java.util.Date;

/**
 * 
 * This class serves as a means to store information regarding a user's hike.
 * It stores the name of the trail hiked, the date it was hiked,
 * the distance the user hiked, how long, and calculates the pace in
 * miles per hour. It also contains a compareTo and toString method,
 * as well as various general methods such as getters and setters.
 * 
 * @author Nick Broome
 *
 */
public class History implements Comparable<History> {
	private String trailName;
	private Date dateHiked;
	private double distanceHiked;
	private double durationHiked;
	private double averagePace;

	public History(String trailName) {
		this.trailName = trailName;
	}

	public History(String name, double timeTook, Date day, String distance) {
		this.trailName = name;
		this.dateHiked = day;
		this.distanceHiked = Double.parseDouble(distance);
		this.durationHiked = timeTook;
		this.averagePace = calcAveragePace(distance, timeTook);
	}
/**
 * 
 * @param distance This variable is a string of the distance traveled in miles
 * @param timeTook This variable is a double of the time it took to travel
 * in minutes.
 * @return this method returns a calculation of the miles per hour for the user's
 * hike by converting the distance to a double and the timeTook to hours.
 */
	private double calcAveragePace(String distance, double timeTook) {
		double distDoub = Double.parseDouble(distance);
		double timeTookHr = timeTook / 60;
		double milePerHour = distDoub / timeTookHr;
		return milePerHour;
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
		String dateHikedStr = dateHiked.toString();
		String pace = String.format("%.2f", averagePace);
		pace = pace + " miles per hour";
		return "Trail Name: " + trailName + ", Date Hiked: " + dateHikedStr + ", Distance Hiked in Miles: " + distanceHiked
				+ ", Duration Hiked in Minutes: " + durationHiked + ", Pace: " + pace;
	}

	
	
	

}
