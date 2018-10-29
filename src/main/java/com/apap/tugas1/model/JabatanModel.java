package com.apap.tugas1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "jabatan")
public class JabatanModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "nama",nullable = false)
    @Size(max = 255)
    private String nama;

    @NotNull
    @Column(name = "deskripsi",nullable = false)
    @Size(max = 255)
    private String deskripsi;

    @NotNull
    @Column(name = "gaji_pokok",nullable = false)
    private double gaji_pokok;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public double getGaji_pokok() {
        return gaji_pokok;
    }

    public void setGaji_pokok(double gaji_pokok) {
        this.gaji_pokok = gaji_pokok;
    }
}
