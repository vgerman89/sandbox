package svz.mantis.model;

import org.hibernate.annotations.Type;
import javax.persistence.*;

@Entity
@Table(name = "mantis_user_table")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Column(name = "username")
  @Type(type = "text")
  private String username;
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  public ContactData() {
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withUsername(String username) {
    this.username = username;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (username != null ? !username.equals(that.username) : that.username != null) return false;
    return email != null ? email.equals(that.email) : that.email == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }
}
