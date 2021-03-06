package com.yonamz.aucsusu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final HttpSession httpSession;
    private final UserService userService;

    @PostMapping(value = "/login")
    public String login(HttpServletRequest req, Model model, String uid, String password, RedirectAttributes rttr){
        HttpSession session = req.getSession();

        User user = userService.login(uid,password);

        if(user == null){
            session.setAttribute("user",null);
            rttr.addFlashAttribute("error",true);
            System.out.println("로그인 실패");
            return "redirect:/login";
        }else{
            model.addAttribute("user",user);
            session.setAttribute("user",user);
            System.out.println("로그인 성공");
            return "redirect:/";
        }
    }

    @GetMapping(value = "/modify")
    public String modify(Model model){
        User user=(User) httpSession.getAttribute("user");
        model.addAttribute(user);
        return "update-userInfo";
    }



    @PostMapping(value = "/report")
    public String report(String uid){
        System.out.println(uid);
        userService.userReport(uid);
        return "redirect:/";
    }
}
