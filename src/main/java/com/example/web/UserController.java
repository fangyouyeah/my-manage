package com.example.web;

import com.example.param.UserParam;
import com.example.pojo.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "redirect:list";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value ="page" ,defaultValue = "1") Integer page, @RequestParam(value ="size" ,defaultValue = "2")Integer size){
        PageHelper.startPage(page, size,true);

        List<User> userList =  userService.findList();
        PageInfo<User> p = new PageInfo<User>(userList);

        System.out.println("总数量：" + p.getTotal());
        System.out.println("当前页查询记录：" + p.getList().size());
        System.out.println("当前页码：" + p.getPageNum());
        System.out.println("每页显示数量：" + p.getPageSize());
        System.out.println("总页：" + p.getPages());
        PageHelper.startPage(2,3);
        model.addAttribute("total",p.getTotal());
        model.addAttribute("pages",p.getPages());
        model.addAttribute("num",p.getPageNum());
        model.addAttribute("users", userList);
        return "user/list";
    }

    @GetMapping("/toAdd")
    public String toAdd(){
        return "user/userAdd";
    }

    @GetMapping("/footer")
    public String toFooter(){
        return "user/footer";
    }

    @GetMapping("/toDel")
    public String toDel(@RequestParam("id")Integer[] userId,Model model){
        int res = userService.deleteUser(userId);
        if (res>0){
            model.addAttribute("res","删除用户成功!");
        }else {
            model.addAttribute("res","操作失败!!");
        }
        return "redirect:list";
    }

    @GetMapping(value = "/toEdit")
    public String toEdit(@RequestParam("id") Integer userId, Model model){

        User u = userService.queryById(userId);
        UserParam userParam = new UserParam();
        BeanUtils.copyProperties(u,userParam);
        model.addAttribute("userParam",userParam);
        return "user/userEdit";
    }

    @RequestMapping(value = "/update")
    public String updateUser(@RequestParam("id") Integer userId,@RequestParam("password")String password,@RequestParam("phone")String phone,@RequestParam("username")String username, Model model){
        User user = new User();
        System.out.println("userId = " + userId);
        System.out.println("username = " + username);
        System.out.println("phone = " + phone);
        user.setId(userId);
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);
        int res = userService.updateUser(user);
        if (res>0){
            model.addAttribute("res","编辑用户成功!");
        }else {
            model.addAttribute("res","操作失败!!");
        }
        return "redirect:list";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(UserParam userParam, BindingResult result, ModelMap modelMap){

        String errorMsg = "";
        if(result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            System.out.println("userParam = " + userParam);
            for(ObjectError e: errors){
                errorMsg = errorMsg + e.getCode()+":"+e.getDefaultMessage();
            }
            modelMap.addAttribute("errorMsg",errorMsg);
            return "user/userAdd";
        }
//        User user = userService.findUserByName(userParam.getUsername());
//        if(user!=null){
//            System.out.println("userParam = " + userParam);
//            modelMap.addAttribute("errorMssg","用户名已存在!");
//            return "user/userAdd";
//        }
        User u = new User();
        BeanUtils.copyProperties(userParam,u);
        u.setCreated(new Date());
        userService.insertUser(u);


        return "redirect:list";
    }


}
