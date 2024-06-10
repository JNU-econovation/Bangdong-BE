package com.econovation.bangdong.reservation.persistence;

import com.econovation.bangdong.common.persistence.BaseEntity;
import com.econovation.bangdong.team.persistence.TeamEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Table(name = ReservationMemberEntity.PREFIX)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE reservation_member SET is_deleted=true WHERE reservation_id=? AND reservation_member_id=?")
@Where(clause = "is_deleted=false")
public class ReservationMemberEntity extends BaseEntity {

    public static final String PREFIX = "reservation_member";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PREFIX + "_id", nullable = false)
    private Long reservationMemberId;

    @Column(name = PREFIX + "_member_id", nullable = false)
    private Long memberId;

    @Column(name = PREFIX + "_reservation_id", nullable = false)
    private Long reservationId;

}
