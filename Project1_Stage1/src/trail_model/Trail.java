package trail_model;

public class Trail implements Comparable<Trail> {

	private String name;
	private String id;

	private static int idCounter = 0;

	public Trail(String name) {
		this.name = name;
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