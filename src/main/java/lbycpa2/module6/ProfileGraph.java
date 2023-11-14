package lbycpa2.module6;

public class ProfileGraph {
    private Profile[] profiles;
    private int[][] adjacencyMatrix;
    private int numProfiles;

    public ProfileGraph(int maxSize) {
        profiles = new Profile[maxSize];
        adjacencyMatrix = new int[maxSize][maxSize];
        numProfiles = 0;
    }

    public void addFriend(String user1, String user2) {
        Profile p1 = searchProfile(user1);
        Profile p2 = searchProfile(user2);

        if (p1 != null && p2 != null) {
            int index1 = p1.getIndex();
            int index2 = p2.getIndex();
            adjacencyMatrix[index1][index2] = 1;
            adjacencyMatrix[index2][index1] = 1;
        }
    }

    public void removeFriend(String user1, String user2) {
        Profile p1 = searchProfile(user1);
        Profile p2 = searchProfile(user2);

        if (p1 != null && p2 != null) {
            int index1 = p1.getIndex();
            int index2 = p2.getIndex();
            adjacencyMatrix[index1][index2] = 0;
            adjacencyMatrix[index2][index1] = 0;
        }
    }

    //TODO: MODIFY THIS
    public void addUser(String name) {
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

        Profile newProfile = new Profile(name, numProfiles);
        profiles[numProfiles] = newProfile;
        numProfiles++;
    }

    public void removeUser(String name) {
        Profile userToRemove = searchProfile(name);

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

    public Profile searchProfile(String name) {
        for (int i = 0; i < numProfiles; i++) {
            if (profiles[i] != null && profiles[i].getName().equals(name)) {
                return profiles[i];
            }
        }
        return null;
    }

    public void displaySocialNetwork() {
        System.out.println("Social Network:");

        for (int i = 0; i < numProfiles; i++) {
            Profile user = profiles[i];
            if (user != null) {
                System.out.print(user.getName() + " -> ");
                for (int j = 0; j < numProfiles; j++) {
                    if (adjacencyMatrix[i][j] == 1 && profiles[j] != null) {
                        System.out.print(profiles[j].getName() + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
