package com.likhilesh.usecase.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likhilesh.usecase.response.model.ResponseModel;

public interface ResponseDAO extends JpaRepository<ResponseModel, Long>{

}
