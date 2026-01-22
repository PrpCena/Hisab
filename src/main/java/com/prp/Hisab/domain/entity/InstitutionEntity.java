package com.prp.Hisab.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "institutions")
public class InstitutionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
  @Column(name = "name", nullable = false)
  private String name;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "createdBy", nullable = false, updatable = false)
  private UserEntity createdBy;
  @CreatedDate
  @Column(name = "created", nullable = false, updatable = false)
  private Instant created;
  @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AccountEntity> accounts;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	InstitutionEntity that = (InstitutionEntity) o;
	return Objects.equals(id, that.id);
  }
  
  @Override
  public int hashCode() {
	return getClass().hashCode();
  }
}
