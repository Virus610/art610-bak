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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

public class Main extends Application implements EventHandler<ActionEvent> {

    Stage primaryStage;
    private Pane pane = new Pane();
    private Group drawables = new Group();
    private Color fill;
    private Color stroke;
    private double state = 0, maxState = 1000, total = 0;
    private int frameRate = 30;
    private Button restartButton;
    private Slider slider;
    private TextField sliderField;
    private ComboBox<String> patternBox;
    private Timeline animation;

    @Override
    public void start(Stage stage) throws Exception {

        // Buncha boring
        primaryStage = stage;
        primaryStage.setTitle("'Art'");
        primaryStage.setScene(new Scene(pane, 700, 700));
        primaryStage.show();

        pane.setBackground(new Background(new BackgroundFill(new Color(.85, .85, .85, 1.0), null, null)));
        pane.getChildren().add(drawables);

        restartButton = new Button("Restart");
        restartButton.setOnAction(event -> {
            state = 0;
            total = 0;
        });
        pane.getChildren().add(restartButton);

        sliderField = new TextField();
        sliderField.setLayoutY(0);
        sliderField.setLayoutX(restartButton.getLayoutX() + 65);
        sliderField.setPrefWidth(30);
        sliderField.setEditable(false);
        pane.getChildren().add(sliderField);

        slider = new Slider(0,90,3);
        slider.setLayoutY(sliderField.getLayoutY() + 5);
        slider.setLayoutX(sliderField.getLayoutX() + 35);
        slider.setPrefWidth(480);
        pane.getChildren().add(slider);

        ObservableList<String> patternList = FXCollections.observableList(Arrays.asList("devX", "tornado", "xmastree"));
        patternBox = new ComboBox<String>(patternList);
        patternBox.setLayoutX(restartButton.getLayoutX());
        patternBox.setLayoutY(restartButton.getLayoutY() + 30);
        patternBox.setPrefWidth(150);
        patternBox.getSelectionModel().selectFirst();
        pane.getChildren().add(patternBox);


        animation = new Timeline(
                        new KeyFrame(Duration.seconds(1.0/frameRate),
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
            case "devx":
                devExchange();
            default:
        }
    }





    private void devExchange() {
        clearPane();

//        circle(100+total, 100, 25, Color.RED);

        // Heart thing (Math is hard)
//        for (double k = -1; k < 1; k+= 0.025) {
//            double x = (k+0.05*total%3.5)-2;
//            double y = Math.sqrt(1-(0.75*Math.abs(x))) + 0.75*Math.abs(x);
//
//            double x2 = 0;
//            double y2 = 0;
//
//            // This will make stuff wobbly
//            x2 = Math.sin(0.1*total+5*k)*10;
//            y2 = Math.cos(0.1*total+5*k)*10;
//
//            circle(300+x*100 + x2, 620-y*300 + y2, 5, new Color(1,0, clamp(1-Math.sin(k)*0.8),clamp(k/1.5)));
//            circle(300+x*200 + x2, 1380-y * 900 + y2, 5, new Color(1, 0, clamp(1-Math.sin(k)*0.8), clamp(k/1.5)));
//        }

        // Sine wave road thing
//        for (double i = 0; i < 150; i++) {
//            for (double j = 3; j >= 0; j--) {
//                double x = 10 * i;
//                double y = pane.getHeight()/2 - 50*Math.sin(i/5 + 0.1*total);
//                circle(
//                        x -7*j
//                        , y + 7*j
//                        ,
//                        10
//                        , new Color(Math.abs(Math.sin(i)) / (1+j/3),0,0.5* Math.abs(Math.cos(i)) / (1+j/3), 1)
//                );
//            }
//        }


        // Bubbles thing
//        double bubbleRadius = 14;
//        rect(0, 0, pane.getWidth(), pane.getHeight(), new Color(0.1, 0.3, 0.6, 1));
//        for (int i = 0; i < slider.getValue()*10; i++) {
//            double x = i*40 + Math.sin(0.2*total + 12*i)*5;
//            double y = Math.max(0.5, Math.abs(Math.cos(7*i)) * 2.5);
//            circle(
//                    -50 + x % (pane.getWidth() + 100)
//                    , (pane.getHeight()+bubbleRadius) - ((Math.pow(i, 2) + y * total)) % (pane.getHeight() + 2*bubbleRadius)
//                    , bubbleRadius
//                    , new Color (1,1,1,0.3 + Math.sin(i) * 0.2)
//                    , new Color(0.9 + (0.1*Math.sin(i)), 0.9 + (0.1*Math.sin(i)), 1, 0.8));
//        }

        // Cool spinny lines circle thing
//        double baseCircleSize = 50;
//        for (double i = 0; i < 200; i += 0.25) {
//            line(
//                    pane.getWidth()/2 + (baseCircleSize)*Math.sin(i)// + (200 * Math.sin(0.5*i))
//                    ,pane.getHeight()/2 + (baseCircleSize)*Math.cos(i)// + (200 * Math.cos(0.5*i))
//                    ,0.5*pane.getWidth() + Math.sin(2*i+(0.1*total)) * 0.5* pane.getWidth()
//                    ,0.5*pane.getHeight() + 0.5*pane.getHeight() * Math.sin(i)
//                    , Color.hsb(.4*total, 1.0, Math.abs(Math.sin(i+(.002*total))*0.8))
//                    //, new Color (0, Math.abs(Math.sin(i+(.002*total))*0.8), 0, 1)
//            );
//        }

        total++;
    }






    private void christmasTree() {
        double tiers = 5;
        clearPane();

        double midX = pane.getWidth()/2, midY = pane.getHeight()/2;
        stroke = new Color(0, 0, 0, 0.5);


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

        state+=4;
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
            double  x = (Math.sin(Math.abs((Math.pow(total,0.5)+i)/Math.sin((int) slider.getValue()))) * Math.pow(i,0.8) * Math.pow(Math.tan(Math.abs(Math.sin(i/10))),1/Math.log(i+10*total))),
                    y = (Math.cos(Math.abs((Math.pow(total,0.5)+i)/Math.cos((int) slider.getValue()))) * Math.pow(i,0.8) * Math.pow(Math.tan(Math.abs(Math.cos(i/10))),1/Math.log(i+10*total)));

            x /= Math.max(1, y / 25);

            double i0 = i-1;
            double  x0 = (Math.sin(Math.abs((Math.pow(total,0.5)+i0)/Math.sin((int) slider.getValue()))) * Math.pow(i0,0.8) * Math.pow(Math.tan(Math.abs(Math.sin(i0/10))),1/Math.log(i0+10*total))),
                    y0 = (Math.cos(Math.abs((Math.pow(total,0.5)+i0)/Math.cos((int) slider.getValue()))) * Math.pow(i0,0.8) * Math.pow(Math.tan(Math.abs(Math.cos(i0/10))),1/Math.log(i0+10*total)));

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
        state+=10;
        total++;
        if (state > maxState) state = maxState;
    }

    // Return value between 0.0 and 1.0
    private double clamp(double n) {
        return clamp (n, 0, 1);
    }

    // Return value between specified min and max
    private double clamp(double n, double min, double max) {
        return Math.max(Math.min(n,max),min);
    }

    private void clearPane() {
        drawables.getChildren().clear();
        sliderField.setText(""+(int) slider.getValue());
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
        drawables.getChildren().add(circle);
    }

    // LINES
    private void line(double x1, double y1, double x2, double y2) {
        line (x1, y1, x2, y2, this.stroke);
    }

    private void line(double x1, double y1, double x2, double y2, Color stroke) {
        Line line = new Line(x1, y1, x2, y2);
        line.setFill(this.fill);
        line.setStroke(stroke);
        line.setStrokeWidth(0.25);
        drawables.getChildren().add(line);
    }

    // RECTS
    private void rect(double x, double y, double w, double h) {
        rect(x, y, w, h, this.fill, this.stroke);
    }
    private void rect(double x, double y, double w, double h, Color fill) {
        rect(x, y, w, h, fill, this.stroke);
    }
    private void rect(double x, double y, double w, double h, Color fill, Color stroke) {
        Rectangle rect = new Rectangle(x, y, w, h);
        rect.setFill(fill);
        rect.setStroke(stroke);
        drawables.getChildren().add(rect);
    }

    // OTHER STUFF
    private void string(String s, double x, double y) {
        Text text = new Text(x, y, s);
        text.setStroke(this.stroke);
        drawables.getChildren().add(text);
    }
}
