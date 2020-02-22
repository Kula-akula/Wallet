import kg.wallet.dao.UserDao;
import kg.wallet.model.User;

public class Main {
    public static void main(String[] args) {

        User user=new User();
        user.setName("gogo");
        user.setPassword("4asd2");
        UserDao userDao=new UserDao();
        userDao.createUser(user);
    }
}
