package com.example.demo1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Embeddable
public class TaskWorkerPK {
    
    @Column(name = "task_id", insertable = false, updatable = false)
    private Long taskId;

    @Column(name = "worker_id", insertable = false, updatable = false)
    private Long workerId;
}
