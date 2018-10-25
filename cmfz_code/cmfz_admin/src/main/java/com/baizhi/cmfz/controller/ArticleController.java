package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.entity.ErrorManager;
import com.baizhi.cmfz.service.ArticleService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/editor")
@Slf4j
public class ArticleController {

    @Resource
    private FastFileStorageClient storageClient;
    @Resource
    private ArticleService articleService;

    @RequestMapping("/upload")
    @ResponseBody
    public ErrorManager upload(MultipartFile[] keyName){
        ErrorManager message = new ErrorManager();
        try {
         ArrayList<String> data = new ArrayList<String>();
           for (MultipartFile file : keyName) {
                // 获得文件的 输入流 file.getInputStream()
                // 通过fastdfs 文件分布式系统 上传图片让

               String filename = file.getOriginalFilename();//  获取到文件的名字
               String extension = filename.substring(filename.lastIndexOf(".") + 1); // 拿到文件的后缀
              // long fileSize = file.getSize(); // 文件的大小
               StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension,null);
              log.info("http://192.168.136.132:8888/"+storePath.getGroup()+"/"+storePath.getPath());
               data.add("http://192.168.136.132:8888/"+storePath.getGroup()+"/"+storePath.getPath());

           }
           message.setErrno(0);
            message.setData(data);
        }catch (Exception e){
            message.setErrno(1);
            message.setSuccess(false);
            message.setMessage("上传失败!!!!!!!!!!!!!");
        }
        return message;
    }

    @RequestMapping("/articleUpload")
    @ResponseBody
    public ErrorManager articleUpload(Article article,String content){
        log.info("这是文章的控制器"+article);
        log.info("这是文章的内容"+content);
        ErrorManager  message = new ErrorManager();
        try {
            article.setId(UUID.randomUUID().toString());
            article.setCreateDate(new Date());
            if (article.getStatus()==null) article.setStatus("off");
            articleService.insertArticle(article);
            message.setMessage("文章创建成功");
        }catch (Exception e){
            message.setSuccess(false);
            message.setMessage("文章创建失败");
        }
        return  message;
    }
    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Article> articleQueryAll(){
        List<Article> list=null;
        try {
            list = articleService.findAll();
            return list;
        }catch(Exception e){

        }
        return  list;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Article findOne(String id){
        return articleService.findOneArticl(id);
    }

    @ResponseBody
    @RequestMapping("/updateArticle")
    public ErrorManager update(Article article){
        log.info("这是收到的参数文章 "+article);
        ErrorManager message = new ErrorManager();
        try {
            articleService.updateArticle(article);
            message.setMessage("更新成功");
        }catch (Exception e){
            message.setMessage("更新失败");
            message.setSuccess(false);
        }
        return message;
    }
}
