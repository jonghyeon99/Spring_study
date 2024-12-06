package net.scit.cashbook.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.cashbook.dto.CashbookDTO;
import net.scit.cashbook.entity.CashbookEntity;
import net.scit.cashbook.repository.CashbookRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashbookService {
	
	private final CashbookRepository repository;
	
	public void insert(CashbookDTO cashbookDTO) {
		log.info("--------------{}", cashbookDTO.toString());
		
		CashbookEntity cashbookEntity = CashbookEntity.toEntity(cashbookDTO);
		
		repository.save(cashbookEntity);
	}

	public List<CashbookDTO> selectAll() {
		List<CashbookEntity> temp = repository.findAll(Sort.by(Sort.Direction.ASC, "regdate"));
		List<CashbookDTO> list = new ArrayList<>();
		
		for(CashbookEntity entity : temp) {
			CashbookDTO dto = CashbookDTO.toDTO(entity);
			
			list.add(dto);
		}
		return list;
	}
	
	public void delete(Long cash_seq) {
		repository.deleteById(cash_seq);
	}
}
