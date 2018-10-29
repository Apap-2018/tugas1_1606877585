package com.apap.tugas1.service.implementation;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;
import com.apap.tugas1.service.interfaceService.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
    @Autowired
    private PegawaiDb pegawaiDb;
    @Override
    public PegawaiModel getPegawaiByNip(String nip) {
        return pegawaiDb.findPegawaiModelByNip(nip);
    }

    @Override
    public void addPegawai(PegawaiModel pegawaiModel) {
        pegawaiDb.save(pegawaiModel);

    }

    @Override
    public void deletePegawai(PegawaiModel pegawaiModel) {
        pegawaiDb.delete(pegawaiModel);

    }

    @Override
    public List<PegawaiModel> findPegawaiByInstansiAndTanggalLahir(PegawaiModel pegawaiModel) {
        return pegawaiDb.findByInstansiAndTahun_masukAndTanggal_lahir(pegawaiModel.getInstansi().getId(),pegawaiModel.getTahun_masuk(),pegawaiModel.getTanggal_lahir());
    }

    @Override
    public List<PegawaiModel> findPegawaiByProvinsi(int idProvinsi) {
        return pegawaiDb.findByProvinsi(idProvinsi);
    }

    @Override
    public List<PegawaiModel> findPegawaiByInstansi(long idInstansi) {
        return pegawaiDb.findByInstansiOrderByTanggal_lahirDesc(idInstansi);
    }

    @Override
    public List<PegawaiModel> findPegawaiByJabatan(long id_jabatan) {
        return pegawaiDb.findByJabatan(id_jabatan);
    }
}
