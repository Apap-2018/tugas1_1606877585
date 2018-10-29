package com.apap.tugas1.controller;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.interfaceService.InstansiService;
import com.apap.tugas1.service.interfaceService.ProvinsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class InstansiController {
    @Autowired
    private ProvinsiService provinsiService;

    @Autowired
    InstansiService instansiService;

    @RequestMapping(value = "/instansi-list", method = RequestMethod.GET)
    public @ResponseBody
    List<InstansiModel> findAllInstansi(@RequestParam(value = "provinsiId", required = true) int provinsiId, Model model) {
        ProvinsiModel provinsi = provinsiService.findProvinsiById(provinsiId);
        return instansiService.getInstansiByProvinsi(provinsi);
    }
}
