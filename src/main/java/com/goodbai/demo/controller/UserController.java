package com.goodbai.demo.controller;

import com.goodbai.demo.model.User;
import com.goodbai.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author: huiqi
 * @CreateTime: 2019-10-31 18:10
 */
//用户信息修改
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String Sender;

    @GetMapping("user/login")
    //跳转到登录页面
    public String loginGet(Model model) {
        return "login";
    }

    //验证登录
    @PostMapping("user/login")
    public String loginPost(User user, Model model) {
        User user1 = userService.selectByNameAndPwd(user);
        if (user1 != null) {
            httpSession.setAttribute("user", user1);
            User name = (User) httpSession.getAttribute("user");
            return "redirect:dashboard";
        } else {
            model.addAttribute("error", "账号或密码错误");
            return "login";
        }
    }

    //跳转注册页面
    @GetMapping("user/register")
    public String registerGet(Model model) {
        return "register";
    }

    //注册
    @PostMapping("user/register")
    public String registerPost(User user, Model model) {
        System.out.println("用户名：" + user.getUserName());
        try {
            userService.selectIsName(user);
            model.addAttribute("error", "该用户已存在");
        } catch (Exception e) {
            Date date = new Date();
            user.setAddDate(date);
            user.setUpdateDate(date);
            userService.insert(user);
            System.out.println("注册成功");
            model.addAttribute("error", "注册成功");
            return "login";
        }
        return "register";
    }

    //跳转到忘记页面
    @GetMapping("user/forget")
    public String forgetGet(Model model) {
        return "forget";
    }

    //找回密码（发送邮件）
    @PostMapping("user/forget")
    public String forgetPost(User user, Model model) {
        String password = userService.selectPwdByName(user);
        if (password == null) {
            model.addAttribute("error", "账号不存在");
        } else {
            String email = user.getEmail();//获取邮箱
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(Sender);//发件人邮箱
            message.setTo(email);//收件人邮箱
            message.setSubject("密码找回");//邮件标题
            StringBuffer sbf = new StringBuffer();
            sbf.append(user.getUserName() + "你好：你的密码是" + password);//用sbf来存储邮件内容
            message.setText(sbf.toString());//发送内容
            javaMailSender.send(message);//正式发送邮件
            model.addAttribute("邮件已经发送至你的邮箱，请查收");
        }
        return "forget";
    }

    /*跳转用户修改页面*/
    @GetMapping("user/userManage")
    public String userManage(Model model) {
        User user = (User) httpSession.getAttribute("user"); //获取登录的用户信息
        User user1 = userService.selectByNameAndPwd(user); //获取该用户信息的其他所有信息
        model.addAttribute("user", user1);
        return "user/userManage";
    }

    /*用户修改页面*/
    @PostMapping("user/userManage")
    public String userManagePost(User user, Model model, HttpSession httpSession) {
        Date date = new Date();
        user.setUpdateDate(date); //设置更新日期为最新日期
        int i = userService.update(user);
        httpSession.setAttribute("user", user);
        return "redirect:login";
    }


}
