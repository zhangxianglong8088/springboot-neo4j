package com.dalaoyang.web;

import com.dalaoyang.entity.Follow;
import com.dalaoyang.entity.Service;
import com.dalaoyang.repository.FollowRepository;
import com.dalaoyang.repository.ServiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangxianglong
 * @date 2020-11-15
 */
@Controller
public class TestNeo4jController {

    @Resource
    private FollowRepository followRepository;

    @Resource
    private ServiceRepository serviceRepository;



    /**
     * @return
     */
    @RequestMapping("neo4j")
    public String neo4j() {
        return "/neo4j";
    }
}
