package com.example.demo.entity;

import com.example.demo.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    private String nickname;

    private String body;

    public Comment(Article article, String nickname, String body) {
        this.article = article;
        this.nickname = nickname;
        this.body = body;
    }

    public static Comment create(Article article, CommentDto dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Fail to create comment. Comment should not have an id!");
        }
        if (!article.getId().equals(dto.getArticleId())) {
            throw new IllegalArgumentException("Fail to create comment. Article id was wrong!");
        }
        return new Comment(
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto comment) {
        if (!comment.getId().equals(id)) {
            throw new IllegalArgumentException("Fail to update comment. Invalid Id");
        }

        if (comment.getNickname() != null) {
            nickname = comment.getNickname();
        }
        if (comment.getBody() != null) {
            body = comment.getBody();
        }
    }
}
