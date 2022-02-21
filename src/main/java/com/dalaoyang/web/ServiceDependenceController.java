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
 * @author zhangxianglong
 * @date 2020-11-15
 */
@RestController
public class ServiceDependenceController {

    @Resource
    private FollowRepository followRepository;

    @Resource
    private ServiceRepository serviceRepository;

    /**
     * 初始化构建依赖关系
     */
    @GetMapping("builder/services")
    public void create() {
        Service accounting = Service.builder().name("accounting").build();
        Service calculate = Service.builder().name("calculate").build();
        Service book = Service.builder().name("book").build();
        Service settlement = Service.builder().name("settlement").build();
        Service performance = Service.builder().name("performance").build();
        Service payment = Service.builder().name("payment").build();

        Service profit = Service.builder().name("profit").build();
        Service sign = Service.builder().name("sign").build();

        List<Service> serviceList = Arrays.asList(accounting, calculate, book, settlement, payment);
        serviceRepository.saveAll(serviceList);

        //calculate依赖accounting
        Follow calculateDepAccounting = Follow.builder().startNode(calculate).endNode(accounting).build();
        //book依赖accounting
        Follow bookDepAccounting = Follow.builder().startNode(book).endNode(accounting).build();

        //accounting 依赖payment
        Follow accountingDepPayment = Follow.builder().startNode(accounting).endNode(payment).build();

        //performance依赖payment
        Follow followPerformance = Follow.builder().startNode(performance).endNode(payment).build();

        Follow settlementDepPayment = Follow.builder().startNode(settlement).endNode(payment).build();

        //签约依赖profit

        Follow signDepProfit = Follow.builder().startNode(sign).endNode(profit).build();

        List<Follow> relationShips = Arrays.asList(calculateDepAccounting, bookDepAccounting, accountingDepPayment, followPerformance, settlementDepPayment, signDepProfit);

        followRepository.saveAll(relationShips);
    }

    /**
     * 查询和一个服务相关的所有服务
     *
     * @param name
     * @return
     */
    @GetMapping("query/indirectByFollowsByName")
    public List<Service> get(String name) {
        List<Service> list = serviceRepository.findIndirectByFollowsByName(name);
        return list;
    }

    /**
     * 输入一个服务名，查询和这个服务直接相关的服务
     *
     * @param name
     * @return
     */
    @GetMapping("query/directByFollowsByName")
    public List<Service> directByFollowsByName(String name) {
        List<Service> list = serviceRepository.findDirectByFollowsByName(name);
        return list;
    }
}
