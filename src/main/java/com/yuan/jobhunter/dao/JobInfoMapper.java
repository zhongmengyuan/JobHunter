package com.yuan.jobhunter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yuan.jobhunter.base.mapper.IBaseMapper;
import com.yuan.jobhunter.model.LieTouJobInfo;

/**
 * @author code4crafer@gmail.com Date: 13-6-23 Time: 下午4:27
 */
public interface JobInfoMapper extends IBaseMapper {

	@Insert("insert into JobInfo (`title`,`salary`,`company`,`description`,`source`,`url`,`urlMd5`,`area`,`education`,`experience`,`language`,`age`,`salary_max`,`salary_ave`,`salary_min`) values (#{title},#{salary},#{company},#{description},#{source},#{url},#{urlMd5},#{area},#{education},#{experience},#{language},#{age},#{salaryMax},#{salaryAve},#{salaryMin})")
	public int add(LieTouJobInfo jobInfo);

	@Select("select * from JobInfo where id=#{id}")
	public LieTouJobInfo getById(long id);

	@Select("select count(*) from JobInfo")
	public Integer getTotalCount();

	public List<LieTouJobInfo> getByKeyWord(@Param("keyword") String keyword, @Param("start") int start,
			@Param("size") int size, @Param("area") String area, @Param("company") String company,
			@Param("title") String title, @Param("education") String education, @Param("salary") String salary);

	public Integer getCountByKeyWord(@Param("keyword") String keyword, @Param("area") String area,
			@Param("company") String company, @Param("title") String title, @Param("education") String education,
			@Param("salary") String salary);

	@Select("select * from JobInfo where title like  CONCAT('%',#{title},'%') limit #{start},#{size}")
	public List<LieTouJobInfo> getByTitle(@Param("title") String title, @Param("start") int start,
			@Param("size") int size);

	@Select("select count(*) from JobInfo where title like  CONCAT('%',#{title},'%') ")
	public Integer getCountByTitle(@Param("title") String title);

	@Select("select * from JobInfo where salary like  CONCAT('%',#{salary},'%') limit #{start},#{size}")
	public List<LieTouJobInfo> getBySalary(@Param("salary") String salary, @Param("start") int start,
			@Param("size") int size);

	@Select("select count(*) from JobInfo where salary like  CONCAT('%',#{salary},'%') ")
	public List<LieTouJobInfo> getCountBySalary(@Param("salary") String salary);

	@Select("select * from JobInfo where company like  CONCAT('%',#{company},'%') limit #{start},#{size}")
	public List<LieTouJobInfo> getByCompany(@Param("company") String company, @Param("start") int start,
			@Param("size") int size);

	@Select("select count(*) from JobInfo where company like  CONCAT('%',#{company},'%') ")
	public Integer getCountByCompany(@Param("company") String company);

	// -- 查询招聘的公司
	@Select("SELECT DISTINCT company from jobinfo")
	public List<LieTouJobInfo> getAllCompany();

	// 查询所有公司数量
	@Select("SELECT count(distinct company) from jobinfo")
	public Integer getAllCompanyCount();

	@Select("SELECT count(distinct area) from jobinfo")
	public Integer getAllAreaCount();

	@Select("SELECT count(distinct title) from jobinfo")
	public Integer getAllJobCount();

	// -- 查询所有公司在招岗位数
	@Select(" select DISTINCT company,COUNT(1) as num from jobinfo GROUP BY company ORDER BY num desc limit #{start},#{size}")
	public List<Map<String, String>> getAllCompanyAndNum(@Param("start") int start, @Param("size") int size);

	// -- 查询招聘最多的岗位
	@Select("select DISTINCT title,COUNT(1) as num from jobinfo GROUP BY title  ORDER BY num desc limit #{start},#{size}")
	public List<Map<String, String>> getAllTitleAndNum(@Param("start") int start, @Param("size") int size);

	// -- 查询不同地方招聘岗位数量--以区为单位
	@Select("select DISTINCT area,COUNT(1) as num from jobinfo GROUP BY area  ORDER BY num desc limit #{start},#{size}")
	public List<Map<String, String>> getAllAreaAndNum(@Param("start") int start, @Param("size") int size);

	@Select("select DISTINCT experience,COUNT(1) as num from jobinfo GROUP BY experience  ORDER BY num desc limit #{start},#{size}")
	public List<Map<String, Object>> getAllExperienceAndNum(@Param("start") int start, @Param("size") int size);

	@Select("select DISTINCT age,COUNT(1) as num from jobinfo GROUP BY age  ORDER BY num desc limit #{start},#{size}")
	public List<Map<String, Object>> getAllAgeAndNum(@Param("start") int start, @Param("size") int size);

	@Select("select DISTINCT education,COUNT(1) as num from jobinfo GROUP BY education  ORDER BY num desc limit #{start},#{size}")
	public List<Map<String, Object>> getAllEducationAndNum(@Param("start") int start, @Param("size") int size);

	@Select("delete  from jobinfo where area='' or company='' or salary=''")
	public Integer deleteUsedData();

	// 获得所有的薪水
	@Select("select distinct salary from jobinfo")
	public List<LieTouJobInfo> getAllsalary();

	@Update("update jobinfo set salary_max=#{salaryMax},salary_ave=#{salaryAve},salary_min=#{salaryMin} where salary=#{salary}")
	public Integer updateSalary(LieTouJobInfo lieTouJobInfo);
}
