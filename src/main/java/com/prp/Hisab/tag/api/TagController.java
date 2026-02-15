package com.prp.Hisab.tag.api;

import com.prp.Hisab.tag.application.TagService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/tags")
public class TagController {

  private final TagService tagService;

  @PostMapping
  ResponseEntity<TagResponse> createTag(@Valid @RequestBody CreateTagRequest request) {
    TagResponse response = tagService.createTag(request);

    return ResponseEntity.ok().body(response);
  }

  @GetMapping
  ResponseEntity<List<TagResponse>> listTags() {
    List<TagResponse> response = tagService.listTags();

    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("/{tagId}")
  ResponseEntity<Void> deleteTag(@PathVariable UUID tagId) {
    tagService.deleteTag(tagId);

    return ResponseEntity.ok().build();
  }
}
