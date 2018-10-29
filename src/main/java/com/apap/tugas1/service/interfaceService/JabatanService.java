package com.apap.tugas1.service.interfaceService;

import com.apap.tugas1.model.JabatanModel;

import java.util.List;

public interface JabatanService {
    JabatanModel getJabatanById(long id);
    JabatanModel getJabatanByNama(String nama);
    List<JabatanModel> findAll();
    void addJabatan(JabatanModel jabatanModel);
    void deleteJabatan(JabatanModel jabatanModel);
}
