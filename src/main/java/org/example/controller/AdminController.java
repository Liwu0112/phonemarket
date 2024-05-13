package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.PhoneDto;
import org.example.entity.Administrator;
import org.example.entity.Phone;
import org.example.service.AdminService;
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
@Api(tags = "管理员")
@RequestMapping("/admin")
public class AdminController extends BaseApiService {
    @Autowired
    private AdminService adminService;

    //管理员登录
    @PostMapping("/adminLogin")
    @ApiOperation(value = "管理员登录", notes = "管理员登录")
    public BaseResponse adminLogin(@RequestBody Administrator administrator) {
        Integer a =administrator.getAccount();
        String b =administrator.getPassword();
        if (a == null || b == null){
            return setResultError("adminInformations is null");
        }
        Integer account = administrator.getAccount();
        String password = administrator.getPassword();
        Administrator adminLogin = adminService.adminLogin(account, password);
        if (adminLogin == null){
            return setResultError("账号或密码错误");
        }
        return setResultSuccessData(adminLogin);
    }

    //管理员查看所有手机信息
    @GetMapping("/selectAll")
    @ApiOperation(value = "查看所有手机信息", notes = "查看所有手机信息")
    public BaseResponse<List<Phone>> adminSelectAll() {
        List<Phone> phoneList = adminService.adminSelectall();
        return setResultSuccessData(phoneList);
    }

    //管理员根据编号查看手机信息
    @GetMapping("/selectById/{id}")
    @ApiOperation(value = "根据id获取手机信息", notes = "根据id获取手机信息")
    public BaseResponse adminSelectById(@PathVariable("id") Integer id) {
        Phone phone = adminService.adminSelectById(id);
        PhoneDto phoneDto = BeanUtils.doToDto(phone, PhoneDto.class);
        return setResultSuccessData(phoneDto);
    }


    //管理员增加手机信息
    @PostMapping("/insertPhone")
    @ApiOperation(value = "新增手机信息", notes = "新增手机信息")
    public BaseResponse adminInsertPhone(@RequestBody Phone phone) {
        int a = adminService.adminInsertPhone(phone);
        return setResultDb(a);
    }

    //管理员根据编号修改手机信息
    @PutMapping("/updatePhone")
    @ApiOperation(value = "修改手机信息", notes = "修改手机信息")
    public BaseResponse adminUpdatePhoneById(@RequestBody Phone phone) {
        int result = adminService.adminUpdatePhoneById(phone);
        return setResultDb(result);
    }

    //管理员根据编号彻底删除手机信息
    @PutMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除手机信息", notes = "根据id删除手机信息")
    public BaseResponse adminDeleteById(@PathVariable("id") Integer id) {
        int a = adminService.adminDeleteById(id);
        return setResultDb(a);
    }

}
