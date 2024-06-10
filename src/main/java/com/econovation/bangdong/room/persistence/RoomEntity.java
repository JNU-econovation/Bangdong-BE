package com.econovation.bangdong.room.persistence;

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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = RoomEntity.PREFIX,
        indexes = @Index(name = "idx_room_name", columnList = RoomEntity.PREFIX + "_name")
)
@SQLDelete(sql="UPDATE room SET is_deleted=true WHERE room_id = ?")
@Where(clause = "is_deleted=false")
@SuperBuilder(toBuilder = true)
public class RoomEntity extends BaseEntity {

    public static final String PREFIX = "room";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PREFIX + "_id", nullable = false)
    private Long roomId;

    @Column(name = PREFIX + "_name", nullable = false)
    private String roomName;

    @Column(name = PREFIX + "_location", nullable = false, columnDefinition = "TEXT")
    private String roomLocation;

}
