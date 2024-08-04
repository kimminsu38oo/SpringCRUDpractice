package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId(){
        /* Case 1 : 3번 게시글의 모든 댓글 조회 */
        {
            //given
            Long articleId = 3L;
            Article article = new Article(3L, "what is your favorite color?", "comment gogo");
            Comment comment1 = new Comment(article, "Park", "Red");
            Comment comment2 = new Comment(article, "Kim", "Yellow");
            List<Comment> expected = Arrays.asList(comment1, comment2);
            //when
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //then
            assertEquals(expected.toString(), comments.toString());
        }
        /* Case 2 : 1번 게시글의 모든 댓글 조회 */
        {
            //given
            Long articleId = 1L;
            Article article = new Article(1L, "article1", "1111");
            //when
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            List<Object> expected = Arrays.asList();
            //then
            assertEquals(expected.toString(), comments.toString(),"1번은 댓글이 없음");
        }
    }


//    @Test
//    @DisplayName("특정 닉네임의 모든 댓글 조회")
//    void findByNickname() {
//        //given
//        String nickname = "Park";
//
//        //when
//        List<Comment> comments = commentRepository.findByNickname(nickname);
//
//        //then
//
//    }
}