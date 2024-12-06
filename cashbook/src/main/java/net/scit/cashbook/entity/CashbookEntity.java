package net.scit.cashbook.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.cashbook.dto.CashbookDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="cashbook_info")
public class CashbookEntity {
	@Id
	@Column(name="cash_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cash_seq;
	
	@Column(name="io_type")
	private String io_type;
	
	@Column(name="memo")
	private String memo;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="regdate")
	@CreationTimestamp
	private LocalDate regdate;
	
	
	// DTO를 Entity로 변환하는 static 메소드 toEntity()
	public static CashbookEntity toEntity(CashbookDTO cashbookDTO) {
		return CashbookEntity.builder()
				.cash_seq(cashbookDTO.getCash_seq())
				.io_type(cashbookDTO.getIo_type())
				.memo(cashbookDTO.getMemo())
				.amount(cashbookDTO.getAmount())
				.regdate(cashbookDTO.getRegdate())
				.build();
	}
}
