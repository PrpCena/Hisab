package com.prp.Hisab.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "institutions")
public class Institution {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
  @Column(name = "name", nullable = false)
  private String name;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User createdBy;
  @CreatedDate
  @Column(name = "created", nullable = false, updatable = false)
  private Instant created;
  @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Account> accounts;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	Institution that = (Institution) o;
	return Objects.equals(id, that.id);
  }
  
  @Override
  public int hashCode() {
	return getClass().hashCode();
  }
}
