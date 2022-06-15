package com.example.awshomework.web;

import com.example.awshomework.config.auth.LoginUser;
import com.example.awshomework.config.auth.dto.SessionUser;
import com.example.awshomework.service.posts.PostsService;
import com.example.awshomework.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        // model
        // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
        // posts 라는 이름으로 index.mustache 에 전달
        model.addAttribute("posts",postsService.findAllDesc());

        if(user != null){
            model.addAttribute("userName",user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
