package com.linguaai.language.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "languages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "code")
public class Language {
    @Id
    @Column(length = 5, nullable = false)
    @Pattern(regexp = "^[A-Z]{2}$", message = "Code must be 2 uppercase letters")
    private String code;
    @Column(nullable = false, length = 50)
    private String name;
}