package lbycpa2.module6;

import java.util.ArrayList;
import java.util.List;

public class ProfileGraph {
    private Profile[] profiles;
    private int[][] adjacencyMatrix;
    private int numProfiles;

    public ProfileGraph(int maxSize) {
        profiles = new Profile[maxSize];
        adjacencyMatrix = new int[maxSize][maxSize];
        numProfiles = 0;
    }

    public void addFriend(Profile p1, Profile p2) {
        if (p1 != null && p2 != null) {
            int index1 = p1.getIndex();
            int index2 = p2.getIndex();
            System.out.println(p1.getName() + " index: "+index1);
            System.out.println(p2.getName() + " index: "+index2);
            adjacencyMatrix[index1][index2] = 1;
            adjacencyMatrix[index2][index1] = 1;
        }
    }

    public void removeFriend(Profile p1, Profile p2) {
        if (p1 != null && p2 != null) {
            int index1 = p1.getIndex();
            int index2 = p2.getIndex();
            adjacencyMatrix[index1][index2] = 0;
            adjacencyMatrix[index2][index1] = 0;
        }
    }

    public List<Profile> getFriends(Profile profile) {
        if (profile == null) {
            return null;
        }

        List<Profile> friendsList = new ArrayList<>();
        int index = profile.getIndex();
        for (int i = 0; i < adjacencyMatrix[index].length; i++) {
            if (adjacencyMatrix[index][i] == 1) {
                // If friends sila
                friendsList.add(searchProfile(i));
            }
        }

        return friendsList;
    }

    //TODO: MODIFY THIS
    public void addUser(Profile newProfile) {
        if (numProfiles >= profiles.length) {
            // Expand the adjacency matrix and profiles array if necessary
            int newSize = profiles.length * 2;
            Profile[] newUsers = new Profile[newSize];
            int[][] newAdjacencyMatrix = new int[newSize][newSize];

            // Copy existing data to the new arrays
            for (int i = 0; i < numProfiles; i++) {
                newUsers[i] = profiles[i];
                for (int j = 0; j < numProfiles; j++) {
                    newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                }
            }

            // Update the references to the new arrays
            profiles = newUsers;
            adjacencyMatrix = newAdjacencyMatrix;
        }

        profiles[numProfiles] = newProfile;
        numProfiles++;
        newProfile.setIndex(numProfiles-1);
    }

    public void removeUser(Profile userToRemove) {
        if (userToRemove != null) {
            int indexToRemove = userToRemove.getIndex();
            profiles[indexToRemove] = null;
            numProfiles--;

            // Remove user's row and column from the adjacency matrix
            for (int i = 0; i < numProfiles + 1; i++) {
                adjacencyMatrix[indexToRemove][i] = 0;
                adjacencyMatrix[i][indexToRemove] = 0;
            }
        }
    }

    public Profile searchProfile(int index) {
        for (int i = 0; i < numProfiles; i++) {
            if (profiles[i] != null && profiles[i].getIndex() == index) {
                return profiles[i];
            }
        }
        return null;
    }

    public void displaySocialNetwork() {
        System.out.println("Adjacency Matrix: ");
        /* Print Column Header */
        System.out.print("  ");
        if (numProfiles > 9) {
            System.out.print(" ");
        }

        for (int i = 0; i < numProfiles; i++) {

            System.out.print((i+1) + " ");
        }
        System.out.println();

        /* Print Table Elements */
        for (int i = 0; i < numProfiles; i++) {
            /* Print Row Header */
            System.out.print((i+1) + " ");
            if (numProfiles > 9) {
                if (i <= 8) {
                    System.out.print(" ");
                }
            }
            for (int j = 0; j < numProfiles; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
