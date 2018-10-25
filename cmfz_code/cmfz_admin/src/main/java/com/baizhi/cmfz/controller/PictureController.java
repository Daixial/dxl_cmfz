package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.ErrorManager;
import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.PictureService;
import com.github.tobato.fastdfs.domain.MataData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;
    @Resource
    private FastFileStorageClient storageClient;

    /**
     *
     * @return
     *          这是查所有的方法  返回json字符串
     */
    @RequestMapping("/queryAll")
    public List<Picture> queryAll(){
        log.info("这是查所有的controller 你已经进入到这个方法了");
        List<Picture> pictures = pictureService.queryAll();
        return pictures;
    }

    /**
     *
     * @param picture
     * @return
     *          这是添加的方法  在业务层设置的UUID
     */
    @RequestMapping("/add")
    public ErrorManager addPicture( MultipartFile uploadFile,Picture picture){

        String originalFileName = uploadFile.getOriginalFilename();// 获取文件的名字
        //  获取文件的格式
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);// 获取文件的后缀
       // String fileName = uploadFile.getName();//实参名字
        long fileSize = uploadFile.getSize(); // 文件的大小
        log.info(originalFileName + "=====================" + fileSize + "===============" + extension );

        HashSet<MataData> metadate = new HashSet<MataData>();
        metadate.add(new MataData("width","1024"));
        ErrorManager manager = new ErrorManager();
        try {
            StorePath storePath = storageClient.uploadFile(uploadFile.getInputStream(), fileSize, extension, metadate);
          //  uploadFile.transferTo(new File(realPath,filename));  上传到本地
            picture.setGroups(storePath.getGroup());
            picture.setName(picture.getName());
            picture.setImgurl(storePath.getPath());
            picture.setStatus(picture.getStatus());
            pictureService.addPicture(picture);
            manager.setMessage("上传成功");
        } catch (Exception e) {
            manager.setMessage("上传失败");
            manager.setSuccess(false);
        }
        return manager;
    }
    @RequestMapping("/download")
    public void download( String imgurl,String groups, HttpServletResponse response) throws IOException {
        byte[] group1s = storageClient.downloadFile(groups,imgurl, new DownloadByteArray());
       OutputStream outputStream = response.getOutputStream();
        outputStream.write(group1s);
    }

    /**
     *
     * @param picture
     * @return
     *              这一个是修改的 方法
     */
    @RequestMapping("/update")
    public ErrorManager updatePicture(Picture picture){
       // log.info("这是修改的对象 ："+picture);
        ErrorManager manager = new ErrorManager();
        try {
            pictureService.modyfiPicture(picture);
            manager.setMessage("修改成功");
        } catch (Exception e) {
            manager.setMessage("修改失败");
            manager.setSuccess(false);
        }
        return manager;

    }
    @RequestMapping("/findOne")
    public Picture fingOne(String id){
        //log.info("这是轮播图控制器中的方法"+id);
        Picture picture = pictureService.queryOne(id);
        return picture;
    }
    @RequestMapping("/page")
    public HashMap<String, Object> pageView(Integer page,Integer rows){
        log.info("这是页面传来的page"+page+"和rows"+rows);
        List<Picture> pictures = pictureService.queryPage(page, rows);
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 查询所有的总条数
        Long page1 = pictureService.queryPage();
        map.put("total",page1);
        map.put("rows",pictures);
        return map;
    }

}
