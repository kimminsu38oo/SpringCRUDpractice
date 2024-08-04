package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@ToString @AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if (article.getTitle() != null) {
            title = article.getTitle();
        }
        if (article.getContent() != null) {
            content = article.getContent();
        }
    }
}

