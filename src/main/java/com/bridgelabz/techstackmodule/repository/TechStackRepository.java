package com.bridgelabz.techstackmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.techstackmodule.model.TechStackModel;
@Repository
public interface TechStackRepository extends JpaRepository<TechStackModel, Long> {

}
