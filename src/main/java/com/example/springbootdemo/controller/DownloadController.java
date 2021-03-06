package com.example.springbootdemo.controller;

import com.example.springbootdemo.ResultData;
import com.example.springbootdemo.exception.BizException;
import com.example.springbootdemo.service.TbUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("download")
public class DownloadController {

    @Value("${file.path}")
    private String filePath;

    @Value("${file.uploadPath}")
    private String fileUploadPath;

    @Autowired
    TbUserService tbUserService;

    /**
     * 文件下载 xlsx格式
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
        //生成的文件名  压缩包就用zip结尾   xlsx就用xlsx结尾
        String fileName="template"+downloadPath.substring(downloadPath.lastIndexOf("."));
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
                throw  new BizException("文件不能为空");
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
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
            throw new BizException(e.getMessage(),"500",e);
        }
    }

    @RequestMapping("ceshi")
    public String ceshi(){
        return "ceshi";
    }


    @RequestMapping("downloadZip")
    public void downloadZip(HttpServletResponse response,@RequestParam(defaultValue = "false") boolean keepDirStructure) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("下载.zip","utf-8"));//防止中文乱码
        List<String> paths=new ArrayList<>();
        paths.add("C:\\Users\\ex_jingjintao\\Desktop/新建文本文档 (3).txt");
        paths.add("C:\\Users\\ex_jingjintao\\Desktop/非车代理协议.xls");
        paths.add("C:\\Users\\ex_jingjintao\\Desktop/新建 DOCX 文档.docx");
        paths.add("C:\\Users\\ex_jingjintao\\Desktop/ttt.docx");
        //File zipFile=new File("D:/test.zip");//zip的位置
//        if(!zipFile.exists()){
//            zipFile.createNewFile();
//        }
        ZipOutputStream zos=null;
        try{
            byte[] buf=new byte[1024];
            //zos=new ZipOutputStream(new FileOutputStream(zipFile));
            //浏览器下载
            zos=new ZipOutputStream(response.getOutputStream());
            for (int i = 0; i < paths.size(); i++) {
                String path = paths.get(i);
                if(StringUtils.isEmpty(path)){
                    continue;
                }
                File sourceFile=new File(path);
                FileInputStream fis=new FileInputStream(sourceFile);
                if(keepDirStructure){
                    zos.putNextEntry(new ZipEntry(path));
                }else {
                    zos.putNextEntry(new ZipEntry(sourceFile.getName()));
                }
                int len;
                while ((len=fis.read(buf))  != -1){
                    zos.write(buf,0,len);
                }
                zos.closeEntry();
                fis.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(zos !=null){
                zos.close();
            }
        }
    }
}
