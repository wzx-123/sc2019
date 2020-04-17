package com.xzsd.pc.client.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.client.entity.Client;
import com.xzsd.pc.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Resource
    private ClientService clientService;


    /**
     * 查询客户列表（分页）
     *
     */
    @PostMapping("listClients")
    public AppResponse getListClient(Client client){
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            client.setUserId(userId);
            return clientService.getListClient(client);
        }catch (Exception e){
            logger.error("查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}