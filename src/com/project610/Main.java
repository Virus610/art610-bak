package com.project610;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

public class Main extends Application implements EventHandler<ActionEvent> {

    Stage primaryStage;
    private Pane pane = new Pane();
    private Group ballz = new Group();
    private Color fill;
    private Color stroke;
    private double state = 0, maxState = 1000, total = 0;
    Button restartButton;
    Slider sinSlider;
    TextField sinSliderField;
    ComboBox<String> patternBox;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("'Art'");
        primaryStage.setScene(new Scene(pane, 700, 700));
        primaryStage.show();

        pane.setBackground(new Background(new BackgroundFill(new Color(.85, .85, .85, 1.0), null, null)));
        pane.getChildren().add(ballz);

        restartButton = new Button("Restart");
        restartButton.setOnAction(event -> {
            state = -200;
            total = -200;
        });
        pane.getChildren().add(restartButton);

        sinSliderField = new TextField();
        sinSliderField.setLayoutY(0);
        sinSliderField.setLayoutX(restartButton.getLayoutX() + 65);
        sinSliderField.setPrefWidth(30);
        sinSliderField.setEditable(false);
        pane.getChildren().add(sinSliderField);

        sinSlider = new Slider(0,90,3);
        sinSlider.setLayoutY(sinSliderField.getLayoutY() + 5);
        sinSlider.setLayoutX(sinSliderField.getLayoutX() + 35);
        sinSlider.setPrefWidth(480);
        pane.getChildren().add(sinSlider);

        ObservableList<String> patternList = FXCollections.observableList(Arrays.asList("tornado", "xmastree"));
        patternBox = new ComboBox<String>(patternList);
        patternBox.setLayoutX(restartButton.getLayoutX());
        patternBox.setLayoutY(restartButton.getLayoutY() + 30);
        patternBox.setPrefWidth(100);
        patternBox.getSelectionModel().selectFirst();
        pane.getChildren().add(patternBox);


