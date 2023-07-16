package com.harmonengineering.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterTransactionPartRepository
        extends JpaRepository<MasterTransactionPartRecord,Long>
{
}
