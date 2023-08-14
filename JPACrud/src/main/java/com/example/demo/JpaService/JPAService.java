package com.example.demo.JpaService;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.JpaModel.JpaModel;
import com.example.demo.JpaModel.Response;
import com.example.demo.jparepository.Jparepository;

@Service
public class JPAService {

	Response rsp = new Response();
	@Autowired
	Jparepository jparepo;
	@PersistenceContext
	EntityManager entityManager;

	public Response createUser(JpaModel values) {
		String uuid = UUID.randomUUID().toString();
		values.setsNo(uuid);
		values.setCreatedBy(uuid);
		values.setUpdatedBy(uuid);
		@SuppressWarnings("unused")
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		values.setCreatedDate(date);
		values.setUpdatedDate(date);
		values.setIsActive(1);
		try {

			jparepo.save(values);
			rsp.setData("User Created Successfully");
			rsp.setReponseCode(200);
			rsp.setResponseMessage("success");
		} catch (Exception e) {
			e.printStackTrace();
			rsp.setData("Cannot Created Successfully");
			rsp.setReponseCode(500);
			rsp.setResponseMessage("Error");
		}

		return rsp;

	}

	public Response getUser() {

		return rsp;
	}

	public Response updateUser(JpaModel values) {
		try {
			Optional<JpaModel> optobj = jparepo.findById(values.getsNo());
			if (optobj.isPresent()) {

				JpaModel update = optobj.get();
				update.setEmail(values.getEmail());

				jparepo.save(update);
				rsp.setData("User update Successfully");
				rsp.setReponseCode(200);
				rsp.setResponseMessage("success");

			} else {
				rsp.setData("Failed to update user ");
				rsp.setReponseCode(500);
				rsp.setResponseMessage("Error");

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return rsp;
	}

	public Response getOneUser() {

		return rsp;
	}

	public Response deleteUser(String sNo) {
		try {
			jparepo.deleteById(sNo);

			rsp.setData("User deleted Successfully");
			rsp.setReponseCode(200);
			rsp.setResponseMessage("success");

		} catch (Exception e) {
			e.printStackTrace();
			rsp.setData("Failed to delete user ");
			rsp.setReponseCode(500);
			rsp.setResponseMessage("Error");
		}
		return rsp;
	}

	public Response scamUser() {

		return rsp;
	}

	public Response loginUser(String email, String password) {
		try {
			Query query = entityManager
					.createQuery("SELECT u FROM JpaModel u WHERE u.email = :email AND u.password = :password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			@SuppressWarnings("unchecked")
			List<JpaModel> value = query.getResultList();
			if (value.isEmpty()) {
				rsp.setData("No such user");
				rsp.setReponseCode(200);
				rsp.setResponseMessage("success");
			} else {
				rsp.setData("Login Successfully!");
				rsp.setReponseCode(200);
				rsp.setResponseMessage("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rsp.setData("Login Failed  ");
			rsp.setReponseCode(500);
			rsp.setResponseMessage("Error");
		}
		return rsp;
	}

}
