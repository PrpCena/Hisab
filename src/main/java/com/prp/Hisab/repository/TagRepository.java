package com.prp.Hisab.repository;

import com.prp.Hisab.domain.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository
        extends JpaRepository<TagEntity, UUID> {
}
