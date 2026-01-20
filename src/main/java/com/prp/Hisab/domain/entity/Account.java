package com.prp.Hisab.domain.entity;

import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.domain.enums.AccountType;
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
  @JoinColumn(name = "institution", nullable = false)
  private Institution institution;
  @CreatedDate
  @Column(name = "created", nullable = false, updatable = false)
  private Instant created;
  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Transaction> transactions;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	Account account = (Account) o;
	return Objects.equals(id, account.id);
  }
  
  @Override
  public int hashCode() {
	return getClass().hashCode();
  }
}
