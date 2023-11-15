package lbycpa2.module6;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import org.controlsfx.control.tableview2.TableView2;

import java.util.ArrayList;
import java.util.List;

public class SocialFriendNetwork {
    @FXML
    private TableView2<List<String>> table;

    @FXML
    private void initialize() {
        // Initialize variables
        ProfileGraph graph = SocialApplication.getGraph();
        byte[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        int numProfiles = graph.getNumProfiles();

        graph.displaySocialNetwork();

        // Prepare table before adding data
        table.getColumns().clear();
        table.setRowHeaderVisible(true);

        // Prepare row header
        TableColumn<List<String>, String> rowHeader = new TableColumn<>();
        rowHeader.setResizable(true);
        rowHeader.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().get(0)));
        table.setRowHeader(rowHeader);

        // Some workaround to resize row header properly
        final int[] headerWidthSetThresh = {0};
        int combinations = numProfiles * numProfiles;

        // Iterate through the first layer of matrix.
        // This actually prepares the columns and the per-row data in a single for-loop,
        //    saving computing time :)
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            // Initialize variables
            Profile profile = graph.searchProfile(i);
            if (profile == null)
                continue;

            String name = profile.getName();
            TableColumn<List<String>, String> column = new TableColumn<>(name);

            // Set column properties
            final int ci = i+1;
            column.setSortable(false);
            column.setMinWidth(30);
            column.setMaxWidth(150);
            column.setCellValueFactory(data -> {
                if (headerWidthSetThresh[0] >= combinations && headerWidthSetThresh[0] < combinations + numProfiles) {
                    double width = data.getTableColumn().getWidth();
                    table.setRowHeaderWidth(Math.min(Math.max(width, table.getRowHeaderWidth()), 150));
                }
                if (headerWidthSetThresh[0] <= combinations + numProfiles) {
                    headerWidthSetThresh[0]++;
                }

                // Due to the quirkiness of JavaFX TableViews, this had to be done
                // https://stackoverflow.com/a/25395661
                List<String> rowValues = data.getValue();
                String cellValue = ci < rowValues.size() ? rowValues.get(ci) : "";
                return new ReadOnlyStringWrapper(cellValue);
            });
            table.getColumns().add(column);

            // Row Data
            List<String> rowData = new ArrayList<>();
            rowData.add(name);
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                rowData.add(i == j ? "" : adjacencyMatrix[i][j] > 0 ? "YES" : "NO");
            }
            table.getItems().add(rowData);
        }
    }
}
