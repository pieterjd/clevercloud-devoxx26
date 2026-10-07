package com.pieterjd.devoxx26.clevercloud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WaffleController {

    private final WaffleSelectionRepository waffleSelectionRepository;

    public WaffleController(WaffleSelectionRepository waffleSelectionRepository) {
        this.waffleSelectionRepository = waffleSelectionRepository;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("selection", new WaffleSelection());
        model.addAttribute("toppings", List.of("Strawberry", "Chocolate", "Blueberry", "Maple", "Pecan", "Banana"));
        model.addAttribute("savedSelections", waffleSelectionRepository.findAllByOrderByCreatedAtDesc());
        return "waffle-form";
    }

    @PostMapping("/waffles")
    public String saveSelection(@ModelAttribute("selection") WaffleSelection selection, RedirectAttributes redirectAttributes) {
        if (selection.getTopping() == null || selection.getTopping().isBlank()) {
            redirectAttributes.addFlashAttribute("message", "Please choose a topping before submitting.");
            return "redirect:/";
        }

        waffleSelectionRepository.save(selection);
        redirectAttributes.addFlashAttribute("message", "Saved topping: " + selection.getTopping());
        return "redirect:/";
    }
}
