package br.com.model.misc;

import java.util.Iterator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.model.input.PageIn;

public class PageIterator implements Iterator<PageIn> {

	private Client client;
	private Gson gson;
	private PageIn page;
	private int pageNum; 
		
	public PageIterator() {
		super();

		this.client = ClientBuilder.newClient();		
		this.gson = new Gson();
		this.pageNum = -1; //TODO testar para ver se é 0 ou -1				
		//this.page = getPage();
	}	
	
	private PageIn getPage() {
		//target = client.target("http://smartwaysolucoes.com/item.json");
		String json = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=" + pageNum) 
							.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);		
		return gson.fromJson(json, PageIn.class);		
	}	
	
	
	@Override
	public boolean hasNext() {			
		return pageNum < page.getTotalPages();
	}

	@Override
	public PageIn next() {
		pageNum++;
		return getPage();
	}

}
