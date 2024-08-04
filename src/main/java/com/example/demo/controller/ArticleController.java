package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final CommentService commentService;

    @GetMapping("articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());

        //dto를 엔티티로 변환
        Article article = form.toEntity();

        //repository에 저장
        Article save = articleRepository.save(article);
        log.info(save.toString());

        return "redirect:/articles/" + save.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        //id 를 조회해 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        //모델에 데이터 등록하기
        model.addAttribute("article", article);
        model.addAttribute("commentDtos", commentDtos);
        //뷰 페이지 반환하기

        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //repository에서 모든 객체 불러오기
        ArrayList<Article> articles = articleRepository.findAll();
        //Model에 add하기
        model.addAttribute("articles", articles);

        //view return
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", article);

        return "articles/edit";
    }

    @PostMapping("articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        Article updatedArticle = form.toEntity();
        Article oldArticle = articleRepository.findById(updatedArticle.getId()).orElse(null);

        if (oldArticle != null) {
            articleRepository.save(updatedArticle);
        }
        return "redirect:/articles/" + updatedArticle.getId();
    }

    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.info("delete request!");

        Article article = articleRepository.findById(id).orElse(null);

        if (article != null) {
            articleRepository.delete(article);
            redirectAttributes.addFlashAttribute("msg", "deleted!");
        }

        return "redirect:/articles";
    }
}
