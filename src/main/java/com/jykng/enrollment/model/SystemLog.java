package com.jykng.enrollment.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "system_logs")
@Data
@NoArgsConstructor
public class SystemLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="timestamp")
    @CreatedDate
    private Instant timeStamp;

    @Column(name="request_url")
    private String requestURL;

    @Column(name="request_type")
    private String requestType;

    @Column(name="request_ip")
    private String requestIP;

    @Column(name="response_status")
    private int responseStatus;

    @Column(name="response_exception")
    private String responseException;
}
