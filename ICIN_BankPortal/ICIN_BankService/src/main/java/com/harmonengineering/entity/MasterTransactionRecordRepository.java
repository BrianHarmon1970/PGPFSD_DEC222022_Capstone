package com.harmonengineering.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterTransactionRecordRepository
        extends JpaRepository< MasterTransactionRecord, Long> {
}
