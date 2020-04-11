package com.example.demo.controllor;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class OAuthController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           HttpServletResponse response) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        try{
            String accessToken = githubProvider.getAccessToken(accessTokenDTO);
            GithubUser githubUser = githubProvider.getUser(accessToken);
            if(githubUser != null){
                User user = new User();
                user.setAccount_id(String.valueOf(githubUser.getId()));
                user.setName(githubUser.getLogin());
                String token = UUID.randomUUID().toString();
                user.setToken(token);
                user.setGmt_create(System.currentTimeMillis());
                user.setGmt_modified(user.getGmt_create());
                userMapper.insert(user);
                response.addCookie(new Cookie("token", token));
            }
        }
        catch (IOException e){
            return "error";
        }
        return "redirect:/";
//        return "index";
    }
}
