package com.prp.Hisab.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tags")
public class TagEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;
  @Column(name = "name", nullable = false)
  private String name;
  @CreatedDate
  @Column(name = "created")
  private Instant created;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "tag_hierarchy", joinColumns = @JoinColumn(name = "child"), inverseJoinColumns = @JoinColumn(name = "parent"))
  private Set<TagEntity> parents;
  @ManyToMany(mappedBy = "parents", fetch = FetchType.LAZY)
  private Set<TagEntity> children;
  @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TransactionAllocationEntity> transactionAllocations;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	TagEntity tag = (TagEntity) o;
	return Objects.equals(id, tag.id);
  }
  
  @Override
  public int hashCode() {
	return getClass().hashCode();
  }
  
  public void addChild(TagEntity child) {
	if (child != this) {
	  this.children.add(child);
	  child
		.getParents()
		.add(this);
	}
  }
  
  public void removeChild(TagEntity child) {
	if (child != this) {
	  this.children.remove(child);
	  child
		.getParents()
		.remove(this);
	}
  }
}
