package com.econovation.bangdong.join.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Table(
        name = TeamMemberEntity.PREFIX,
        indexes = {
                @Index(name = "idx_team_ids", columnList = TeamMemberEntity.PREFIX + "_team_id"),
                @Index(name = "idx_member_ids", columnList = TeamMemberEntity.PREFIX + "_member_id")
        }
)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE team_member SET is_deleted=true WHERE team_member_id=?")
@Where(clause = "is_deleted=false")
public class TeamMemberEntity {

    public static final String PREFIX = "team_member";

    @Id
    @Column(name = PREFIX+"_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamMemberId;

    @Column(name = PREFIX+"_team_id", nullable = false)
    private Long teamId;

    @Column(name = PREFIX+"_member_id", nullable = false)
    private Long memberId;

}
