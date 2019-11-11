package com.example.classes.web;


import com.example.classes.Classes;
import com.example.classes.ClassesDetailed;
import com.example.classes.repository.ClassesRepository;
import com.example.classes.repository.PaginationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;


import javax.naming.NameNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//end::baseClass[]
//tag::baseClass[]

@RestController
//@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassesRepository classesRepository;
    private PaginationDao paginationDao;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    TeacherService teacherService;
    @Autowired
    GroupService groupService;

    @Autowired
    public ClassesController(ClassesRepository classesRepository, PaginationDao paginationDao) {
        this.classesRepository = classesRepository;
        this.paginationDao = paginationDao;
    }

    @GetMapping("/ps")
    public Iterable<ClassesDetailed> getAllClasses(@RequestParam String orderBy, @RequestParam String direction,
                                               @RequestParam int page, @RequestParam int size) {
        PaginationService paginationService = new PaginationService(paginationDao);
        return getDetailedClassById(
                paginationService.findJsonDataByCondition(orderBy, direction, page, size));
    }


    @GetMapping("/classID/{id}")
    public Classes getClassById(@PathVariable("id") long id) throws NameNotFoundException {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        if (classes != null) {
            return classes;
        }
        throw new NameNotFoundException(
                "Class '" + id + "' not found");
    }

    @GetMapping("/psOrderBy")
    public Iterable<ClassesDetailed> getAllClasses(@RequestParam String orderBy) {
        PaginationService paginationService = new PaginationService(paginationDao);
        return getDetailedClassById(
                paginationService.findJsonDataByCondition(orderBy, "ASC",0,5));
    }

    @GetMapping
    public Iterable<ClassesDetailed> getAllClasses() {
        return getDetailedClassById(classesRepository.findAll());
    }

    @GetMapping("/find")
    public Iterable<ClassesDetailed> findClasses(@RequestParam String searchText) {
        return getDetailedClassById(classesRepository.findAllByName(searchText));
    }





    private ArrayList<ClassesDetailed> getDetailedClassById(Iterable<Classes> classes) {
        ArrayList<ClassesDetailed> classesNew = new ArrayList<>();
        classes.forEach(classes1 -> {
            String className = classes1.getName();
            System.out.println(classes1);
            long teacherId = classes1.getTeacherId();
            long groupId = classes1.getGroupId();
            String teacherName = teacherService.getTeacherNameById(teacherId);
            String groupName = groupService.getGroupNameById(groupId);
            classesNew.add(new ClassesDetailed(classes1.getId(), className, teacherName, groupName));
        });
        classesNew.forEach(s ->
                System.out.println(s.getId()));
        return classesNew;
    }






    @PostMapping("/classes/add")
    public Classes saveClass(@RequestBody @Valid Classes classes) {
        return classesRepository.save(classes);
    }

    @PutMapping("/classes/update/{id}")
    public void updateClass(@PathVariable long id, @RequestBody Classes classes) {
        System.out.println("argyn"+id);
        if (classes.getId() != id) {
            throw new IllegalStateException("Given class's ID doesn't match the ID in the path.");
        }
//        classes.setTeacherId(new Long(2));
//        classes.setGroupId(new Long(2));
        classesRepository.save(classes);
    }

    @DeleteMapping("/classes/delete/{id}")
    public void deleteClass(@PathVariable long id) {
        classesRepository.deleteById(id);
    }

}