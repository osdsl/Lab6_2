package com.example.lab5.Controllers;


import com.example.lab5.Repositories.Entities.TvEntity;
import com.example.lab5.Services.TvService;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
@PreAuthorize("hasAuthority('ROLE_USER')")
@RequestMapping("/api")
public class TvUserController {
    @NonNull
    private final TvService tvService;


    @GetMapping("/tv")
    public String getAllTv(@ModelAttribute TvEntity tvEntity, Model model){
        model.addAttribute("tvEntities",  tvService.getAllTv());
        return "tv";
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
