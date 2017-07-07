package com.yuan.jobhunter.model.vo;

/**
 * @author 1432816213@qq.com Date: 13-6-23 Time: 下午4:28
 */
public class LieTouJobInfoDto {

	private String keyword;
	private String description = "";
	private String area = "";
	private String source = "liepin.com";
	private String url = "";
	private String jobQualifications;
	private String urlMd5 = "";
	private String education;
	private String salary;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String experience;
	private String language;
	private String age;
	private String company;
	private String title;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJobQualifications() {
		return jobQualifications;
	}

	public void setJobQualifications(String jobQualifications) {
		this.jobQualifications = jobQualifications;
	}

	public String getUrlMd5() {
		return urlMd5;
	}

	public void setUrlMd5(String urlMd5) {
		this.urlMd5 = urlMd5;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

}
