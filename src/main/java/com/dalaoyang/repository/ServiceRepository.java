package com.dalaoyang.repository;

import com.dalaoyang.entity.Dept;
import com.dalaoyang.entity.Service;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description：
 * @author: zhangxianglong
 * @date: 2022/2/8
 */
@Repository
public interface ServiceRepository extends Neo4jRepository<Service, Long> {

    /**
     * 通过服务名称查询 依赖当前服务的服务列表
     *
     * @param serviceName
     * @return
     */
    @Query("MATCH(p:service)-[:follow]->(:service{name:$serviceName}) RETURN p")
    List<Service> findByFollowsByName(@Param("serviceName") String serviceName);
}
