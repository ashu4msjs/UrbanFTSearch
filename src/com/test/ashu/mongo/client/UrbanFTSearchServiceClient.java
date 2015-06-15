package com.test.ashu.mongo.client;

import java.util.List;

import com.test.ashu.mongo.bean.Venue;
import com.test.ashu.mongo.service.UrbanFTSearchService;
import com.test.ashu.mongo.service.UrbanFTSearchServiceImpl;

public class UrbanFTSearchServiceClient {

	
	public static void main(String[] args) {
		
		UrbanFTSearchService service = new UrbanFTSearchServiceImpl();
		
		Venue venue = new Venue();
//		venue.setForeign_venue_name("Chipotle Mexican Grill");//("/.*D.*");
		venue.setForeign_venue_city("Boston");
//		venue.setForeign_venue_zipcode("2215");
		venue.setForeign_venue_rating("3.8");
//		venue.setForeign_venue_lat("42.3447"); venue.setForeign_venue_lng("-71.1009");
//		venue.setRadius(1);
		
		
		
		List<Venue> venueList = service.searchVenue(venue);
		
		
		
		int count = 1;
		try {
			for (Venue venue2 : venueList) {				
				System.out.println(count++ + "  " +venue2.getForeign_venue_name()  +" , "+ venue2.getForeign_venue_address1()
						+" , "+ venue2.getForeign_venue_lat()+" , "+ venue2.getForeign_venue_lng()
						+" , "+ venue2.getForeign_venue_address2()+" , "+ venue2.getForeign_venue_city()+" , "+ venue2.getForeign_venue_state()
						+" , "+ venue2.getForeign_venue_country()+" , "+ venue2.getForeign_venue_zipcode()
						+" , "+ venue2.getForeign_venue_phonenumber()+" , "+ venue2.getForeign_venue_rating()
						);
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("Sorry !! can't give you result for the entered value");
//			e.printStackTrace();
		}
		
		
	}
	
	
	/*
	public static boolean testSearchForeignVenueByRatingZipcode(){
		
//		System.out.println("testSearchForeignVenueByRatingZipcode succeeded : " + testSearchForeignVenueByRatingZipcode());
		
		UrbanFTSearchService service = new UrbanFTSearchServiceImpl();
		
		List<Venue> venues = service.searchForeignVenueByRatingZipcode(4.0, "2215");
		
		if(null != venues ){
			return true;
		}
		else 
			return false;
	}
	*/
}
