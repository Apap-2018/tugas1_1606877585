package com.apap.tugas1.service.interfaceService;

import com.apap.tugas1.model.ProvinsiModel;

import java.util.List;

public interface ProvinsiService {

    ProvinsiModel findProvinsiById(int id);
    List<ProvinsiModel> findAll();

}
