package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.WaterTankData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WaterTankDataRepository extends JpaRepository<WaterTankData, Long> {
    @Query(value="select * from nivel e where e.momento_da_medicao between :di and :df", nativeQuery = true)
    List<WaterTankData> findByDateRange(@Param("di") LocalDate startDate,
                                        @Param("df") LocalDate finalDate);
}
