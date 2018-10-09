package svz.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import svz.mantis.model.UserData;
import svz.mantis.model.Users;

import java.util.List;

public class DbHelper extends HelperBase {

  private final SessionFactory sessionFactory;

  public DbHelper(ApplicationManager app) {
    super(app);
// A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where enabled=1").list();
    System.out.println(result);
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public List<UserData> usersList() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where enabled=1 and access_level=25").list();
    session.getTransaction().commit();
    session.close();
    return result;
  }
}
