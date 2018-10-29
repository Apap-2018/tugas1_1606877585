package com.apap.tugas1.service.implementation;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDb;
import com.apap.tugas1.service.interfaceService.InstansiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
    @Autowired
    private InstansiDb instansiDb;

    @Override
    public InstansiModel getInstansiById(long id) {
        return instansiDb.findInstansiModelById(id);
    }

    @Override
    public List<InstansiModel> getInstansiByProvinsi(ProvinsiModel provinsi) {
        return instansiDb.findByProvinsiModel(provinsi);
    }
}
