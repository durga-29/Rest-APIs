package com.mongo.example.mongodemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.mongo.example.mongodemo.models.apimodel.*;

@Repository
public interface CarRepository extends MongoRepository<Car, Integer> {
//	@Query("{ speed: {$eq: ?0} }")
//	public List<Car> incSlider(int speed);
//	db.cars.update({ _id:1 },{ $inc: { SunroofSlider: 1 } }, {upsert:true})
}
