package com.xzsd.pc.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.order.dao.OrderDao;
import com.xzsd.pc.order.entity.OrderDetails;
import com.xzsd.pc.order.entity.OrderDetailsList;
import com.xzsd.pc.order.entity.Order;
import com.xzsd.pc.order.entity.OrderVO;
import com.xzsd.pc.user.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description OrderDao订单实现类
 * @author wzx
 * @date 2020-3-30
 */
@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private UserDao userDao;

    /**
     * 查询订单列表（分页）
     * @param order
     * @return
     */
    public AppResponse getListOrder(Order order){
        List<OrderVO> listOrder = null;
        PageHelper.startPage(order.getPageNum(), order.getPageSize());
        //根据角色查询订单列表
        if("2".equals(order.getRole())){
            listOrder = orderDao.getListOrder(order);
        }else if("0".equals(order.getRole()) || "1".equals(order.getRole())){
            listOrder = orderDao.getListOrderByAdmin(order);
        }
        PageInfo<OrderVO> pageData = new PageInfo<>(listOrder);
        return AppResponse.success("查询订单列表成功！", pageData);
    }


    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    public AppResponse getOrderDetailsById(String orderId){
        List<OrderDetails> orderDetails = orderDao.getOrderDetailsById(orderId);
        if(orderDetails.size() == 0){
            return AppResponse.versionError("查询订单详情失败！");
        }
        //封装成接口文档需要的名称
        OrderDetailsList orderDetailsList = new OrderDetailsList();
        orderDetailsList.setOrderDeepenList(orderDetails);
        return AppResponse.success("查询订单详情成功！", orderDetailsList);
    }

    /**
     * 修改订单状态
     * @param order
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderStatus(Order order){
        String role = userDao.getUserRole(order.getLoginUserId());
        //判断只有店长才能修改订单
        if("2".equals(role) == false){
            return AppResponse.versionError("只有店长才可以修改订单！");
        }
        List<String> listOrderId = Arrays.asList(order.getOrderId().split(","));
        List<String> listVersion = Arrays.asList(order.getVersion().split(","));
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < listOrderId.size() && i < listVersion.size(); i++) {
            Order orderInfo = new Order();
            orderInfo.setOrderId(listOrderId.get(i));
            orderInfo.setVersion(listVersion.get(i));
            orderInfo.setOrderStateId(order.getOrderStateId());
            orderInfo.setUpdateUser(order.getUpdateUser());
            orderList.add(orderInfo);
        }
        int count = orderDao.updateOrderStatus(orderList);
        if(count == 0){
            return AppResponse.versionError("更新订单状态失败");
        }
        return AppResponse.success("更新订单状态成功");
    }
}