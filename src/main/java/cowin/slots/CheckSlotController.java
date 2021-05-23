package cowin.slots;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CheckSlotController {

	@RequestMapping(method = RequestMethod.GET,path = "/checkslot")
	
	public String sayHi(@RequestParam("pincode") String pincode, @RequestParam("date") String date) {
		
		if(pincode != null && pincode != "" && date != null && date != "" ) {
			
			
			VaccineService vaccineService = new VaccineService();
			String data = vaccineService.isAvailable( pincode, date);
			
			/*
			 * ModelAndView mv= new ModelAndView(); mv.addObject("respdata", data);
			 * mv.setViewName("checkslot.jsp");
			 */
			return data;
		}
		
		return null;
	}

}
