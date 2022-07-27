package com.mongo.example.mongodemo.models.apimodel;

public class AC {
	private Car car;
	private String ac;
	private int minTemp = 16;
	private int maxTemp = 30;
	private String flowUp;
	private String flowBoth;
	private String flowDown;
	private String defrostFront;
	private String defrostRear;
	private String airOuter;
	private String airCirculation;
	
	public AC() {
		super();
	}

	public AC(Car car, String ac, int minTemp, int maxTemp, String flowUp, String flowBoth, String flowDown,
			String defrostFront, String defrostRear, String airOuter, String airCirculation) {
		super();
		this.car = car;
		this.ac = ac;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.flowUp = flowUp;
		this.flowBoth = flowBoth;
		this.flowDown = flowDown;
		this.defrostFront = defrostFront;
		this.defrostRear = defrostRear;
		this.airOuter = airOuter;
		this.airCirculation = airCirculation;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "AC [car=" + car + ", ac=" + ac + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", flowUp=" + flowUp
				+ ", flowBoth=" + flowBoth + ", flowDown=" + flowDown + ", defrostFront=" + defrostFront
				+ ", defrostRear=" + defrostRear + ", airOuter=" + airOuter + ", airCirculation=" + airCirculation
				+ "]";
	}
}
