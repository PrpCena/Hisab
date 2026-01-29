package com.prp.Hisab.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transaction_allocations")
public class TransactionAllocationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "transaction", nullable = false, updatable = false)
  private TransactionEntity transaction;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "tag", nullable = false)
  private TagEntity tag;
  @Column(nullable = false, precision = 19, scale = 4)
  private BigDecimal amount;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	TransactionAllocationEntity that = (TransactionAllocationEntity) o;
	return Objects.equals(id, that.id);
  }
  
  @Override
  public int hashCode() {
	return getClass().hashCode();
  }
}
