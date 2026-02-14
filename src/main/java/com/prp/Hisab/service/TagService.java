package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateTagRequest;
import com.prp.Hisab.domain.dto.response.TagResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface TagService {
  TagResponse createTag(@Valid CreateTagRequest request);

  List<TagResponse> listTags();

  void deleteTag(UUID tagId);
}
