package cn.edu.xmu.nextgencomm.dao.impl;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.xmu.nextgencomm.dao.DosageDao;
import cn.edu.xmu.nextgencomm.model.Dosage;

@Repository
public class DosageDaoImpl implements DosageDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Dosage get(long id) {
		Session session = sessionFactory.getCurrentSession();
		Dosage dosage = (Dosage) session.get(Dosage.class, id);
		return dosage;
	}

	@Override
	public List<Dosage> getByHouse(long house_id) {
		Session session = sessionFactory.getCurrentSession();
		List<Dosage> dosages = (List<Dosage>) session
				.createQuery("from Dosage where house_id = :house_ID")
				.setLong("house_ID", house_id).list();
		return dosages;
	}

	@Override
	public int getCount(Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select count(*) from Dosage where date = :Date").setDate(
				"Date", date);
		int count = Integer.parseInt(query.uniqueResult().toString());
		return count;
	}

	@Override
	public List<Dosage> get(Date date, int start, int offset) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Dosage where date = :Date")
				.setDate("Date", date);
		query.setFirstResult(start);
		query.setMaxResults(offset);
		List<Dosage> dosages = (List<Dosage>) query.list();
		return dosages;
	}

	@Override
	public Dosage get(String seriaLNum, Date date) {
		Session session = sessionFactory.getCurrentSession();
		Dosage dosage = (Dosage) session
				.createQuery(
						"from Dosage where seriaLNum = :SeriaLNum and date = :Date")
				.setString("SeriaLNum", seriaLNum).setDate("Date", date)
				.uniqueResult();
		return dosage;
	}

	@Override
	public int getCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Dosage");
		int count = (int) query.uniqueResult();
		return count;
	}

	@Override
	public List<Dosage> getAll(int start, int offset) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Dosage");
		query.setFirstResult(start);
		query.setMaxResults(offset);
		List<Dosage> dosages = (List<Dosage>) query.list();
		return dosages;
	}

	@Override
	public void saveOrUpdate(Dosage dosage) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(dosage);
	}

	@Override
	public void saveOrUpdate(List<Dosage> wds) {
		Session session = sessionFactory.getCurrentSession();
		Iterator<Dosage> iterator = wds.iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			session.saveOrUpdate(iterator.next());
			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
	}

	@Override
	public List<Dosage> get(Date date, String string) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from Dosage where date = ? and serialNum like ?")
				.setDate(0, date).setString(1, string);
		List<Dosage> dosages = (List<Dosage>) query.list();
		return dosages;
	}

}
