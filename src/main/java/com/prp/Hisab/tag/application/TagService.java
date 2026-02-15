package com.prp.Hisab.tag.application;

import com.prp.Hisab.tag.api.CreateTagRequest;
import com.prp.Hisab.tag.api.TagResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface TagService {
  TagResponse createTag(@Valid CreateTagRequest request);

  List<TagResponse> listTags();

  void deleteTag(UUID tagId);
}
