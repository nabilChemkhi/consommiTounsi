package com.consomiTounsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consomiTounsi.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
