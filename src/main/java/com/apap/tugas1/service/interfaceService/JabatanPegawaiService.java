package com.apap.tugas1.service.interfaceService;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;

import java.util.List;

public interface JabatanPegawaiService {
    List<JabatanPegawaiModel> findByPegawai(PegawaiModel pegawaiModel);
    void addJabatanPegawai(JabatanPegawaiModel jabatanPegawaiModel);
    void deleteJabatanPegawai(JabatanPegawaiModel jabatanPegawaiModel);
}
