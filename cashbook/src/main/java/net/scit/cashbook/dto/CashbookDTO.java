package net.scit.cashbook.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.cashbook.entity.CashbookEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CashbookDTO {
	private Long cash_seq;
	private String io_type;
	private String memo;
	private int amount;
	private LocalDate regdate;
	
	public static CashbookDTO toDTO(CashbookEntity cashbookEntity) {
		return CashbookDTO.builder()
			.cash_seq(cashbookEntity.getCash_seq())
			.io_type(cashbookEntity.getIo_type())
			.memo(cashbookEntity.getMemo())
			.amount(cashbookEntity.getAmount())
			.regdate(cashbookEntity.getRegdate())
			.build();
	}
}
