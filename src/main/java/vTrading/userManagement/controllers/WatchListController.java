package vTrading.userManagement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vTrading.userManagement.UserManagementApplication;
import vTrading.userManagement.models.User;
import vTrading.userManagement.models.WatchList;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WatchListController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/createWatchList")
    public WatchList createWatchList(@RequestParam long userId, @RequestParam String name) {
        WatchList watchlist = new WatchList(counter.incrementAndGet(), name);
        UserManagementApplication.watchlistCache.put(watchlist.getId(), watchlist);
        User user = UserManagementApplication.userCache.get(userId);
        user.addWatchlist(watchlist.getId());
        return watchlist;
    }

    @GetMapping("/deleteWatchList")
    public void deleteWatchList(@RequestParam long userId, @RequestParam long watchlistId) {
        User user = UserManagementApplication.userCache.get(userId);
        UserManagementApplication.watchlistCache.remove(watchlistId);
        user.removeWatchlist(watchlistId);
    }

    @GetMapping("/getWatchList")
    public WatchList getWatchList(@RequestParam long watchlistId) throws NoSuchElementException {
        if (!UserManagementApplication.watchlistCache.containsKey(watchlistId)) {
            throw new NoSuchElementException();
        }
        return UserManagementApplication.watchlistCache.get(watchlistId);
    }

}
