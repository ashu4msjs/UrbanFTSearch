package com.test.ashu.mongo.service;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.test.ashu.mongo.bean.Venue;
import com.test.ashu.mongo.dao.DBUtil;
import com.test.ashu.mongo.resources.ApplicationConstants;



public class DistanceCalculator implements ApplicationConstants {
	
	/**
	 * Distance calculation using Haversine formula
	 */
	public double distanceFrom(double lat1, double lng1, double lat2, double lng2) {
	    double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);
	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
	            * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    return dist;
	}
	
	/**
	 * This method checks radius, i.e. - distance between user given lat & lng and those present in database
	 * is less than the radius value given by user. It returns the list of source_id stored in database
	 */
	public List<String> checkRadius(double radius, double lat1, double lng1){
		DistanceCalculator dc = new DistanceCalculator();
		Venue venue = new Venue();
		List <String> sourceIdList = new ArrayList<String>();
		
		DBCollection collection = DBUtil.getVenueMongoCollection(DATABASE_NAME, COLLECTION_NAME);		
		
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()){
			DBObject dbObject = (DBObject)cursor.next(); double distance;
			
			venue.setForeign_venue_lat(String.valueOf(dbObject.get(FOREIGN_VENUE_LAT)));
			venue.setForeign_venue_lng(String.valueOf(dbObject.get(FOREIGN_VENUE_LNG)));
			
			distance = dc.distanceFrom(lat1, lng1, Double.valueOf(venue.getForeign_venue_lat()), Double.valueOf(venue.getForeign_venue_lng()));
			
			if(distance <= radius){
				sourceIdList.add(String.valueOf(dbObject.get(FOREIGN_VENUE_SOURCE_ID)));
			}
			
		}
		System.out.println("number of source ids found within radius = "+ sourceIdList.size());
		return sourceIdList;
	}

	public static void main(String[] args) {
	
		double lat1,lat2,lng1,lng2;
		lat1 = 40.6065;
		lat2 = 40.7388;
		lng1 = -74.0123;
		lng2 = -73.9946;
		DistanceCalculator dc = new DistanceCalculator();
		double distance = dc.distanceFrom(lat1, lng1, lat2, lng2);
		System.out.println(distance);

	}

}
