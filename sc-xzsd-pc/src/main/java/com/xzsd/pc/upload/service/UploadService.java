package com.xzsd.pc.upload.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.upload.entity.Upload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.xzsd.pc.util.COSClientUtil;

import java.util.List;

@Service
public class UploadService {
    /**
     * 图片上传
     * @param
     * @return
     */
    public AppResponse imagesUpload(List<MultipartFile> imageFile) {
        COSClientUtil cosClientUtil = new COSClientUtil();
        String name, imgUrl;
        String  url = "";
        try {
            if(imageFile.size() == 1){
                //上传一张图片
                name = cosClientUtil.uploadFile2Cos(imageFile.get(0));
                imgUrl = cosClientUtil.getImgUrl(name);
                String[] split = imgUrl.split("\\?");
                url = split[0];
            }else if(imageFile.size() > 1){
                //上传多张图片
                for (MultipartFile image : imageFile) {
                    name = cosClientUtil.uploadFile2Cos(image);
                    imgUrl = cosClientUtil.getImgUrl(name);
                    String[] split = imgUrl.split("\\?");
                    url = url + split[0] + ",";
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(url);
        Upload upload = new Upload();
        upload.setImagePath(url);
        return AppResponse.success("图片上传成功！", upload);
        //return url;
    }
}
