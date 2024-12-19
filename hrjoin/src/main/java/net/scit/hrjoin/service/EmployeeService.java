package net.scit.hrjoin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.scit.hrjoin.dto.EmployeeDTO;
import net.scit.hrjoin.entity.EmployeeEntity;
import net.scit.hrjoin.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository repository;
	
	/* Entity타입을 반환하면 안 됨
	 * DTO로 반환해야 함. 지금은 연습이니까
	 */
	public List<EmployeeDTO> selectAll() {
		List<EmployeeEntity> temp = repository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
		
		List<EmployeeDTO> list = new ArrayList<>();
		
//		String fname = temp.get(58).getFirstName();
//		System.out.println("이름: " + fname);
		
		//String dname = temp.get(58).getDepartment().getDepartmentName();
		//System.out.println("부서명: " + dname);
		
		temp.forEach((entity) -> list.add(EmployeeDTO.toDTO(entity)));
		return list;
	}
}
