package com.prp.Hisab.controller;

import com.prp.Hisab.domain.dto.request.CreateTagRequest;
import com.prp.Hisab.domain.dto.response.CreateTagResponse;
import com.prp.Hisab.domain.dto.response.ListTagResponse;
import com.prp.Hisab.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/tags")
public class TagController {

  private final TagService tagService;

  @PostMapping
  ResponseEntity<CreateTagResponse> createTag(@Valid @RequestBody CreateTagRequest request) {
    CreateTagResponse response = tagService.createTag(request);

    return ResponseEntity.ok().body(response);
  }
  
  @GetMapping
  ResponseEntity<ListTagResponse> listTags() {
    ListTagResponse response = tagService.listTags();
    
    return ResponseEntity.ok().body(response);
    
  }
}
