package com.apap.tugas1.service.implementation;

import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.ProvinsiDb;
import com.apap.tugas1.service.interfaceService.ProvinsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
    @Autowired
    private ProvinsiDb provinsiDb;
    @Override
    public ProvinsiModel findProvinsiById(int id) {
        return provinsiDb.findById(id);
    }

    @Override
    public List<ProvinsiModel> findAll() {
        return provinsiDb.findAll();
    }
}
