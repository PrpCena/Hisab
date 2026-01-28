package com.prp.Hisab.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transactions")
public class TransactionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
  @Column(name = "description", nullable = false)
  private String description;
  @Column(name = "amount", nullable = false, precision = 19, scale = 4)
  private BigDecimal amount;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "account", nullable = false)
  private AccountEntity account;
  @Column(name = "date", nullable = false)
  private LocalDate date;
  @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TransactionAllocationEntity> transactionAllocations;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	TransactionEntity that = (TransactionEntity) o;
	return Objects.equals(id, that.id);
  }
  
  @Override
  public int hashCode() {
	return getClass().hashCode();
  }
}
