package org.example.service;

import org.apache.catalina.User;
import org.example.entity.Byphone;
import org.example.entity.Phone;
import org.example.entity.Users;

import java.util.List;

public interface UserService {


    //用户登录
    Users userLogin(Integer id, String password);
    //用户注册
    int userInsert(Users users);
    //用户修改个人信息
    int userUpdate(Users users);
    //用户查看所有手机信息
    List<Phone> userSelectAll();
    //用户购买
    int userUpdatePhone(Integer id);

    //根据id查询用户信息
    Users userSelectById(Integer id);
    //用户购买数据新增
    int userINsertBuyPhone(Byphone byphone);
//用户查看已购买商品
    List<Byphone> userSelectPhoneBYId(Integer userid);
//用户退货
    int userDeletePhone(Integer id);
}
