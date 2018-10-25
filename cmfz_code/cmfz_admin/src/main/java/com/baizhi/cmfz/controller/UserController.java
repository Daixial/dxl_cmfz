package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.ErrorManager;
import com.baizhi.cmfz.entity.Statistics;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<User> fingdAll(){
        List<User> users = userService.queryAll();
        return users;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ErrorManager add(MultipartFile file){
        ErrorManager manager = new ErrorManager();
        try {
            //创建工作簿
            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = wb.getSheetAt(0);
            manager.setMessage("上传成功");
        }catch (Exception e){
            manager.setSuccess(false);
            manager.setMessage("批量上传失败");

        }
        return manager;
    }

    @RequestMapping("/outload")
    @ResponseBody
    public ErrorManager Outload() throws IOException {
        ErrorManager message = new ErrorManager();
        try {
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("学生信息");
            Row row = sheet.createRow(0);
            String[] titles = {"id","手机号","名字","法名","性别","创建时间","标签","密码","私盐","状态","位置"};
            for (int i = 0; i < titles.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(titles[i]);
            }
            //  处理日期格式
            //CellStyle cellStyle = workbook.createCellStyle(); //  获得样式对象
           // DataFormat dataFormat = workbook.createDataFormat();// 日期格式
           // cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd")); // 设置日期格式

            List<User> users = userService.queryAll();
            int i=0;
            for (User user : users) {
                i=i+1;
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getPhone());
                row.createCell(2).setCellValue(user.getName());
                row.createCell(3).setCellValue(user.getNickName());
                row.createCell(4).setCellValue(user.getSex());
                row.createCell(5).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(user.getCreateDate()));
                row.createCell(6).setCellValue(user.getSignature());
                row.createCell(7).setCellValue(user.getPassword());
                row.createCell(8).setCellValue(user.getSalt());
                row.createCell(9).setCellValue(user.getStatus());
                row.createCell(10).setCellValue(user.getLocation());
            }
            //5. 创建Excel文件
            workbook.write(new FileOutputStream("E:\\test3.xls"));
            workbook.close();
            message.setMessage("文件导出成功");
        } catch (IOException e) {
            message.setSuccess(false);
            message.setMessage("数据导入失败");
        }
        return message;
    }

    @RequestMapping("/batchOut")
    @ResponseBody
    public ErrorManager batchOut(String[] ids){
        ErrorManager message = new ErrorManager();
        String[] titles = {"id","手机号","名字","法名","性别","创建时间","标签","密码","私盐","状态","位置"};
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生信息");
        Row row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }
        //  处理日期格式
        CellStyle cellStyle = workbook.createCellStyle(); //  获得样式对象
        DataFormat dataFormat = workbook.createDataFormat();// 日期格式
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd")); // 设置日期格式
        try {
            int i=0;
            for (String id : ids) {
                i=i+1;
                log.info("这是收到的参数"+id);
                User user = userService.queryOne(id);
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getPhone());
                row.createCell(2).setCellValue(user.getName());
                row.createCell(3).setCellValue(user.getNickName());
                row.createCell(4).setCellValue(user.getSex());
                row.createCell(5).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(user.getCreateDate()));
                row.createCell(6).setCellValue(user.getSignature());
                row.createCell(7).setCellValue(user.getPassword());
                row.createCell(8).setCellValue(user.getSalt());
                row.createCell(9).setCellValue(user.getStatus());
                row.createCell(10).setCellValue(user.getLocation());

            }
            workbook.write(new FileOutputStream("E:\\test.xls"));
            workbook.close();
            message.setMessage("导出成功");
        }catch (Exception e){
            message.setSuccess(false);
            message.setMessage("导出失败");
        }
        return message;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ErrorManager findOne(User user){
        ErrorManager message = new ErrorManager();
        try {
            userService.renewalUser(user);
            message.setMessage("状态修改成功");
        }catch (Exception e){
            message.setSuccess(false);
            message.setMessage("状态修改失败");
        }
        return message;
    }
    @ResponseBody
    @RequestMapping("/findOne")
    public User findOne(String id){
        User user = userService.queryOne(id);
        return user;
    }

    @RequestMapping("/customExport")
    @ResponseBody
    public ErrorManager columnQuery(String titles, String columns, HttpServletResponse response){
        log.info("这是自定义的收参"+titles+"lie"+columns);
        ErrorManager manager = new ErrorManager();
        try {
            List<User> users = userService.queryColums(columns);
            Workbook workbook = new HSSFWorkbook();

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy-MM-dd"));

            Sheet sheet = workbook.createSheet("用户信息");
            Row row = sheet.createRow(0);
            // 将用户信息导出到Excel表格中
            // Excel=标题行+数据行
            String[] title = titles.split(",");
            for (int i = 0; i < title.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(title[i]);
            }

            // [id,name,age,birthday]
            // get+Cname = get方法名
            String[] column = columns.split(",");

            // 数据行
            for (int i = 1; i <= users.size(); i++) {
                row = sheet.createRow(i);
                User user = users.get(i - 1);
                Class<? extends User> c = user.getClass();
                for (int j = 0; j < column.length; j++) {
                    Cell cell = row.createCell(j);
                    // id ---> Id
                    String cName = column[j];
                    String getMethodName = "get" + cName.substring(0, 1).toUpperCase() + cName.substring(1, cName.length());
                    Method method = c.getMethod(getMethodName, null);
                    // get方法对应的返回值
                    Object obj = method.invoke(user, null);
                    if (obj == null) {
                        continue;
                    }
                    if (obj instanceof Date) {
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue((Date) obj);
                    } else {
                        cell.setCellValue(obj.toString());
                    }
                }
            }

            response.setHeader("content-disposition", "attachment;filename=user.xls");
            response.setContentType("application/vnd.ms-excel");
            ((HSSFWorkbook) workbook).write(response.getOutputStream());

        }catch (Exception e){
            manager.setSuccess(false);
            manager.setMessage("导出失败");
        }
        return manager;
    }

    /*统计各个省用户人数*/
    @RequestMapping("/statistics")
    @ResponseBody
    public List<Statistics> statistics(String sex){
        List<Statistics> users = userService.findactive(sex);
        return users;
    }
    /*统计用户的活跃度*/
    @RequestMapping("/active")
    @ResponseBody
    public List<Integer> findActive(){
        return userService.findActive();
    }


}
