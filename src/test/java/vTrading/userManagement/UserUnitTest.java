package vTrading.userManagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import vTrading.userManagement.controllers.UserController;
import vTrading.userManagement.models.User;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserUnitTest {

    @Autowired
    private UserController userController;

    @Test
    public void CreateUser1() {
        UserManagementApplication.runTest();
        User user = userController.createUser("Jeff", "Bezos");
        assertThat(UserManagementApplication.userCache.get(user.getId())).isEqualTo(user);
        assertThat(user.getFirstName()).isEqualTo("Jeff");
        assertThat(user.getLastName()).isEqualTo("Bezos");
        assertThat(user.getWatchLists()).isEmpty();
    }

    @Test
    public void CreateUserFail1() {
        assertThatIllegalArgumentException().isThrownBy(() -> { userController.createUser("Jeff", ""); });
        assertThatIllegalArgumentException().isThrownBy(() -> { userController.createUser("", "Bezos"); });
        assertThatIllegalArgumentException().isThrownBy(() -> { userController.createUser("", ""); });
    }

    @Test
    public void GetUser1() {
        UserManagementApplication.runTest();
        Long id = userController.createUser("Jeff", "Bezos").getId();
        User user = userController.getUser(id);
        assertThat(UserManagementApplication.userCache.get(user.getId())).isEqualTo(user);
        assertThat(user.getFirstName()).isEqualTo("Jeff");
        assertThat(user.getLastName()).isEqualTo("Bezos");
        assertThat(user.getWatchLists()).isEmpty();
    }

    @Test
    public void GetUserFail1() {
        UserManagementApplication.runTest();
        assertThatThrownBy(() -> {
            userController.getUser(-1);
        }).isInstanceOf(NoSuchElementException.class);
    }

}
