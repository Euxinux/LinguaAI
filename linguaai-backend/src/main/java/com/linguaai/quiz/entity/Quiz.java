package com.linguaai.quiz.entity;

import com.linguaai.language.entity.Language;
import com.linguaai.quiz.enums.Difficulty;
import com.linguaai.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_code", referencedColumnName = "code", nullable = false)
    private Language language;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Difficulty difficulty;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ColumnDefault("false")
    private boolean aiGenerated;
}