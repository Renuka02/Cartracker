package com.spring.entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity(name="Readings_Master")
@Table(name="Readings_Master")
public class Readings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "readingId")
	private int readingId;
	
	public int getReadingId() {
		return readingId;
	}



	public void setReadingId(int readingId) {
		this.readingId = readingId;
	}

	@Column(name = "vin")
	private String vin;
	
	@Column(name = "latitude")
	private float latitude;
	
	@Column(name = "longitude")
	private float longitude;
	
	
	@Column(name = "timestamp")
	private String timestamp;

	@Column(name = "fuelVolume")
	private float fuelVolume;

	@Column(name = "speed")
	private int speed;

	@Column(name = "engineHp")
	private int engineHp;

	@NotNull(message="NOT_NULL")
	@Type(type="true_false")
	@Column(name = "checkEngineLightOn" , nullable = false)
	private boolean checkEngineLightOn;

	@NotNull(message="NOT_NULL")
	@Type(type="true_false")
	@Column(name = "engineCoolantLow" , nullable = false)
	private boolean engineCoolantLow;

	@NotNull(message="NOT_NULL")
	@Type(type="true_false")
	@Column(name = "cruiseControlOn" , nullable = false)
	private boolean cruiseControlOn;

	@Column(name = "engineRpm")
	private int engineRpm;
	
	@Column(name = "tirefrontleft")
	private int tirefrontleft;
	
	@Column(name = "tirefrontright")
	private int tirefrontright;
	
	@Column(name = "tirerearleft")
	private int tirerearleft;
	
	@Column(name = "tirerearright")
	private int tirerearright;
	
	@Column(name = "alert_priority")
	private String alert_priority;
	//@OneToOne(mappedBy)
	//@Column(name = "tire")
	//private Tires tire;

	public String getAlert_priority() {
		return alert_priority;
	}



	public void setAlert_priority(String alert_priority) {
		this.alert_priority = alert_priority;
	}



	public Readings() {
		
	}

	
	
	public int getTirefrontleft() {
		return tirefrontleft;
	}



	public void setTirefrontleft(int tirefrontleft) {
		this.tirefrontleft = tirefrontleft;
	}



	public int getTirefrontright() {
		return tirefrontright;
	}



	public void setTirefrontright(int tirefrontright) {
		this.tirefrontright = tirefrontright;
	}



	public int getTirerearleft() {
		return tirerearleft;
	}



	public void setTirerearleft(int tirerearleft) {
		this.tirerearleft = tirerearleft;
	}



	public int getTirerearright() {
		return tirerearright;
	}



	public void setTirerearright(int tirerearright) {
		this.tirerearright = tirerearright;
	}



	

	public Readings(String vin, float latitude, float longitude, String timestamp, float fuelVolume, int speed,
			int engineHp, boolean checkEngineLightOn, boolean engineCoolantLow, boolean cruiseControlOn, int engineRpm,
			int tirefrontleft, int tirefrontright, int tirerearleft, int tirerearright) {
		super();
		this.vin = vin;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
		this.fuelVolume = fuelVolume;
		this.speed = speed;
		this.engineHp = engineHp;
		this.checkEngineLightOn = checkEngineLightOn;
		this.engineCoolantLow = engineCoolantLow;
		this.cruiseControlOn = cruiseControlOn;
		this.engineRpm = engineRpm;
		this.tirefrontleft = tirefrontleft;
		this.tirefrontright = tirefrontright;
		this.tirerearleft = tirerearleft;
		this.tirerearright = tirerearright;
	}



	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public float getFuelVolume() {
		return fuelVolume;
	}

	public void setFuelVolume(float fuelVolume) {
		this.fuelVolume = fuelVolume;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getEngineHp() {
		return engineHp;
	}

	public void setEngineHp(int engineHp) {
		this.engineHp = engineHp;
	}

	public boolean isCheckEngineLightOn() {
		return checkEngineLightOn;
	}

	public void setCheckEngineLightOn(boolean checkEngineLightOn) {
		this.checkEngineLightOn = checkEngineLightOn;
	}

	public boolean isEngineCoolantLow() {
		return engineCoolantLow;
	}

	public void setEngineCoolantLow(boolean engineCoolantLow) {
		this.engineCoolantLow = engineCoolantLow;
	}

	public boolean isCruiseControlOn() {
		return cruiseControlOn;
	}

	public void setCruiseControlOn(boolean cruiseControlOn) {
		this.cruiseControlOn = cruiseControlOn;
	}

	public int getEngineRpm() {
		return engineRpm;
	}

	public void setEngineRpm(int engineRpm) {
		this.engineRpm = engineRpm;
	}

	
	//@Override
	//public String toString() {
		//return "Readings [vin=" + vin + ", latitude=" + latitude + ", longitude=" + longitude + ", timestamp="
		//		+ timestamp + ", fuelVolume=" + fuelVolume + ", speed=" + speed + ", engineHp=" + engineHp
	//			+ ", checkEngineLightOn=" + checkEngineLightOn + ", engineCoolantLow=" + engineCoolantLow
	//			+ ", cruiseControlOn=" + cruiseControlOn + ", engineRpm=" + engineRpm + ", tire=" +0 + "]";
	//}

}
