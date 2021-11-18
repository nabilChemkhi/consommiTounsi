package com.consomiTounsi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consomiTounsi.entities.Likes;

public interface LikeRepository extends JpaRepository<Likes, Long> {

}
