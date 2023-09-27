module lbycpa2.module1_q4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens lbycpa2.module1_q4 to javafx.fxml;
    exports lbycpa2.module1_q4;
}