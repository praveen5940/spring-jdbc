package com.jdbc.demo.service;

import org.springframework.stereotype.Service;

import com.jdbc.demo.model.Response;
import com.jdbc.demo.model.SignUpModel;

@Service
public interface SignUpService {

	public Response createUser(SignUpModel values);

	public Response getUser();

	public Response getOneUser(String s_no);

	public Response updateUser(String s_no, String email_id);

	public Response deleteUser(String s_no);

}
