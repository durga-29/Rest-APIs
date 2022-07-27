package com.mongo.example.mongodemo.repository;

import com.mongo.example.mongodemo.models.apimodel.Car;

public interface CarRepositoryInterface {

	public Car handlingDoorLock(int id);

	public String handlingDoorUnlock(int id);

	public Car AcTempPlus(Car employee);

	public Car addSpeedtry(Car restCar);

	public String handlingTrunkOpen(int id);

	public String handlingTrunkClose(int id);

	public String handlingACOn(int id);

	public String handlingACOff(int id);

	public String IncreSunroofSlider(int id);

	public String DecreSunroofSlider(int id);

	public String handlingAirOuterOff(int id);

	public String handlingAirOuterOn(int id);

	public String handlingAirCirculationOff(int id);

	public String handlingAirCirculationOn(int id);

	public String defrostRearOn(int id);

	public String defrostRearOff(int id);

	public String defrostFrontOn(int id);

	public String defrostFrontOff(int id);

	public String flowDownOn(int id);

	public String flowDownOff(int id);

	public String flowUpOff(int id);

	public String flowUpOn(int id);

	public String flowBothOn(int id);

	public String flowBothOff(int id);

}
