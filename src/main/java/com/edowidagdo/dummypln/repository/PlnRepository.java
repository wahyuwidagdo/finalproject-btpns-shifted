package com.edowidagdo.dummypln.repository;

import com.edowidagdo.dummypln.model.Pln;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlnRepository extends JpaRepository<Pln, Long> {
    List<Pln> findAll();
}
