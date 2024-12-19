package net.scit.hrjoin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.hrjoin.entity.EmployeeEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EmployeeDTO {

	private Long employeeId;
	private String firstName;
	private String lastName;
	private Double salary;
	private String departmentName;
	
	public static EmployeeDTO toDTO(EmployeeEntity entity) {
		return EmployeeDTO.builder()
				.employeeId(entity.getEmployeeId())
				.firstName(entity.getFirstName())
				.lastName(entity.getLastName())
				.salary(entity.getSalary())
				.departmentName(entity.getDepartment() != null
								? entity.getDepartment().getDepartmentName()
								: "부서없음")
				.build();
	}
}
