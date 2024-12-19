package net.scit.hrjoin.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name="departments")
public class DepartmentEntity {
	
	@Id
	@Column(name="department_id")
	private Long departmentId;
	
	@Column(name="department_name")
	private String departmentName;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<EmployeeEntity> employees;
	
	public static DepartmentEntity toEntity(EmployeeDTO employeeDTO) {
		return DepartmentEntity.builder()
				.departmentId(employeeDTO.getEmployeeId())
				.departmentName(employeeDTO.getFirstName())
				.build();
	}
}
