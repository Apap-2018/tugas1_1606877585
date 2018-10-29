package com.apap.tugas1.service.implementation;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;
import com.apap.tugas1.service.interfaceService.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
    @Autowired
    private JabatanDb jabatanDb;

    @Override
    public JabatanModel getJabatanById(long id) {
        return jabatanDb.findJabatanModelById(id);
    }

    @Override
    public JabatanModel getJabatanByNama(String nama) {
        return null;
    }

    @Override
    public List<JabatanModel> findAll() {
        return jabatanDb.findAll();
    }

    @Override
    public void addJabatan(JabatanModel jabatanModel) {
        jabatanDb.save(jabatanModel);
    }

    @Override
    public void deleteJabatan(JabatanModel jabatanModel) {
        jabatanDb.delete(jabatanModel);
    }
}
