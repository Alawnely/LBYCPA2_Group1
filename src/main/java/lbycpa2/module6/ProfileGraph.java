package lbycpa2.module6;

import java.util.ArrayList;
import java.util.List;

public class ProfileGraph {
    private static final List<Profile> profilesList = SocialApplication.getProfiles();
    private byte[][] adjacencyMatrix;
    private int numProfiles;

    public ProfileGraph() {
        numProfiles = 0;
        adjacencyMatrix = new byte[numProfiles][numProfiles];
    }

    public void addFriend(Profile p1, Profile p2) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("Profile is null");
        }

        int i1 = profilesList.indexOf(p1);
        int i2 = profilesList.indexOf(p2);
        if (i1 < 0 || i2 < 0) {
            throw new ArrayIndexOutOfBoundsException("Profile not found");
        }

        adjacencyMatrix[i1][i2] = 1;
        adjacencyMatrix[i2][i1] = 1;
    }

    public void removeFriend(Profile p1, Profile p2) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("Profile is null");
        }

        int i1 = profilesList.indexOf(p1);
        int i2 = profilesList.indexOf(p2);
        if (i1 < 0 || i2 < 0) {
            throw new ArrayIndexOutOfBoundsException("Profile not found");
        }

        adjacencyMatrix[i1][i2] = 0;
        adjacencyMatrix[i2][i1] = 0;
    }

    public List<Profile> getFriends(Profile profile) {
        if (profile == null) {
            throw new NullPointerException("Profile is null");
        }

        int profileIdx = profilesList.indexOf(profile);
        if (profileIdx < 0) {
            throw new ArrayIndexOutOfBoundsException("Profile \""+profile.getName()+"\" not found");
        }

        List<Profile> friendsList = new ArrayList<>();
        for (int i = 0; i < numProfiles; i++) {
            if (adjacencyMatrix[profileIdx][i] == 1) {
                // If friends sila
                friendsList.add(searchProfile(i));
            }
        }

        return friendsList;
    }

    public void addUser(Profile profile) {
        // Expand the adjacency matrix
        int newNum = numProfiles + 1;
        byte[][] newMatrix = new byte[newNum][newNum];

        // Copy existing data to the new array
        for (int i = 0; i < numProfiles; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, numProfiles);
        }

        // Add to profiles list
        profilesList.add(profile);

        // Update the references to the new array
        adjacencyMatrix = newMatrix;
        numProfiles = newNum;
    }

    public void removeUser(Profile profile) {
        if (profile == null) {
            throw new NullPointerException("Profile is null");
        }

        int profileIdx = profilesList.indexOf(profile);
        if (profileIdx < 0) {
            throw new ArrayIndexOutOfBoundsException("Profile \""+profile.getName()+"\" not found");
        }

        // Shrink the adjacency matrix
        int newNum = numProfiles - 1;
        byte[][] newMatrix = new byte[newNum][newNum];

        // Copy existing data to the new array, excluding the deleted one
        for (int i = 0, j = 0; i < numProfiles; i++) {
            if (i == profileIdx) {
                continue;
            }
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[j], 0, profileIdx);
            System.arraycopy(adjacencyMatrix[i], profileIdx + 1, newMatrix[j], profileIdx, numProfiles - profileIdx - 1);
            j++;
        }

        // Remove from profiles list
        profilesList.remove(profile);

        // Update the references to the new array
        adjacencyMatrix = newMatrix;
        numProfiles = newNum;
    }

    public Profile searchProfile(int index) {
        if (index < 0 || index >= numProfiles) {
            throw new ArrayIndexOutOfBoundsException("Index "+index+" is outside of graph");
        }

        return profilesList.get(index);
    }

    public byte[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getNumProfiles() {
        return numProfiles;
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
