package com.dant.app;

import com.dant.entity.Account;
import com.dant.entity.Column;
import com.dant.entity.DataBase;
import com.dant.entity.Table;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
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

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes({MediaType.TEXT_PLAIN,MediaType.TEXT_HTML})
	@Path("/list")
		public ArrayList<Integer> getListInParams(@QueryParam("ids") List<String> ids,
											@QueryParam("name") String name,
											String body) {
		String something = body + "!";
		System.out.println("the id you've inserted: " +ids);
		System.out.println("The name you gave: " + name);
		System.out.println("The body: " + something);
		ArrayList<String> listIds = (ArrayList<String>) ids;
		ArrayList<Integer> lisIdsint = new ArrayList<>();

		for (String s: listIds) {
			lisIdsint.add(Integer.parseInt(s));
		}

 		System.out.println("The list of INTEGER: " + lisIdsint);


		return lisIdsint;
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

	@POST
	@Path("/insert")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadImage(
			@FormDataParam("data") InputStream uploadedInputStream,
			@FormDataParam("data") FormDataContentDisposition fileDetails) {

		System.out.println(fileDetails.getFileName());

		String uploadedFileLocation = "Test/" + fileDetails.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).build();
	}
	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
							 String uploadedFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
