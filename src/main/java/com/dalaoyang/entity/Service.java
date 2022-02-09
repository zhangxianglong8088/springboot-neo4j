package com.dalaoyang.entity;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * @description：
 * @author: zhangxianglong
 * @date: 2022/2/8
 */
@NodeEntity(label = "service")
@Data
@Builder
public class Service extends BaseNode {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "flagId")
    private String flagId;

    @Property(name = "name")
    private String name;

    @Relationship(type = "follow")
    private List<Follow> follows;

}
