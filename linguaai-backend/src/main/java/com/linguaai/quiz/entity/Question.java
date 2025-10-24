package com.linguaai.quiz.entity;

import com.linguaai.common.converter.JsonConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
@Builder
@ToString(exclude = "quiz")
@EqualsAndHashCode(of = "id")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    @Column(nullable = false)
    private String questionText;
    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private List<String> options;
    @Column(nullable = false, length = 5)
    private String correctOption;
    @CreationTimestamp
    private LocalDateTime createdAt;
}