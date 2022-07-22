package com.mongo.example.mongodemo.repository;




import java.util.List;

import com.mongo.example.mongodemo.models.apimodel.Car;

public interface CarRepositoryInterface {
	
	public Car handlingDoorLock( int id);
	
	public String handlingDoorUnlock( int id);
	
	public Car AcTempPlus(Car employee);
	
	public Car addSpeedtry( Car restCar);
	
	public String handlingTrunkOpen(int id);
	
	public String handlingTrunkClose( int id);
	
	public String handlingACOn( int id) ;
	
	public String handlingACOff( int id) ;
	
	public String IncreSunroofSlider(int id);
	
	public String DecreSunroofSlider( int id);
}
