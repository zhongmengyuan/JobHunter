package com.yuan.jobhunter.model;

import org.apache.commons.codec.digest.DigestUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author 1432816213@qq.com Date: 13-6-23 Time: 下午4:28
 */
@TargetUrl("https://www.liepin.com/job/*")
@HelpUrl("https://www.liepin.com/sojob/?dqs=020&curPage=\\d+")
public class LieTouJobInfo implements AfterExtractor {
	@ExtractBy(value = "//h1/text()", notNull = true)
	private String title = "";
	@ExtractBy(value = "//p[@class='job-item-title']/text()", notNull = true)
	private String salary = "";
	@ExtractBy(value = "//div[@class='title-info']/h3/a/text()", notNull = true)
	private String company = "";
	@ExtractBy("//div[@class='content content-word']/allText()")
	private String description = "";
	@ExtractBy(value = "//p[@class='basic-infor']/span/a/text()", notNull = true)
	private String area = "";
	private String source = "liepin.com";
	@ExtractByUrl
	private String url = "";
	@ExtractBy("//div[@class='job-qualifications']/html()")
	private String jobQualifications;
	private String urlMd5 = "";
	private String education;
	private String experience;
	private String language;
	private String age;
	private float salaryMax;
	private float salaryMin;
	private float salaryAve;

	public String getTitle() {
		return title;
	}

	public String getJobQualifications() {
		return jobQualifications;
	}

	public void setJobQualifications(String jobQualifications) {

		String[] split = jobQualifications.replace("<span>", "").replace("\n", "").split("</span>");
		setEducation(split[0].trim());
		setExperience(split[1].trim());
		setLanguage(split[2].trim());
		setAge(split[3].trim());
		this.jobQualifications = jobQualifications;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description != null) {
			this.description = description;
		}
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
		this.urlMd5 = DigestUtils.md5Hex(url);
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		if (this.salaryMax == 0.0) {
			if (salary.trim().equals("面议")) {
				this.salaryAve = -1;
				this.salaryMax = -1;
				this.salaryMin = -1;
			} else {
				String[] split = salary.split("-");
				this.salaryMin = Float.parseFloat(split[0]);
				this.salaryMax = Float.parseFloat(split[1].split("万")[0]);
				this.salaryAve = (this.salaryMax + this.salaryMin) / 2;
			}

		}
		this.salary = salary;
	}

	public void setSalaryMax(float salaryMax) {
		this.salaryMax = salaryMax;
	}

	public void setSalaryMin(float salaryMin) {
		this.salaryMin = salaryMin;
	}

	public void setSalaryAve(float salaryAve) {
		this.salaryAve = salaryAve;
	}

	@Override
	public String toString() {
		return "JobInfo{" + "title='" + title + '\'' + ", salary='" + salary + '\'' + ", company='" + company + '\''
				+ ", description='" + description + '\'' + ", source='" + source + '\'' + ", url='" + url + '\'' + '}';
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public void afterProcess(Page page) {
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

	public float getSalaryMax() {
		return salaryMax;
	}

	public float getSalaryMin() {
		return salaryMin;
	}

	public float getSalaryAve() {
		return salaryAve;
	}

}
