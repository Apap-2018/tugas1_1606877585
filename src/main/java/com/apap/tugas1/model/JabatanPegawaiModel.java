package com.apap.tugas1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawaiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_pegawai",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private PegawaiModel pegawai;

    @ManyToOne
    @JoinColumn(name = "id_jabatan",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private JabatanModel jabatan;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JabatanPegawaiModel){
            JabatanPegawaiModel other = (JabatanPegawaiModel) obj;
            return other.getPegawai().getId()==this.getPegawai().getId()&&
                    other.getJabatan().getId()==this.getJabatan().getId();
        }
        return false;
    }

    public PegawaiModel getPegawai() {
        return pegawai;
    }

    public void setPegawai(PegawaiModel pegawai) {
        this.pegawai = pegawai;
    }

    public JabatanModel getJabatan() {
        return jabatan;
    }

    public void setJabatan(JabatanModel jabatan) {
        this.jabatan = jabatan;
    }
}
