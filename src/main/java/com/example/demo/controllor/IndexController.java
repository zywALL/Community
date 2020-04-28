package com.example.demo.controllor;

import com.example.demo.dto.PagenationDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.QuestionPublishService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionPublishService questionPublishService;


    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "3") Integer size,
                        @RequestParam(name = "pagePublish", defaultValue = "1") Integer pagePublish,
                        @RequestParam(name = "sizePublish", defaultValue = "3") Integer sizePublish){
        if(request.getCookies() != null){
            Cookie[] cookies = request.getCookies();
            if (cookies.length == 0)return "index";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        PagenationDTO pagenationDTO = questionService.list(page, size);
        model.addAttribute("pagenation", pagenationDTO);

        PagenationDTO pagenationPublishDTO = questionPublishService.list(pagePublish, sizePublish);
        model.addAttribute("pagenationPublish", pagenationPublishDTO);


        return "index";
    }
}
