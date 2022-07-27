package com.mongo.example.mongodemo.models.apimodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="cars")
public class Car {
	@Id
	private int id;
	private int level;
	private int speed;
	private String door;
	private String trunk;
	private String ac;
	private int minTemp = 16;
	private int maxTemp = 30;
//	@Size(min = 0, max = 5)
	private int sunroofSlider;
	private String flowUp;
	private String flowBoth;
	private String flowDown;
	private String defrostFront;
	private String defrostRear;
	private String airOuter;
	private String airCirculation;

	public Car(int id, int level, int speed, String door, String trunk, String ac, int minTemp, int maxTemp,
			int sunroofSlider, String flowUp, String flowBoth, String flowDown, String defrostFront, String defrostRear,
			String airOuter, String airCirculation) {
		super();
		this.id = id;
		this.level = level;
		this.speed = speed;
		this.door = door;
		this.trunk = trunk;
		this.ac = ac;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.sunroofSlider = sunroofSlider;
		this.flowUp = flowUp;
		this.flowBoth = flowBoth;
		this.flowDown = flowDown;
		this.defrostFront = defrostFront;
		this.defrostRear = defrostRear;
		this.airOuter = airOuter;
		this.airCirculation = airCirculation;
	}

	public Car() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public int getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(int minTemp) {
		this.minTemp = minTemp;
	}

	public int getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}

	public int getSunroofSlider() {
		return sunroofSlider;
	}

	public void setSunroofSlider(int sunroofSlider) {
		this.sunroofSlider = sunroofSlider;
	}
	
	public String getTrunk() {
		return trunk;
	}

	public void setTrunk(String trunk) {
		this.trunk = trunk;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}
	
	public String getFlowUp() {
		return flowUp;
	}

	public void setFlowUp(String flowUp) {
		this.flowUp = flowUp;
	}

	public String getFlowBoth() {
		return flowBoth;
	}

	public void setFlowBoth(String flowBoth) {
		this.flowBoth = flowBoth;
	}

	public String getFlowDown() {
		return flowDown;
	}

	public void setFlowDown(String flowDown) {
		this.flowDown = flowDown;
	}

	public String getDefrostFront() {
		return defrostFront;
	}

	public void setDefrostFront(String defrostFront) {
		this.defrostFront = defrostFront;
	}

	public String getDefrostRear() {
		return defrostRear;
	}

	public void setDefrostRear(String defrostRear) {
		this.defrostRear = defrostRear;
	}

	public String getAirOuter() {
		return airOuter;
	}

	public void setAirOuter(String airOuter) {
		this.airOuter = airOuter;
	}

	public String getAirCirculation() {
		return airCirculation;
	}

	public void setAirCirculation(String airCirculation) {
		this.airCirculation = airCirculation;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", level=" + level + ", speed=" + speed + ", door=" + door + ", trunk=" + trunk
				+ ", ac=" + ac + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", sunroofSlider=" + sunroofSlider
				+ ", flowUp=" + flowUp + ", flowBoth=" + flowBoth + ", flowDown=" + flowDown + ", defrostFront="
				+ defrostFront + ", defrostRear=" + defrostRear + ", airOuter=" + airOuter + ", airCirculation="
				+ airCirculation + "]";
	}
}
