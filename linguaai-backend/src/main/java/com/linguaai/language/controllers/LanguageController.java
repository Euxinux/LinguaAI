package com.linguaai.language.controllers;

import com.linguaai.language.entity.Language;
import com.linguaai.language.exception.LanguageNotFoundException;
import com.linguaai.language.service.LanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("${api.url}/languages")
public class LanguageController {
    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages() {
        log.info("Request received: GET /languages - Fetching all languages");
        List<Language> languageList = service.getAllLanguages();
        log.debug("Number of languages found: {}", languageList.size());
        return ResponseEntity.ok(languageList);
    }

    @GetMapping("/codes/{code}")
    public ResponseEntity<Language> getLanguageByCode(@PathVariable("code") String code) {
        log.info("Request received: GET /languages/codes/{} - Fetching language by code", code);
        Language language = service.findByCode(code)
                .orElseThrow(() -> new LanguageNotFoundException("CODE: " + code));
        log.debug("Language found: code={}, name={}", language.getCode(), language.getName());
        return ResponseEntity.ok(language);
    }
}