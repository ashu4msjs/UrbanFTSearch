package com.test.ashu.mongo.service;

import java.util.List;

import com.test.ashu.mongo.bean.Venue;

public interface UrbanFTSearchService {

	public List<Venue> searchVenue(Venue venue);
	
	
//	public List<Venue> searchForeignVenueByRatingZipcode(double foreign_venue_rating, String foreign_venue_zipcode);
}
