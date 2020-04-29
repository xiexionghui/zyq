package com.ubo.zyq.controller;

import com.ubo.zyq.entity.User;
import com.ubo.zyq.service.UserService;
import com.ubo.zyq.util.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/managerLogin")
    public String login() {
        return "login";
    }

    /**
     * 登录校验
     */
    @PostMapping("/loginVerification")
    public String login(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.loadByUsername(username);
        if (user == null) {
            model.addAttribute("success",false);
            model.addAttribute("msg", "查无此用户！");
            return "login";
        }
        boolean isSuccess = SecretUtil.verify(password, user.getPassword());
        if (!isSuccess) {
            model.addAttribute("success",false);
            model.addAttribute("msg", "用户名或密码错误！");
            return "login";
        }

        // 校验通过时，在session里放入一个标识
        // 后续通过session里是否存在该标识来判断用户是否登录
        request.getSession().setAttribute("loginName", "admin");
        return "redirect:/managerIndex";
    }

    @GetMapping("/managerIndex")
    public String managerIndex() {
        return "admin/admin";
    }


    /**
     * 注销登录
     */
    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/managerLogin";
    }

}

