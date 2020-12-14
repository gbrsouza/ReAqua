package com.ufrn.embarcados.reaqua.service;

import com.ufrn.embarcados.reaqua.model.Tower;
import com.ufrn.embarcados.reaqua.repository.TowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TowerService {

    private final TowerRepository towerRepository;

    public List<Tower> listAll(){
        return towerRepository.findAll();
    }


}
