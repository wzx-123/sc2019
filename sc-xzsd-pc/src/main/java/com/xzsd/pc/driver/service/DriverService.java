package com.xzsd.pc.driver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.driver.dao.DriverDao;
import com.xzsd.pc.driver.entity.Driver;
import com.xzsd.pc.driver.entity.DriverVO;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 司机实现类
 */
@Service
public class DriverService {

    @Resource
    private DriverDao driverDao;

    @Resource
    private UserDao userDao;

    /**
     * 新增司机
     * @param driver
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addDriver(Driver driver){
        // 校验司机账号是否存在
        int countDriverAccount = driverDao.countDriverAccount(driver);
        if(countDriverAccount != 0){
            return AppResponse.bizError("账号已存在，请重新输入");
        }
        //随机生成司机编码
        driver.setDriverId(StringUtil.getCommonCode(2));
        driver.setIsDelete(0);
        //地址表id
        driver.setDriverInfoId(StringUtil.getCommonCode(2));
        //密码加密
        String password = driver.getUserPassword();
        String pwd = PasswordUtils.generatePassword(password);
        driver.setUserPassword(pwd);
        //新增司机
        int count = driverDao.addDriver(driver);
        int num = driverDao.addDriverArea(driver);
        if(count == 0 && num == 0){
            return AppResponse.bizError("新增失败！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询司机详情
     * @param driverId
     * @return
     */
    public AppResponse getDriverById(String driverId){
        DriverVO driver = driverDao.getDriverById(driverId);
        if(driver == null){
            return AppResponse.bizError("查询失败");
        }
        return AppResponse.success("查询成功", driver);
    }

    /**
     * 修改司机信息
     * @param driver
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateDriver(Driver driver){
        DriverVO driverVO = driverDao.getDriverById(driver.getDriverId());
        //判断传入的账号是否和当前的一样，如果不相同才能修改
        if(driverVO.getUserAcct().equals(driver.getUserAcct()) == false){
            //校验账号是否在表中存在
            int count = driverDao.countDriverAccount(driver);
            if(count != 0){
                return AppResponse.bizError("该司机账号已存在，请重新输入！");
            }
        }
        //判断传入的手机是否和当前的一样，如果不相同才能修改
        if(driverVO.getPhone().equals(driver.getPhone()) == false){
            // 校验手机号是否在表中存在
            int countPhone = driverDao.countPhone(driver);
            if(0 != countPhone){
                return AppResponse.bizError("手机号已存在，请重新输入");
            }
        }
        //判断传入的密码是否和当前的一样，如果不相同才能修改
        if(driverVO.getUserPassword().equals(driver.getUserPassword()) == false){
            //密码加密
            String password = driver.getUserPassword();
            String pwd = PasswordUtils.generatePassword(password);
            driver.setUserPassword(pwd);
        }
        //修改司机信息
        int count= driverDao.updateDriver(driver);
        int driverArea = driverDao.updateDriverArea(driver);
        if(count == 0 && driverArea == 0) {
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 查询司机列表（分页）
     * @param driver
     * @return
     */
    public AppResponse getListDriver(Driver driver){
        PageHelper.startPage(driver.getPageNum(), driver.getPageSize());
        List<DriverVO> listDriver = null;
        //根据角色展示对应的司机信息
        if("2".equals(driver.getRole())){
            listDriver = driverDao.getListDriverByStore(driver);
        }else if("0".equals(driver.getRole()) || "1".equals(driver.getRole())){
            listDriver = driverDao.getListDriverByAdmin(driver);
        }
        PageInfo<DriverVO> pageData = new PageInfo<DriverVO>(listDriver);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 删除司机信息
     * @param driverId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDriverById(String driverId, String loginId){
        List<String> listDriverId = Arrays.asList(driverId.split(","));
        int count = driverDao.deleteDriverById(listDriverId, loginId);
        int num = driverDao.deleteDriverAreaById(listDriverId, loginId);
        if(count == 0 && num == 0){
            return AppResponse.success("删除失败！");
        }
        return AppResponse.success("删除成功！");
    }
}
