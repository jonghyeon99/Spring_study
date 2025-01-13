package net.scit.carsharing.entity;

/* Car(부모) 엔티티는 Order(자식) 엔티티와 1:1 관계
 * Order(자식)에서만 @OneToOne   */

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "sharing_order")
public class SharingOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_seq", nullable = false)
    private Integer orderSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private SharingUserEntity userEntity;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "car_seq", unique = true) // 컬럼명 수정
    private SharingCarEntity carEntity;

    @Column(name = "sharing_status")
    @Builder.Default
    private boolean sharingStatus = true;

    @Column(name = "sharing_date")
    @CreationTimestamp
    private LocalDateTime sharingDate;
    
    public static SharingOrderEntity toEntity(SharingUserEntity uEntity, SharingCarEntity cEntity, boolean sStatus) {
        return SharingOrderEntity.builder()
                .userEntity(uEntity)
                .carEntity(cEntity)
                .sharingStatus(sStatus)
                .build();
    }
}
