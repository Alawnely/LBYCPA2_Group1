/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lbycpa2.module7.visualgo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import lbycpa2.module7.visualgo.sorts.AbstractSort;
import lbycpa2.module7.visualgo.sorts.SortOperator;
import lbycpa2.module7.visualgo.sorts.SortOperatorList;
import lbycpa2.module7.visualgo.ui.AnimationController;
import lbycpa2.module7.visualgo.util.Logger;
import lbycpa2.module7.visualgo.util.RandomValues;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * FXML Controller class
 * From https://github.com/EricCanull/fxsortinganimation
 */
public class MainController implements Initializable {
    public static final SimpleIntegerProperty DELAY_PROPERTY = new SimpleIntegerProperty();

    private final SimpleBooleanProperty disableUI = new SimpleBooleanProperty(false);

    private final SortOperatorList sortOperators = new SortOperatorList();
    
    private AnimationController animationController;
    
    private ExecutorService executor;

    private Timeline timeline;
       
    @FXML private AnchorPane anchorPane;
    @FXML private TextArea logTextArea;
    @FXML private Button sortButton, clearButton;
    @FXML private ComboBox<String> algorithmsComboBox, presetsComboBox;
    @FXML private Spinner<Integer> delaySpinner;
    @FXML private Label algorithmLabel, countLabel, statusLabel;
    
    /**
     * Initializes the main controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Add the graphics controller pane 
        animationController = new AnimationController();
        FXMLLoader loader = new FXMLLoader(AnimationController.class.getResource("/lbycpa2/module7/visualgo/fxml/FXMLAnimationPane.fxml"));
        try {
            loader.setController(animationController);
            loader.setRoot(animationController);
            anchorPane.getChildren().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AnchorPane.setTopAnchor(animationController, 50.0);
        AnchorPane.setBottomAnchor(animationController, 0.0);
        AnchorPane.setLeftAnchor(animationController, 0.0);
        AnchorPane.setRightAnchor(animationController, 0.0);

        // Add algorithms list and select the first index in the combo box
        algorithmsComboBox.getItems().setAll(getAlgorithmsList());
        algorithmsComboBox.getSelectionModel().select(0);
        
        // Add preset values list and listener to combo box
        presetsComboBox.getItems().setAll(getPresetsList());
        presetsComboBox.valueProperty().addListener(this::presetComboBoxAction);
        presetsComboBox.getSelectionModel().select(0);

        // Create spinner factory with default min, max, current, step
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(0, 2000, 250, 10);

        // Set the spinner value factory
        delaySpinner.setValueFactory(valueFactory);
        DELAY_PROPERTY.bind(delaySpinner.valueProperty());

        // Create a timeline with animation delay and indefinite cycle count
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(
                delaySpinner.getValue()), ae -> updateViews()));
        timeline.setCycleCount(Animation.INDEFINITE);
      
        // Bind algorithm name to the label
        algorithmLabel.textProperty().set(algorithmsComboBox.getValue());

        // Bind the UI controls to the disableUI boolean property 
        statusLabel.getGraphic().visibleProperty().bind(disableUI);
        sortButton.disableProperty().bind(disableUI);
        clearButton.disableProperty().bind(disableUI);
        delaySpinner.disableProperty().bind(disableUI);
        algorithmsComboBox.disableProperty().bind(disableUI);
        presetsComboBox.disableProperty().bind(disableUI);
    }
       
    /**
     * Observable helper method updates the preset values
     * on selection change. 
     */
    private void presetComboBoxAction(Observable observable) {
        animationController.setPresetValues(presetsComboBox.getValue());
        animationController.onResizeAnimated();
    }
    
    /**
     * Mouse listener increases or decreases the spinner value with
     * the mouse scroll wheel.
     */
    @FXML
    private void spinnerScrollAction(ScrollEvent event) {
        if (event.getDeltaY() > 0) {
            delaySpinner.increment();
        } else if (event.getDeltaY() < 0) {
            delaySpinner.decrement();
        }
    }
    
