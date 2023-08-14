package com.jdbc.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.jdbc.demo.model.Response;
import com.jdbc.demo.model.SignUpModel;
import com.jdbc.demo.service.SignUpService;

@Component
public class SignUpDao implements SignUpService {
	Response rsp = new Response();
	String url = "jdbc:mysql://127.0.0.1:3306/kg";
	String username = "root";
	String password = "praveen7200";

	public Response createUser(SignUpModel values) {
		String uuid = UUID.randomUUID().toString();
		values.setsNo(uuid);
		values.setCreatedBy(uuid);
		values.setCreatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		values.setCreatedDate(date);
		values.setUpdatedDate(date);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();) {
				String insertQuery = "INSERT INTO kg.user_details(s_no,first_name,last_name,email_id,dob,gender,mobile_number,password,created_by,updated_by,created_date,updated_date)"
						+ "VALUES('" + values.getsNo() + "','" + values.getFirstName() + "','" + values.getLastName()
						+ "','" + values.getEmail() + "','" + values.getDob() + "','" + values.getGender() + "',"
						+ values.getMobileNumber() + ",'" + values.getPassword() + "','" + values.getCreatedBy() + "','"
						+ values.getUpdatedBy() + "','" + values.getCreatedDate() + "','" + values.getUpdatedDate()
						+ "')";
				st.executeUpdate(insertQuery);
				rsp.setData("user Created Successfully!");
				rsp.setReponseCode(200);
				rsp.setResponseMessage("success");

			} catch (Exception e) {
				e.printStackTrace();
				rsp.setData("Cannot create user!");
				rsp.setReponseCode(500);
				rsp.setResponseMessage("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rsp.setData("Driver class error!");
			rsp.setReponseCode(500);
			rsp.setResponseMessage("error");
		}
		return rsp;

	}

	@SuppressWarnings("unchecked")
	public Response getUser() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String selectQuery = "select * from user_details;";
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(selectQuery);) {
				JSONArray jsonArray = new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("sNo", rs.getString("s_no"));
					jsonObject.put("firstName", rs.getString("first_name"));
					jsonObject.put("lastName", rs.getString("last_name"));
					jsonObject.put(" email", rs.getString("email_id"));
					jsonObject.put("gender", rs.getString("gender"));
					jsonObject.put(" mobileNumber", rs.getString("mobile_number"));
					jsonObject.put("createdBy", rs.getString("created_by"));
					jsonObject.put("createdDate", rs.getString("s_no"));
					jsonArray.add(jsonObject);
				}
				rsp.setData("success");
				rsp.setjData(jsonArray);
				rsp.setReponseCode(200);
				rsp.setResponseMessage("Data Fetched Successfully");
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsp;
	}

	@SuppressWarnings("unchecked")
	public Response getOneUser(String s_no) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String selectQuery = "select * from user_details where s_no='" + s_no + "'";
			JSONObject jsonObject = new JSONObject();
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(selectQuery);) {
				while (rs.next()) {

					jsonObject.put("sNo", rs.getString("s_no"));
					jsonObject.put("firstName", rs.getString("first_name"));
					jsonObject.put("lastName", rs.getString("last_name"));
					jsonObject.put(" email", rs.getString("email_id"));
					jsonObject.put("gender", rs.getString("gender"));
					jsonObject.put(" mobileNumber", rs.getString("mobile_number"));
					jsonObject.put("createdBy", rs.getString("created_by"));

				}
				rsp.setData("success");
				rsp.setjData(jsonObject);
				rsp.setReponseCode(200);
				rsp.setResponseMessage("Data Fetched Successfully");
			} catch (Exception e) {
				rsp.setData("Error");
				rsp.setReponseCode(500);
				rsp.setResponseMessage("Logic Error");
				e.printStackTrace();
			}

		} catch (Exception e) {
			rsp.setData("Error");
			rsp.setReponseCode(500);
			rsp.setResponseMessage("Driver Error");
			e.printStackTrace();
		}
		return rsp;
	}

	public Response updateUser(String s_no, String email) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();) {
				String selectQuery = "update kg.user_details set email_id = '" + email + "' where s_no='" + s_no + "';";
				st.executeUpdate(selectQuery);

				rsp.setData("update successfully");
				rsp.setReponseCode(200);
				rsp.setResponseMessage(" Success");
			} catch (Exception e) {
				rsp.setData("Error");
				rsp.setReponseCode(500);
				rsp.setResponseMessage("Logic Error");
				e.printStackTrace();
			}

		} catch (Exception e) {
			rsp.setData("Error");
			rsp.setReponseCode(500);
			rsp.setResponseMessage("Driver Error");
			e.printStackTrace();
		}
		return rsp;

	}

	public Response deleteUser(String s_no) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();) {
				String deleteQuery = "Delete kg.user_details  where s_no='" + s_no + "';";
				st.executeUpdate(deleteQuery);

				rsp.setData("Deleted successfully");
				rsp.setReponseCode(200);
				rsp.setResponseMessage(" Success");
			} catch (Exception e) {
				rsp.setData("Error");
				rsp.setReponseCode(500);
				rsp.setResponseMessage("Logic Error");
				e.printStackTrace();
			}

		} catch (Exception e) {
			rsp.setData("Error");
			rsp.setReponseCode(500);
			rsp.setResponseMessage("Driver Error");
			e.printStackTrace();
		}
		return rsp;
	}

}
