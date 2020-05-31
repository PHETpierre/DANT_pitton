package com.dant.app;

import com.dant.entity.Account;
import com.dant.entity.Column;
import com.dant.entity.DataBase;
import com.dant.entity.Table;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pitton on 2017-02-20.
 */
@Path("/api/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestEndpoint {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String helloWorld() {
		System.out.println("HELLO WORLD!");

		return "Hello World";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/list")
		public List<String> getListInParams(@QueryParam("ids") List<String> ids) { //?id=["leiya","popo"]
		System.out.println("the id you've inserted: " +ids);
		return ids;
	}

	@POST
	@Path("/entity")
	public Account getAccount(Account account) {
		System.out.println("Received account " + account);
		account.setUpdated(System.currentTimeMillis());
		return account;
	}

	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}

	@GET
	@Path("/test")
	public Response exception2(){
		throw new RuntimeException("test erreur");
	}




}
