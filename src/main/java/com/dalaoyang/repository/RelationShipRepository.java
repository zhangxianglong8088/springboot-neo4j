package com.dalaoyang.repository;

import com.dalaoyang.entity.RelationShip;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationShipRepository extends Neo4jRepository<RelationShip, Long> {

}
