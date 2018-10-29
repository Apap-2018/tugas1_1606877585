package com.apap.tugas1.repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel,Long> {
    PegawaiModel findPegawaiModelByNip(String nip);
    PegawaiModel findPegawaiModelById(long id);

    @Query(nativeQuery = true,value = "Select distinct * from pegawai p1 " +
            "where p1.id_instansi=:id_instansi and p1.tahun_masuk=:tahun_masuk" +
            " and p1.tanggal_lahir=:tanggal_lahir " +
            "order by nip asc")
    List<PegawaiModel> findByInstansiAndTahun_masukAndTanggal_lahir(@Param("id_instansi") long id_instansi,
                                                                    @Param("tahun_masuk") String tahun_masuk ,
                                                                    @Param("tanggal_lahir") Date tanggal_lahir);
    @Query(nativeQuery = true,value = "select p1.* from pegawai p1 where p1.id_instansi=:id_instansi " +
            "order by p1.tanggal_lahir asc")
    List<PegawaiModel> findByInstansiOrderByTanggal_lahirDesc(@Param("id_instansi")long idInstansi);

    @Query(nativeQuery = true,value = "select p1.* from pegawai p1 ,provinsi pv ,Instansi i " +
            "where p1.id_instansi=i.id and i.id_provinsi=pv.id and pv.id=:id_provinsi")
    List<PegawaiModel> findByProvinsi(@Param("id_provinsi")int id_provinsi);

    @Query(nativeQuery = true , value = "select p1.* from pegawai p1 , jabatan_pegawai jp " +
            "where p1.id = jp.id_pegawai and jp.id_jabatan = :id_jabatan")
    List<PegawaiModel> findByJabatan(@Param("id_jabatan")long idJabatan);


}
