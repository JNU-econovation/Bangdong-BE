package com.econovation.bangdong.device.persistence;

import com.econovation.bangdong.common.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;

@Entity
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = DeviceEntity.PREFIX,
        indexes = {
                @Index(name = "idx_connected_date", columnList = DeviceEntity.PREFIX+"_connected_date"),
                @Index(name = "idx_disconnected_date", columnList = DeviceEntity.PREFIX + "_disconnected_date")
        })
@SQLDelete(sql = "UPDATE device SET is_deleted=true WHERE device_id=?")
@Where(clause = "is_deleted=false")
@SuperBuilder
public class DeviceEntity extends BaseEntity {

    public static final String PREFIX = "device";

    @Id
    @Column(name = PREFIX + "_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    @Column(name = PREFIX + "_member_id", nullable = false)
    private Long memberId;

    @Column(name = PREFIX + "_mac_address", nullable = false)
    private String macAddress;

    @Column(name = PREFIX + "_ip", nullable = false)
    private String ip;

    @Column(name = PREFIX + "_connected_date", nullable = false)
    private Timestamp connectedDate;

    @Column(name = PREFIX + "_disconnected_date", nullable = false)
    private Timestamp disConnectedDate;

}
