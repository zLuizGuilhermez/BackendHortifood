package com.hortifood.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hortifood.demo.entity.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
