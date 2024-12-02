package net.scit.test2.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StockDTO {
	private LocalDate creationDate;
	private String productName;
	private Boolean itemType;
	private Integer unitPrice;
	private Integer count;
	private String details;
}
