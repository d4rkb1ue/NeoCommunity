package cn.edu.xmu.nextgencomm.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;

import cn.edu.xmu.nextgencomm.model.Dosage;

public interface DosageDao {

	/** ����id��ȡ���� **/
	public Dosage get(long id);

	/** ���ݷ����ȡ���� **/
	public List<Dosage> getByHouse(long house_id);

	/** ��ȡĳ���ڵļ�¼�� **/
	public int getCount(Date date);

	/** �������ڻ�ȡ���� **/
	public List<Dosage> get(Date date, int start, int offset);

	/** ���ݷ�������ڻ�ȡ���� **/
	public Dosage get(String serialNum, Date date);

	/** ��ȡ���м�¼�� **/
	public int getCount();

	/** ��ȡ���ж��� **/
	public List<Dosage> getAll(int start, int offset);

	/** �������¶��� **/
	public void saveOrUpdate(Dosage electricityDosage);

	/** �������¼����е����ж��� **/
	public void saveOrUpdate(List<Dosage> eds);

	/** ��ѯ����Ϊdate����serialNumƥ��string�Ķ��� **/
	public List<Dosage> get(Date date, String string);

}
