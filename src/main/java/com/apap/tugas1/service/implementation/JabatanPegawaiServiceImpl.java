package com.apap.tugas1.service.implementation;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDb;
import com.apap.tugas1.service.interfaceService.JabatanPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class JabatanPegawaiServiceImpl implements JabatanPegawaiService {
    @Autowired
    private JabatanPegawaiDb jabatanPegawaiDb;
    @Override
    public List<JabatanPegawaiModel> findByPegawai(PegawaiModel pegawaiModel) {
        return jabatanPegawaiDb.findByPegawai(pegawaiModel);

    }

    @Override
    public void addJabatanPegawai(JabatanPegawaiModel jabatanPegawaiModel) {
        jabatanPegawaiDb.save(jabatanPegawaiModel);
    }

    @Override
    public void deleteJabatanPegawai(JabatanPegawaiModel jabatanPegawaiModel) {
        jabatanPegawaiDb.delete(jabatanPegawaiModel);
    }
}
