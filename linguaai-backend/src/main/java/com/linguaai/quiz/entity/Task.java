package com.linguaai.quiz.entity;

import com.linguaai.common.converter.JsonConverter;
import com.linguaai.quiz.enums.TaskType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "quiz")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskType type;
    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, Object> content;
    @CreationTimestamp
    private LocalDateTime createdAt;
}