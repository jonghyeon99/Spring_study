package net.scit.test3.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.test3.entity.FitnessEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FitnessDTO {
	private long id;
	private String fname;
	private Boolean gender;
	private double height;
	private double weight;
	private LocalDate joinDate;
	private double stdWeight;
	private double bmi;
	private String bmiResult;
	
	public void setHeight(double height) {
		this.height = height;
		calcStdWeight();
		calcBmi();
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
		calcStdWeight();
		calcBmi();
	}
	
	// 표준체중, bmi, 결과
	// 사용자 정의 메서드
	private void calcStdWeight() {
		double temp = this.height / 100; // cm ==> m로 환산
		
		if (this.gender) {
			this.stdWeight = temp * temp * 22;	// 남성의 표준체중
		} else {
			this.stdWeight = temp * temp * 21;	// 여성의 표준체중
		}
	}
	
	private void calcBmi() {
		double temp = this.height / 100; // cm ==> m로 환산
		
		this.bmi = this.weight / (temp * temp);
		calcBmiResult();
	}
	
	private void calcBmiResult() {
		if (this.bmi >= 35) 		this.bmiResult = "고도 비만";
		else if (this.bmi >= 30) 	this.bmiResult = "중도 비만";
		else if (this.bmi >= 25) 	this.bmiResult = "경도 비만";
		else if (this.bmi >= 23) 	this.bmiResult = "과체중";
		else if (this.bmi >= 18.5) 	this.bmiResult = "정상";
		else 						this.bmiResult = "저체중";
	}
	
	// Entity를 받아서 DTO로 반환
	public static FitnessDTO toDTO(FitnessEntity fitnessEntity) {
		return FitnessDTO.builder()
			.id(fitnessEntity.getId())
			.fname(fitnessEntity.getFname())
			.gender(fitnessEntity.getGender())
			.height(fitnessEntity.getHeight())
			.weight(fitnessEntity.getWeight())
			.joinDate(fitnessEntity.getJoinDate())
			.build();
	}
}
