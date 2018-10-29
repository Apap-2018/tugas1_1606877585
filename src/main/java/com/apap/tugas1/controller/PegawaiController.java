package com.apap.tugas1.controller;


import com.apap.tugas1.model.*;
import com.apap.tugas1.service.interfaceService.*;
import com.apap.tugas1.util.PegawaiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.LinkedList;
import java.util.List;

@Controller
public class PegawaiController {
    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private ProvinsiService provinsiService;

    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;

    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private InstansiService instansiService;

    /**
     * This method will handle a request to view a detail of a
     * Pegawai using their NIP
     * @param nip
     * @param model
     * @return
     */
    @RequestMapping(value = "/pegawai/",method = RequestMethod.GET)
    private String viewPegawai( @RequestParam("nip") String nip, Model model){
        PegawaiModel pegawaiModel = pegawaiService.getPegawaiByNip(nip);
        List<JabatanPegawaiModel> jabatanPegawaiModel = jabatanPegawaiService.findByPegawai(pegawaiModel);
        model.addAttribute("jabatan",jabatanPegawaiModel);
        model.addAttribute("pegawai",pegawaiModel);

        int gaji = PegawaiUtil.getPegawaiHighestGaji(pegawaiModel,jabatanPegawaiModel);
        model.addAttribute("gaji",gaji);
        return "view-pegawai";
    }

    /**
     * When a request to edit a pegawai , this mapping will lead to the form to edit the
     * data.
     * @param nip
     * @param model
     * @return
     */

    @RequestMapping(value = "/pegawai/ubah",method = RequestMethod.GET)
    private String updatePegawai(@RequestParam("nip")String nip,Model model){
        PegawaiModel pegawaiModel = pegawaiService.getPegawaiByNip(nip);
        model.addAttribute("pegawai",pegawaiModel);
        model.addAttribute("listProvinsi",provinsiService.findAll());
        model.addAttribute("listJabatan",jabatanService.findAll());
        return "pegawai-update";
    }

    /**
     * This method will handle the update process to the database using data from previous mapping
     * This is include all kind of entity related to Pegawai. Nip also changed when a nip related field
     * is changed.
     * @param pegawaiModel
     * @return
     */
    @RequestMapping(value = "/pegawai/ubah",method = RequestMethod.POST)
    private String updateDataPegawai(@ModelAttribute PegawaiModel pegawaiModel){
        PegawaiModel temp = pegawaiService.getPegawaiByNip(pegawaiModel.getNip());
        // If NIP related field is changed , NIP will be change
        // Else the old NIP still used
        if (PegawaiUtil.checkConditionAffectingNIP(temp,pegawaiModel)) {
            pegawaiModel.setNip(PegawaiUtil.generateNIP(pegawaiModel, pegawaiService));
            for (JabatanPegawaiModel jabatanPegawaiModel : jabatanPegawaiService.findByPegawai(pegawaiModel))
                jabatanPegawaiService.deleteJabatanPegawai(jabatanPegawaiModel);
        }
        //Add Pegawai to the database
        pegawaiService.addPegawai(pegawaiModel);
        //Save the jabatan that pegawai has now and set the pegawai to this pegawai
        List<JabatanPegawaiModel> jabatanNow = pegawaiModel.getJabatanPegawaiModel();
        for (JabatanPegawaiModel jabatanPegawaiModel : jabatanNow){
            jabatanPegawaiModel.setPegawai(pegawaiModel);
        }
        // If exits old jabatan that pegawai had , delete it right away
        for (JabatanPegawaiModel jabatanPegawaiModelall : jabatanPegawaiService.findByPegawai(pegawaiModel)){
            if (!jabatanNow.contains(jabatanPegawaiModelall))
                jabatanPegawaiService.deleteJabatanPegawai(jabatanPegawaiModelall);
        }
        // Add new jabatan or keep old data if nothing changed
        List<JabatanPegawaiModel> currentData = jabatanPegawaiService.findByPegawai(pegawaiModel);
        for (JabatanPegawaiModel jabatanPegawaiModel : pegawaiModel.getJabatanPegawaiModel()){
            if (!currentData.contains(jabatanPegawaiModel)) {
                jabatanPegawaiService.addJabatanPegawai(jabatanPegawaiModel);
            }
        }
        return "update-success";

    }

    /**
     * This method will create a new row in the form to
     * add jabatan to a pegawai
     * @param pegawaiModel
     * @param model
     * @return
     */
    @RequestMapping(value = "/pegawai/ubah",params = {"add"})
    private String addDropDown(@ModelAttribute PegawaiModel pegawaiModel , Model model){
        if (pegawaiModel.getJabatanPegawaiModel() == null){
            pegawaiModel.setJabatanPegawaiModel(new LinkedList<>());
        }
        pegawaiModel.getJabatanPegawaiModel().add(new JabatanPegawaiModel());
        model.addAttribute("listProvinsi",provinsiService.findAll());
        model.addAttribute("listJabatan",jabatanService.findAll());
        model.addAttribute("pegawai",pegawaiModel);
        return "pegawai-update";
    }

