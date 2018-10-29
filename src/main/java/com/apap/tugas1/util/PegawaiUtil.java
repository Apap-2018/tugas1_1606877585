package com.apap.tugas1.util;

import com.apap.tugas1.comparator.CompareJabatanByHigestGaji;
import com.apap.tugas1.model.*;
import com.apap.tugas1.service.interfaceService.InstansiService;
import com.apap.tugas1.service.interfaceService.PegawaiService;
import com.apap.tugas1.service.interfaceService.ProvinsiService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class PegawaiUtil {

    public static int getPegawaiHighestGaji(PegawaiModel pegawaiModel , List<JabatanPegawaiModel> jabatanPegawaiModel){
        List<JabatanModel> temp = JabatanUtil.findAll(jabatanPegawaiModel);
        Comparator<JabatanModel> gajiCompare = new CompareJabatanByHigestGaji();
        JabatanModel highestWage = JabatanUtil.findJabatanModelByAttributes(gajiCompare,temp);
        int gaji = (int)findGaji(pegawaiModel,highestWage);
        return gaji;
    }

    public static double findGaji(PegawaiModel pegawaiModel , JabatanModel highestWage){
        double gajiMax = highestWage.getGaji_pokok();
        double gajiDou = gajiMax + (pegawaiModel.getInstansi().getProvinsiModel().getPresentase_tunjangan()/100*gajiMax);
        return gajiDou;
    }

    public static String generateNIP(PegawaiModel pegawaiModel, PegawaiService pegawaiService){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pegawaiModel.getInstansi().getId());
        LocalDate tanggalLahir = pegawaiModel.getTanggal_lahir().toLocalDate();
        stringBuilder.append(String.format("%02d",tanggalLahir.getDayOfMonth()));
        stringBuilder.append(String.format("%02d",tanggalLahir.getMonth().getValue()));
        String year = Integer.toString(tanggalLahir.getYear());
        year = year.substring(2);
        stringBuilder.append(String.format("%s",year));
        stringBuilder.append(pegawaiModel.getTahun_masuk());


        List<PegawaiModel> myPegawai = pegawaiService.findPegawaiByInstansiAndTanggalLahir(pegawaiModel);
        stringBuilder.append(getNextNip(myPegawai));

        return stringBuilder.toString();


    }



    private static String getNextNip(List<PegawaiModel> myPegawai){
        if (myPegawai.isEmpty()){
            return String.format("%02d",1);
        }else {
            int nip = nipIntegerValue(myPegawai.get(myPegawai.size()-1).getNip(),14);
            nip ++;
            return String.format("%02d", nip);

        }

    }

    private static int nipIntegerValue(String nip,int digitStart){
        String subs = nip.substring(digitStart);
        int res = Integer.parseInt(subs);
        return res;


    }

    public static boolean checkConditionAffectingNIP(PegawaiModel lama , PegawaiModel baru){
        if (lama.getInstansi().getId()!=baru.getInstansi().getId())
            return true;
        if (!lama.getTanggal_lahir().equals(baru.getTanggal_lahir()))
            return true;
        if (!lama.getTahun_masuk().equalsIgnoreCase(baru.getTahun_masuk()))
            return true;
        return false;
    }

    public static List<PegawaiModel> findPegawai(PegawaiService pegawaiService,ProvinsiModel provinsiModel,InstansiModel instansiModel , JabatanModel jabatanModel){
        List<PegawaiModel> pegawai = new ArrayList<>();
        List<PegawaiModel> pegawaiByProvinsi = new ArrayList<>();
        List<PegawaiModel> pegawaiByInstansi = new ArrayList<>();
        List<PegawaiModel> pegawaiByJabatan = new ArrayList<>();

        if (provinsiModel!=null)
            pegawaiByProvinsi = pegawaiService.findPegawaiByProvinsi(provinsiModel.getId());
        if (instansiModel!=null)
            pegawaiByInstansi = pegawaiService.findPegawaiByInstansi(instansiModel.getId());
        if (jabatanModel!=null)
            pegawaiByJabatan = pegawaiService.findPegawaiByJabatan(jabatanModel.getId());

        pegawai = intersect(pegawaiByProvinsi ,pegawaiByInstansi);
        List<PegawaiModel> res = new ArrayList<>();
        res = intersect(pegawai,pegawaiByJabatan);
        return res;
    }

    private static List<PegawaiModel> intersect( List<PegawaiModel> category1 , List<PegawaiModel> category2){
        List<PegawaiModel> target  = new ArrayList<>();
        if (category1.isEmpty() && category2.size()>0)
            return category2;
        if (category2.isEmpty()&&category1.size()>0)
            return category1;
        for (PegawaiModel pegawai1 : category1){
            for (PegawaiModel pegawai2 : category2){
                if (pegawai2.getNip().equalsIgnoreCase(pegawai1.getNip())) {
                    target.add(pegawai2);
                }
            }
        }
        return target;
    }

}
