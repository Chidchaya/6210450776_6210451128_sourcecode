package th.ac.ku.ring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.ring.model.Rings;
import th.ac.ku.ring.service.RingsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rings")
public class RingsController {

    @Autowired
    private RingsService service;

    @GetMapping
    public List<Rings> getAll(){
        return service.getAll();
    }

    @PostMapping
    public Rings create(@RequestBody Rings rings){
        return service.create(rings);
    }

    @GetMapping("/{id}")
    public Rings getRings(@PathVariable UUID id){
        return service.getRings(id);
    }

    @PutMapping("/{id}")
    public Rings update(@PathVariable UUID id, @RequestBody Rings rings){
        return service.update(id, rings);
    }

    @DeleteMapping("/{id}")
    public Rings delete(@PathVariable UUID id){
        return service.delete(id);
    }
}
