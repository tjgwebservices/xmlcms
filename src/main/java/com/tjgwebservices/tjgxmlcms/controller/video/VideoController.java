package com.tjgwebservices.tjgxmlcms.controller.video;

import com.tjgwebservices.tjgxmlcms.dbo.review.ReviewDBO;
import com.tjgwebservices.tjgxmlcms.dbo.video.ArtistDBO;
import com.tjgwebservices.tjgxmlcms.dbo.video.VideoDBO;
import com.tjgwebservices.tjgxmlcms.form.ArtistForm;
import com.tjgwebservices.tjgxmlcms.form.school.VideoForm;
import com.tjgwebservices.tjgxmlcms.form.review.ReviewForm;
import com.tjgwebservices.tjgxmlcms.model.video.Artist;
import com.tjgwebservices.tjgxmlcms.model.FileUpload;
import com.tjgwebservices.tjgxmlcms.model.video.Video;
import com.tjgwebservices.tjgxmlcms.model.review.Review;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class VideoController {

    private List<Video> videos = new ArrayList<>();
    private List<Artist> artists = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    private String[] validFileExtensions = {"mpg","mp4","ogg","avi","jpg","gif","png"};
    
    @Autowired
    ServletContext context;

    static {
    }    
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;

    @Value("${title.message}")
    private String titleMessage;

    @RequestMapping(value = { "/videos/videoList" }, method = RequestMethod.GET)
    public String videoList(Model model) {
        videos = VideoDBO.loadVideos();
        model.addAttribute("videos", videos);
        artists = ArtistDBO.loadArtists();
        model.addAttribute("artists", artists);
        return "videos/videoList";
    }

    @RequestMapping(value = { "/videos/addVideo" }, method = RequestMethod.GET)
    public String addVideoForm(Model model) {
 
        VideoForm videoForm = new VideoForm();
        model.addAttribute("videoForm", videoForm);
        titleMessage = "Add Video";
        model.addAttribute("titleMessage", titleMessage); 
 
        return "videos/addVideo";
    }
 
    @RequestMapping(value = { "/videos/addVideo" }, method = RequestMethod.POST)
    public String addVideoSave(@Validated VideoForm file, BindingResult result,
            Model model, //
        @ModelAttribute("videoForm") VideoForm videoForm) {
        String videoName = videoForm.getVideoName();
        if (result.hasErrors() ||
                videoName == null || videoName.length() < 0){
            System.out.println("validation errors");
            String error = "All fieds are required!";
            model.addAttribute("errorMessage", errorMessage);
            return "videos/addVideo";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = file.getVideoContent();
        String uploadPath = context.getRealPath("") + File.separator +
                "temp" + File.separator;
            try {
                Optional<String> fileExtension = Optional.ofNullable(multipartFile.getOriginalFilename())
                .filter(f -> f.contains("."))
                .map(f -> f.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1));
                boolean validFile = false;
                for (String extension : validFileExtensions) {
                    if (fileExtension.isPresent() && 
                        fileExtension.get().equalsIgnoreCase(extension)){
                        validFile = true;
                        break;
                    }
                }
                if (validFile){
                    model.addAttribute("message", "Valid file uploaded");
                } else {
                    String error = "Only image and video files for lecture poster!";
                    model.addAttribute("errorMessage", error);
                    return "videos/addVideo";
                }
                String filePath = uploadPath+multipartFile.getOriginalFilename();
                FileCopyUtils.copy(multipartFile.getBytes(),
                        new File(filePath
                        ));
                        String fileName = multipartFile.getOriginalFilename();
                        Video video = new Video(videoName,filePath,multipartFile);
                        model.addAttribute("fileName",fileName);
                        videos.add(video);
                        VideoDBO.saveSQLVideo(video);
                        return "redirect:/videos/videoList";
            } catch (IOException ex) {
                Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
                String error = "All fieds are required!";
                model.addAttribute("errorMessage", error);
                return "videos/addVideo";
            }
        }
    }
    
    @RequestMapping(value = { "/videos/addArtist" }, method = RequestMethod.GET)
    public String addArtistForm(Model model) {
 
        ArtistForm artistForm = new ArtistForm();
        model.addAttribute("artistForm", artistForm);
        titleMessage = "Add Artist";
        model.addAttribute("titleMessage", titleMessage); 

        return "videos/addArtist";
    }
 
    @RequestMapping(value = { "/videos/addArtist" }, method = RequestMethod.POST)
    public String addArtistSave(Model model, //
        @ModelAttribute("artistForm") ArtistForm artistForm) {
        String artistName = artistForm.getArtistName();

        if (artistName != null && artistName.length() > 0 ){
            Artist artist = new Artist(artistName);
            artists.add(artist);
            ArtistDBO.saveSQLArtist(artist);
            return "redirect:/videos/videoList";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "schools/addArtist";
    }
    
    
}
