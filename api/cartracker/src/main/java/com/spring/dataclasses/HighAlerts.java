package com.spring.dataclasses;

public class HighAlerts{
		private String vin, make , model;
		private int alertCount;
		
		
		public String getVin() {
			return vin;
		}
		public void setVin(String vin) {
			this.vin = vin;
		}
		public String getMake() {
			return make;
		}
		public void setMake(String make) {
			this.make = make;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public int getAlertCount() {
			return alertCount;
		}
		public void setAlertCount(int alertCount) {
			this.alertCount = alertCount;
		}
		public HighAlerts(String vin, String make, String model, int alertCount) {
			super();
			this.vin = vin;
			this.make = make;
			this.model = model;
			this.alertCount = alertCount;
		}
		
		
			
		
		
	

}
