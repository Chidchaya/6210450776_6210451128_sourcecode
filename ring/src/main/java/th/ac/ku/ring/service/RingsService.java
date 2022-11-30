package th.ac.ku.ring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.ring.model.Rings;
import th.ac.ku.ring.repository.RingsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RingsService {

    @Autowired
    private RingsRepository repository;

    public List<Rings> getAll(){
        return repository.findAll();
    }

    public Rings create(Rings rings){
        repository.save(rings);
        return rings;
    }

    public Rings getRings(UUID id){
        return repository.findById(id).get();
    }

    public Rings update(UUID id, Rings requestBody){
        Rings record = repository.findById(id).get();
        record.setName(requestBody.getName());
        record.setSize(requestBody.getSize());
        record.setPrice(requestBody.getPrice());
        record.setAmount(requestBody.getAmount());

        repository.save(record);
        return record;
    }

    public Rings delete(UUID id){
        Rings record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }
}
