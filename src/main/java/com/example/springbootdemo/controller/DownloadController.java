package com.example.springbootdemo.controller;

import com.example.springbootdemo.ResultData;
import com.example.springbootdemo.exception.BizException;
import com.example.springbootdemo.exception.CommonEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("download")
public class DownloadController {

    @Value("${file.path}")
    private String filePath;

    @Value("file.uploadPath")
    private String fileUploadPath;

    /**
     * 文件下载
     * @param response
     * @return
     */
    @RequestMapping("downloadExcel")
    @ResponseBody
    public ResultData downloadExcel(HttpServletResponse response){
        if(filePath ==null || "".equals(filePath)){
            throw new BizException("500","未配置路径");
        }
        //创建输入输出流
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        String downloadPath=filePath;
        //生成的文件名
        String fileName="template.xlsx";
        //要下载的文件对象
        File file=new File(downloadPath);
        //如果目录不存在，创建目录
        if(!file.exists()){
            file.mkdirs();
        }
        //获取文件长度
        long length = file.length();
        try {
            //Content-Disposition: attachment; filename="filename.xls"
            //第一个参数是attachment（意味着消息体应该被下载到本地；大多数浏览器会呈现一个“保存为”的对话框，
            // 将filename的值预填为下载后的文件名，假如它存在的话）
            response.setHeader("Content-disposition","attachment;filename="+new String(fileName.getBytes("utf-8"),"ISO8859-1"));
            //Content-Type 实体头部用于指示资源的MIME类型 media type
            response.setHeader("Content-Type","application/json");
            //Content-Length, HTTP消息长度, 用十进制数字表示的八位字节的数目
            response.setHeader("Content-Length",String.valueOf(length));
            // 创建输入输出流实例
            bis=new BufferedInputStream(new FileInputStream(downloadPath));
            bos=new BufferedOutputStream(response.getOutputStream());
            //创建字节缓冲大小
            byte[] buff=new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead=bis.read(buff,0,buff.length))){
                bos.write(buff,0,bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.error();
        }finally {
            if(bis!=null){
                try {
                    //关闭输入流
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos!=null){
                try {
                    //关闭输出流
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultData.success();
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public ResultData fileUpload(MultipartFile file){
        try {
            if (file.isEmpty()){
                throw  new BizException("文件不能未空");
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
            //文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //文件存放路径
            String path=fileUploadPath+"/"+fileName;
            File dest=new File(path);
            //检测是否存在目录
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            //文件写入到上述的存放路径path
            file.transferTo(dest);
            return ResultData.success(200,"文件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(CommonEnum.INTER_SERVER_ERROR,e);
        }
    }

    @RequestMapping("ceshi")
    public String ceshi(){
        return "ceshi";
    }
}
