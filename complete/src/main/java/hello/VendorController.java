package hello;

import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javafx.util.Pair;
import sela.model.Hotel;
import sela.service.HotelLookupService;
import sela.util.PairComarator;

@RestController
public class VendorController {
	 
	@Autowired
	private HotelLookupService hotelLookupService;
	
	
	//@Autowired
	//private GreetingService service;
	
	//@Autowired
	private PairComarator pairComarator;
	
	@PostConstruct
	public void init() {
		pairComarator=new PairComarator();
	}

	@GetMapping({ "/api/{hotel}" })
	public Pair<String, Hotel> hotel(@PathVariable String hotel) {
		  	
	 
		List<Pair<String, Hotel>> hotelsList=null;
		 
		// null
		hotelsList =hotelLookupService.findAllHotels(hotel.toLowerCase());
	 
		 
	        
        if(hotelsList==null) {
        	return new Pair<>("Requeted Hotel not Found ",null);
        }
           
	 	Stream<Pair<String, Hotel>> str=hotelsList.stream();
	 	Pair<String, Hotel> minPair=str.min(pairComarator).orElse(new Pair<>("Requeted Hotel not Found ",null));
	 	
	 //	Pair<String, Hotel> minPair=null;//str.min(pairComarator).orElse(new Pair<>("Requeted Hotel not Found ",null));
	 	return minPair;
	 	
	 	//return null;
	 		 
	}

	 
	
	
	 

}
