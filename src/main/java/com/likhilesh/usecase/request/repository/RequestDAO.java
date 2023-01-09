package com.likhilesh.usecase.request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.likhilesh.usecase.request.model.RequestModel;

public interface RequestDAO extends JpaRepository<RequestModel, Long>{

	@Query(value="SELECT id FROM messages WHERE (date<:date) OR (date=:date and time<=:time)", nativeQuery = true)
	List<Long> getMessageIDsToSend(@Param("date") String date, @Param("time") String time);
}
