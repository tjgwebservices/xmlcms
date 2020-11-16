package com.tjgwebservices.tjgxmlcms.controller.game;

import com.tjgwebservices.tjgxmlcms.dbo.game.GameDBO;
import com.tjgwebservices.tjgxmlcms.form.game.GameForm;
import com.tjgwebservices.tjgxmlcms.model.game.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@Controller
public class GameController {
    private static List<Game> games = new ArrayList<Game>();

    static {
    }
    
    @Autowired
    private ServletContext context;


    @RequestMapping(value = { "/game/games" }, method = RequestMethod.GET)
    public String gamesList(Model model) {
        games = GameDBO.loadGames();
        model.addAttribute("games", games);
         
        return "game/games";
    }

    @RequestMapping(value = { "/game/blocks" }, method = RequestMethod.GET)
    public String blocks(Model model) {
         
        return "game/blocks";
    }

    @RequestMapping(value = { "/game/discotrucks" }, method = RequestMethod.GET)
    public String discoTrucks(Model model) {
         
        return "game/discotrucks";
    }

    @RequestMapping(value = { "/game/ghosts" }, method = RequestMethod.GET)
    public String ghosts(Model model) {
         
        return "game/ghosts";
    }

    @RequestMapping(value = { "/game/pastelblocks" }, method = RequestMethod.GET)
    public String pastelBlocks(Model model) {
         
        return "game/pastelblocks";
    }

    @RequestMapping(value = { "/game/rhythmblocks" }, method = RequestMethod.GET)
    public String rhythmBlocks(Model model) {
         
        return "game/rhythmblocks";
    }

    @RequestMapping(value = { "/game/tankblocks" }, method = RequestMethod.GET)
    public String tankBlocks(Model model) {
         
        return "game/tankblocks";
    }

    @RequestMapping(value = { "/game/highscore" }, method = RequestMethod.POST,
            produces=MediaType.TEXT_EVENT_STREAM_VALUE)
 
    @ResponseBody 
    public ResponseEntity<ResponseBodyEmitter>  saveChatIdForm(Model model, @RequestParam Integer gameid, 
            @RequestParam Integer score) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/event-stream");
        headers.add("Cache-Control","no-cache");
        headers.add("Custom-Event-Source","high-score");
 
        List<Game> games = GameDBO.loadGames();
        List<Game> specificgame = games.stream()
            .filter((game) -> Objects.equals(game.getId(), gameid))
            .collect(Collectors.toList());        
        GameDBO.updateGame(specificgame.get(0));

        SseEmitter emitter = new SseEmitter();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            cachedThreadPool.execute(() -> {
                try {
                        emitter.send(SseEmitter
                                .event()
                                .name("message")
                                .data("[{\"highscore\":\""+score+"\","
                                        + "\"event\":\"high score\"}]"));
                    emitter.complete();
                } catch (Exception e) {
                    emitter.completeWithError(e);
                }
            });
        
        
       return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/game/addGame" }, method = RequestMethod.GET)
    public String addGame(Model model) {
 
        GameForm gameForm = new GameForm();
        model.addAttribute("gameForm", gameForm);
 
        return "game/addGame";
    }
 
    @RequestMapping(value = { "/game/addGame" }, method = RequestMethod.POST)
    public String addGameSave(Model model, //
        @ModelAttribute("gameForm") GameForm gameForm) {
        String title = gameForm.getTitle();
        Integer highScore = gameForm.getHighScore();
        String created = gameForm.getCreated();
        String lastUpdated = gameForm.getLastUpdated();
 
        if (title != null && title.length() > 0 
                && created != null && created.length() > 0 
                && lastUpdated != null && lastUpdated.length() > 0) {
            Game newGame = new Game(title, highScore, 
                    created, lastUpdated);
            games = GameDBO.loadGames();
            games.add(newGame);
            GameDBO.saveSQLGame(newGame);
            return "redirect:/game/games";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "game/addGame";
    }

     @RequestMapping(value = { "/game/editGame/{id}" }, method = RequestMethod.GET)
    public String editGameForm(Model model,
            @PathVariable("id") Integer id) {
 
        GameForm gameForm = new GameForm();
        GameForm gameEditForm = new GameForm();
        games = GameDBO.loadGames();
        Game editGame = games.stream()
            .filter((game) -> Objects.equals(game.getId(), id))
            .collect(Collectors.toList()).get(0);
        gameEditForm.setTitle(editGame.getTitle());
        gameEditForm.setHighScore(editGame.getHighScore());
        gameEditForm.setCreated(editGame.getCreated());
        gameEditForm.setLastUpdated(editGame.getLastUpdated());
        gameEditForm.setId(id);
        model.addAttribute("gameForm", gameForm);
        model.addAttribute("gameEditForm", gameEditForm);
        return "game/editGame/"+id;
    }
 
    @RequestMapping(value = { "/game/editGame/{id}" }, method = RequestMethod.POST)
    public String editGameSave(Model model, //
        @ModelAttribute("gameForm") GameForm gameForm, @PathVariable("id") Integer id) {
        String title = gameForm.getTitle();
        Integer highScore = gameForm.getHighScore();
        String created = gameForm.getCreated();
        String lastUpdated = gameForm.getLastUpdated();
 
        if (title != null && title.length() > 0 
                && highScore != null 
                && created != null && created.length() > 0 
                && lastUpdated != null && lastUpdated.length() > 0) {
            Game newGame = new Game(title, highScore, 
                    created, lastUpdated);
            newGame.setId(id);
            //games = GameDBO.loadGames();
            //games.add(newGame);
            GameDBO.updateGame(newGame);
            return "redirect:/game/games";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "game/editGame/{id}";
    }
   
    
}
