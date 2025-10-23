package com.tp.metier;

import com.tp.dao.IDao;
import com.tp.entities.Product;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductDaoImpl implements IDao<Product> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(Product product) {
        Session session = sessionFactory.openSession();
        session.save(product);
        return true;
    }

    @Override
    public boolean update(Product product) {
        sessionFactory.getCurrentSession().update(product);
        return true;
    }

    @Override
    public boolean delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
        return true;
    }

    @Override
    public Product findById(Long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product")
                .list();
    }
}
