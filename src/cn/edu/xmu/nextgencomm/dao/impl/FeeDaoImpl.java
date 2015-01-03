package cn.edu.xmu.nextgencomm.dao.impl;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.xmu.nextgencomm.dao.FeeDao;
import cn.edu.xmu.nextgencomm.model.Fee;

@Repository
public class FeeDaoImpl implements FeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Fee get(long id) {
		Fee fee = (Fee) sessionFactory.getCurrentSession().get(Fee.class, id);
		return fee;
	}

	@Override
	public List<Fee> getByHouse(long house_id) {
		Session session = sessionFactory.getCurrentSession();
		List<Fee> fees = (List<Fee>) session
				.createQuery("from Fee where house_id = ?")
				.setLong(0, house_id).list();
		return fees;
	}

	@Override
	public int getCount(Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select count(*) from Fee where date = ?").setDate(0, date);
		int count = Integer.parseInt(query.uniqueResult().toString());
		return count;
	}

	@Override
	public List<Fee> get(Date date, int start, int offset) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Fee where date = ?").setDate(0,
				date);
		query.setFirstResult(start);
		query.setMaxResults(offset);
		List<Fee> fees = (List<Fee>) query.list();
		return fees;
	}

	@Override
	public Fee get(long house_id, Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from Fee where house_id = ? and date = ?")
				.setLong(0, house_id).setDate(1, date);
		Fee fee = (Fee) query.uniqueResult();
		return fee;
	}

	@Override
	public int getCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Fee");
		int count = Integer.parseInt(query.uniqueResult().toString());
		return count;
	}

	@Override
	public List<Fee> getAll(int start, int offset) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Fee");
		query.setFirstResult(start);
		query.setMaxResults(offset);
		List<Fee> fees = (List<Fee>) query.list();
		return fees;
	}

	@Override
	public void saveOrUpdate(Fee fee) {
		sessionFactory.getCurrentSession().saveOrUpdate(fee);
	}

	@Override
	public void saveOrUpdate(List<Fee> fees) {
		Session session = sessionFactory.getCurrentSession();
		Iterator<Fee> iterator = fees.iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			session.saveOrUpdate(iterator.next());
			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
	}

	@Override
	public void saveOrUpdate(String tableName, Map<String, Object> map) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(tableName, map);
	}

}
