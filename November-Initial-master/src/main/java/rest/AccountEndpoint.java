package rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import business.service.AccountService;
import util.JSONUtil;

@Path("/account")
public class AccountEndpoint {
	@Inject 
	private AccountService service;
	
	@Path("/operation/{id}")
	@GET
	@Produces({ "application/json" })
	public String findAccount(@PathParam("id") int id) {
		return JSONUtil.getJSONForObject(service.findAccount(id));
	}
	
	@Path("/operation")
	@GET
	@Produces({ "application/json" })
	public String findAccount() {
		return JSONUtil.getJSONForObject(service.findAccounts());
	}
	
	@Path("/operation")
	@POST
	@Produces({ "application/json" })
	public String addAccount(String account) {
		return service.createAccount(account);
	}
	
	@Path("/operation/{id}")
	@PUT
	@Produces({ "application/json" })
	public boolean updateAccount(@PathParam("id") int id,String account) {
		return service.updateAccount(id,account);
	}
	
	@Path("/operation/{id}")
	@PUT
	@Produces({ "application/json" })
	public boolean deleteAccount(@PathParam("id") int id) {
		return service.deleteAccount(id);
	}
	
	public void setService(AccountService service) {
		this.service = service;
	}
}
