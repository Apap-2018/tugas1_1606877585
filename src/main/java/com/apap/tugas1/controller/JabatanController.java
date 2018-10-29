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

/**
 * Controller Class to control operation related to Jabatan
 */
@Controller
public class JabatanController {

    @Autowired
    private JabatanService jabatanService;

    /**
     * This method's function is to show a detail about certain Jabatan Instance
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "jabatan/view",method = RequestMethod.GET)
    private String viewJabatan(@RequestParam(name = "idJabatan")long id,Model model){
        JabatanModel jabatanModel = jabatanService.getJabatanById(id);
        model.addAttribute("jabatan",jabatanModel);
        model.addAttribute("gaji",(int)jabatanModel.getGaji_pokok());
        return "jabatan-detail";
    }

    /**
     * When a request to create a new jabatan this mapping will handle it.
     * @param model
     * @return
     */
    @RequestMapping("/jabatan/tambah")
    private String addJabatan(Model model){
        model.addAttribute("jabatan",new JabatanModel());
        return "jabatan-add";
    }

    /**
     * When jabatan successfully created , a page success will shown up and redirected to home
     * @param jabatanModel
     * @return
     */
    @RequestMapping(value = "/jabatan/tambah",method = RequestMethod.POST)
    private String saveJabatan(@ModelAttribute JabatanModel jabatanModel){
        jabatanService.addJabatan(jabatanModel);
        return "add-success";

/**
 * This mapping to handle an edit to Jabatan
 */
    }
    @RequestMapping(value = "/jabatan/ubah",method = RequestMethod.GET)
    private String ubahJabatan(@RequestParam(name = "idJabatan")long idJabatan,Model model){
        JabatanModel jabatanModel = jabatanService.getJabatanById(idJabatan);
        model.addAttribute("jabatan",jabatanModel);
        model.addAttribute("gaji",(int)jabatanModel.getGaji_pokok());
        return "jabatan-ubah";

    }

    /**
     *This method to handle the deletion of a jabatan
     * @param jabatanModel
     * @return
     */
    @RequestMapping(value = "/jabatan/hapus")
    private String hapusJabatan(@ModelAttribute JabatanModel jabatanModel){
        jabatanService.deleteJabatan(jabatanModel);
        return "update-success";
    }

    /**
     * This mapping will lead to a page that show you all jabatan that exists in the database
     * @param model
     * @return
     */
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
