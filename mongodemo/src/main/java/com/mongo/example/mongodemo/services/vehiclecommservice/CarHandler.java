package com.mongo.example.mongodemo.services.vehiclecommservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.mongo.example.mongodemo.exception.BusinessException;
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

	@Override
	public Car addSpeedtry(@RequestBody Car restCar) {
		if (restCar.getSpeed() < 0 || restCar.getSpeed() > 100) {
			throw new BusinessException("631", "speed out of range");
		}
		try {
			return carRepositoryinterface.addSpeedtry(restCar);
		} catch (Exception e) {
			throw new BusinessException("633",
					"Something went wrong in service layer while saving new user" + e.getMessage());
		}
	}

	@Override
	public Car handlingDoorLock(int id) {
		try {
			return (Car) carRepositoryinterface.handlingDoorLock(id);
		} catch (BusinessException e) {
			throw new BusinessException("635",
					"Something went wrong in service layer while saving new user" + e.getMessage());
		}
	}

	@Override
	public String handlingDoorUnlock(int id) {
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
		if (employee.getMinTemp() < 16) {
			throw new InvalidTemperatureException("601", "min temp <16 send proper value");
		} else if (employee.getMaxTemp() > 30) {
			throw new InvalidTemperatureException("602", "max temp >30 send proper value");
		}
		return carRepositoryinterface.AcTempPlus(employee);
	}

	@Override
	public String handlingACOn(int id) {
		return carRepositoryinterface.handlingACOn(id);
	}

	@Override
	public String handlingACOff(int id) {
		return carRepositoryinterface.handlingACOff(id);
	}

	@Override
	public String IncreSunroofSlider(int id) {
		return carRepositoryinterface.IncreSunroofSlider(id);
	}

	@Override
	public String DecreSunroofSlider(int id) {
		return carRepositoryinterface.DecreSunroofSlider(id);
	}

	@Override
	public String handlingAirOuterOff(int id) {
		return carRepositoryinterface.handlingAirOuterOff(id);
	}

	@Override
	public String handlingAirOuterOn(int id) {
		return carRepositoryinterface.handlingAirOuterOn(id);
	}

	@Override
	public String handlingAirCirculationOff(int id) {
		return carRepositoryinterface.handlingAirCirculationOff(id);
	}

	@Override
	public String handlingAirCirculationOn(int id) {
		return carRepositoryinterface.handlingAirCirculationOn(id);
	}

	@Override
	public String defrostRearOn(int id) {
		return carRepositoryinterface.defrostRearOn(id);
	}

	@Override
	public String defrostRearOff(int id) {
		return carRepositoryinterface.defrostRearOff(id);
	}

	@Override
	public String defrostFrontOn(int id) {
		return carRepositoryinterface.defrostFrontOn(id);
	}

	@Override
	public String defrostFrontOff(int id) {
		return carRepositoryinterface.defrostFrontOff(id);
	}

	@Override
	public String flowDownOn(int id) {
		return carRepositoryinterface.flowDownOn(id);
	}

	@Override
	public String flowDownOff(int id) {
		return carRepositoryinterface.flowDownOff(id);
	}

	@Override
	public String flowUpOff(int id) {
		return carRepositoryinterface.flowUpOff(id);
	}

	@Override
	public String flowUpOn(int id) {
		return carRepositoryinterface.flowUpOn(id);
	}

	@Override
	public String flowBothOn(int id) {
		return carRepositoryinterface.flowBothOn(id);
	}

	@Override
	public String flowBothOff(int id) {
		return carRepositoryinterface.flowBothOff(id);
	}

}
