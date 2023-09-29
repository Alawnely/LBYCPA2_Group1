module lbycpa2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens lbycpa2.module1_q4 to javafx.fxml;
    exports lbycpa2.module1_q4;
    opens lbycpa2.module2 to javafx.fxml;
    exports lbycpa2.module2;
}