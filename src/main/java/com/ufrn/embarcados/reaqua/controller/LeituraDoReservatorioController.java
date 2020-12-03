package com.ufrn.embarcados.reaqua.controller;

import com.ufrn.embarcados.reaqua.model.Leitura;
import com.ufrn.embarcados.reaqua.service.LeituraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LeituraDoReservatorioController {

    @Autowired
    private LeituraService leituraService;

    @RequestMapping(value = "/niveis", method = RequestMethod.GET)
    public ResponseEntity<List<Leitura>> getNiveis(HttpServletRequest request)
    {
        List<Leitura> niveis;
        ResponseEntity<List<Leitura>> re;

        try {
            niveis = leituraService.getNiveis();
            re = new ResponseEntity<> (niveis, HttpStatus.OK);
        } catch (Exception e) {
            re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }

        return re;
    }

    @RequestMapping(value = "/niveis/{dataInicial}/{dataFinal}", method = RequestMethod.GET)
    public ResponseEntity<List<Leitura>> mostrarNiveisPorDia(HttpServletRequest request,
                                                             @PathVariable("dataInicial") String dataInicial,
                                                             @PathVariable("dataFinal") String dataFinal)
    {
        List<Leitura> niveis;
        ResponseEntity<List<Leitura>> re;

        try {
            niveis = leituraService.getNiveisPorDias(
                    LocalDate.parse(dataInicial) , LocalDate.parse(dataFinal));
            re = new ResponseEntity<>(niveis, HttpStatus.OK);
        }
        catch (Exception e) {
            re = new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }

        return re;
    }


    @RequestMapping(value = "/nivel", method = RequestMethod.POST)
    public String registrarNivel(HttpServletRequest request,
                                 @RequestBody Leitura leitura){
        try {
            leituraService.save(leitura);
        }
        catch (Exception e) {
            return "Falha ao salvar. Formato do objeto:\n{\n{\n" +
                    "    \"valor\": 25,\n" +
                    "    \"momentoDaMedicao\": \"2020-12-05 1:55\"\n" +
                    "}";
        }
        return "Nivel recebido";
    }
}
