package com.xzsd.pc.client.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.client.dao.ClientDao;
import com.xzsd.pc.client.entity.Client;
import com.xzsd.pc.client.entity.ClientVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户实现类
 */
@Service
public class ClientService {

    @Resource
    private ClientDao clientDao;

    /**
     * 查询顾客列表（分页）
     * @param client
     * @return
     */
    public AppResponse getListClient(Client client){
        List<ClientVO> listClient = null;
        PageHelper.startPage(client.getPageNum(), client.getPageSize());
        //管理员查全部，店长查对应客户
        if("2".equals(client.getRole())){
            listClient = clientDao.getListClient(client);
        }else if("0".equals(client.getRole()) || "1".equals(client.getRole())){
            listClient = clientDao.getListClientByAdmin(client);
        }
        PageInfo<ClientVO> pageData = new PageInfo<>(listClient);
        return AppResponse.success("查询成功！", pageData);
    }
}