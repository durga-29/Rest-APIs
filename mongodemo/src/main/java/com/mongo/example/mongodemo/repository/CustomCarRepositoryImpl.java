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

import com.mongo.example.mongodemo.exception.BusinessException;
import com.mongo.example.mongodemo.exception.InvalidTemperatureException;
import com.mongo.example.mongodemo.exception.ResourceNotFoundException;
import com.mongo.example.mongodemo.models.apimodel.Car;
import com.mongodb.client.result.UpdateResult;

@Repository
public class CustomCarRepositoryImpl implements CarRepositoryInterface {

	@Autowired
    MongoTemplate mongoTemplate;
	
	@Autowired
    CarRepository carRepo;
	
	@Override
	public Car addSpeedtry(@RequestBody Car restCar) {
		try {
	if(restCar.getSpeed() >= 0 && restCar.getSpeed() <=20)
	{
		restCar.setLevel(1);
	}
	else if(restCar.getSpeed() > 20 && restCar.getSpeed() <= 40) {
		restCar.setLevel(2);
	}
	else if(restCar.getSpeed() > 40 && restCar.getSpeed() <= 60) {
		restCar.setLevel(3);
	}
	else if(restCar.getSpeed() > 60 && restCar.getSpeed() <= 80) {
		restCar.setLevel(4);
	}
	else if(restCar.getSpeed() > 80 && restCar.getSpeed() <= 100) {
		restCar.setLevel(5);
	}
	Car save = this.carRepo.insert(restCar);

	return save;
		} 
		catch (Exception e) {
			throw new BusinessException("632","invalid speed"+ e.getMessage());
		}
			}	
	
	@Override
	public Car handlingDoorLock( int id) {
		Query query = null;
		List<Car> restCar = null;
		try {
		 query=new Query(Criteria.where("_id").is(id));

		 restCar=mongoTemplate.find(query,Car.class);
		 System.out.println(""+restCar);
		}
		catch (Exception e) {
			throw new BusinessException("638",
					"Something went wrong in repository  " + e.getMessage());
			}
	    if(restCar!=null){
	        Update update=new Update().set("door","LOCK");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,Car.class);	
	        System.out.println("handlingDoorLock modified count "+result.getModifiedCount());
	    }  
	return restCar.get(0);
	}

	@Override
	public String handlingDoorUnlock( int id) {

		Query query=new Query(Criteria.where("_id").is(id));
		System.out.println(query);   // Query: { "_id" : 2}, Fields: {}, Sort: {}
	
	    List<Car> restCar=mongoTemplate.find(query,Car.class);
	    if(restCar!=null){
	        Update update=new Update().set("door","UNLOCK");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,Car.class);	
	        System.out.println("modified count = "+result.getModifiedCount());
	    }
	return  " door successfully unlocked";
	}
	
	@Override
	public Car AcTempPlus(Car employee) {
//		if(employee.getMinTemp() < 16  ) {
//			throw new InvalidTemperatureException("601","min temp <16 send proper valu");
//		}
//		else if(employee.getMaxTemp() > 30  ) {
//			throw new InvalidTemperatureException("602","max temp >30 send proper valu");
//		}
		try {
			Car save = this.carRepo.save(employee);
			return save;
		}catch (IllegalArgumentException e) {
			throw new InvalidTemperatureException("603","given temp is invalid" + e.getMessage());
		}
	}

	@Override
	public String handlingTrunkOpen(int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<Car> restCar=mongoTemplate.find(query,Car.class);

	    if(restCar!=null){
	        Update update=new Update().set("trunk","OPEN");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,Car.class);	
	        System.out.println("count incre"+result.getModifiedCount());
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}
	
	@Override
	public String handlingTrunkClose( int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<Car> restCar=mongoTemplate.find(query,Car.class);

	    if(restCar!=null){
	        Update update=new Update().set("trunk","CLOSE");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,Car.class);	
	        System.out.println("count incre"+result.getModifiedCount());
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}
	
	@Override
	public String handlingACOn(@PathVariable("id") int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<Car> restCar=mongoTemplate.find(query,Car.class);

	    if(restCar!=null){
	        Update update=new Update().set("ac","ON");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,Car.class);	
	        System.out.println("count incre"+result.getModifiedCount());
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}

	@Override
	public String handlingACOff(@PathVariable("id") int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<Car> restCar=mongoTemplate.find(query,Car.class);

	    if(restCar!=null){
	        Update update=new Update().set("ac","OFF");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,Car.class);	
	        System.out.println("count incre"+result.getModifiedCount());
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}
	
	@Override
	public String IncreSunroofSlider( @PathVariable("id") int id) {

	    Query query=new Query(Criteria.where("_id").is(id));
	    List<Car> restCar=mongoTemplate.find(query,Car.class);

	    if(restCar!=null){
//	    	System.out.println("if");
	        Update update=new Update().inc("sunroofSlider",1);
//	        Update up = new Update().set("sunroofSlider", 3);
	        UpdateResult result = mongoTemplate.updateFirst(query,update,Car.class);	
	        System.out.println("count incre"+result.getModifiedCount());
	    }else{
System.out.println("else");		   
}		    
	return  "success";
}
	
	@Override
	public String DecreSunroofSlider(int id) {
		 List<Car> restCar=null;
		try {
		    Query query=new Query(Criteria.where("_id").is(id));
		    restCar=mongoTemplate.find(query,Car.class);
		
		    if(restCar!=null){
		        Update update=new Update().inc("sunroofSlider",-1);
		       
//		        Update up = new Update().set("sunroofSlider", 3);
		        UpdateResult result = mongoTemplate.updateFirst(query,update,Car.class);	
		        System.out.println("count decre"+result.getModifiedCount());
		       System.out.println( restCar.get(0));
//		       for(Car car: restCar) {
//		    	   System.out.println("for");
//					if (car.getSunroofSlider() == -1) {
//						System.out.println(car.getSunroofSlider());
//						car.setSunroofSlider(0);
////						throw new BusinessException("634", "Please send proper details,got null");	
//			        }
//		       }
		    }
		    else {
				throw new BusinessException("634", "Please send proper details,got null");	
			}
		}
		catch (Exception e) {
			throw new BusinessException("611",
					"Something went wrong in service layer while saving new user" + e.getMessage());
			}
		
		
		
		
		return  "success";
	}
	
}
