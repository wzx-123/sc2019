package com.xzsd.pc.client.dao;

import com.xzsd.pc.client.entity.Client;
import com.xzsd.pc.client.entity.ClientVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientDao {

    /**
     * 查询当前登录用户是店长时的所有客户信息
     *
     * @param client
     * @return
     */
    List<ClientVO> getListClient(Client client);

    /**
     * 查询登录者为管理员时的用户列表
     *
     * @param client
     * @return
     */
    List<ClientVO> getListClientByAdmin(Client client);
}