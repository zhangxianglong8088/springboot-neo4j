package com.dalaoyang.repository;

import com.dalaoyang.entity.Follow;
import com.dalaoyang.entity.Service;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @description：
 * @author: zhangxianglong
 * @date: 2022/2/8
 */
@Repository
public interface FollowRepository extends Neo4jRepository<Follow,Long> {
}
