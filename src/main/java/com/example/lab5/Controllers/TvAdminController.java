package com.example.lab5.Controllers;

import com.example.lab5.Repositories.Entities.TvEntity;
import com.example.lab5.Services.TvService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/admin")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class TvAdminController {
    @NonNull
    private final TvService tvService;

    @GetMapping("/tv")
    public String getAllTv(@ModelAttribute TvEntity tvEntity, Model model){
        model.addAttribute("tvEntities",  tvService.getAllTv());
        return "tv";
    }

    @PostMapping("/add")
    public String addTv(@ModelAttribute  @Valid TvEntity tvEntity, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "add";
        }
        tvService.addTv(tvEntity);
        return "addResult";
    }

    @GetMapping("/add")
    public String addTvResult(Model model){
        model.addAttribute("tvEntity", new TvEntity());
        return "add";
    }

    @PostMapping("/delete")
    public String deleteTv(@ModelAttribute  TvEntity tvEntity, BindingResult bindingResult, Model model){

        @Valid int id = tvEntity.getId();
        model.addAttribute("tvId", id);
        if (bindingResult.hasErrors()) {
            return "delete";
        }

        if (tvService.existsTv(tvEntity.getId())) {
            tvService.deleteTv(tvEntity.getId());
            return "deleteResult";
        }
        return "deleteNoResult";
    }

    @GetMapping("/delete")
    public String deleteTvResult(Model model){
        model.addAttribute("tvEntity", new TvEntity());
        return "delete";
    }

    @GetMapping("/edit")
    public String updateTv(Model model){

        model.addAttribute("tvEntity", new TvEntity());

        return "checkConditionEdit";
    }

    @PostMapping("/edit")
    public String editTv(@ModelAttribute  TvEntity tvEntity,BindingResult bindingResult ,Model model){
        model.addAttribute("tvId", tvEntity.getId());
        if (bindingResult.hasErrors()) {
            return "checkConditionEdit";
        }
        if (tvService.existsTv(tvEntity.getId())) {
            model.addAttribute("tvEntity", tvService.loadUserById(tvEntity.getId()));
            return "editExist";
        }
        return "add";
    }
    @PostMapping("/edit/successful")
    public String editTvSuccess(@ModelAttribute TvEntity tvEntity, Model model){
        model.addAttribute("tvEntity", tvService.saveTv(tvEntity));
        return "editSuccessful";
    }

    @GetMapping("/search")
    public String searchTvResult(Model model){
        model.addAttribute("tvEntity", new TvEntity());
        return "search";
    }

    @PostMapping("/search")
    public String searchTv(@ModelAttribute  TvEntity tvEntity, BindingResult bindingResult, Model model){
        @Valid int id = tvEntity.getId();
        model.addAttribute("tvId", id);
        if (bindingResult.hasErrors()){
            return "search";
        }
        if (tvService.existsTv(tvEntity.getId())) {
            model.addAttribute("tvEntity", tvService.loadUserById(tvEntity.getId()));
            return "searchResult";
        }

        return "searchNoResult";
    }

    @GetMapping("/search/by/price")
    public String searchTvByLowPrice(Model model){
        model.addAttribute("tvEntity", new TvEntity());
        return "searchByPrice";
    }

    @PostMapping("/search/by/price")
    public String searchTvByLowPrice(@ModelAttribute  TvEntity tvEntity, BindingResult bindingResult, Model model){
        @Valid double price = tvEntity.getPrice();
        model.addAttribute("tvPrice", price);
        if (bindingResult.hasErrors()){
            return "searchByPrice";
        }

        model.addAttribute("tvEntities", tvService.searchByLowPrice(tvEntity.getPrice()));
        if (tvService.searchByLowPrice(tvEntity.getPrice()).isEmpty()){

            return "searchByPriceNoResult";
        }

        return "searchByPriceResult";
    }

}
