package com.mongo.example.mongodemo.services.vehiclecommservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.mongo.example.mongodemo.models.apimodel.Car;

public interface CarInterface {
	public String handlingDoorUnlock( int id);

	public Car handlingDoorLock( int id) ;

	public	Car AcTempPlus(Car employee);
	
	public Car addSpeedtry(Car employee);
	
	public String handlingTrunkOpen(int id);
	
	public String handlingTrunkClose( int id);
	
	public String handlingACOn( int id) ;
	
	public String handlingACOff(int id) ;
	
	public String IncreSunroofSlider(int id);
	
	public String DecreSunroofSlider( int id);

}
