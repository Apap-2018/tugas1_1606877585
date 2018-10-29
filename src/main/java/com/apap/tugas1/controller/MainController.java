package com.apap.tugas1.controller;

import com.apap.tugas1.service.interfaceService.JabatanService;
import com.apap.tugas1.service.interfaceService.ProvinsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller class to handle the home page
 */
@Controller
public class MainController {
    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private ProvinsiService provinsiService;


    @RequestMapping("/")
    private String home(Model model){
        model.addAttribute("listJabatan",jabatanService.findAll());
        model.addAttribute("listProvinsi",provinsiService.findAll());
        return "home";
    }
}
