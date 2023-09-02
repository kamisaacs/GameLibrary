

package org.wecancodeit.gamelibrary.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.gamelibrary.Models.BoardGameModel;
import org.wecancodeit.gamelibrary.Models.PublisherModel;
// import org.wecancodeit.gamelibrary.Repository.BoardGameRepo;
// import org.wecancodeit.gamelibrary.Repos.PublisherRepo;
import org.wecancodeit.gamelibrary.Repositories.BoardGameRepository;
import org.wecancodeit.gamelibrary.Repositories.PublisherRepository;

import jakarta.annotation.Resource;

@Controller
public class EditController {

    @Resource
    private BoardGameRepository repo;

    @Resource
    private PublisherRepository pubRepo;

    @PostMapping("/edit-title")
    public String editTitle(@RequestParam long gameId, @RequestParam String gameName,@RequestParam String name, Model model){
        model.addAttribute("publisher", repo.findById(gameId).get());
        BoardGameModel title = repo.findByName(gameName);
        title.setName(name);
        repo.save(title);        
        return "redirect:/publisher/editPublisher/" + gameId;
    }
    
    @PostMapping("/edit-description")
    public String editDescription(@RequestParam long gameId, @RequestParam String gameName,@RequestParam String description, Model model){
        model.addAttribute("publisher", repo.findById(gameId).get());
        BoardGameModel title = repo.findByName(gameName);
        title.setDescription(description);
        repo.save(title);        
        return "redirect:/publisher/editPublisher/" + gameId;
    }

    @PostMapping("/add-game")
    public String addGame(
    @RequestParam String gameId, 
    @RequestParam String gameName, 
    @RequestParam String gameDes, 
    @RequestParam String imgUrl, 
    @RequestParam long pubId,
     Model model){
    model.addAttribute("publisher", repo.findById(pubId).get());
    PublisherModel publisher = pubRepo.findByName(gameId);
    BoardGameModel title = repo.findByName(gameName);

    if(title == null){
        BoardGameModel newGame = new BoardGameModel(gameName, gameDes, imgUrl, publisher);
        repo.save(newGame);
    }

        return "redirect:/publisher/editPublisher/" + pubId;
    }

    @GetMapping("/publisher/editPublisher/{id}")
    public String getPublisherDetails(@PathVariable Long id, Model model){
        model.addAttribute("publishers", pubRepo.findAll());
        model.addAttribute("publisher", pubRepo.findById(id).get());
        return "editGameDetailsView";
    }

@PostMapping("/add-publisher")
public String addPublisher(@RequestParam String name){
    PublisherModel publisher = pubRepo.findByName(name);
    if(publisher == null){
        PublisherModel publisherModel = new PublisherModel(name);
        pubRepo.save(publisherModel);
    }
    return "redirect:/publisher/editPublishers";
}

@PostMapping("/edit-publisher")
public String editPublisher(@RequestParam String name, @RequestParam String pubName ){
    PublisherModel publisher = pubRepo.findByName(pubName);
    publisher.setName(name);
    pubRepo.save(publisher);

    return "redirect:/publisher/editPublishers";
}
}