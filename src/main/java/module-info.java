module lbycpa2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens lbycpa2.module1_q4 to javafx.fxml;
    exports lbycpa2.module1_q4;

    opens lbycpa2.module2 to javafx.fxml;
    exports lbycpa2.module2;

    opens lbycpa2.module4 to javafx.fxml;
    exports lbycpa2.module4;

    opens lbycpa2.module5.tictactoe to javafx.fxml;
    exports lbycpa2.module5.tictactoe;
}