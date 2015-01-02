package cn.edu.xmu.nextgencomm.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.xmu.nextgencomm.dao.HouseDao;
import cn.edu.xmu.nextgencomm.model.House;

@Repository("houseDaoImpl")
public class HouseDaoImpl implements HouseDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public House get(long id) {
		House house = (House) sessionFactory.getCurrentSession().get(
				House.class, id);
		return house;
	}

	@Override
	public House get(String serialNum) {
		House house = (House) sessionFactory.getCurrentSession()
				.createQuery("from House where serialNum = ?")
				.setString(0, serialNum).uniqueResult();
		return house;
	}

	@Override
	public void saveOrUpadate(House house) {
		sessionFactory.getCurrentSession().saveOrUpdate(house);
	}

	@Override
	public void saveOrUpdate(List<House> houses) {
		Session session = sessionFactory.getCurrentSession();
		Iterator<House> iterator = houses.iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			session.saveOrUpdate(iterator.next());
			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
	}

	@Override
	public void delete(long id) {
		sessionFactory.getCurrentSession().delete(get(id));
	}

	@Override
	public void delete(House house) {
		sessionFactory.getCurrentSession().delete(house);
	}

	@Override
	public void delete(List<House> houses) {
		Session session = sessionFactory.getCurrentSession();
		Iterator<House> iterator = houses.iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			session.delete(iterator.next());
			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
	}

}
