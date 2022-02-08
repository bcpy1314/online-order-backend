package com.laioffer.onlineOrder.dao;
import com.laioffer.onlineOrder.entity.Authorities;
import com.laioffer.onlineOrder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository// similar to Component
// DAO is data access object
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;// session is an interface that have CURD operations
    public void signUp(Customer customer) {
        Session session = null;
        Authorities authorities =new Authorities();
        authorities.setEmail(customer.getEmail());
        authorities.setAuthorities("ROLE_USER");

        try{
            // create a new session instance
            session = sessionFactory.openSession();
            // if multiple tables involved, need to use transaction to ensure the atomicity of the operation
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            // use method in session to do operations, such as CURD for database
            session.getTransaction().commit();

        }catch (Exception ex){
            ex.printStackTrace();
            // if error occurs, can roll back to previous state to avoid any loss of data
            if(session != null) session.getTransaction().rollback();

        }finally{
            if(session != null){
                session.close();
            }

        }

    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            customer = session.get(Customer.class, email);

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return customer;
    }

}
