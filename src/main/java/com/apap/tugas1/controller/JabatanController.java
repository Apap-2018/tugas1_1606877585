package com.apap.tugas1.controller;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.interfaceService.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JabatanController {

    @Autowired
    private JabatanService jabatanService;

    @RequestMapping(value = "jabatan/view",method = RequestMethod.GET)
    private String viewJabatan(@RequestParam(name = "idJabatan")long id,Model model){
        JabatanModel jabatanModel = jabatanService.getJabatanById(id);
        model.addAttribute("jabatan",jabatanModel);
        model.addAttribute("gaji",(int)jabatanModel.getGaji_pokok());
        return "jabatan-detail";
    }

    @RequestMapping("/jabatan/tambah")
    private String addJabatan(Model model){
        model.addAttribute("jabatan",new JabatanModel());
        return "jabatan-add";
    }

    @RequestMapping(value = "/jabatan/tambah",method = RequestMethod.POST)
    private String saveJabatan(@ModelAttribute JabatanModel jabatanModel){
        jabatanService.addJabatan(jabatanModel);
        return "add-success";


    }
    @RequestMapping(value = "/jabatan/ubah",method = RequestMethod.GET)
    private String ubahJabatan(@RequestParam(name = "idJabatan")long idJabatan,Model model){
        JabatanModel jabatanModel = jabatanService.getJabatanById(idJabatan);
        model.addAttribute("jabatan",jabatanModel);
        model.addAttribute("gaji",(int)jabatanModel.getGaji_pokok());
        return "jabatan-ubah";

    }

    @RequestMapping(value = "/jabatan/hapus")
    private String hapusJabatan(@ModelAttribute JabatanModel jabatanModel){
        jabatanService.deleteJabatan(jabatanModel);
        return "update-success";
    }

    @RequestMapping(value = "/jabatan/viewall")
    private String viewall(Model model){
        model.addAttribute("listJabatan",jabatanService.findAll());
        List<Integer> gaji = new ArrayList<>();
        for (JabatanModel jabatanModel : jabatanService.findAll()){
            gaji.add((int)jabatanModel.getGaji_pokok());
        }
        model.addAttribute("gaji",gaji);
        return "jabatan-all";
    }

}
