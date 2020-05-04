package vTrading.userManagement.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    //private Portfolio portfolio;
    @ElementCollection(targetClass=Integer.class)
    private Set<Long> watchlists;

    public User() {

    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        //this.portfolio = new Portfolio();
        this.watchlists = new HashSet<>();
    }

    public void addWatchlist(long watchlistId) {
        watchlists.add(watchlistId);
    }

    public void removeWatchlist(long watchlistId) {
        watchlists.remove(watchlistId);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    //public Portfolio getPortfolio() {
    //    return portfolio;
    //}

    public Set<Long> getWatchLists() {
        return watchlists;
    }

}
