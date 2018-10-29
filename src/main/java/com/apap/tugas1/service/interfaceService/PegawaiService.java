package com.apap.tugas1.service.interfaceService;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;


import java.util.List;

public interface PegawaiService {

    PegawaiModel getPegawaiByNip(String nip);
    void addPegawai(PegawaiModel pegawaiModel);
    void deletePegawai(PegawaiModel pegawaiModel);
    List<PegawaiModel> findPegawaiByInstansiAndTanggalLahir(PegawaiModel pegawaiModel);
    List<PegawaiModel> findPegawaiByProvinsi(int idProvinsi);
    List<PegawaiModel> findPegawaiByInstansi(long idInstansi);
    List<PegawaiModel> findPegawaiByJabatan(long id_jabatan);

}
