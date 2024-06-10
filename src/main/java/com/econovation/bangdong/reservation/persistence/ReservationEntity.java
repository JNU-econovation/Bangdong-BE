package com.econovation.bangdong.reservation.persistence;

import com.econovation.bangdong.common.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(
        name = ReservationEntity.PREFIX,
        indexes = @Index(name = "idx_reservation_date", columnList = ReservationEntity.PREFIX + "_date")
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE reservation SET is_deleted=true where reservation_id=?")
@Where(clause = "is_deleted=false")
@ToString
public class ReservationEntity extends BaseEntity {

    public static final String PREFIX = "reservation";

    @Id
    @Column(name = PREFIX + "_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(name = PREFIX + "_master_id", nullable = false)
    private Long reservationMasterId;

    @Column(name = PREFIX + "_room_id", nullable = false)
    private Long roomId;

    @Column(name = PREFIX + "_date", nullable = false)
    private Timestamp reservationDate;

}
