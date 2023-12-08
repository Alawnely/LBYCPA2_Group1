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
package lbycpa2.module7.visualgo.ui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lbycpa2.module7.visualgo.MainController;
import lbycpa2.module7.visualgo.util.CompareValue;
import lbycpa2.module7.visualgo.util.ISortOperator;
import lbycpa2.module7.visualgo.util.RandomValues;

import java.util.stream.IntStream;

/**
 * FXML Controller class
 *
 * @author Eric Canull
 */
public class AnimationController extends AnchorPane implements ISortOperator {
    
    @FXML private GridPane barsGrid;
    @FXML private GridPane textFieldsGrid;
   
//    public AnimationController() {
//        initialize();
//    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize() {
        barsGrid.widthProperty().addListener(evt -> onResize());
        barsGrid.heightProperty().addListener(evt -> onResize());
    }
  
    /**
     * Checks if the bars grid is done resizing.
     */
    private boolean isPaneLoaded() {
        // Compare actual width vs preferred width
        return barsGrid.getWidth() >= barsGrid.getPrefWidth()
                || barsGrid.getHeight() >= barsGrid.getPrefHeight();
    }

    /**
     * Sets the text field values.
     * @param presetChoice 
     */
    public void setPresetValues(String presetChoice) {
        RandomValues.setRandomSet(presetChoice, null);

        IntStream.range(0, 10).forEachOrdered(index -> {
            TextField tf = (TextField) textFieldsGrid.getChildren().get(index);
            tf.setText(String.valueOf(RandomValues.getArray()[index].getValue()));
        });
    }
    
    /**
     * Adds the bars to the grid pane when resizing occurs.
     */
    private void onResize() {
        // If the array is still null or pane hasn't finished resizing
        if (RandomValues.getArray() == null || !isPaneLoaded()) {
            return; // let's bounce
        }
        
        // Only if the bars grid pane is empty
        if (barsGrid.getChildren().isEmpty()) {
            // Create new bars and add a style class
            IntStream.range(0, RandomValues.MAX_SIZE).forEachOrdered((int index) -> {
                CompareValue compareValue = RandomValues.getArray()[index];
                double height = calculateHeight(compareValue.getValue());

                Bar rect = new Bar();
                rect.getStyleClass().add("rect");
                rect.setPrefHeight(height);
                rect.setMaxHeight(height);
                barsGrid.add(rect, index, 0);
            });
        } else {
            // Just resize the height of the existing bars
            IntStream.range(0, RandomValues.MAX_SIZE).forEachOrdered((int index) -> {
                CompareValue compareValue = RandomValues.getArray()[index];
                double height = calculateHeight(compareValue.getValue());

                Bar bar = (Bar) barsGrid.getChildren().get(index);
                bar.setPrefHeight(height);
                bar.setMaxHeight(height);

            });
        }
    }

    /**
     * Resize the bars with animation.
     */
    public void onResizeAnimated() {
        if (isPaneLoaded()) {
            IntStream.range(0, RandomValues.MAX_SIZE).forEachOrdered((int index) -> {
                CompareValue compareValue = RandomValues.getArray()[index];
                double height = calculateHeight(compareValue.getValue());

                Bar bar = (Bar) barsGrid.getChildren().get(index);
                animate(bar, height, CompareValue.NORMAL_COLOR);
            });
        }
    }
   
    /**
     * Animates the resized bar height.
     * @param rect
     * @param height
     * @param color 
     */
    private void animate(Bar rect, double height, Color color) {
        final Timeline tl = new Timeline();
        tl.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(Math.max(MainController.DELAY_PROPERTY.get(), 20)),
                        new KeyValue(rect.colorProperty, color),
                        new KeyValue(rect.prefHeightProperty(), height),
                        new KeyValue(rect.maxHeightProperty(), height)));
        tl.play();
    }

    /**
     * Use slope and y-intercept formulas to calculate the bars height
     * for resizing.
     */
    private double calculateHeight(double value) {
        double x1 = RandomValues.getMaxValue(), 
               y2 = barsGrid.getHeight();

        // calculate the new height
        double height = y2 - ((-y2 / x1) * value + ((y2 * x1) / (x1)));

        return Math.round(height);
    }
    
    @Override
    public Object apply(Object object) {
        CompareValue[] objectArray = (CompareValue[]) object;

        for (int indexPos = 0; indexPos < objectArray.length; indexPos++) {

            CompareValue compareValue = objectArray[indexPos];

            String webColor = "#".concat(Integer.toHexString(compareValue.getColor().hashCode()));
            Color color = compareValue.getColor();
            int value = compareValue.getValue();

            Bar rect = (Bar) barsGrid.getChildren().get(indexPos);
            TextField textfield = (TextField) textFieldsGrid.getChildren().get(indexPos);
            rect.setStyle("-fx-background-color: " + webColor + ";");
            final Timeline tl = new Timeline();
            tl.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(Math.max(MainController.DELAY_PROPERTY.get(), 20)),
                        new KeyValue(rect.colorProperty, color)));
                        tl.play();
            double height = calculateHeight(value);
            if(rect.getHeight() != height) {
            animate(rect, calculateHeight(value), color);
            }
            textfield.setStyle("-fx-border-color: " + webColor + ";"
                    + "-fx-background-color: " + webColor.replace("ff", "33") + ";");

            textfield.setText(String.valueOf(value));

        }

        return null;
    }

    /**
     * Private inner class creates a region with a color property listener that
     * sets the background during animations.
     */
    private final class Bar extends Region {

        public final SimpleObjectProperty<Color> colorProperty = new SimpleObjectProperty<>(Color.web("#b43073"));

        public Bar() {

            colorProperty.addListener(this::colorPropertyAction);
        }

        public void colorPropertyAction(Observable observable) {
            if (colorProperty.get() == null) {
                return;
            }
            String color = "#".concat(Integer.toHexString(colorProperty.get().hashCode()));
            setStyle("-fx-background-color: " + color + ";");
        }
    }
}
