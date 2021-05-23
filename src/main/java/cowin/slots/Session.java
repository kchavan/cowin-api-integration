package cowin.slots;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreType
public class Session {
    public String session_id;
    public String date;
    public int available_capacity;
    public int min_age_limit;
    public String vaccine;
    public List<String> slots;
	@Override
	public String toString() {
		return "Session [session_id=" + session_id + ", date=" + date + ", available_capacity=" + available_capacity
				+ ", min_age_limit=" + min_age_limit + ", vaccine=" + vaccine + ", slots=" + slots + "]";
	}
    
    
}
