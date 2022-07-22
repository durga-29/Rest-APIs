package com.mongo.example.mongodemo.services.vehiclecommservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mongo.example.mongodemo.exception.BusinessException;
import com.mongo.example.mongodemo.exception.ControllerException;
import com.mongo.example.mongodemo.exception.InvalidTemperatureException;
import com.mongo.example.mongodemo.models.apimodel.*;
import com.mongo.example.mongodemo.repository.CarRepository;
import com.mongo.example.mongodemo.repository.CarRepositoryInterface;


@Service
public class CarHandler implements CarInterface {
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	@Autowired
    CarRepository carRepo;

	@Autowired
	CarRepositoryInterface carRepositoryinterface;
	
//	private Car restCar;
	
	@Override
	public Car addSpeedtry(@RequestBody Car restCar) {
//		if (restCar.getSpeed() ) {
//			throw new BusinessException("601", "Please send proper name");
//		}
		if(restCar.getSpeed()<0 || restCar.getSpeed() > 100) {
			throw new BusinessException("631","speed out of range");		}	

		try {
		return carRepositoryinterface.addSpeedtry(restCar);
		}
//		catch (IllegalArgumentException e) {
//			throw new BusinessException("632", "Given user object is blank" + e.getMessage());
//		} 
//		catch (BusinessException e) {
//			throw new BusinessException("631","invalid speed, service "+ e.getMessage());
////			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
////			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
//		}
		catch (Exception e) {
			throw new BusinessException("633",
					"Something went wrong in service layer while saving new user" + e.getMessage());
		}

	}	
	
	@Override
	public Car handlingDoorLock( int id) {
		try {
		 return (Car) carRepositoryinterface.handlingDoorLock(id);
		}
		catch (BusinessException e) {
			throw new BusinessException("635",
					"Something went wrong in service layer while saving new user" + e.getMessage());
		}
		
	}
	 
	@Override
	public String handlingDoorUnlock( int id) {
		 return carRepositoryinterface.handlingDoorUnlock(id);
	}

	@Override
	public String handlingTrunkOpen(int id) {
		return carRepositoryinterface.handlingTrunkOpen(id);
	}
	
	@Override
	public String handlingTrunkClose(int id) {
		return carRepositoryinterface.handlingTrunkClose(id);
		
	}
	
	@Override
	public Car AcTempPlus(Car employee) {
		if(employee.getMinTemp() < 16  ) {
			throw new InvalidTemperatureException("601","min temp <16 send proper value");
		}
		else if(employee.getMaxTemp() > 30  ) {
			throw new InvalidTemperatureException("602","max temp >30 send proper value");
		}
		return carRepositoryinterface.AcTempPlus(employee);
	}

	@Override
	public String handlingACOn(int id) {
		return carRepositoryinterface.handlingACOn(id);
	}
	
	public String handlingACOff(int id) {
		return carRepositoryinterface.handlingACOff(id);
	}
	
	public String IncreSunroofSlider(int id) {
		return carRepositoryinterface.IncreSunroofSlider(id);
	}
	
	public String DecreSunroofSlider( int id) {
		return carRepositoryinterface.DecreSunroofSlider(id);
	}
}
