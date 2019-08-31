package hello;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javafx.util.Pair;
import sela.model.Hotel;
import sela.service.HotelLookupService;
import sela.util.PairComarator;


@RunWith(SpringRunner.class)
@WebMvcTest(VendorController.class)
//@ContextConfiguration(classes = {PairComarator.class})
public class VendoeMockTest {

	
	 @Autowired
	 private MockMvc mockMvc;

	     
	 @MockBean
	private GreetingService service;
	 
	 
	 @MockBean
	 private HotelLookupService hotelService;
	    
	 
	 
	 @Test
	 public void greetingTest() throws Exception {
	        when(service.myGreet()).thenReturn("Hello Eyal");
	        this.mockMvc.perform(get("/api/test/greeting")).andDo(print()).andExpect(status().isOk())
	                .andExpect(content().string(containsString("Hello Mock")));
	    }
	 

	@Test
	public void testVendor() throws Exception {
		
		List<Pair<String, Hotel>> list=new ArrayList<>();
		Hotel h=new Hotel();
		h.setName("sheraton");
		h.setAddress("http://somedomain.com");
		h.setPrice(120);
		Pair<String ,Hotel> pair=new Pair<>(h.getAddress(), h);
		list.add(pair);
		
		Hotel h1=new Hotel();
		h1.setName("sheraton");
		h1.setAddress("http://otherdomain.com");
		h1.setPrice(200);
		Pair<String ,Hotel> pair1=new Pair<>(h1.getAddress(), h1);
		list.add(pair1);
		
		
		when(hotelService.findAllHotels("sheraton")).thenReturn(list);

		
		
		//when(hotelService.getTestString()).thenReturn("Eyal");
		 this.mockMvc.perform(get("/api/sheraton")).andDo(print()).andExpect(status().isOk())
         .andExpect(content().string(containsString("Hello Mock")));
	}

}
