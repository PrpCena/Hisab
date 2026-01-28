package com.prp.Hisab.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
  @Column(name = "key_cloack_id", updatable = false, nullable = false)
  private String keyCloackId;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "email", nullable = false)
  private String email;
  @CreatedDate
  @Column(name = "created", nullable = false, updatable = false)
  private Instant created;
  @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<InstitutionEntity> institutions;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	UserEntity user = (UserEntity) o;
	return Objects.equals(id, user.id) && Objects.equals(keyCloackId, user.keyCloackId);
  }
  
  @Override
  public int hashCode() {
	return getClass().hashCode();
  }
}
