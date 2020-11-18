package trail_model;

import java.io.Serializable;
/**
 * 
 * This enum is for the type of a trail. Per the project instructions,
 * the difficulty could only be LOOP, OUT_AND_BACK, POINT_TO_POINT, 
 * which are the three values listed below.
 * 
 * @author Nick Broome
 *
 */
public enum TrailType implements Serializable{
	LOOP, OUT_AND_BACK, POINT_TO_POINT;

}
