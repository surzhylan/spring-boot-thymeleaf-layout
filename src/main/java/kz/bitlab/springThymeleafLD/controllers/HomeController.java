package kz.bitlab.springThymeleafLD.controllers;

import kz.bitlab.springThymeleafLD.model.Task;
import kz.bitlab.springThymeleafLD.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/task")
public class HomeController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/hometask")
    public String homePage(Model model){
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "home";
    }

    @PostMapping(value = "/addtask")
    public String addTask(@RequestParam(name = "name") String name,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "deadline") String deadline){

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setDeadlineDate(deadline);
        task.setCompleted(false);

        taskRepository.save(task);
        return "redirect:/task/hometask";
    }

    @GetMapping(value = "/details/{taskId}")
    public String details(@PathVariable(name = "taskId") Long id,
                          Model model){
        Task task = taskRepository.findById(id).orElse(null);
        model.addAttribute("task",task);
        return "details";
    }

    @PostMapping(value = "/savetask")
    public String updateTask(@RequestParam(name = "id") Long id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "deadline") String deadline,
                             @RequestParam(name = "completed") boolean completed){

        Task task = taskRepository.findById(id).orElse(null);

        if(task!=null) {
            task.setName(name);
            task.setDescription(description);
            task.setDeadlineDate(deadline);
            task.setCompleted(completed);
            taskRepository.save(task);
            return "redirect:/task/hometask";
        }

        return "redirect:/task/details/"+id;
    }

    @PostMapping(value = "/deletetask/{id}")
    public String deleteTask(@PathVariable(name = "id") Long id){
        taskRepository.deleteById(id);
        return "redirect:/task/hometask";
    }
}
