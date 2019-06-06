package com.jie.controller;

import com.google.code.kaptcha.Constants;
import com.jie.pojo.Page;
import com.jie.pojo.Users;
import com.jie.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 * @date 2019/5/31 -21:45
 */
@RequestMapping("/info")
@Controller
public class UserController {
    @Resource
    private UsersService usersService;


    @PostMapping("/login")
    @ResponseBody
    public String login(String name, String password,String code, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        String oldCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String message = null;
        Users login = usersService.login(map);
        if (!code.equals(oldCode)){
            message="验证码错误";
        }else if (login==null){
           message = "用户名不存在";
        }else if (!login.getPassword().equals(password)){
            message = "密码错误";
        }else if (login.getStatus()==0){
            message = "账号冻结";
        }else {
            request.getSession().setAttribute("user",login);
            message = "成功";
        }
        request.setAttribute("name", name);
        request.setAttribute("password", password);
        return message;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1")int pageNo, @RequestParam(defaultValue = "") String name,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("pageNo",(pageNo-1)*2);
        map.put("name",name);
        map.put("pageSize",2);
        int count = usersService.getCount(map);
        List<Users> usersByPage = usersService.getUsersByPage(map);
        Page<Users> page = new Page<Users>(pageNo,2 , count);
        page.setList(usersByPage);
        request.setAttribute("page",page);
        request.setAttribute("pageNo",pageNo);
        request.setAttribute("name",name);
        return "list";
    }


    @PostMapping("/modify")
    @ResponseBody
    public String modify(String password,int id){
        boolean flag = usersService.updatePwd(password, id);
        if (flag){
          return "ok";
        }else {
            return "false";
        }

    }
}
