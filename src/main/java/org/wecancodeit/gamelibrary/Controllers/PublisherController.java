package org.wecancodeit.gamelibrary.Controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.gamelibrary.Repositories.PublisherRepository;

import jakarta.annotation.Resource;

@Controller
public class PublisherController {

    @Resource
    private PublisherRepository repository;

    public PublisherController(PublisherRepository repository) {
        this.repository = repository;
    }

    @RequestMapping({ "", "/", "/publisher" })
    public String getAllPublishers(Model model) {
        // referenced in the view
        model.addAttribute("publishers", repository.findAll());
        return "displayPublishersView";
    }

    @GetMapping("/publisher/gameDetails/{id}")
    public String getPublisherDetails(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", repository.findById(id).get());
        return "gameDetailsView";

    }

    @GetMapping("publisher/deleteDetails/{d}")
    public String deletePublisher(@PathVariable Long id, Model model) {

        repository.deleteById(id);

        return "redirect:/publisher";
    }

}
