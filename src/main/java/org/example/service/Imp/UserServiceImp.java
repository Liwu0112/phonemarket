package org.example.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.entity.Byphone;
import org.example.entity.Phone;
import org.example.entity.Users;
import org.example.mapper.ByphoneMapper;
import org.example.mapper.PhoneMapper;
import org.example.mapper.UsersMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private ByphoneMapper byphoneMapper;

    //用户登录
    @Override
    public Users userLogin(Integer id, String password) {
        QueryWrapper<Users> userinformationsQueryWrapper = new QueryWrapper<>();
        userinformationsQueryWrapper.eq("id", id);
        Users users = usersMapper.selectOne(userinformationsQueryWrapper);
        if (usersMapper == null) {
            return null;
        }
        String dbUserPassword = users.getPassword();
        //对用户输入的密码进行加密，与数据库中密码进行比对
        if (!(dbUserPassword.equals(password))) {
            return null;
        }
        return users;
    }

    //用户注册
    @Override
    public int userInsert(Users users) {
        return usersMapper.insert(users);
    }

    //用户修改个人信息
    @Override
    public int userUpdate(Users users) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", users.getId());
        int result = usersMapper.update(users, queryWrapper);
        return result;
    }

    //用户查看所有手机信息
    @Override
    public List<Phone> userSelectAll() {
        QueryWrapper<Phone> queryWrapper = new QueryWrapper<>();
        List<Phone> phones = phoneMapper.selectList(queryWrapper);
        return phones;
    }

    //用户购买
    @Override
    public int userUpdatePhone(Integer id) {
        Phone phone = phoneMapper.selectById(id);
        Integer number = phone.getNumber();
        if (number <= 0) {
            return 0;
        }
        phone.setNumber(number - 1);
        return phoneMapper.updateById(phone);
    }

    //根据id查看用户信息
    @Override
    public Users userSelectById(Integer id) {
        return usersMapper.selectById(id);
    }
//用户都买数据新增
    @Override
    public int userINsertBuyPhone(Byphone byphone) {
        return byphoneMapper.insert(byphone);
    }

    @Override
    public List<Byphone> userSelectPhoneBYId(Integer userid) {
        QueryWrapper<Byphone> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        return byphoneMapper.selectList(queryWrapper);
    }

    @Override
    public int userDeletePhone(Integer id) {
       Byphone byphone = byphoneMapper.selectById(id);
       int a = byphone.getPhoneid();
       Phone phone = phoneMapper.selectById(a);
       int b =phone.getNumber();
       phone.setNumber(b+1);
       return byphoneMapper.deleteById(id);
    }


}
