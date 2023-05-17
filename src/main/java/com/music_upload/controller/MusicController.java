package com.music_upload.controller;

import com.music_upload.model.Music;
import com.music_upload.model.MusicForm;
import com.music_upload.model.MusicGenres;
import com.music_upload.service.MusicService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@PropertySource("classpath:upload_music.properties")
public class MusicController {
    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        List<Music> musicList = musicService.getAll();
        model.addAttribute("musicList", musicList);
        return "index";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("musicForm", new MusicForm());
        modelAndView.addObject("musicGenres", Arrays.stream(MusicGenres.values()).toList());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(MusicForm musicForm, RedirectAttributes redirectAttributes) throws Exception {
        boolean isSaveSuccessfully = musicService.save(musicForm);
        String message;
        if (isSaveSuccessfully) {
            message = "Created " + musicForm.getName() + " successfully!";
        } else {
            message = "Type of file is invalid! Types of file are valid : .mp3, .wav, .ogg, .m4p";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/create";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("edit");
        MusicForm musicForm = musicService.getByIdMusic(id);
        modelAndView.addObject("musicForm", musicForm);
        modelAndView.addObject("musicGenres", Arrays.stream(MusicGenres.values()).toList());
        return modelAndView;
    }
    @PostMapping("/edit/update")
    public ModelAndView update(MusicForm musicForm) throws Exception {
        ModelAndView modelAndView = new ModelAndView("edit");
        boolean isSaveSuccessfully = musicService.save(musicForm);
        String message;
        if (isSaveSuccessfully) {
            message = "Updated " + musicForm.getName() + " successfully!";
        } else {
            message = "Update FAIL";
        }
        modelAndView.addObject("message", message);
        modelAndView.addObject("musicForm", musicForm);
        modelAndView.addObject("musicGenres", Arrays.stream(MusicGenres.values()).toList());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        musicService.delete(id);
        return "redirect:/";
    }
}
