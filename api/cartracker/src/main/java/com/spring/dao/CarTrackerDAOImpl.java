package com.spring.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.apache.tomcat.util.buf.UEncoder;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dataclasses.HighAlerts;
import com.spring.dataclasses.LatitudeLongitude;
import com.spring.entity.Reading;
import com.spring.entity.Readings;
import com.spring.entity.Vehicle;

public class CarTrackerDAOImpl implements CarTrackerDAO{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionfactory;
	
	@Transactional
	public Vehicle createVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(vehicle);
		return vehicle;
	}

	@Transactional
	public Readings createReadings(final Reading reading){
		// TODO Auto-generated method stub
		//save the reading to database . if same vin exists update the entry.
		/*hibernateTemplate.execute(new HibernateCallback<String>() {

			public String doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery("From Readings_Master WHERE vin='"+ reading.getVin()+"'"); 
				List<Readings> reading_list = query.list();
				if(reading_list.size()>0){
					System.out.println("entry phle se h gadhe me hi update krta hu");
					hibernateTemplate.update(reading);
				}else{
					hibernateTemplate.saveOrUpdate(reading);
					
				}
				return null;
			}
		});*/
		Readings reading_to_save = new Readings(reading.getVin(), reading.getLatitude(), reading.getLongitude(), (reading.getTimestamp().replace('Z', '0').replace('T', ' ').split("\\."))[0], reading.getFuelVolume(), reading.getSpeed(), reading.getEngineHp(),reading.isCheckEngineLightsOn(), reading.isEngineCoolantLow(), reading.isCruiseControlOn(), reading.getEngineRpm(), reading.getTires().getFrontLeft(), reading.getTires().getFrontRight(), reading.getTires().getRearLeft(), reading.getTires().getRearRight());
		reading_to_save.setAlert_priority(createAlert(reading_to_save));
		hibernateTemplate.saveOrUpdate(reading_to_save);
		return reading_to_save;
	

	}
	
	@Transactional
	public String createAlert(final Readings reading_to_save){
		String priorit_result = "";
		priorit_result = hibernateTemplate.execute(new HibernateCallback<String>() {

			public String doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery("FROM vehicle_master WHERE VIN = '"+reading_to_save.getVin()+"'");
				List<Vehicle> vehicles_with_vin = query.list();
				if(vehicles_with_vin.size() > 0){
				if(reading_to_save.getEngineRpm() > vehicles_with_vin.get(0).getRedlineRpm()){
					return "HIGH";
				}else if(reading_to_save.getFuelVolume() < (vehicles_with_vin.get(0).getMaxFuelVolume()*0.1f)){
					return "MEDIUM";
				}else if(reading_to_save.getTirefrontleft() < 32 || reading_to_save.getTirefrontleft()>36){
					return "LOW";
				}else if(reading_to_save.getTirefrontright() < 32 || reading_to_save.getTirefrontright()>36){
					return "LOW";
				}else if(reading_to_save.getTirerearleft() < 32 || reading_to_save.getTirerearleft()>36){
					return "LOW";
				}else if(reading_to_save.getTirerearright() < 32 || reading_to_save.getTirerearright()>36){
					return "LOW";
				}else if(reading_to_save.isEngineCoolantLow() || reading_to_save.isCheckEngineLightOn()){
					return "LOW";
				}
				}
				return "";
			}
		});
		return priorit_result;
	}

	
	@Transactional
	public List<Vehicle> returnVehicles() {
		// TODO Auto-generated method stub
		List<Vehicle> result = hibernateTemplate.execute(new HibernateCallback<List<Vehicle>>() {

			public List<Vehicle> doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery("From vehicle_master");
				return query.list();
			}
		});
		return result;
	}

	@Transactional
	public List<Readings> returnReadingsOfVehicle(final String vin) {
		// TODO Auto-generated method stub
		List<Readings> result = hibernateTemplate.execute(new HibernateCallback<List<Readings>>() {

			public List<Readings> doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery("FROM Readings_Master WHERE vin = '"+vin+"'");
				return query.list();
			}
		});
		return result;
	}

	@Transactional
	public List<Readings> returnReadingsOfVehicle(final String vin,final int hours , final int minutes) {
		// TODO Auto-generated method stub
		List<Readings> result = hibernateTemplate.execute(new HibernateCallback<List<Readings>>() {

			public List<Readings> doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				SQLQuery query = arg0.createSQLQuery("select * from readings_master , dual "
						+ "where vin = '"+vin+"' AND "
						+ "(EXTRACT(hour from (CURRENT_TIMESTAMP AT TIME ZONE '+00:00') - (to_timestamp(TIMESTAMP , 'yyyy-mm-dd hh24:mi:ss') AT TIME ZONE '+00:00')) * 60)+(EXTRACT(minute from (CURRENT_TIMESTAMP AT TIME ZONE '+00:00') - (to_timestamp(TIMESTAMP , 'yyyy-mm-dd hh24:mi:ss') AT TIME ZONE '+00:00'))) <= "+minutes);
				query.addEntity(Readings.class);
				return query.list();
			}
		});
		List query_readings = result;
		List<Readings> result_readings =new ArrayList<Readings>();
		for(Iterator iterator = query_readings.iterator() ; iterator.hasNext();){
			Readings new_reading = (Readings)iterator.next();
			result_readings.add(new_reading);
		}
		return result_readings;
	}

	@Transactional
	public List<HighAlerts> returnReadingsofHighAlerts() {
		// TODO Auto-generated method stub
		List<Object[]> result = hibernateTemplate.execute(new HibernateCallback<List<Object[]>>() {

			public List<Object[]> doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				SQLQuery query = arg0.createSQLQuery("select vin , make , model , count(ALERT_PRIORITY) from Readings_master NATURAL JOIN VEHICLE_MASTER "
						+ "where alert_priority = 'HIGH' "
						+ "AND "
						+ "EXTRACT(hour from (Current_timestamp at time zone '+00:00') - (to_timestamp(timestamp , 'yyyy-mm-dd hh24:mi:ss') at time zone '+00:00')) < 2  "
						+ "group by vin , make , model");
				return query.list();
			}
		});
		List<HighAlerts> highAlertsresult = new ArrayList<HighAlerts>();
		for (Object[] result_data : result) {
			highAlertsresult.add(new HighAlerts((String)result_data[0], (String)result_data[1],(String)result_data[2],Integer.parseInt(result_data[3].toString())));
		}
		
		return highAlertsresult;
	}

	@Transactional
	public Vehicle returnVehicle(final String vin) {
		// TODO Auto-generated method stub
		Vehicle result = hibernateTemplate.execute(new HibernateCallback<Vehicle>() {

			public Vehicle doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery("FROM vehicle_master where vin = '"+vin+"'");
				return (Vehicle) query.list().get(0);
			}
		});
		return result;
	}

	
	@Transactional
	public List<LatitudeLongitude> returnLatLng(final String vin) {
		// TODO Auto-generated method stub
		List<Object[]> query_result = hibernateTemplate.execute(new HibernateCallback<List<Object[]>>() {

			public List<Object[]> doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				SQLQuery query  = arg0.createSQLQuery("select vin , latitude, longitude , timestamp from readings_master , dual where  "
						+ "vin = '"+vin+"' "
						+ "AND"
						+ " EXTRACT(hour from (Current_timestamp at time zone '+00:00') - (to_timestamp(timestamp , 'yyyy-mm-dd hh24:mi:ss') at time zone '+00:00')) = 0"
						+ " AND "
						+ "EXTRACT(minute from (Current_timestamp at time zone '+00:00') - (to_timestamp(timestamp , 'yyyy-mm-dd hh24:mi:ss') at time zone '+00:00')) <= 30	"
						);
				
				return query.list();
			}
		});
		List<LatitudeLongitude> result = new ArrayList<LatitudeLongitude>();
		for (Object[] objects : query_result) {
			LatitudeLongitude ll = new LatitudeLongitude(objects[0].toString(), Float.parseFloat(objects[1].toString()), Float.parseFloat(objects[2].toString()), objects[3].toString());
			result.add(ll);
		}
		return result;
	}

	

}
