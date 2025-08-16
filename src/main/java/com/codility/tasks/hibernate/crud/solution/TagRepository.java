package com.codility.tasks.hibernate.crud.solution;

import org.springframework.data.jpa.repository.JpaRepository;

interface TagRepository extends JpaRepository<Tag, Long> {
}
