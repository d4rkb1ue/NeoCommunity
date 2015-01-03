package cn.edu.xmu.nextgencomm.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;

import cn.edu.xmu.nextgencomm.model.Dosage;

public interface DosageDao {

	/** 根据id获取对象 **/
	public Dosage get(long id);

	/** 根据房间获取对象 **/
	public List<Dosage> getByHouse(long house_id);

	/** 获取某日期的记录数 **/
	public int getCount(Date date);

	/** 根据日期获取对象 **/
	public List<Dosage> get(Date date, int start, int offset);

	/** 根据房间和日期获取对象 **/
	public Dosage get(String serialNum, Date date);

	/** 获取所有记录数 **/
	public int getCount();

	/** 获取所有对象 **/
	public List<Dosage> getAll(int start, int offset);

	/** 保存或更新对象 **/
	public void saveOrUpdate(Dosage electricityDosage);

	/** 保存或更新集合中的所有对象 **/
	public void saveOrUpdate(List<Dosage> eds);

	/** 查询日期为date，且serialNum匹配string的对象 **/
	public List<Dosage> get(Date date, String string);

}
