package trail_model;

import java.io.Serializable;
/** 
 *  This class is holds all the information needed for the trail object. It stores
 *  the name, a unique incrementing ID, an array of the headAddresses, the length,
 *  the elevation, the difficulty, and the trailType. Whenever a new trail is made,
 *  it increments a static counter. This class contains common methods such as 
 *  getters and setters, as well as a toString and compareTo method.
 * @author Nick Broome
 *
 */

public class Trail implements Comparable<Trail>, Serializable{

	private String name;
	private String id;
	private String[] headAddress;
	private long length;
	private long elevation;
	private Difficulty diff;
	private TrailType type;

	private static int idCounter = 0;

	public Trail(String name) {
		this.name = name;
		id = String.valueOf(idCounter++);
	}

	public Trail(String name, String[] address, Long length, Long elev, Difficulty diff,
			TrailType type) {
		this.name = name;
		this.headAddress = address;
		this.length = length;
		this.elevation = elev;
		this.diff = diff;
		this.type = type;
		id = String.valueOf(idCounter++);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String[] getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String[] headAddress) {
		this.headAddress = headAddress;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public long getElevation() {
		return elevation;
	}

	public void setElevation(long elevation) {
		this.elevation = elevation;
	}

	public Difficulty getDiff() {
		return diff;
	}

	public void setDiff(Difficulty diff) {
		this.diff = diff;
	}

	public TrailType getType() {
		return type;
	}

	public void setType(TrailType type) {
		this.type = type;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	@Override
	public String toString() {
		return "Trail [name=" + name + ", id=" + id + "]";
	}

	@Override
	public int compareTo(Trail o) {
		if (name.compareTo(o.name) == 0) {
			return 0;
		} else if (name.compareTo(o.name) > 0) {
			return 1;
		} else {
			return -1;
		}
	}

}