package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.Leitura;
import com.ufrn.embarcados.reaqua.repository.LeituraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeituraService {

    @Autowired
    private LeituraRepository leituraRepository;

    public List<Leitura> getNiveis()
    {
        return leituraRepository.findAll();
    }

    public List<Leitura> getNiveisPorDias(LocalDate dataInicial,
                                          LocalDate dataFinal)
    {
        return leituraRepository.findByDiasDeMedicao(dataInicial, dataFinal);
    }

    public void save(Leitura leitura)
    {
        if (leitura.getMomentoDaMedicao() == null)
            leitura.setMomentoDaMedicao(LocalDateTime.now());

        leituraRepository.save(leitura);
    }
}
