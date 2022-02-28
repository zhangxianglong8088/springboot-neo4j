package com.dalaoyang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * @description：
 * @author: zhangxianglong
 * @date: 2022/2/8
 */
@RelationshipEntity(type = "follow")
@Data
@Builder
public class Follow {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @StartNode
    private BaseNode startNode;

    @Property(name="sourceId")
    private String sourceId;

    @JsonIgnore
    @EndNode
    private BaseNode endNode;

    @Property(name="targetId")
    private String targetId;
}
