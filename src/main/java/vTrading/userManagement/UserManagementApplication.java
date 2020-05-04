package vTrading.userManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vTrading.userManagement.models.User;
import vTrading.userManagement.models.WatchList;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class UserManagementApplication {

	public static Map<Long, User> userCache;
	public static Map<Long, WatchList> watchlistCache;

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);

		userCache = new HashMap<>();
		watchlistCache = new HashMap<>();
	}

	public static void runTest() {
		userCache = new HashMap<>();
		watchlistCache = new HashMap<>();
	}

}
