package com.mongo.example.mongodemo.controller;


import org.modelmapper.ModelMapper;
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
	
//	@Autowired
//	private ModelMapper modelMapper;

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

	@PutMapping("/trunkopen/{id}")
	public String handlingTrunkOpen(@PathVariable("id") int id) {
		 return	 carInterface.handlingTrunkOpen(id);
	}

	@PutMapping("/trunkclose/{id}")
	public String handlingTrunkClose(@PathVariable("id") int id) {
		return	 carInterface.handlingTrunkClose(id);
	}


	@PutMapping("/acon/{id}")
	public String handlingACOn(@PathVariable("id") int id) {
		return	 carInterface.handlingACOn(id);
	}

	@PutMapping("/acoff/{id}")
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
	
	@PutMapping("/airOuterOff/{id}")
	public String handlingairOuterOff(@PathVariable("id") int id) {
		return carInterface.handlingAirOuterOff(id);
	}
	
	@PutMapping("/airOuterOn/{id}")
	public String handlingairOuterOn(@PathVariable("id") int id) {
		return carInterface.handlingAirOuterOn(id);
	}
	
	@PutMapping("/airCirculationOff/{id}")
	public String handlingAirCirculationOff(@PathVariable("id") int id) {
		return carInterface.handlingAirCirculationOff(id);
	}
	
	@PutMapping("/airCirculationOn/{id}")
	public String handlingAirCirculationOn(@PathVariable("id") int id) {
		return carInterface.handlingAirCirculationOn(id);
	}
	
	@PutMapping("/defrostRearOn/{id}")
	public String defrostRearOn(@PathVariable("id") int id) {
		return carInterface.defrostRearOn(id);
	}
	
	@PutMapping("/defrostRearOff/{id}")
	public String defrostRearOff(@PathVariable("id") int id) {
		return carInterface.defrostRearOff(id);
	}
	
	@PutMapping("/defrostFrontOn/{id}")
	public String defrostFrontOn(@PathVariable("id") int id) {
		return carInterface.defrostFrontOn(id);
	}
	
	@PutMapping("/defrostFrontOff/{id}")
	public String defrostFrontOff(@PathVariable("id") int id) {
		return carInterface.defrostFrontOff(id);
	}

	@PutMapping("/flowDownOn/{id}")
	public String flowDownOn(@PathVariable("id") int id) {
		return carInterface.flowDownOn(id);
	}
	
	@PutMapping("/flowDownOff/{id}")
	public String flowDownOff(@PathVariable("id") int id) {
		return carInterface.flowDownOff(id);
	}
	
	@PutMapping("/flowUpOff/{id}")
	public String flowUpOff(@PathVariable("id") int id) {
		return carInterface.flowUpOff(id);
	}
	
	@PutMapping("/flowUpOn/{id}")
	public String flowUpOn(@PathVariable("id") int id) {
		return carInterface.flowUpOn(id);
	}
	
	@PutMapping("/flowBothOn/{id}")
	public String flowBothOn(@PathVariable("id") int id) {
		return carInterface.flowBothOn(id);
	}
	
	@PutMapping("/flowBothOff/{id}")
	public String flowBothOff(@PathVariable("id") int id) {
		return carInterface.flowBothOff(id);
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
