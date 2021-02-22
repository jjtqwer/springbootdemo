package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.TbUserEntity;
import com.example.springbootdemo.service.TbUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Slf4j
@RequestMapping("/tb/user")
@RestController
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${mail.username}")
    private String sender;

    @RequestMapping("ex")
    public String ex(){
        throw new RuntimeException();
    }

    @RequestMapping("/test/{name}")
    public String name(@PathVariable String name){
        return "hello "+name;
    }

    @RequestMapping("/list")
    public List<TbUserEntity> list(){
        List<TbUserEntity> list = tbUserService.list();
        return list;
    }

    @RequestMapping("{id}")
    public TbUserEntity getById(@PathVariable String id){
        TbUserEntity entity = tbUserService.getById(id);
        return entity;
    }

    @RequestMapping("sendEmail")
    public int sendEmail(){
        try {
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo("ex_jingjintao@axatp.com");
            mailMessage.setSubject("这是标题");
            mailMessage.setText("这是内容");
            javaMailSender.send(mailMessage);
            return 1;
        } catch (MailException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("sendEmail2")
    public int sendEmail2() throws MessagingException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //设置邮件内容中文不乱码
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true,"utf-8");
            messageHelper.setSubject("这是标题内容");
            messageHelper.setFrom(sender);
            messageHelper.setTo("ex_jingjintao@axatp.com");
            //发送内容为html
            messageHelper.setText("<h1>标题</h1><br/><p>这是邮件内容</p>",true);
            javaMailSender.send(messageHelper.getMimeMessage());
            return 1;
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        } catch (MailException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
