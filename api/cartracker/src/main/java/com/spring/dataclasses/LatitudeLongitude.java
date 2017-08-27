package com.spring.dataclasses;

public class LatitudeLongitude {
	private String vin;
	private float latitude;
	private float longitude;
	private String time_stamp;
	
	public float getLatitude() {
		return latitude;
	}
	public LatitudeLongitude(String vin, float latitude, float longitude, String time_stamp) {
		super();
		this.vin = vin;
		this.latitude = latitude;
		this.longitude = longitude;
		this.time_stamp = time_stamp;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	

	
}
