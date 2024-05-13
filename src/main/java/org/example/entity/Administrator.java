package org.example.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengjing
 * @since 2024-01-13
 */
@ApiModel(value = "Administrator对象", description = "")
@TableName("administrator")
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;
        @TableId(value = "account",type = IdType.AUTO)
      private Integer account;

    private String password;


    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator{" +
        "account=" + account +
        ", password=" + password +
        "}";
    }
}