        Timeline animation = new Timeline(
                        new KeyFrame(Duration.seconds(1.0/60),
                        this
                ));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        primaryStage.setOpacity(1.0);
    }
    public void handle(ActionEvent actionEvent) {

        switch (patternBox.getValue().toLowerCase()) {
            case "tornado":
                rainbowTornado();
                break;
            case "xmastree":
                christmasTree();
                break;
            default:
        }
    }

    private void christmasTree() {
        double tiers = 5;
        clearPane();

        double midX = pane.getWidth()/2, midY = pane.getHeight()/2;
        this.stroke = new Color(0, 0, 0, 0.5);


        // Ornaments on back
        for (double i = state; i >= 0; i-= 50) {
            if (Math.cos((i+total)/tiers) <= 0.55) continue;
            double x = christmasTree_ornament_getX(i, tiers);
            double y = christmasTree_ornament_getY(i, tiers);
            double x0 = christmasTree_ornament_getX(i-1, tiers);
            double y0 = christmasTree_ornament_getY(i-1, tiers);

            double red = 0, green = 0, blue = 0;
            int colourScale = 167;

            red += Math.abs((-0.5 * Math.cos(i/10))*2)/1.25;
            //green += state%100;
            //blue += state/(i%200);
            fill = new Color (clamp(red), clamp(green), clamp(blue),1);
            stroke = null;

            circle( midX + x,
                    midY + y,
                    8);

            //line (midX + x0, midY + y0, midX + x, midY + y);
        }

        // The actual tree
        for (double i = state; i >= 0; i --) {
            double x = christmasTree_getX(i, tiers);
            double y = christmasTree_getY(i, tiers);
            double x0 = christmasTree_getX(i-1, tiers);
            double y0 = christmasTree_getY(i-1, tiers);

            double red = 0, green = 0, blue = 0;
            int colourScale = 167;

            red += 0.1 + 0.2 * Math.cos((i+total)/tiers);
            green += 0.65  + 0.45 * Math.cos((i+total)/tiers);
            blue += 0.1 + 0.2 * Math.cos((i+total)/tiers);
            fill = new Color (clamp(red), clamp(green), clamp(blue),1);
            stroke = null;

            circle( midX + x,
                    midY + y,
                    6);

            i += i/(maxState*1.1);
            //line (midX + x0, midY + y0, midX + x, midY + y);
        }

        // Ornaments on front
        for (double i = state; i >= 0; i -= 50) {
            if (Math.cos((i+total)/tiers) >= 0) continue;
            double x = christmasTree_ornament_getX(i, tiers);
            double y = christmasTree_ornament_getY(i, tiers);
            double x0 = christmasTree_ornament_getX(i-1, tiers);
            double y0 = christmasTree_ornament_getY(i-1, tiers);

            double red = 0, green = 0, blue = 0;
            int colourScale = 167;

            red += Math.abs((-0.5 * Math.cos(i/10))*2);
            //green += state%100;
            //blue += state/(i%200);
            fill = new Color (clamp(red), clamp(green), clamp(blue),1);
            stroke = null;

            circle( midX + x,
                    midY + y,
                    8);

            //line (midX + x0, midY + y0, midX + x, midY + y);

            i += i/(maxState*2);
        }

        state++;
        total++;
        if (state > maxState) state = maxState;
    }

    private double christmasTree_getX(double i, double tiers) {
        return i/10 * Math.sin((i+total)/tiers) * (i/3 + i % (maxState/(tiers-0.1))) / 300;
    }

    private double christmasTree_getY(double i, double tiers) {
        return -300 + i/2;
    }

    private double christmasTree_ornament_getX(double i, double tiers) {
        return -1 * i/10 * Math.sin((i+total)/tiers) * (i/3 + i % (maxState/(tiers-0.1))) / 300;
    }

    private double christmasTree_ornament_getY(double i, double tiers) {
        return -300 + i/2;
    }

    private void rainbowTornado() {

        clearPane();

        stroke = new Color(0, 0, 0, 1);

        double midX = pane.getWidth()/2, midY = pane.getHeight()/2;

        for (double i = 0; i < state; i++) {
            double  x = (Math.sin(Math.abs((Math.pow(total,0.5)+i)/Math.sin((int)sinSlider.getValue()))) * Math.pow(i,0.8) * Math.pow(Math.tan(Math.abs(Math.sin(i/10))),1/Math.log(i+10*total))),
                    y = (Math.cos(Math.abs((Math.pow(total,0.5)+i)/Math.cos((int)sinSlider.getValue()))) * Math.pow(i,0.8) * Math.pow(Math.tan(Math.abs(Math.cos(i/10))),1/Math.log(i+10*total)));

            x /= Math.max(1, y / 25);

            double i0 = i-1;
            double  x0 = (Math.sin(Math.abs((Math.pow(total,0.5)+i0)/Math.sin((int)sinSlider.getValue()))) * Math.pow(i0,0.8) * Math.pow(Math.tan(Math.abs(Math.sin(i0/10))),1/Math.log(i0+10*total))),
                    y0 = (Math.cos(Math.abs((Math.pow(total,0.5)+i0)/Math.cos((int)sinSlider.getValue()))) * Math.pow(i0,0.8) * Math.pow(Math.tan(Math.abs(Math.cos(i0/10))),1/Math.log(i0+10*total)));

            x0 /= Math.max(1, y0 / 25);

            //stroke = null;

            double red = 0, green = 0, blue = 0;
            int colourScale = 167;
            red = clamp(0.5 + Math.cos(Math.toRadians((total+i)/11.1*4)));
            green = clamp(0.5 + Math.cos(Math.toRadians(((total+i)+2*colourScale)/11.1*4)));
            blue = clamp(0.5 + Math.cos(Math.toRadians(((total+i)+4*colourScale)/11.1*4)));
            fill = new Color (clamp(red), clamp(green), clamp(blue),1);

            x = midX + x;
            y = midY + y;

            x0 = midX + x0;
            y0 = midY + y0;

            circle( x,
                    y,
                    6);

            line (x0, y0, x, y);
        }
        state++;
        total++;
        if (state > maxState) state = maxState;
    }

    private double clamp(double n) {
        return clamp (n, 0, 1);
    }

    private double clamp(double n, double min, double max) {
        return Math.max(Math.min(n,max),min);
    }

    private void clearPane() {
        ballz.getChildren().clear();
        sinSliderField.setText(""+(int)sinSlider.getValue());
    }

    public static void main(String[] args) {
        launch(args);
    }

    // CIRCLES
    private void circle(double x, double y, double r) {
        circle (x, y, r, this.fill, this.stroke);
    }

    private void circle(double x, double y, double r, Color fill) {
        circle (x, y, r, fill, this.stroke);
    }

    private void circle(double x, double y, double r, Color fill, Color stroke) {
        Circle circle = new Circle(x, y, r);
        circle.setFill(fill);
        circle.setStroke(stroke);
        ballz.getChildren().add(circle);
    }

    // LINES
    private void line(double x1, double y1, double x2, double y2) {
        line (x1, y1, x2, y2, new Color(0, 0, 0, 1));
    }

    private void line(double x1, double y1, double x2, double y2, Color stroke) {
        Line line = new Line(x1, y1, x2, y2);
        line.setFill(this.fill);
        line.setStroke(stroke);
        line.setStrokeWidth(0.25);
        ballz.getChildren().add(line);
    }

    private void string(String s, double x, double y) {
        Text text = new Text(x, y, s);
        text.setStroke(this.stroke);
        ballz.getChildren().add(text);
    }
}
