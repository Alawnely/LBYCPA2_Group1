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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * JavaFX sorting algorithms visualizer
 * From https://github.com/EricCanull/fxsortinganimation
 * @version 1.0
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load custom fonts used in css stylesheet
        Font.loadFont(MainApp.class.getResource("fonts/OpenSans-Regular.ttf").toExternalForm(), 10);
        Font.loadFont(MainApp.class.getResource("fonts/FiraCode-Regular.ttf").toExternalForm(), 10);
        
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/FXMLMainPane.fxml")));
        stage.setTitle("Sorting Visualization");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
