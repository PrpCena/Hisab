package com.prp.Hisab.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tags")
public class Tag {
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
  private Set<Tag> parents;
  @ManyToMany(mappedBy = "parents", fetch = FetchType.LAZY)
  private Set<Tag> children;
  
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	Tag tag = (Tag) o;
	return Objects.equals(id, tag.id) && Objects.equals(name, tag.name) && Objects.equals(created, tag.created);
  }
  
  @Override
  public int hashCode() {
	return Objects.hash(id, name, created);
  }
  
  public void addChild(Tag child) {
	if(child != this){
	  this.children.add(child);
	  child
		.getParents()
		.add(this);
	}
  }
  
  public void removeChild(Tag child) {
	if(child != this){
	  this.children.remove(child);
	  child
		.getParents()
		.remove(this);
	}
  }
}
