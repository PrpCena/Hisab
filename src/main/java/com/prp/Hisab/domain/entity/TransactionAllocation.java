package com.prp.Hisab.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transcatoion_allocations")
public class TransactionAllocation {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "transaction", nullable = false)
  private Transaction transaction;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "tag", nullable = false)
  private Tag tag;
  
  @Column(nullable = false, precision = 19, scale = 4)
  private BigDecimal amount;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	TransactionAllocation that = (TransactionAllocation) o;
	return Objects.equals(id, that.id);
  }
  
  @Override
  public int hashCode() {
	return getClass().hashCode();
  }
}
