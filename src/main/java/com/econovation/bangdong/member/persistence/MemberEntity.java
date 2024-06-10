package com.econovation.bangdong.member.persistence;

import com.econovation.bangdong.common.persistence.BaseEntity;
import com.econovation.bangdong.member.application.domain.MemberPosition;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;

/** SuperBuilder 어노테이션은, 상위 클래스의 필드에 대해서도 builder 패턴을 사용할 수 있게 해주는 어노테이션이다. */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Table(
        name = MemberEntity.PREFIX,
        indexes = @Index(name = "idx_member_total_connect_time", columnList = MemberEntity.PREFIX+ "_total_connect_time"))
@SQLDelete(sql = "UPDATE member SET is_deleted = true where member_id=?")
@Where(clause = "is_deleted=false")
@Getter
@SuperBuilder(toBuilder = true)
public class MemberEntity extends BaseEntity {

    public static final String PREFIX = "member";

    @Id
    @Column(name = PREFIX + "_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = PREFIX + "_login_id", nullable = false)
    private String loginId;

    @Column(name = PREFIX + "_name", nullable = false)
    private String name;

    @Column(name = PREFIX + "_email", nullable = false)
    private String email;

    @Column(name = PREFIX + "_generation", nullable = false)
    private Long generation;

    @Column(name = PREFIX + "_position", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberPosition position;

    @Column(name = PREFIX + "_status_message", nullable = false, columnDefinition = "TEXT")
    private String statusMessage;

    @Column(name = PREFIX + "_total_connect_time", nullable = false)
    private Timestamp totalConnectTime;


}
