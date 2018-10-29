package com.apap.tugas1.repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstansiDb extends JpaRepository<InstansiModel,Long> {
    InstansiModel findInstansiModelById(long id);
    List<InstansiModel> findByProvinsiModel(ProvinsiModel provinsiModel);
}
