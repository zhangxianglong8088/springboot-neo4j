package com.dalaoyang.web;

import com.dalaoyang.entity.Follow;
import com.dalaoyang.entity.Service;
import com.dalaoyang.repository.FollowRepository;
import com.dalaoyang.repository.ServiceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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
        Service order = Service.builder().name("order").build();

        Service profit = Service.builder().name("profit").build();
        Service sign = Service.builder().name("sign").build();

        List<Service> serviceList = Arrays.asList(accounting, calculate, book, settlement, payment);
        serviceRepository.saveAll(serviceList);

        //calculate依赖accounting
        Follow calculateDepAccounting = Follow.builder().startNode(calculate).endNode(accounting).sourceId(calculate.getName()).targetId(accounting.getName()).build();
        //book依赖accounting
        Follow bookDepAccounting = Follow.builder().startNode(book).endNode(accounting).sourceId(book.getName()).targetId(accounting.getName()).build();

        //accounting 依赖payment
        Follow accountingDepPayment = Follow.builder().startNode(accounting).endNode(payment).sourceId(accounting.getName()).targetId(payment.getName()).build();

        //performance依赖payment
        Follow followPerformance = Follow.builder().startNode(performance).endNode(payment).sourceId(performance.getName()).sourceId(payment.getName()).build();

        Follow settlementDepPayment = Follow.builder().startNode(settlement).endNode(payment).sourceId(settlement.getName()).targetId(payment.getName()).build();

        //签约依赖profit

        Follow signDepProfit = Follow.builder().startNode(sign).endNode(profit).sourceId(sign.getName()).targetId(profit.getName()).build();

        //payment依赖 order
        Follow paymentFollowOrder = Follow.builder().startNode(payment).endNode(order).sourceId(payment.getName()).targetId(order.getName()).build();


        List<Follow> relationShips = Arrays.asList(calculateDepAccounting, bookDepAccounting, accountingDepPayment, followPerformance, settlementDepPayment, signDepProfit, paymentFollowOrder);

        followRepository.saveAll(relationShips);
    }

//    /**
//     * 查询和一个服务相关的所有服务
//     *
//     * @param name
//     * @return
//     */
//    @GetMapping("query/indirectByFollowsByName")
//    public List<Service> get(String name) {
//        List<Service> list = serviceRepository.findIndirectByFollowsByName(name);
//        return list;
//    }
//

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


    /**
     * 输入一个服务名，查询和这个服务直接相关的服务
     *
     * @param name
     * @return
     */
    @GetMapping("query/findAll")
    public void findAll(String name) {
        Iterable<Service> nodes = serviceRepository.findAll();
        for (Service node : nodes) {
            List<Follow> relationNodeSet = node.getFollows();
            for (Follow relationNode : relationNodeSet) {
                System.out.println("id:" + node.getId() + " name:" + node.getName() + " 关系：" + relationNode.getStartNode() + "子节点：" + relationNode.getEndNode());
            }
        }
    }

    /**
     * @param name
     * @return
     */
    @GetMapping("query/related")
    public List<Service> related(String name) {
        return serviceRepository.reverseDirection(name);
    }

    /**
     * @param name
     * @return
     */
    @GetMapping("get")
    public Map<String, Object> get(String name) {
        Map<String, Object> retMap = new HashMap<>();
        List<Service> nodeList = serviceRepository.reverseDirection(name);
        retMap.put("nodeList", nodeList);
        return retMap;
    }
}
