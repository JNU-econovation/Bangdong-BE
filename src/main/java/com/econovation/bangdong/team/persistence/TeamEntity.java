package com.econovation.bangdong.team.persistence;

import com.econovation.bangdong.common.persistence.BaseEntity;
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
@Table(
        name = TeamEntity.PREFIX,
        indexes = @Index(name = "idx_team_name", columnList = TeamEntity.PREFIX + "_name")
)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE team SET is_deleted=true WHERE team_id=?")
@Where(clause = "is_deleted=false")
public class TeamEntity extends BaseEntity {

    public static final String PREFIX = "team";

    @Id
    @Column(name = PREFIX+"_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(name = PREFIX+"_name", nullable = false)
    private String teamName;

}
