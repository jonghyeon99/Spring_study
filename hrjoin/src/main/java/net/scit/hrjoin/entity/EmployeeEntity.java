package net.scit.hrjoin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.scit.hrjoin.dto.EmployeeDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name="employees")
public class EmployeeEntity {

	@Id
	@Column(name="employee_id")
	private Long employeeId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="salary")
	private Double salary;
	
	// Has A(포함 관계), Is A(상속 관계)
	
	@ManyToOne
	@JoinColumn(name="department_id", referencedColumnName = "department_id")
	private DepartmentEntity department;
	
	public static EmployeeEntity toEntity(EmployeeDTO employeeDTO) {
		return EmployeeEntity.builder()
				.employeeId(employeeDTO.getEmployeeId())
				.firstName(employeeDTO.getFirstName())
				.lastName(employeeDTO.getLastName())
				.salary(employeeDTO.getSalary())
				.build();
	}
}
