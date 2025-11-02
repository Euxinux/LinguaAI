package com.linguaai.language.repository;

import com.linguaai.language.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {
    Optional<Language> findByCode(String code);
}