package com.linguaai.language.service;

import com.linguaai.language.entity.Language;
import com.linguaai.language.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    private final LanguageRepository repository;

    public LanguageService(LanguageRepository repository) {
        this.repository = repository;
    }

    public List<Language> getAllLanguages() {
        return repository.findAll();
    }

    public Optional<Language> findByCode(String code) {
        return repository.findByCode(code.toUpperCase());
    }
}