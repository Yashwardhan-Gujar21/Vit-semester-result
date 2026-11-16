package jar.controller;

import jar.model.StudentResult;
import jar.repository.StudentResultRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResultController {

    private final StudentResultRepository repository;

    public ResultController(StudentResultRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("studentResult", new StudentResult());
        model.addAttribute("savedResults", repository.findAll());
        return "result";
    }

    @PostMapping("/generate")
    public String generateResult(@ModelAttribute StudentResult studentResult, Model model) {
        StudentResult saved = repository.save(studentResult);
        model.addAttribute("currentMarksheet", saved);
        model.addAttribute("studentResult", new StudentResult());
        model.addAttribute("savedResults", repository.findAll());
        return "result";
    }

    @GetMapping("/view/{id}")
    public String viewMarksheet(@PathVariable Long id, Model model) {
        repository.findById(id).ifPresent(res -> model.addAttribute("currentMarksheet", res));
        model.addAttribute("studentResult", new StudentResult());
        model.addAttribute("savedResults", repository.findAll());
        return "result";
    }
}