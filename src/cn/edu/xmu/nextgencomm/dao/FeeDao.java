package cn.edu.xmu.nextgencomm.dao;

import java.sql.Date;
import java.util.List;

import cn.edu.xmu.nextgencomm.model.Fee;

public interface FeeDao {

	/** 根据id获取对象 **/
	public Fee get(long id);

	/** 根据房间获取对象 **/
	public List<Fee> getByHouse(long house_id);

	/** 获取某日期的记录数 **/
	public int getCount(Date date);

	/** 根据日期获取对象 **/
	public List<Fee> get(Date date, int start, int offset);

	/** 根据房间和日期获取对象 **/
	public Fee get(long house_id, Date date);

	/** 获取所有记录数 **/
	public int getCount();

	/** 获取所有对象 **/
	public List<Fee> getAll(int start, int offset);

	/** 保存或更新对象 **/
	public void saveOrUpdate(Fee fee);

	/** 保存或更新集合中的对象 **/
	public void saveOrUpdate(List<Fee> fees);
}
