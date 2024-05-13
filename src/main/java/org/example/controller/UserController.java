package org.example.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.dto.userLoginDto;
import org.example.entity.Administrator;
import org.example.entity.Byphone;
import org.example.entity.Phone;
import org.example.entity.Users;
import org.example.service.UserService;
import org.example.utils.BeanUtils;
import org.example.utils.api.BaseApiService;
import org.example.utils.api.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@ResponseBody
@Slf4j
@Api(tags = "用户")
@RequestMapping("/user")
public class UserController extends BaseApiService {
    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping("/userLogin")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public BaseResponse userLogin(@RequestBody userLoginDto user) {
        if (user == null) {
            return setResultError("user is null");
        }
        if (user.getId() == null) {
            return setResultError("userName is null");
        }
        if (user.getPassword() == null) {
            return setResultError("userPassword is null");
        }
        Users users = userService.userLogin(user.getId(), user.getPassword());
        if (users == null) {
            return setResultError("用户信息不存在");
        }
        userLoginDto userDto = BeanUtils.doToDto(users, userLoginDto.class);
        return setResultSuccessData(userDto);
    }

    //用户注册
    @PostMapping("/userinsert")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public BaseResponse userInsert(@RequestBody Users users) {
        if (users == null || users.getPassword() == null || users.getAge() == null || users.getAddress() == null
                || users.getNickname() == null || users.getPhonenumber() == null) {
            return setResultError("注册失败");
        }
        int a = userService.userInsert(users);
        if (a > 0) {
            return setResultError("注册成功，您的账号是：" + users.getId() + "，点击确认返回登录页使用该账号登录");
        }
        return setResultError("注册失败");
    }

    //用户修改个人信息
    @PutMapping("/updateuser")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public BaseResponse userUpdate(@RequestBody Users users) {
        int a = userService.userUpdate(users);
        return setResultDb(a);
    }

    //用户查看所有商品
    @GetMapping("/selectAll")
    @ApiOperation(value = "查看所有手机信息", notes = "查看所有手机信息")
    public BaseResponse<List<Phone>> userSelectAll() {
        List<Phone> phoneList = userService.userSelectAll();
        return setResultSuccessData(phoneList);
    }

    //根据id查看用户信息
    @GetMapping("/selectbyid/{id}")
    @ApiOperation(value = "根据id查看用户信息", notes = "根据id查看用户信息")
    public BaseResponse userSelectById(Integer id) {
            Users users = userService.userSelectById(id);
            UserDto userDto = BeanUtils.doToDto(users, UserDto.class);
            if (userDto == null){
                return setResultError("没有该id数据");
            }else {
                return setResultSuccessData(userDto);
            }
        }


    //用户购买商品
    @PostMapping("/userbuy/{id}")
    @ApiOperation(value = "购买商品", notes = "购买商品")
    public BaseResponse userBuyPhone(@PathVariable("id") Integer id) {
        int update = userService.userUpdatePhone(id);
        return setResultDb(update);
    }
    //用户新增购买数据
    @PostMapping("/userinsertbuyphone")
    @ApiOperation(value = "用户购买数据新增",notes = "用户数据新增")
    public BaseResponse userInsertBuyPhone(@RequestBody Byphone byphone){
        int a = userService.userINsertBuyPhone(byphone);
        return setResultDb(a);
    }

    //    //用户查看已购买商品
    @GetMapping("/userselectbyid/{userid}")
    @ApiOperation(value = "查看购买商品", notes = "查看购买商品")
    public BaseResponse<List<Byphone>> userSelectBuyId(@PathVariable("userid") Integer userid) {
        List<Byphone> byphoneList = userService.userSelectPhoneBYId(userid);
        return setResultSuccessData(byphoneList);

    }
    //用户退货
    @PutMapping("/userDeletePhone/{id}")
    @ApiOperation(value = "用户退货",notes = "用户退货")
    public BaseResponse userDeletePhone(@PathVariable("id") Integer id){
        int a = userService.userDeletePhone(id);
        return setResultDb(a);
    }
}
