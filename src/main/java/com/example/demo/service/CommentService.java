package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.internal.ParameterizedTypeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("Fail to create comment. There is no such article!"));

        Comment comment = Comment.create(article, dto);

        Comment created = commentRepository.save(comment);

        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 1. 해당 comment(target) 불러오기
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Fail to update comment. There is no such comment!"));

        // 2. target.patch(dto.entity)
        target.patch(dto);
        // 3. 수정된 target 다시 저장하기
        Comment updated = commentRepository.save(target);
        // 4. target -> dto and return
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        // 1. id로 comment 불러오기
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fail to delete comment. There is no such comment!"));

        // 2. 해당 comment delete
        commentRepository.delete(target);

        return CommentDto.createCommentDto(target);
    }
}
