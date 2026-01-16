package com.prp.Hisab.domain.entity;

import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.domain.enums.AccountType;
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
@Table(name = "accounts")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
  @Enumerated(EnumType.STRING)
  @Column(name = "type", updatable = false, nullable = false)
  private AccountType type;
  @Enumerated(EnumType.STRING)
  @Column(name = "status", updatable = false, nullable = false)
  private AccountStatus status;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "institution_id", nullable = false)
  private Institution institution;
  @CreatedDate
  @Column(name = "created", nullable = false, updatable = false)
  private Instant created;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	Account account = (Account) o;
	return Objects.equals(id, account.id) && type == account.type && status == account.status && Objects.equals(
	  created, account.created);
  }
  
  @Override
  public int hashCode() {
	return Objects.hash(id, type, status, created);
  }
  
}
