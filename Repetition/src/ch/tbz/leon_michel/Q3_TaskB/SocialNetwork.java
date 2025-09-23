package ch.tbz.leon_michel.Q3_TaskB;

import java.util.*;

public class SocialNetwork {
    private Map<Person, List<Person>> graph = new HashMap<>();

    public void addPerson(Person person) {
        graph.putIfAbsent(person, new ArrayList<>());
    }

    public void addFriendship(Person p1, Person p2) {
        graph.get(p1).add(p2);
        graph.get(p2).add(p1);
    }

    public Person findPersonWithCar(Person start) {
        if (start.hasCar()) {
            return start;
        }

        Set<Person> visited = new HashSet<>();
        Queue<Person> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            List<Person> friends = graph.getOrDefault(current, new ArrayList<>());
            for (Person friend : friends) {
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    if (friend.hasCar()) {
                        return friend;
                    }
                    queue.add(friend);
                }
            }
        }

        return null;
    }
}