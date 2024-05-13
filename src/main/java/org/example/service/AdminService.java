package org.example.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Administrator;
import org.example.entity.Phone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminService {
    //管理员查询所有手机信息
    List<Phone> adminSelectall();
    //管理员根据手机编号查询手机信息
    Phone adminSelectById(Integer id);
    //管理员增加手机信息
    int adminInsertPhone(Phone phone);
    //管理员根据编号修改手机信息
    int adminUpdatePhoneById(Phone phone);
    //管理员根据编号彻底删除手机信息
    int adminDeleteById(Integer id);
//管理员登录
    Administrator adminLogin(Integer account,String password);
}