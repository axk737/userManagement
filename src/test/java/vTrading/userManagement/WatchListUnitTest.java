package vTrading.userManagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vTrading.userManagement.controllers.UserController;
import vTrading.userManagement.controllers.WatchListController;
import vTrading.userManagement.models.User;
import vTrading.userManagement.models.WatchList;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
public class WatchListUnitTest {

    @Autowired
    private UserController userController;
    @Autowired
    private WatchListController watchlistController;

    @Test
    public void CreateWatchList() {
        UserManagementApplication.runTest();
        Long userId = userController.createUser("Jeff", "Bezos").getId();
        WatchList watchlist1 = watchlistController.createWatchList(userId, "myTechWatchlist");
        assertThat(UserManagementApplication.watchlistCache.get(watchlist1.getId())).isEqualTo(watchlist1);
        assertThat(watchlist1.getName()).isEqualTo("myTechWatchlist");
        assertThat(watchlist1.getTickers()).isEmpty();
    }

    @Test
    public void DeleteWatchList1() {
        UserManagementApplication.runTest();
        Long userId = userController.createUser("Jeff", "Bezos").getId();
        Long watchlistId = watchlistController.createWatchList(userId, "testDelete").getId();
        watchlistController.deleteWatchList(userId, watchlistId);
        User user = userController.getUser(userId);

        assertThat(user.getWatchLists().contains(watchlistId)).isFalse();
        assertThatThrownBy(() -> {
            watchlistController.getWatchList(watchlistId);
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void GetWatchList1() {
        UserManagementApplication.runTest();
        Long userId = userController.createUser("Jeff", "Bezos").getId();
        Long watchlistId = watchlistController.createWatchList(userId, "myTechWatchlist").getId();
        WatchList watchlist = watchlistController.getWatchList(watchlistId);
        assertThat(watchlist.getName()).isEqualTo("myTechWatchlist");
    }

    @Test
    public void GetWatchListFail1() {
        UserManagementApplication.runTest();
        assertThatThrownBy(() -> {
            watchlistController.getWatchList(new Long(-1));
        }).isInstanceOf(NoSuchElementException.class);
    }


}
