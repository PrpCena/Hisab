package com.prp.Hisab.account.infrastructure;

import com.prp.Hisab.account.domain.AccountStatus;
import com.prp.Hisab.account.domain.AccountType;
import com.prp.Hisab.institution.infrastructure.InstitutionEntity;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "accounts")
public class AccountEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private AccountType type;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private AccountStatus status;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "institution", nullable = false)
  private InstitutionEntity institution;

  @CreatedDate
  @Column(name = "created", nullable = false, updatable = false)
  private Instant created;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    AccountEntity account = (AccountEntity) o;
    return Objects.equals(id, account.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
