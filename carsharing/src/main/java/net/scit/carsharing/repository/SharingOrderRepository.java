package net.scit.carsharing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import net.scit.carsharing.dto.MyOrderDTO;
import net.scit.carsharing.entity.SharingOrderEntity;
@EnableJpaRepositories
public interface SharingOrderRepository extends JpaRepository<SharingOrderEntity, Integer>{

	List<SharingOrderEntity> findByUserEntity_UserId(String userId); 

	// 1) JPQL (DTO를 직접 입력해야 하며 이때, 경로를 포함한 전체 Full Name을 기재해야하는 어려움이 있다.)
	@Query("SELECT net.scit.carsharing.dto.MyOrderDTO (" +
	           "o.userEntity.userId, o.carEntity.carSeq, o.sharingStatus, o.sharingDate, c.carType) " +
	           "FROM net.scit.carsharing.entity.SharingOrderEntity o " +
	           "JOIN o.carEntity c " +
	           "WHERE o.userEntity.userId = :userId")
	 List<MyOrderDTO> findOrderCarInfoByUserId(@Param("userId") String userId);
	
//	// 2) Native Query (특정 DB에 맞게 작성된 쿼리문 - 반환타입이 Object[] 이므로 사용시 변환 필요
//	@Query(value = "SELECT o.order_seq as orderSeq, o.user_id as userId, o.car_seq as carSeq, " +
//			"o.sharing_status as sharingStatus, o.sharing_date as sharingDate, " +
//			"c.car_type as carType " +
//			"FROM scit.sharing_car c " +
//			"JOIN scit.sharing_order o ON c.car_seq = o.car_seq " +
//			"WHERE o.user_id = :userId", 
//			nativeQuery = true)
//	List<Object[]> findOrderCarInfoByUserIdNative(@Param("userId") String userId);

}
