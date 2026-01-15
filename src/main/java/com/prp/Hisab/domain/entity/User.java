package com.prp.Hisab.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
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
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	User user = (User) o;
	return Objects.equals(id, user.id) && Objects.equals(keyCloackId, user.keyCloackId) && Objects.equals(
	  name, user.name) && Objects.equals(email, user.email) && Objects.equals(created, user.created);
  }
  
  @Override
  public int hashCode() {
	return Objects.hash(id, keyCloackId, name, email, created);
  }
}
