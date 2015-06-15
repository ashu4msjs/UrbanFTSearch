package com.test.ashu.mongo.resources;

import java.util.ResourceBundle;

public interface ApplicationConstants {

	public static final ResourceBundle bundle = ResourceBundle.getBundle("com.test.ashu.mongo.resources.ApplicationResources");
	public static final String MONGO_SERVER_HOSTNAME = "mongo.server.hostname";
	public static final int PORT_NO = 27017;
	public static final String COLLECTION_NAME = "venue";
	public static final String DATABASE_NAME = "urbanft";
	
	public static final String FOREIGN_VENUE_SOURCE_ID = "foreign_venue_source_id";
	public static final String FOREIGN_VENUE_SOURCE = "foreign_venue_source";
	public static final String FOREIGN_VENUE_NAME = "foreign_venue_name";
	public static final String FOREIGN_VENUE_LAT = "foreign_venue_lat";
	public static final String FOREIGN_VENUE_LNG = "foreign_venue_lng";
	public static final String FOREIGN_VENUE_ADDRESS1 = "foreign_venue_address1";
	public static final String FOREIGN_VENUE_ADDRESS2 = "foreign_venue_address2";
	public static final String FOREIGN_VENUE_CITY = "foreign_venue_city";
	public static final String FOREIGN_VENUE_STATE = "foreign_venue_state";
	public static final String FOREIGN_VENUE_ZIPCODE = "foreign_venue_zipcode";
	public static final String FOREIGN_VENUE_COUNTRY = "foreign_venue_country";
	public static final String FOREIGN_VENUE_PHONENUMBER = "foreign_venue_phonenumber";
	public static final String FOREIGN_VENUE_RATING = "foreign_venue_rating";
	
}
