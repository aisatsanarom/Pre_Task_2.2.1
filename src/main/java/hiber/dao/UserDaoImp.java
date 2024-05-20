package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public void getUserByCarModelAndSeries(String model, int series){
      User user = sessionFactory.getCurrentSession().createQuery("FROM User as u where u.car.model=:carModel and  u.car.series=:carSeries", User.class)
              .setParameter("carModel", model).setParameter("carSeries", series).uniqueResult();
      System.out.println(user);
   }







}
