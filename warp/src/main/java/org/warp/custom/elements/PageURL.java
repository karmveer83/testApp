package org.warp.custom.elements;



public class PageURL {
	String pageTitle;
	String pageURL;
	
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getPageURL() {
		return pageURL;
	}
	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}
	public PageURL(String pageTitle, String pageURL) {
		super();
		this.pageTitle = pageTitle;
		this.pageURL = pageURL;
	}
	@Override
	public String toString() {
		return "PageURL [pageTitle=" + pageTitle + ", pageURL=" + pageURL + "]";
	}


}
