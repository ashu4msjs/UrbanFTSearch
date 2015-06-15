package com.test.ashu.mongo.service;

import java.util.List;

import com.test.ashu.mongo.bean.Venue;
import com.test.ashu.mongo.dao.MongoDAO;
import com.test.ashu.mongo.resources.ApplicationConstants;

public class UrbanFTSearchServiceImpl implements UrbanFTSearchService, ApplicationConstants {
	
	
	/**
	 * Covers all the cases of search
	 */
	@Override
	public List<Venue> searchVenue(Venue venue) {
		
		List<Venue> venueList = null;
		
		if(null == venue){
			return venueList;
		}
		//name & city
		if(null != venue.getForeign_venue_name() && null != venue.getForeign_venue_city()){
			return searchForeignVenueByNameAndCity(venue.getForeign_venue_name(),venue.getForeign_venue_city());
		}
		//name & area code
		else if (null != venue.getForeign_venue_zipcode() && null != venue.getForeign_venue_name()){
			return searchForeignVenueByAreaCodeAndName(venue.getForeign_venue_zipcode(),venue.getForeign_venue_name());
		}
		//name, latitude, longitude, radius
		else if(null != venue.getForeign_venue_name() && null != venue.getForeign_venue_lat() && null != venue.getForeign_venue_lng() && null != String.valueOf(venue.getRadius())){
			return searchForeignVenueByNameLatLngRadius(venue.getForeign_venue_name(),venue.getForeign_venue_lat(),venue.getForeign_venue_lng(),venue.getRadius());
		}
		//city & rating
		else if(null != venue.getForeign_venue_city() && null != venue.getForeign_venue_rating()){
			return searchForeignVenueByRatingCity(Double.valueOf(venue.getForeign_venue_rating()), venue.getForeign_venue_city());
		}
		//zipcode & rating
		else if(null != venue.getForeign_venue_rating() && null != venue.getForeign_venue_zipcode()){
			return searchForeignVenueByRatingZipcode(Double.valueOf(venue.getForeign_venue_rating()), venue.getForeign_venue_zipcode());
		}	
		//latitude, longitude, radius & rating
		else if (null != venue.getForeign_venue_rating() && null != venue.getForeign_venue_lat() && null != venue.getForeign_venue_lng() && null != String.valueOf(venue.getRadius())){
			return searchForeignVenueByRatingLatLngRadius(Double.valueOf(venue.getForeign_venue_rating()), venue.getForeign_venue_lat(),venue.getForeign_venue_lng(),venue.getRadius());
		}
		//latitude, longitude & radius
		else if(null != venue.getForeign_venue_lat() && null != venue.getForeign_venue_lng() && null != String.valueOf(venue.getRadius())){
			return searchForeignVenuebyLatitudeLongitudeRadius(venue.getForeign_venue_lat(),venue.getForeign_venue_lng(),venue.getRadius());
		}
		//area code
		else if (null != venue.getForeign_venue_zipcode()){
			return searchForeignVenueByAreaCode(venue.getForeign_venue_zipcode());
		}
		//city
		else if (null != venue.getForeign_venue_city()){		
			return searchForeignVenueByCity(venue.getForeign_venue_city());
		}
		//name
		else if (null != venue.getForeign_venue_name()){
			return searchForeignVenueByName(venue.getForeign_venue_name());
		}
//		else{
//			System.out.println("Sorry for NullPointerException, can't give you result as of now ! Working on it");
//		}
		
		return venueList;
		
	}

	

	/**
	 * 1.> Search venue by name 
	 */
	private List<Venue> searchForeignVenueByName(String foreign_venue_name){
		
		return dao.searchForeignVenueByName(foreign_venue_name);
		
	}
	
	/**
	 * 2.> Search venue by city
	*/	
	private List<Venue> searchForeignVenueByCity(String foreign_venue_city){

		return dao.searchForeignVenueByCity(foreign_venue_city);
		
	}
	
	/**
	 * 3.> Search venue by area code
	*/	
	private List<Venue> searchForeignVenueByAreaCode(String foreign_venue_zipcode){
		
		return dao.searchForeignVenueByAreaCode(foreign_venue_zipcode);
		
	}
	
	/**
	 * 4.> Search venue by latitude, longitude and radius
	 */
	private List<Venue> searchForeignVenuebyLatitudeLongitudeRadius(
			String foreign_venue_lat, String foreign_venue_lng, double radius) {
		
		return dao.searchForeignVenuebyLatitudeLongitudeRadius(foreign_venue_lat, foreign_venue_lng, radius);
	}

	/**
	 * 5.> Search venue by name and city
	 */
	private List<Venue> searchForeignVenueByNameAndCity(String foreign_venue_name, String foreign_venue_city){
		
		return dao.searchForeignVenueByNameAndCity(foreign_venue_name, foreign_venue_city);
			
	}
	
	/**
	 * 6.> Search venue by area code and name
	*/
	private List<Venue> searchForeignVenueByAreaCodeAndName(String foreign_venue_zipcode, String foreign_venue_name){
		
		return dao.searchForeignVenueByAreaCodeAndName(foreign_venue_zipcode, foreign_venue_name);
		
	}
	
	/**
	 * 7.> Search venue by name, latitude, longitude, and radius
	 */
	private List<Venue> searchForeignVenueByNameLatLngRadius(
			String foreign_venue_name, String foreign_venue_lat, String foreign_venue_lng, double radius){
				
		return dao.searchForeignVenueByNameLatLngRadius(foreign_venue_name, foreign_venue_lat, foreign_venue_lng, radius);
		
	}
	
	/**
	 * 8.> Search venue by rating and city
	 */
	private List<Venue> searchForeignVenueByRatingCity(double foreign_venue_rating, String foreign_venue_city){
		
		return dao.searchForeignVenueByRatingCity(foreign_venue_rating, foreign_venue_city);
		
	}
	
	/**
	 * 9.> Search venue by rating and zipcode
	 */
	private List<Venue> searchForeignVenueByRatingZipcode(double foreign_venue_rating, String foreign_venue_zipcode){
		
		return dao.searchForeignVenueByRatingZipcode(foreign_venue_rating, foreign_venue_zipcode);
	}
	
	/**
	 * 10.> Search venue by rating, latitude, longitude and radius
	 */
	private List<Venue> searchForeignVenueByRatingLatLngRadius(
			double foreign_venue_rating, String foreign_venue_lat, String foreign_venue_lng, double radius){
		
		return dao.searchForeignVenueByRatingLatLngRadius(foreign_venue_rating, foreign_venue_lat, foreign_venue_lng, radius);
		
	}
		
	/**
	 * This is implemented here so as to cause minimal changes in the service class when we decide to move onto some
	 * different database, e.g:- Cassandra. In that case we simple need to write CassandraDAO dao = new CassandraDAO();
	 * to implement new database.
	 */
	MongoDAO dao = new MongoDAO();
	
	
}
