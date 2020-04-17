package com.xzsd.pc.slideshowHome.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.slideshowHome.dao.SlideshowHomeDao;
import com.xzsd.pc.slideshowHome.entity.SlideshowHome;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * 轮播图
 * @Author wzx
 * @Date 2020-04-10
 */
@Service
public class SlideshowHomeService {

    @Resource
    private SlideshowHomeDao slideshowHomeDao;

    /**
     * 添加首页轮播图实现
     * @param slideshowHome
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addSlideshowHome(SlideshowHome slideshowHome){
        String slideshowId = slideshowHome.getSlideshowId();
        //查询轮播图对应的商品是否存在
        int countGoodsId = slideshowHomeDao.countGoodsId(slideshowHome.getGoodsId(),slideshowId);
        if(0 != countGoodsId){
            return AppResponse.bizError("该商品已存在轮播图中");
        }
        //查询轮播图对应的序号是否存在
        int countSlideshowNum = slideshowHomeDao.countSlideshowNum(slideshowHome.getSlideshowNum(),slideshowId);
        if(countSlideshowNum != 0){
            //处理排序序号
            slideshowHomeDao.solveSlideshowNum(slideshowHome.getSlideshowNum());
        }
        slideshowHome.setSlideshowId("lbt" + StringUtil.getCommonCode(2));
        //获取修改人的id
        String createUser = SecurityUtils.getCurrentUserId();
        slideshowHome.setCreateUser(createUser);
        int count = slideshowHomeDao.addSlideshowHome(slideshowHome);
        if(0 == count){
            return AppResponse.bizError("添加首页轮播图失败");
        }
        return AppResponse.success("添加首页轮播图成功",slideshowHome);
    }

    /**
     * 分页查询首页轮播图实现
     * @param slideshowStateId
     * @return
     */
    public AppResponse listSlideshowHome(String slideshowStateId){
        List<SlideshowHome> slideshowHomeList = slideshowHomeDao.listSlideshowHomeByPage(slideshowStateId);
        return AppResponse.success("查询首页轮播图列表成功" ,getPageInfo(slideshowHomeList));
    }

    /**
     * 查询商品分页实现
     * @param goodsName
     * @param goodsId
     * @return
     */
    public AppResponse listGoods(String goodsName,String goodsId){
        List<Goods> goodsList = slideshowHomeDao.listGoodsByPage(goodsName,goodsId);
        return AppResponse.success("查询商品列表成功" ,getPageInfo(goodsList));
    }

    /**
     * 修改首页轮播图状态实现
     * @param slideshowId
     * @param slideshowStateId
     * @param version
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateSlideshowHomeState(String slideshowId,int slideshowStateId,String version){
        //获取首页轮播图id
        List<String> listId = Arrays.asList(slideshowId.split(","));
        //获取首页轮播图版本
        List<String> listVersion = Arrays.asList(version.split(","));
        List<SlideshowHome> updateSlideList = new ArrayList<>();
        //获取当前登录人id
        String updateUser = SecurityUtils.getCurrentUserId();
        //将全部的更改信息放入一个list里
        for (int i = 0 ; i < listId.size() ; i++ ) {
            SlideshowHome slideshowHome = new SlideshowHome();
            slideshowHome.setSlideshowId(listId.get(i));
            slideshowHome.setVersion(listVersion.get(i));
            slideshowHome.setSlideshowStateId(slideshowStateId);
            slideshowHome.setUpdateUser(updateUser);
            updateSlideList.add(slideshowHome);
        }
        int count = slideshowHomeDao.updateSlideshowHomeState(updateSlideList);
        if(0 == count){
            return AppResponse.bizError("修改状态失败！");
        }
        return AppResponse.success("修改状态成功！");
    }

    /**
     * 删除首页轮播图
     * @param slideshowId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteSlideshowHome(String slideshowId){
        //获取首页轮播图id
        List<String> slideshowList = Arrays.asList(slideshowId.split(","));
        //获取当前登录人id
        String updateUser = SecurityUtils.getCurrentUserId();
        int count = slideshowHomeDao.deleteSlideshowHome(slideshowList,updateUser);
        if(0 == count){
            return AppResponse.bizError("删除商品失败！");
        }
        return AppResponse.success("删除商品成功！");
    }
}
