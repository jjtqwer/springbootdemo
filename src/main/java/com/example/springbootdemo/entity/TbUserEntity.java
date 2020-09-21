package com.example.springbootdemo.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
//@TableName("tb_user")
@TableName("t_collection_package")
public class TbUserEntity {
   /* @TableId
    private Integer userId;

    private String userName;

    private String mobile;

    private String password;

    private Date createTime;*/

   @TableId
    private String packageId;

   private String packageName;

   private String createdBy;

   private Date createdDate;
}
