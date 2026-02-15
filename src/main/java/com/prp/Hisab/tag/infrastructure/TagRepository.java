package com.prp.Hisab.tag.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {

  List<ListTagProjection> findAllByCreatedById(UUID userId);

  Optional<TagEntity> findByIdAndCreatedById(UUID tagId, UUID id);
}
