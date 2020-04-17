package com.xzsd.pc.store.dao;

import com.xzsd.pc.store.entity.Store;
import com.xzsd.pc.store.entity.StoreVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreDao {
    /**
     * 统计营业执照编码数量
     * @param store 门店信息
     * @return
     */
    int countBusinessCode(Store store);

    /**
     * 校验是否出现重复的邀请码
     * @param store
     * @return
     */
    int countInviteCode(Store store);
    /**
     * 新增门店
     * @param store
     * @return
     */
    int addStore(Store store);

    /**
     * 查询店长编号
     * @param store
     * @return
     */
    Store getManagerId(Store store);

    /**
     * 查询门店详情信息
     * @param storeId
     * @return
     */
    StoreVO getStoreInfoById(@Param("storeId") String storeId);

    /**
     * 修改店详情信息
     * @param store
     * @return
     */
    int updateStore(Store store);

    /**
     * 店长查询自己门店信息
     * @param store
     * @return
     */
    List<StoreVO> getListStore(Store store);

    /**
     * 管理员查询所有门店信息
     * @param store
     * @return
     */
    List<StoreVO> getListStoreByAdmin(Store store);

    /**
     * 删除门店信息
     * @param listStoreId
     * @param loginUserId
     * @return
     */
    int deleteStoreById(@Param("listStoreId") List<String> listStoreId, @Param("loginUserId") String loginUserId);
}
