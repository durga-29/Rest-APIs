package com.mongo.example.mongodemo.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mongo.example.mongodemo.MongodemoApplication;
import com.mongo.example.mongodemo.exception.BusinessException;
import com.mongo.example.mongodemo.exception.InvalidTemperatureException;
import com.mongo.example.mongodemo.models.apimodel.Car;
import com.mongodb.client.result.UpdateResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class CustomCarRepositoryImpl implements CarRepositoryInterface {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	CarRepository carRepo;

	private static final Logger logger = LoggerFactory.getLogger(MongodemoApplication.class);

	@Override
	public Car addSpeedtry(@RequestBody Car restCar) {
		try {
			if (restCar.getSpeed() >= 0 && restCar.getSpeed() <= 20) {
				restCar.setLevel(1);
			} else if (restCar.getSpeed() > 20 && restCar.getSpeed() <= 40) {
				restCar.setLevel(2);
			} else if (restCar.getSpeed() > 40 && restCar.getSpeed() <= 60) {
				restCar.setLevel(3);
			} else if (restCar.getSpeed() > 60 && restCar.getSpeed() <= 80) {
				restCar.setLevel(4);
			} else if (restCar.getSpeed() > 80 && restCar.getSpeed() <= 100) {
				restCar.setLevel(5);
			}
			Car save = this.carRepo.insert(restCar);

			return save;
		} catch (Exception e) {
			throw new BusinessException("632", "invalid speed" + e.getMessage());
		}
	}

	@Override
	public Car handlingDoorLock(int id) {
		Query query = null;
		List<Car> restCar = null;
		try {
			query = new Query(Criteria.where("_id").is(id));
			restCar = mongoTemplate.find(query, Car.class);
		} catch (Exception e) {
			throw new BusinessException("638", "Something went wrong in repository  " + e.getMessage());
		}
		if (restCar == null) {
			logger.error("null ", restCar);
		} else {
			Update update = new Update().set("door", "LOCK");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			logger.info("modified count = " + result.getModifiedCount());
		}
		return restCar.get(0);
	}

	@Override
	public String handlingDoorUnlock(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		
		List<Car> restCar = mongoTemplate.find(query, Car.class);
		if (restCar == null) {
			logger.error("null ", restCar);
			}
		else {
			Update update = new Update().set("door", "UNLOCK");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			logger.info("modified count = " + result.getModifiedCount());
		}
		return " door successfully unlocked";
	}

	@Override
	public Car AcTempPlus(Car employee) {
		try {
			Car save = this.carRepo.save(employee);
			return save;
		} catch (IllegalArgumentException e) {
			throw new InvalidTemperatureException("603", "given temp is invalid" + e.getMessage());
		}
	}

	@Override
	public String handlingTrunkOpen(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("trunk", "OPEN");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			logger.info("modified count = " + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";
	}

	@Override
	public String handlingTrunkClose(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			logger.error("null ", restCar);
		} else {
			Update update = new Update().set("trunk", "CLOSE");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			logger.info("modified count = " + result.getModifiedCount());
		}
		return "success";
	}

	@Override
	public String handlingACOn(@PathVariable("id") int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar == null) {
			logger.error("null ", restCar);
		} else {
			Update update = new Update().set("ac", "ON");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			logger.info("modified count = " + result.getModifiedCount());
		}
		return "success";
	}

	@Override
	public String handlingACOff(@PathVariable("id") int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar == null) {
			logger.error("null ", restCar);
		} else {
			Update update = new Update().set("ac", "OFF");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			logger.info("modified count = " + result.getModifiedCount());
		}
		return "success";
	}

	@Override
	public String IncreSunroofSlider(@PathVariable("id") int id) {

		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			logger.error("null ", restCar);
		} else {
			Update update = new Update().inc("sunroofSlider", 1);
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			logger.info("modified count = " + result.getModifiedCount());
		}
		return "success";
	}

	@Override
	public String DecreSunroofSlider(int id) {
		List<Car> restCar = null;
		try {
			Query query = new Query(Criteria.where("_id").is(id));
			restCar = mongoTemplate.find(query, Car.class);

			if (restCar == null) {
				throw new BusinessException("634", "Please send proper details,got null");

//		       for(Car car: restCar) {
//		    	   System.out.println("for");
//					if (car.getSunroofSlider() == -1) {
//						System.out.println(car.getSunroofSlider());
//						car.setSunroofSlider(0);
////						throw new BusinessException("634", "Please send proper details,got null");	
//			        }
//		       }
			} else {
				Update update = new Update().inc("sunroofSlider", -1);
				UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
				logger.info("modified count = " + result.getModifiedCount());
			}
		} catch (Exception e) {
			throw new BusinessException("611",
					"Something went wrong in service layer while saving new user" + e.getMessage());
		}
		return "success";
	}

	@Override
	public String handlingAirOuterOff(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("airOuter", "OFF");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String handlingAirOuterOn(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("airOuter", "ON");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";
	}

	@Override
	public String handlingAirCirculationOff(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("airCirculation", "OFF");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String handlingAirCirculationOn(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("airCirculation", "ON");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String defrostRearOn(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("defrostRear", "ON");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String defrostRearOff(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("defrostRear", "OFF");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String defrostFrontOn(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("defrostFront", "ON");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String defrostFrontOff(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("defrostFront", "OFF");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String flowDownOn(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("flowDown", "ON");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";
	}

	@Override
	public String flowDownOff(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("flowDown", "OFF");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String flowUpOff(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("flowUp", "OFF");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String flowUpOn(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("flowUp", "ON");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";

	}

	@Override
	public String flowBothOn(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar != null) {
			Update update = new Update().set("flowBoth", "ON");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			System.out.println("count incre" + result.getModifiedCount());
		} else {
			System.out.println("else");
		}
		return "success";
	}

	@Override
	public String flowBothOff(int id) {
		Query query = new Query(Criteria.where("_id").is(id));
		List<Car> restCar = mongoTemplate.find(query, Car.class);

		if (restCar == null) {
			logger.error("null ", restCar);
		} else {
			Update update = new Update().set("flowBoth", "OFF");
			UpdateResult result = mongoTemplate.updateFirst(query, update, Car.class);
			logger.info("modified count = " + result.getModifiedCount());
		}
		return "success";
	}

}
