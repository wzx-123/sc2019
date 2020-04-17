package com.xzsd.pc.goods.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.goods.entity.GoodsClassifyList;
import com.xzsd.pc.goods.entity.GoodsVO;
import com.xzsd.pc.goods.entity.GoodsVTO;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyVO;
import com.xzsd.pc.utils.RandomUtil;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goods.dao.GoodsDao;
import com.xzsd.pc.goods.entity.Goods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * 商品管理实现类
 * @ClassName GoodsService
 * @Description Goods
 */
@Service
public class GoodsService {

    @Resource
    private GoodsDao goodsDao;


    /**
     * 新增商品
     * @param goodsInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoods(Goods goodsInfo){
        //判断是否出现重复的书号
        int count = goodsDao.countBookNumber(goodsInfo.getIsbn());
        if(count != 0){
            return AppResponse.versionError("书号重复，请输入正确的书号！");
        }
        //判断传入的字段是否为空
        if(null == goodsInfo.getGoodsAdvertise()){
            goodsInfo.setGoodsAdvertise("");
        }
        if(null == goodsInfo.getGoodsDescribe()){
            goodsInfo.setGoodsDescribe("");
        }
        //生成商品id
        goodsInfo.setGoodsId(StringUtil.getCommonCode(2));
        //生成商家编码
        goodsInfo.setStoreId(RandomUtil.randomLetter(3) + StringUtil.getCommonCode(2));
        int goods = goodsDao.addGoods(goodsInfo);
        if(goods == 0){
            return AppResponse.versionError("新增商品失败");
        }
        return AppResponse.success("新增商品成功");
    }

    /**
     * 查询商品详情
     * @param goodsId
     * @return
     */
    public AppResponse getGoodsInfoById(String goodsId){
        GoodsVTO goodsInfo = goodsDao.getGoodsInfoById(goodsId);
        if(goodsInfo == null){
            return AppResponse.versionError("查询商品详情失败！");
        }
        return AppResponse.success("查询商品详情成功！", goodsInfo);
    }

    /**
     * 获取商品一二级分类
     * @param classifyId
     * @return
     */
    public AppResponse getListGoodsCategory(String classifyId){
        List<GoodsClassifyVO> listGoodsCategory = goodsDao.getListGoodsCategory(classifyId);
        if(listGoodsCategory.size() == 0){
            return AppResponse.versionError("获取商品分类失败！");
        }
        //封装成接口文档需要的名称
        GoodsClassifyList goodsClassifyList = new GoodsClassifyList();
        goodsClassifyList.setGoodsClassifyList(listGoodsCategory);
        return AppResponse.success("获取商品分类成功！", goodsClassifyList);
    }

    /**
     * 修改商品信息
     * @param goodsInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsInfo(Goods goodsInfo){
        GoodsVTO goods = goodsDao.getGoodsInfoById(goodsInfo.getGoodsId());
        //判断当前的书号有没有改变
        if(goods.getIsbn().equals(goodsInfo.getIsbn()) == false){
            int count = goodsDao.countBookNumber(goodsInfo.getIsbn());
            if(0 != count){
                return AppResponse.versionError("存在相同的书号，请输入正确的书号！");
            }
        }
        //判断传入的字段是否为空
        if(null == goodsInfo.getGoodsAdvertise()){
            goodsInfo.setGoodsAdvertise("");
        }
        if(null == goodsInfo.getGoodsDescribe()){
            goodsInfo.setGoodsDescribe("");
        }
        int number = goodsDao.updateGoodsInfo(goodsInfo);
        if(number == 0){
            return AppResponse.versionError("修改商品信息失败！");
        }
        return AppResponse.success("修改商品信息成功！");
    }

    /**
     * 更新商品状态
     * @param goodsInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsStatus(Goods goodsInfo){
        //分割字符
        List<String> listGoodsId = Arrays.asList(goodsInfo.getGoodsId().split(","));
        List<String> listVersion = Arrays.asList(goodsInfo.getVersion().split(","));
        List<Goods> goodsInfoList = new ArrayList<>();
        for (int i = 0; i < listGoodsId.size() && i <listVersion.size(); i++) {
            Goods info = new Goods();
            //设置商品id
            info.setGoodsId(listGoodsId.get(i));
            //设置版本号
            info.setVersion(listVersion.get(i));
            //设置更新人
            info.setUpdateUser(goodsInfo.getUpdateUser());
            //设置商品状态
            info.setGoodsStateId(goodsInfo.getGoodsStateId());
            goodsInfoList.add(info);
        }
        int count = goodsDao.updateGoodsStatus(goodsInfoList);
        if(count == 0){
            return AppResponse.versionError("更新商品状态失败！");
        }
        return AppResponse.success("更新商品状态成功！");
    }

    /**
     * 查询商品列表（分页）
     * @param goodsInfo
     * @return
     */
    public AppResponse getListGoods(Goods goodsInfo){
        PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
        List<GoodsVO> listGoods = goodsDao.getListGoods(goodsInfo);
        PageInfo<GoodsVO> pageData = new PageInfo<>(listGoods);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 删除商品
     * @param goodsId
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String goodsId, String userId){
        //传入要删除的商品id
        List<String> list = Arrays.asList(goodsId.split(","));
        //查询已经被热门位和轮播图占用的商品
        List<String> goodsIdList = goodsDao.querySlideAndHotGoods(list);
        //去除已经被轮播图和热门商品使用的商品id
        List<String> listGoodsId = new ArrayList<>();
        int index = 0;
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            for(j = 0; j < goodsIdList.size(); j++){
                if(list.get(i).equals(goodsIdList.get(j)) == false){
                    index++;
                }
            }
            //判断次数是否相同，相同就说明该商品不被用于轮播图或热门商品
            if(index == j){
                listGoodsId.add(list.get(i));
            }
            //index归零，重新遍历下一个商品id
            index = 0;
        }
        if(listGoodsId.size() == 0){
            return AppResponse.versionError("该商品已经被用于轮播图或热门商品，所有不能删除");
        }
        int count = goodsDao.deleteGoods(listGoodsId, userId);
        if(count == 0){
            return AppResponse.versionError("删除失败！");
        }
        return AppResponse.success("删除成功！");
    }
}