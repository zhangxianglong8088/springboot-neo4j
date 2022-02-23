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
public class ServiceDependencedController {

    @Resource
    private FollowRepository followRepository;

    @Resource
    private ServiceRepository serviceRepository;

    /**
     * 初始化构建依赖关系
     */
    @GetMapping("save/services")
    public void create() {
        Service accounting = Service.builder().name("accounting").build();
        Service calculate = Service.builder().name("calculate").build();
        Service book = Service.builder().name("book").build();
        Service settlement = Service.builder().name("settlement").build();
        Service performance = Service.builder().name("performance").build();
        Service payment = Service.builder().name("payment").build();
        Service order = Service.builder().name("order").build();

        Service profit = Service.builder().name("profit").build();
        Service sign = Service.builder().name("sign").build();

        List<Service> serviceList = Arrays.asList(accounting, calculate, book, settlement, payment);
        serviceRepository.saveAll(serviceList);

        //calculate依赖accounting
        Follow calculateDepAccounting = Follow.builder().startNode(accounting).endNode(calculate).build();
        //book依赖accounting
        Follow bookDepAccounting = Follow.builder().startNode(accounting).endNode(book).build();

        //accounting 依赖payment
        Follow accountingDepPayment = Follow.builder().startNode(payment).endNode(accounting).build();

        //performance依赖payment
        Follow followPerformance = Follow.builder().startNode(payment).endNode(performance).build();

        Follow settlementDepPayment = Follow.builder().startNode(payment).endNode(settlement).build();

        //签约依赖profit

        Follow signDepProfit = Follow.builder().startNode(profit).endNode(sign).build();

        //payment依赖 order
        Follow paymentFollowOrder = Follow.builder().startNode(order).endNode(payment).build();


        List<Follow> relationShips = Arrays.asList(calculateDepAccounting, bookDepAccounting, accountingDepPayment, followPerformance, settlementDepPayment, signDepProfit, paymentFollowOrder);

        followRepository.saveAll(relationShips);
    }

    /**
     * 输入一个服务名，查询该服务的被依赖关系图
     * http://localhost:8080/query/beDependedOns?name=order
     * http://localhost:8080/query/beDependedOns?name=payment
     * http://localhost:8080/query/beDependedOns?name=accounting
     *
     * @param name
     * @return
     */
    @GetMapping("query/beDependedOns")
    public List<Service> directByFollowsByName(String name) {
        List<Service> list = serviceRepository.beDependedOns(name);
        return list;
    }
}
