package com.example.demo.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.JpaModel.JpaModel;
@Repository
public interface Jparepository extends JpaRepository<JpaModel, String>{

}
