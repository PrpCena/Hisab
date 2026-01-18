package com.prp.Hisab.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
  @Column(name = "description", nullable = false)
  private String description;
  @Column(name = "amount", nullable = false, precision = 19, scale = 4)
  private BigDecimal amount;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;
  @Column(name = "date", nullable = false)
  private LocalDate date;
  
  @Override
  public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass())
	  return false;
	Transaction that = (Transaction) o;
	return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(
	  amount, that.amount) && Objects.equals(date, that.date);
  }
  
  @Override
  public int hashCode() {
	return Objects.hash(id, description, amount, date);
  }
}
