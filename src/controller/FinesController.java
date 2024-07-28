package controller;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import dto.FinesDto;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import service.ToReturnService;

public class FinesController {

    ToReturnService toReturnService = new ToReturnService();
    // ToReturnController toReturnController = new ToReturnController();
  
    @FXML
    private Label lbl;

    @FXML
    private Label lblLateDays;

    @FXML
    private TextField txtOneDay;

    @FXML
    private Label lblShowTime;

    @FXML
    private Label lblShowFines;

    

    @FXML
    void btnCallculate(ActionEvent event) {

        int fineOneDay = Integer.parseInt(txtOneDay.getText());
        int b = FinesDto.getLateDays();
        int fine = fineOneDay * b;

        lblShowFines.setText("A Fine Is Rs . "+String.valueOf(fine)+ "/=");

    }



    public void initialize() {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            lblShowTime.setText(LocalTime.now().format(timeFormatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();

       System.out.println("fines    "+FinesDto.getLateDays()); 

        int b = FinesDto.getLateDays();

        lblLateDays.setText(String.valueOf(b));


    }

   

   
}
