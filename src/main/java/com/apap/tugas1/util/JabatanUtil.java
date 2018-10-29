package com.apap.tugas1.util;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class JabatanUtil {

    public static List<JabatanModel> findAll(List<JabatanPegawaiModel> jabatanPegawaiModel){
        List<JabatanModel> temp = new LinkedList<>();
        for (JabatanPegawaiModel jabatanPegawaiModel1 : jabatanPegawaiModel){
            temp.add(jabatanPegawaiModel1.getJabatan());
        }
        return temp;

    }

    public static JabatanModel findJabatanModelByAttributes(Comparator<JabatanModel> jabatanModelComparator , List<JabatanModel> jabatanModels ){
        Collections.sort(jabatanModels,jabatanModelComparator);
        return jabatanModels.get(0);
    }
}
