package com.mongo.example.mongodemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.example.mongodemo.exception.BusinessException;
import com.mongo.example.mongodemo.exception.ControllerException;
import com.mongo.example.mongodemo.exception.InvalidTemperatureException;
import com.mongo.example.mongodemo.exception.ResourceNotFoundException;
import com.mongo.example.mongodemo.models.apimodel.Car;
import com.mongo.example.mongodemo.repository.*;
import com.mongo.example.mongodemo.services.vehiclecommservice.CarInterface;
import com.mongodb.client.MongoClient;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
		
	@Autowired
	private CarInterface carInterface;

	MongoClient mongoClient;

	@GetMapping("/speed")
	public ResponseEntity<?> getSpeed() {
		try {
		return ResponseEntity.ok(this.carRepo.findAll());
		}
		catch (Exception e) {
			throw new ResourceNotFoundException("","not found");
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addSpeedtry(@RequestBody Car restCar) {
		try {
			Car employeeSaved = carInterface.addSpeedtry(restCar);
			return new ResponseEntity<Car>(employeeSaved, HttpStatus.CREATED);
		}
//		catch (InvalidTemperatureException e) {
//			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
//			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
//		}
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			ControllerException ce = new ControllerException("611","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/doorunlock/{id}")
	public String handlingDoorUnlock( @PathVariable("id") int id) {
	return	 carInterface.handlingDoorUnlock(id);
	
	}

	@RequestMapping("/doorlock/{id}")
	public ResponseEntity<?> handlingDoorLock(@PathVariable("id") int id) {
		try {
	    Car saved=	carInterface.handlingDoorLock(id);
		return new ResponseEntity<Car>(saved, HttpStatus.OK);

		}
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			ControllerException ce = new ControllerException("636","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping("/trunkopen/{id}")
	public String handlingTrunkOpen(@PathVariable("id") int id) {
		 return	 carInterface.handlingTrunkOpen(id);
	}

	@RequestMapping("/trunkclose/{id}")
	public String handlingTrunkClose(@PathVariable("id") int id) {
		return	 carInterface.handlingTrunkClose(id);
	}


	@RequestMapping("/acon/{id}")
	public String handlingACOn(@PathVariable("id") int id) {
		return	 carInterface.handlingACOn(id);
	}

	@RequestMapping("/acoff/{id}")
	public String handlingACOff(@PathVariable("id") int id) {
		return	 carInterface.handlingACOff(id);
	}

	@PutMapping("/actempchange")
	public ResponseEntity<?> AcTempPlus(@RequestBody Car restCar) {
		try {
			Car employeeSaved = carInterface.AcTempPlus(restCar);
			return new ResponseEntity<Car>(employeeSaved, HttpStatus.CREATED);
		}catch (InvalidTemperatureException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("611","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/decrsunroofslider/{id}")
	public String DecreSunroofSlider( @PathVariable("id") int id) {
		return carInterface.DecreSunroofSlider(id);
	}

	@PutMapping("/incrsunroofslider/{id}")
	public String IncreSunroofSlider( @PathVariable("id") int id) {
			return carInterface.IncreSunroofSlider(id);
	}

}

//@PutMapping("/doorlock/{id}")
//public String handlingDoorUnlock( @PathVariable("id") int id) {
//	Query query=new Query(Criteria.where("_id").is(id));
//
//	List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);
//
//    if(restCar!=null){
//        Update update=new Update().set("door","LOCK");
//        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
//        System.out.println("count incre"+result.getModifiedCount());
//    }else{
//        throw new ResourceNotFoundException(" not found");		   
//}		    
//return  "success";
//}

//@RequestMapping("/doorunlock/{id}")
//public String handlingDoorOff(@PathVariable("id") int id) {
//	Query query=new Query(Criteria.where("_id").is(id));
//    List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);
//
//    if(restCar!=null){
//    	System.out.println("if");
//        Update update=new Update().set("door","UNLOCK");
//        Update up = new Update().set("sunroofSlider", 3);
//        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
//        System.out.println("count incre"+result.getModifiedCount());
//    }else{
//System.out.println("else");		   
//}		    
//return  "success";
//}
