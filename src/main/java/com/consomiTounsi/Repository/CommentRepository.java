package com.consomiTounsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consomiTounsi.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
