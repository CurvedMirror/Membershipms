package com.jie.controller;

import com.jie.pojo.Page;
import com.jie.pojo.Users;
import com.jie.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
public class UserController {
    @Resource
    private UsersService usersService;


    @GetMapping("login.html")
    public String tologin(){
        return "login";
    }

    @PostMapping("login")
    @ResponseBody
    public String login(String name, String password, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        String message = null;
        Users login = usersService.login(map);
        if (login==null){
           message = "用户名不存在sfs";
        }else if (!login.getPassword().equals(password)){
            message = "密码错误";
        }else if (login.getStatus()==0){
            message = "账号冻结";
        }else {
            request.getSession().setAttribute("user",login);
            message = "成功";
        }
        return message;
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("list")
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

    @GetMapping("toModify")
    public String toModify(){
        return "update";
    }
    @PostMapping("modify")
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
