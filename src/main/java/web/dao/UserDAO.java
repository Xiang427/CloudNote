package web.dao;
import org.springframework.stereotype.Repository;
import web.entity.User;
@Repository("userDAO")
public interface UserDAO {
    public User findByName(String name);
    public void save(User user);
}
