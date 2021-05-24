package cowin.slots;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class CheckSlotController {

	@RequestMapping( "/checkslot")
	public ModelAndView sayHi(@RequestParam("pincode") String pincode, @RequestParam("date") String date) {

		if(pincode != null && pincode != "" && date != null && date != "" ) {

			VaccineService vaccineService = new VaccineService();
			String data = vaccineService.isAvailable( pincode, date);

			ModelAndView mv= new ModelAndView(); 
			mv.addObject("respdata", data);
			mv.setViewName("checkslot");

			System.out.println("before return");

			return mv;
		}

		return null;
	}

}
