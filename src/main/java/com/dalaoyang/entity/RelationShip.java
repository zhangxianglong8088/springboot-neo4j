package com.dalaoyang.entity;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "relationShip")
@Data
@Builder
public class RelationShip {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Dept parent;

    @EndNode
    private Dept child;
}
