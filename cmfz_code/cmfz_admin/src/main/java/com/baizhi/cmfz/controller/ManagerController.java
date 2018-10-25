package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.ErrorManager;
import com.baizhi.cmfz.entity.Managers;
import com.baizhi.cmfz.service.ManagersService;
import com.baizhi.cmfz.utils.ImageUtil;
import com.baizhi.cmfz.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/manager")
@Slf4j
public class ManagerController {
    @Autowired
    private ManagersService managersService;

    /**
     *
     * @param name
     * @param password
     * @param enCode
     * @param session
     * @param rememberUsername
     * @return
     * 这是登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public ErrorManager login(String name, String password , String enCode,
                              HttpServletResponse response, HttpSession session, String rememberUsername) throws UnsupportedEncodingException {
        log.info(rememberUsername+"记住密码");


        ErrorManager errorManager = new ErrorManager();
        String code = (String) session.getAttribute("imageCode");
        log.info(enCode+"页面的");
        log.info(code+"后台的");
        try {
            if(!code.equalsIgnoreCase(enCode)){
                errorManager.setSuccess(false);
                errorManager.setMessage("验证码错误");
                return errorManager;
            }
            Managers mana = managersService.queryManager(name, password);
            session.setAttribute("mana",mana);
        } catch (Exception e) {
            errorManager.setMessage("账号密码错误");
            errorManager.setSuccess(false);
        }
        // 判断是否记住用户名
        if(rememberUsername!=null){
          //  String encode = URLEncoder.encode(name, "UTF-8");
            Cookie cookie = new Cookie("name",name);
            cookie.setMaxAge(60*60*23);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return errorManager;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ErrorManager updateManager(String password,HttpSession session){
        log.info("这是修改密码"+password);
        ErrorManager errorManager = new ErrorManager();
        try {
            Managers   mage = (Managers) session.getAttribute("mana");
            log.info("这是登录对象A"+mage);
            mage.setPassword(password);
            managersService.modyfiManager(mage);
            session.setAttribute("mana",mage);
            errorManager.setMessage("修改成功");
        } catch (Exception e) {
          errorManager.setSuccess(false);
          errorManager.setMessage(e.getMessage());
        }

        return errorManager;
    }
    @RequestMapping("/esc")
    public String quitManager(HttpSession session){
        session.invalidate();
        return "redirect:/login/login.jsp";
    }

    /**
     *
     * @param response
     * @param request
     * @throws Exception
     *      图片验证码的 方法
     */
    @RequestMapping("/imagecode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception{
        HttpSession session=request.getSession();
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = ImageUtil.createImage();
        //将验证码存入Session
        session.setAttribute("imageCode",objs[0]);

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    /**
     *
     * @param name
     * @param session
     * @return
     *
     *    去redis中取出  功能未完善
     */
   /* @RequestMapping("/remUser")
    @ResponseBody
    public Map<String,String> rembUser(String name,HttpSession session){
        try {
            if (name==null){
                Map<String, String> map = RedisUtils.getKeys();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    session.setAttribute("key",entry.getKey());
                    session.setAttribute("value",entry.getValue());
                }

                return map;
            }

            Map<String, String> map = RedisUtils.getParameters(name);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                session.setAttribute("key",entry.getKey());
                session.setAttribute("value",entry.getValue());
            }
            return map;
        } catch (Exception e) {
            HashMap<String, String> map = new HashMap<String, String>();
            return map;
        }

    }*/


}
