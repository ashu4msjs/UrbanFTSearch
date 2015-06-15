package com.test.ashu.mongo.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.test.ashu.mongo.bean.Venue;
import com.test.ashu.mongo.resources.ApplicationConstants;


public class DBUtil implements ApplicationConstants {
	
	
	


	@SuppressWarnings("deprecation")
	public static DB getMongoDatabase(MongoClient mongo, String dbName){
	
		return mongo.getDB(dbName); //  "urbanft"
		
	}
	
	
	public static DBCollection getDBCollection (final DB db, final String collectionName) {
		
		DBCollection mongoCollection = db.getCollection(collectionName ) ;  // "venue"
		return mongoCollection;
	}
	
	public static MongoClient getMongoClient(){
		
		return  new MongoClient(bundle.getString(MONGO_SERVER_HOSTNAME), PORT_NO);
		
	}
	
	
	public static void closeMongoClient(MongoClient mongoClient){
		
		mongoClient.close();
		
	}
	
	
	
	public static DBCollection getVenueMongoCollection(String dbName, String collectionName){
		
		MongoClient client = getMongoClient();	
		DB mongoDB = getMongoDatabase(client,  dbName );
		return getDBCollection(mongoDB, collectionName);
		
	}
		
	
	
	public static List<Venue> getListOfVenuesFromDBCursor(DBCursor dbCursor){
		
		if(null == dbCursor ){
			
			return null;
		}
	
		List<Venue> venueList = new ArrayList<Venue>();
		
		while (dbCursor.hasNext()) {
				DBObject dbObject = (DBObject) dbCursor.next();
				
				Venue venue = new Venue();
				
				venue.setForeign_venue_source(String.valueOf(dbObject.get(FOREIGN_VENUE_SOURCE)));
				venue.setForeign_venue_name(String.valueOf(dbObject.get(FOREIGN_VENUE_NAME)));
				venue.setForeign_venue_lat(String.valueOf(dbObject.get(FOREIGN_VENUE_LAT)));
				venue.setForeign_venue_lng(String.valueOf(dbObject.get(FOREIGN_VENUE_LNG)));
				venue.setForeign_venue_address1(String.valueOf(dbObject.get(FOREIGN_VENUE_ADDRESS1)));
				venue.setForeign_venue_address2(String.valueOf(dbObject.get(FOREIGN_VENUE_ADDRESS2)));				
				venue.setForeign_venue_city(String.valueOf(dbObject.get(FOREIGN_VENUE_CITY)));
				venue.setForeign_venue_state(String.valueOf(dbObject.get(FOREIGN_VENUE_STATE)));
				venue.setForeign_venue_country(String.valueOf(dbObject.get(FOREIGN_VENUE_COUNTRY)));
				venue.setForeign_venue_zipcode(String.valueOf(dbObject.get(FOREIGN_VENUE_ZIPCODE)));
				venue.setForeign_venue_phonenumber(String.valueOf(dbObject.get(FOREIGN_VENUE_PHONENUMBER)));
				
//				System.out.println("X" +  String.valueOf(dbObject.get("foreign_venue_rating")) + "Y");
				if(isRecordNull(String.valueOf(dbObject.get(FOREIGN_VENUE_RATING)))){
					venue.setForeign_venue_rating("0");
				}else{
					venue.setForeign_venue_rating(String.valueOf(dbObject.get(FOREIGN_VENUE_RATING)));
					venueList.add(venue);
				}
								
			}
	
		return venueList;
	}
	
	
	private static boolean isRecordNull(String string) {

		if ("null".equalsIgnoreCase(string)) {
			return true;
		}		
		return false;
	}
	
}
