package com.xzsd.pc.driver.dao;

import com.xzsd.pc.driver.entity.Driver;
import com.xzsd.pc.driver.entity.DriverVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DriverDao {

    /**
     * 统计司机账号数量
     * @param driver 司机信息
     * @return
     */
    int countDriverAccount(Driver driver);

    /**
     * 统计手机数量
     * @param driver
     * @return
     */
    int countPhone(Driver driver);

    /**
     * 新增司机
     * @param driver
     * @return
     */
    int addDriver(Driver driver);

    /**
     * 新增司机地址信息
     * @param driver
     * @return
     */
    int addDriverArea(Driver driver);

    /**
     * 查询省市区名称
     * @param driverId
     * @return
     */
    List<String> getListAreaName(@Param("driverId") String driverId);

    /**
     * 查询司机信息
     * @param driverId
     * @return
     */
    DriverVO getDriverById(@Param("driverId") String driverId);

    /**
     * 修改司机信息
     * @param driver
     * @return
     */
    int updateDriver(Driver driver);

    /**
     * 修改司机地区信息
     * @param driver
     * @return
     */
    int updateDriverArea(Driver driver);

    /**
     *管理员查询所有的司机信息
     * @param driver
     * @return 司机信息
     */
    List<DriverVO> getListDriverByAdmin(Driver driver);

    /**
     * 查询当前店长下的所有司机
     * @param driver
     * @return
     */
    List<DriverVO> getListDriverByStore(Driver driver);

    /**
     * 删除司机
     * @param listDriverId
     * @param loginId
     * @return
     */
    int deleteDriverById(@Param("listDriverId") List<String> listDriverId, @Param("loginId") String loginId);
    /**
     * 删除司机地区
     * @param listDriverId
     * @param loginId
     * @return
     */
    int deleteDriverAreaById(@Param("listDriverId") List<String> listDriverId, @Param("loginId") String loginId);
}
