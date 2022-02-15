//======K
package model;
import java.time.LocalDateTime;

public class TimeGet {

	public String getNow () {
    	LocalDateTime ldt = LocalDateTime.now();
    	String time = ldt.toString().substring(0,19);
    	return time;
	    }
	    
}