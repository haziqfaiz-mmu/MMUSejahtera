module com.haziqfaiz.mmusejahtera {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.haziqfaiz.mmusejahtera to javafx.fxml;
    exports com.haziqfaiz.mmusejahtera;

}