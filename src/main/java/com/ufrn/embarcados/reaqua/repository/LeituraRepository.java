package com.ufrn.embarcados.reaqua.repository;

import com.ufrn.embarcados.reaqua.model.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    //Nivel findByDiaDeMedicao(LocalDate diaDeMedicao);

    @Query( value="select * from nivel e where e.momento_da_medicao between :di and :df", nativeQuery = true)
    List<Leitura> findByDiasDeMedicao(@Param("di") LocalDate dataInicial,
                                      @Param("df") LocalDate dataFinal);

}
