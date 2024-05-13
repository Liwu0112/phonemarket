package org.example.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Administrator;
import org.example.entity.Phone;
import org.example.mapper.AdministratorMapper;
import org.example.mapper.PhoneMapper;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private AdministratorMapper administratorMapper;

    //管理员查询所有手机信息
    @Override
    public List<Phone> adminSelectall() {
        QueryWrapper<Phone> queryWrapper = new QueryWrapper<>();
        List<Phone> phoneList = phoneMapper.selectList(queryWrapper);
        return phoneList;
    }

    //管理员根据手机编号查询手机信息
    @Override
    public Phone adminSelectById(Integer id) {
        return phoneMapper.selectById(id);
    }

    //管理员增加手机信息
    @Override
    @Transactional
    public int adminInsertPhone(Phone phone) {
        return phoneMapper.insert(phone);
    }

    //管理员根据编号修改手机信息
    @Override
    public int adminUpdatePhoneById(Phone phone) {
        QueryWrapper<Phone> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", phone.getId());
        int result = phoneMapper.update(phone, queryWrapper);
        return result;
    }


    //管理员根据编号彻底删除手机信息
    @Override
    @Transactional
    public int adminDeleteById(Integer id) {
        return phoneMapper.deleteById(id);
    }

    //管理员登录
    @Override
    public Administrator adminLogin(Integer account, String password) {
        LambdaQueryWrapper<Administrator> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Administrator::getAccount,account);
        //根据adminName查询数据库对应数据
        Administrator adminInformations = administratorMapper.selectOne(queryWrapper);
        String dbAdminPassword = adminInformations.getPassword();
        if (!(dbAdminPassword.equals(password))){
            return null;
        }
        return adminInformations;
    }


}
