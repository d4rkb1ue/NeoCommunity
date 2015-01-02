package cn.edu.xmu.nextgencomm.dao;

import java.sql.Date;
import java.util.List;

import cn.edu.xmu.nextgencomm.model.Fee;

public interface FeeDao {

	/** ����id��ȡ���� **/
	public Fee get(long id);

	/** ���ݷ����ȡ���� **/
	public List<Fee> getByHouse(long house_id);

	/** ��ȡĳ���ڵļ�¼�� **/
	public int getCount(Date date);

	/** �������ڻ�ȡ���� **/
	public List<Fee> get(Date date, int start, int offset);

	/** ���ݷ�������ڻ�ȡ���� **/
	public Fee get(long house_id, Date date);

	/** ��ȡ���м�¼�� **/
	public int getCount();

	/** ��ȡ���ж��� **/
	public List<Fee> getAll(int start, int offset);

	/** �������¶��� **/
	public void saveOrUpdate(Fee fee);

	/** �������¼����еĶ��� **/
	public void saveOrUpdate(List<Fee> fees);
}
