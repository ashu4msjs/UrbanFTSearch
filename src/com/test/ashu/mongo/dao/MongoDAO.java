package com.test.ashu.mongo.dao;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.test.ashu.mongo.bean.Venue;
import com.test.ashu.mongo.resources.ApplicationConstants;
import com.test.ashu.mongo.service.DistanceCalculator;

public class MongoDAO implements ApplicationConstants {


	/**
	 * 1.> Search venue by name 
	 */
	public List<Venue> searchForeignVenueByName(String foreign_venue_name){
		
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		searchQuery.put(FOREIGN_VENUE_NAME, foreign_venue_name);
	 
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);		
		
	}
	
	/**
	 * 2.> Search venue by city
	*/	
	public List<Venue> searchForeignVenueByCity(String foreign_venue_city){
		
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		searchQuery.put(FOREIGN_VENUE_CITY, foreign_venue_city);
	 
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);		
		
	}
	
	/**
	 * 3.> Search venue by area code
	*/	
	public List<Venue> searchForeignVenueByAreaCode(String foreign_venue_zipcode){
		
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		searchQuery.put(FOREIGN_VENUE_ZIPCODE, Integer.valueOf(foreign_venue_zipcode));
	 
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);		
		
	}
	
	/**
	 * 4.> Search venue by latitude, longitude and radius
	 */
	public List<Venue> searchForeignVenuebyLatitudeLongitudeRadius(
			String foreign_venue_lat, String foreign_venue_lng, double radius) {
		
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		DistanceCalculator distanceCalculator = new DistanceCalculator();
		
		List<String> sourceIdList = distanceCalculator.checkRadius(radius, Double.valueOf(foreign_venue_lat), Double.valueOf(foreign_venue_lng));
		
		searchQuery.put(FOREIGN_VENUE_SOURCE_ID, new BasicDBObject("$in", sourceIdList));
		
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);
	}
	
	/**
	 * 5.> Search venue by name and city
	 */
	public List<Venue> searchForeignVenueByNameAndCity(String foreign_venue_name, String foreign_venue_city){
		
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		searchQuery.put(FOREIGN_VENUE_NAME, foreign_venue_name);
		searchQuery.put(FOREIGN_VENUE_CITY, foreign_venue_city);
	 
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);		
			
	}
	
	/**
	 * 6.> Search venue by area code and name
	*/
	public List<Venue> searchForeignVenueByAreaCodeAndName(String foreign_venue_zipcode, String foreign_venue_name){
		
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		searchQuery.put(FOREIGN_VENUE_ZIPCODE, Integer.valueOf(foreign_venue_zipcode));
		searchQuery.put(FOREIGN_VENUE_NAME, foreign_venue_name);
	 
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);		
		
	}
	
	/**
	 * 7.> Search venue by name, latitude, longitude, and radius
	 */
	public List<Venue> searchForeignVenueByNameLatLngRadius(
			String foreign_venue_name, String foreign_venue_lat, String foreign_venue_lng, double radius){
				
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		DistanceCalculator distanceCalculator = new DistanceCalculator();
		
		List<String> sourceIdList = distanceCalculator.checkRadius(radius, Double.valueOf(foreign_venue_lat), Double.valueOf(foreign_venue_lng));
		
		searchQuery.put(FOREIGN_VENUE_SOURCE_ID, new BasicDBObject("$in", sourceIdList));
		searchQuery.put(FOREIGN_VENUE_NAME, foreign_venue_name);
		
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);
	}
	
	/**
	 * 8.> Search venue by rating and city
	 */
	public List<Venue> searchForeignVenueByRatingCity(double foreign_venue_rating, String foreign_venue_city){
		
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		searchQuery.put(FOREIGN_VENUE_RATING, new BasicDBObject("$gte", foreign_venue_rating));
		searchQuery.put(FOREIGN_VENUE_CITY, foreign_venue_city);
		
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);
	}
		
	/**
	 * 9.> Seach venue by rating and zipcode
	 */
	public List<Venue> searchForeignVenueByRatingZipcode(double foreign_venue_rating, String foreign_venue_zipcode){
		
		DBCollection collection = DBUtil.getVenueMongoCollection("urbanft", "venue");
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		searchQuery.put(FOREIGN_VENUE_RATING, new BasicDBObject("$gte", foreign_venue_rating));
		searchQuery.put(FOREIGN_VENUE_ZIPCODE, Integer.valueOf(foreign_venue_zipcode));
		
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);
	}
	
	/**
	 * 10.> Search venue by rating, latitude, longitude and radius
	 */
	public List<Venue> searchForeignVenueByRatingLatLngRadius(
			double foreign_venue_rating, String foreign_venue_lat, String foreign_venue_lng, double radius){
		
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		DistanceCalculator distanceCalculator = new DistanceCalculator();
		
		List<String> sourceIdList = distanceCalculator.checkRadius(radius, Double.valueOf(foreign_venue_lat), Double.valueOf(foreign_venue_lng));
		
		searchQuery.put(FOREIGN_VENUE_SOURCE_ID, new BasicDBObject("$in", sourceIdList));
		searchQuery.put(FOREIGN_VENUE_RATING, new BasicDBObject("$gte", foreign_venue_rating));
		
		DBCursor cursor = collection.find(searchQuery);
		
		return DBUtil.getListOfVenuesFromDBCursor(cursor);
	}


}
