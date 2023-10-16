package com.epam.uladzislau.storage.repository;

import java.util.List;

import com.epam.uladzislau.storage.model.Storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findAll();

    Page<Storage> findAll(Pageable pageable);
}
