package ch.tbz.leon_michel.friend_group;

import java.util.HashMap;
import java.util.List;

public class FriendGroup {
    private final HashMap<Person, List<Person>> friendsGroup;

    public FriendGroup() {
        this.friendsGroup = new HashMap<>();
    }

    public HashMap<Person, List<Person>> getFriendsGroup() {
        return friendsGroup;
    }

    public void printFriendsGroup(){
        for(Person person : friendsGroup.keySet()){
            for(Person friend : friendsGroup.get(person)){
                System.out.println(person.getFirstName() + " "
                        + person.getLastName() + " and " + friend.getFirstName() + " "
                        + friend.getLastName() + " are friends.");
            }
        }
    }

    public void searchForFriends(Person person){
        printFriends(person);
        friendsGroup.get(person).forEach(friend -> printFriends(friend, person));
    }

    public void printFriends(Person person){
        for(Person friend : friendsGroup.get(person)){
            System.out.println(person.getFirstName() + " "
                    + person.getLastName() + " and " + friend.getFirstName() + " "
                    + friend.getLastName() + " are friends.");
        }
    }
    public void printFriends(Person person, Person superiorFriend){
        for(Person friend : friendsGroup.get(person)){
            if(friend.equals(superiorFriend)){
                continue;
            }
            System.out.println(person.getFirstName() + " "
                    + person.getLastName() + " and " + friend.getFirstName() + " "
                    + friend.getLastName() + " are friends.");
        }
    }

    public void addFriend(Person person, List<Person> friends){
        friendsGroup.put(person, friends);
    }
}
