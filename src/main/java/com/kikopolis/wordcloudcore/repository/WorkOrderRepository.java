package com.kikopolis.wordcloudcore.repository;

import com.kikopolis.wordcloudcore.entity.WorkOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WorkOrderRepository extends CrudRepository<WorkOrder, Long> {
    WorkOrder findByUuid(UUID uuid);
}
