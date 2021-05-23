package cowin.slots;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Center {
    public int center_id;
    public String name;
    public String address;
    public String state_name;
    public String district_name;
    public String block_name;
    public int pincode;
    public int lat;
    @JsonProperty("long")
    public int longg;
    public String from;
    public String to;
    public String fee_type;
    private List<Session> sessions;
  
    
	public List<Session> getSessions() {
		return sessions;
	}

	@Override
	public String toString() {
		return "Center [center_id=" + center_id + ", name=" + name + ", address=" + address + ", state_name="
				+ state_name + ", district_name=" + district_name + ", block_name=" + block_name + ", pincode="
				+ pincode + ", lat=" + lat + ", longg=" + longg + ", from=" + from + ", to=" + to + ", fee_type="
				+ fee_type + ", sessions=" + sessions + "]";
	}
	
}
