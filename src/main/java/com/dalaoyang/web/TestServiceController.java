package com.dalaoyang.web;

import com.dalaoyang.entity.Follow;
import com.dalaoyang.entity.Service;
import com.dalaoyang.repository.FollowRepository;
import com.dalaoyang.repository.ServiceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author yyyangyang@didichuxing.com
 * @date 2020-11-15
 */
@RestController
public class TestServiceController {

    @Resource
    private FollowRepository followRepository;

    @Resource
    private ServiceRepository serviceRepository;

    /**
     * CEO
     * -设计部
     * - 设计1组
     * - 设计2组
     * -技术部
     * - 前端技术部
     * - 后端技术部
     * - 测试技术部
     */
    @GetMapping("builder")
    public void create() {
        Service accounting = Service.builder().name("accounting").build();
        Service calculate = Service.builder().name("calculate").build();
        Service book = Service.builder().name("book").build();
        Service settlement = Service.builder().name("settlement").build();
        Service performance = Service.builder().name("performance").build();

        List<Service> serviceList = Arrays.asList(accounting, calculate, book, settlement);

        serviceRepository.saveAll(serviceList);

        //calculate依赖accounting
        Follow followCalculate = Follow.builder().startNode(calculate).endNode(accounting).build();
        //book依赖accounting
        Follow followBook = Follow.builder().startNode(book).endNode(accounting).build();
        //settlement依赖book
        Follow followSettlement = Follow.builder().startNode(settlement).endNode(book).build();
        //performance依赖book
        Follow followPerformance = Follow.builder().startNode(performance).endNode(book).build();

        List<Follow> relationShips = Arrays.asList(followCalculate, followBook, followSettlement, followPerformance);

        followRepository.saveAll(relationShips);
    }

    @GetMapping("query")
    public List<Service> get(String name) {
        List<Service> list = serviceRepository.findByFollowsByName(name);
        return list;
    }
}
