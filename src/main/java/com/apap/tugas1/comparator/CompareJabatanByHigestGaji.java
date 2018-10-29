package com.apap.tugas1.comparator;

import com.apap.tugas1.model.JabatanModel;

import java.util.Comparator;

/**
 * Comparator class to order JabatanModel by property : Gaji Pokok
 */

public class CompareJabatanByHigestGaji implements Comparator<JabatanModel> {
    @Override
    public int compare(JabatanModel o1, JabatanModel o2) {
        return -1*(int)(o1.getGaji_pokok()-o2.getGaji_pokok());
    }
}