    /**
     * Start button method to startSort the sorting operation
     */
    @FXML
    private void sortAction() {
         start();
    }
    
    /**
     * Starts the sorting operation
     */
    public void start() {
        disableUI.set(true);     // Disable UI
        countLabel.setText("0"); // Reset count label

        // Load the selected algorithm 
        int sortIndex = getAlgorithmIndex();
       
        //display the preset values in the text area
        logTextArea.appendText("Preset Values\n");
        logTextArea.appendText(RandomValues.getString().concat("\n\n"));

        // Update the algorithm name to the labels and text area
        String sortName = algorithmsComboBox.getValue().concat(" Sort\n");
        algorithmLabel.setText(sortName);
        logTextArea.appendText(sortName);
        
        // Start the timeline
        timeline.play();

        // Create a new thread to startSort the sorting algorithm
        executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
             // Perform the sort at the position in the list
            AbstractSort sorter = sortOperators.getList().get(sortIndex);
            sorter.sort(RandomValues.getArray(), 0, RandomValues.MAX_SIZE - 1);
         
            // Append text area with metric data
            Platform.runLater(() -> {
                updateViews();
                appendMetricText();
            });

            // Sort completed
            stop();
        });
    }
    
    /**
     * Shutdown running tasks and update the status label
     */
    private void stop() {
        try {
            executor.shutdown();
            executor.awaitTermination(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            setStatusText("Status: Interrupted");
        } finally {
            if (!executor.isTerminated()) {
                executor.shutdownNow();
            }
            timeline.stop();
            enableUI(); // enable UI
            setStatusText("Status: Ready"); // Set status
        }
    }

    /**
     * Updates the animation pane bars and numbered text fields
     * with progressive sort data until the sorting is complete.
     */
    private void updateViews() {
        SortOperator.getInstance().apply(RandomValues.getArray(), animationController);
         Platform.runLater(() -> {
            statusLabel.setText("Status: Sorting");
            countLabel.setText(Long.toString(Logger.getCount()));
        });
    }
    
    /**
     * Enables the UI flag in the FX thread
     */
    private void enableUI() {
         Platform.runLater(() -> this.disableUI.set(false));
    }
    
    /**
     * Sets the status label text in the FX thread
     */
    private void setStatusText(String status) {
         Platform.runLater(() -> statusLabel.setText(status));
    }

    /**
     * Appends the info text area with the metric data for the sorting routine.
     */
    private void appendMetricText() {

        // Calculates the difference between start and end time
        double delta = (double) (Logger.endNanoTime - Logger.startNanoTime) / 1e6;
        
        // Create a new string builder with metric data
        String sb = "Start: " + Logger.startTime.format(DateTimeFormatter.ISO_LOCAL_TIME) + "\n" +
                "Ended: " + Logger.endTime.format(DateTimeFormatter.ISO_LOCAL_TIME) + "\n" +
                "Delay: " + delaySpinner.getValue() + " ms\n" +
                "Speed: " + delta + " ms" + "\n" +
                "Steps: " + Logger.getCount() + "\n\n";
        
        // Appends the time stamp to the text area on the left-side display
        logTextArea.appendText(sb);
    }
    
    /**
     * Gets the current index selected for the algorithm combo box
     */
    private int getAlgorithmIndex() {
        return algorithmsComboBox.getSelectionModel().getSelectedIndex();
    }

    /**
     * Clears the text area
     */
    @FXML
    private void clearTextArea() {
        logTextArea.clear();
    }

    /**
     * The list of sort algorithms to choose in the combo box
     */
    private static List<String> getAlgorithmsList() {
        String[] algorithms = {"Bubble", "Selection", "Insertion", "Merge", "Quick", "Counting", "Gnome"};
        return Arrays.asList(algorithms);
    }

    /**
     * The list of preset values to choose in the combo box
     */
    private static List<String> getPresetsList() {
        String[] presets = {"Random", "Ordered", "Reverse", "Hundreds", "Thousands"};
        return Arrays.asList(presets);
    }
}
