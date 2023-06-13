import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;




public class UserTest {

	public static void main(String[] args) {
		Users users1 = new Users("abc.com", "1234", "tuanvy");
		
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = managerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(users1);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		managerFactory.close();
		
		System.out.println("okeee");
	}

}
