package com.kabdi.springjwt.payload;

import com.google.gson.Gson;

public class PaginationResult {
	
	int currentPage;
	int itemsPerPage;
	int totalItems;
	int totalPages;
	
	
	public PaginationResult(int currentPage, int itemsPerPage, int totalItems, int totalPages) {
		super();
		this.currentPage = currentPage + 1;
		this.itemsPerPage = itemsPerPage;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
	}
	
	public String toJson() {
		Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
