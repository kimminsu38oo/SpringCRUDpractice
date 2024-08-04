package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleServiceTest {


    @Autowired ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;

    @BeforeEach
    void init() {
        articleRepository.deleteAll();
        articleRepository.save(new Article(1L, "article1", "1111"));
        articleRepository.save(new Article(1L, "article2", "2222"));
    }

    @Test
    void index() {
        //given
        Article a = new Article(1L, "article1", "1111");
        Article b = new Article(2L, "article2", "2222");
        ArrayList<Article> expected = new ArrayList<>(Arrays.asList(a, b));

        //when
        List<Article> articles = articleService.index();
        //then
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_성공_존재하는_id_입력() {
        //given
        Long id = 1L;
        Article expected = new Article(1L, "article1", "1111");
        //when
        Article article = articleService.show(id);
        //then
        assertEquals(expected.getTitle(), article.getTitle());
        assertEquals(expected.getContent(), article.getContent());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        //given
        Long id = -1L;
        Article expected = null;

        //when
        Article article = articleService.show(id);

        //then
        assertEquals(expected, article);
    }

    @Test
    void create_성공_title과_content만_있는_dto_입력() {
        //given
        String title = "article3";
        String content = "3333";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, "article3", "3333");
        //when
        Article article = articleService.create(dto);

        //then
        assertEquals(expected.getTitle(), article.getTitle());
        assertEquals(expected.getContent(), article.getContent());

    }

    @Test
    void create_실패_id가_포함된_dto_입력() {
        //given
        Long id = 3L;
        String title = "article3";
        String content = "3333";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        //when
        Article article = articleService.create(dto);

        //then
        assertEquals(expected, article);
    }

    @Test
    void update_성공_존재하는_id와_title_content_가_있는_dto_입력() {
        //given
        Long id = 1L;
        String title = "new title";
        String content = "new Content";
        ArticleForm dto = new ArticleForm(id, title, content);

        Article expected = new Article(1L, title, content);

        //when
        Article updated = articleService.update(id, dto);
        //then
        assertEquals(expected.getTitle(), updated.getTitle());
        assertEquals(expected.getContent(), updated.getContent());
    }

    @Test
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        //given
        Long id = 1L;
        String title = "new title";
        ArticleForm dto = new ArticleForm(id, title, null);

        Article expected = new Article(1L, title, "1111");
        //when
        Article updated = articleService.update(id, dto);

        //then
        assertEquals(expected.getTitle(), updated.getTitle());
        assertEquals(expected.getContent(), updated.getContent());

    }

    @Test
    void update_실패_존재하지_않는_id의_dto_입력() {
        //given
        Long id = -1L;
        String title = "new title";
        String content = "new Content";
        ArticleForm dto = new ArticleForm(id, title, content);

        Article expected = null;
        //when
        Article updated = articleService.update(id, dto);

        //then
        assertEquals(expected, updated);
    }

    @Test
    void delete_성공_존재하는_id_입력() {
        //given
        Long id = 1L;
        Article expected = new Article(id, "article1", "1111");

        //when
        Article deleted = articleService.delete(id);
        //then
        assertEquals(expected.toString(), deleted.toString());
    }

    @Test
    void delete_실패_존재하지_않는_id_입력() {
        //given
        Long id = -1L;
        Article expected = null;
        //when
        Article deleted = articleService.delete(id);

        //then
        assertEquals(expected, deleted);
    }
}