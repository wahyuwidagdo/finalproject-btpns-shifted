package com.edowidagdo.dummypln.controller;

import com.edowidagdo.dummypln.model.Pln;
import com.edowidagdo.dummypln.repository.PlnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlnController {

    @Autowired
    PlnRepository plnRepository;

    @GetMapping("/pln")
    public ResponseEntity<List<Pln>> getPln(@RequestParam(required = false) String args) {
        List<Pln> plns = new ArrayList<>();

        plnRepository.findAll().forEach(plns::add);

        if(plns.isEmpty()) {
            return new ResponseEntity<>(plns, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(plns, HttpStatus.OK);
    }

    @PostMapping("/addPln")
    public ResponseEntity<Pln> createPln(@RequestBody Pln pln) {
        Pln addPln = plnRepository.save(pln);
        return new ResponseEntity<>(addPln, HttpStatus.CREATED);
    }
}
