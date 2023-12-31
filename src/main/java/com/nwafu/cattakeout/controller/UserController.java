package com.nwafu.cattakeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nwafu.cattakeout.common.Result;
import com.nwafu.cattakeout.pojo.User;
import com.nwafu.cattakeout.service.IUserService;
import com.nwafu.cattakeout.util.ValidateCodeUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;


    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody User user, HttpSession session){
        String phone = user.getPhone();

        if(phone!=null&&!phone.isEmpty()){
            String code = ValidateCodeUtil.generateValidateCode(4).toString();
            log.info("code={}",code);

            session.setAttribute(phone,code);

            return Result.success("手机验证码短信发送成功");
        }
        return Result.error("手机验证码短信发送失败");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map map, HttpSession session){
        log.info(map.toString());

        //获取手机号
        String phone = map.get("phone").toString();

        //获取验证码
        String code = map.get("code").toString();

        //从Session中获取保存的验证码
        Object codeInSession = session.getAttribute(phone);

        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if(codeInSession != null && codeInSession.equals(code)){
            //如果能够比对成功，说明登录成功

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);

            User user = userService.getOne(queryWrapper);
            if(user == null){
                //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return Result.success(user);
        }
        return Result.error("登录失败");
    }
}