    /**
     * If a request to create a new Pegawai , this method will handle it.
     * @param model
     * @return
     */
    @RequestMapping("/pegawai/tambah")
    private String tambahPegawai(Model model){
        PegawaiModel pegawaiModelBaru = new PegawaiModel();
        model.addAttribute("pegawai",pegawaiModelBaru);
        model.addAttribute("listProvinsi",provinsiService.findAll());
        model.addAttribute("listJabatan",jabatanService.findAll());
        return "pegawai-add";
    }
    /**
     * This method will create a new row in the form to
     * add jabatan to a pegawai
     * @param pegawaiModel
     * @param model
     * @return
     */

    @RequestMapping(value = "/pegawai/tambah",params = {"add"})
    private String addDropDownTambah(@ModelAttribute PegawaiModel pegawaiModel , Model model){
        if (pegawaiModel.getJabatanPegawaiModel() == null){
            pegawaiModel.setJabatanPegawaiModel(new LinkedList<>());
        }
        pegawaiModel.getJabatanPegawaiModel().add(new JabatanPegawaiModel());
        model.addAttribute("listProvinsi",provinsiService.findAll());
        model.addAttribute("listJabatan",jabatanService.findAll());
        model.addAttribute("pegawai",pegawaiModel);
        return "pegawai-add";
    }

    /**
     * The data related and database access process of adding new Pegawai
     * will be handle by this method
     * @param pegawaiModel
     * @return
     */

    @RequestMapping(value = "/pegawai/tambah",method = RequestMethod.POST)
    private String tambahDataPegawai(@ModelAttribute PegawaiModel pegawaiModel) {
        pegawaiModel.setNip(PegawaiUtil.generateNIP(pegawaiModel, pegawaiService));
        pegawaiService.addPegawai(pegawaiModel);
        List<JabatanPegawaiModel> jabatanNow = pegawaiModel.getJabatanPegawaiModel();
        for (JabatanPegawaiModel jabatanPegawaiModel : jabatanNow) {
            jabatanPegawaiModel.setPegawai(pegawaiModel);
        }
        for (JabatanPegawaiModel jabatanPegawaiModel : pegawaiModel.getJabatanPegawaiModel()) {
                jabatanPegawaiService.addJabatanPegawai(jabatanPegawaiModel);
        }
        return "add-success";
    }

    /**
     * Find a pegawai from database with 3 combination of filter
     * However since instansi is a weak entity , they are dependent to Provinsi,
     * the need to choose provinsi to filter with an instansi is a must.
     * Provinsi and Jabatan can stand as single filter
     * @param model
     * @return
     */
    @RequestMapping("/pegawai/cari")
    private String carPegawai(Model model){
        model.addAttribute("listProvinsi",provinsiService.findAll());
        model.addAttribute("listJabatan",jabatanService.findAll());
        return "pegawai-cari";

    }
    @RequestMapping(name = "/pegawai/cari",method = RequestMethod.GET)
    private String cariPegawai(@RequestParam("idProvinsi")int idProvinsi,
                               @RequestParam(name = "idInstansi",required = false)Long idInstansi,
                               @RequestParam("idJabatan")long idJabatan,
                               Model model){
        ProvinsiModel provinsiModel = null;
        InstansiModel instansiModel = null;
        JabatanModel jabatanModel = null;
        if (idProvinsi!=0)
            provinsiModel = provinsiService.findProvinsiById(idProvinsi);
        if (idInstansi!=null)
            instansiModel = instansiService.getInstansiById(idInstansi);
        if (idJabatan!=0)
            jabatanModel = jabatanService.getJabatanById(idJabatan);
        List<PegawaiModel> myPegawai = PegawaiUtil.findPegawai(pegawaiService,provinsiModel,instansiModel,jabatanModel);
        for (PegawaiModel pegawaiModel : myPegawai){
            System.err.println(pegawaiModel.getNama());
        }
        model.addAttribute("listProvinsi",provinsiService.findAll());
        model.addAttribute("listJabatan",jabatanService.findAll());
        model.addAttribute("pegawai",myPegawai);
        if (jabatanModel!=null)
            model.addAttribute("jabatan",jabatanModel);
        return "pegawai-cari";
    }

    @RequestMapping(value = "/pegawai/termuda-tertua",method = RequestMethod.GET)
    private String termudaTertua(@RequestParam(name = "idInstansi")long idInstansi,Model model){
        List<PegawaiModel> pegawaiModels = pegawaiService.findPegawaiByInstansi(instansiService.getInstansiById(idInstansi).getId());
        PegawaiModel pegawaiModel1 = pegawaiModels.get(pegawaiModels.size()-1);
        PegawaiModel pegawaiModel2 = pegawaiModels.get(0);

        List<JabatanPegawaiModel> jabatanPegawaiModel1 = jabatanPegawaiService.findByPegawai(pegawaiModel1);
        List<JabatanPegawaiModel> jabatanPegawaiModel2 = jabatanPegawaiService.findByPegawai(pegawaiModel2);


        model.addAttribute("pegawai1",pegawaiModel1);
        model.addAttribute("pegawai2",pegawaiModel2);


        int gaji1 = PegawaiUtil.getPegawaiHighestGaji(pegawaiModel1,jabatanPegawaiModel1);
        int gaji2 = PegawaiUtil.getPegawaiHighestGaji(pegawaiModel2,jabatanPegawaiModel2);
        model.addAttribute("gaji1",gaji1);
        model.addAttribute("gaji2",gaji2);
        return "pegawai-termuda-tertua";
    }


}
