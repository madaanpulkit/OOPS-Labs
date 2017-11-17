import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.geometry.Orientation; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.ChoiceBox;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import javafx.scene.paint.*;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.io.*;


public class animation
{ 
   public void add(Color color, StackPane pane, int cMass, int pMass, int i, int j) 
   {    
      // StackPane pane = cell.getOrbPane();
      pane.getChildren().clear();
      PhongMaterial material = new PhongMaterial();
      material.setDiffuseColor(color);
      material.setSpecularColor(color);

      if(cMass == 1 && pMass == 1)
      {
         Sphere sphere = new Sphere(12.5);
         sphere.setMaterial(material);

         TranslateTransition vibrate = new TranslateTransition(); 
          
         pane.getChildren().add(sphere);

         vibrate.setNode(sphere); 
         vibrate.setByX(2.5); 
         vibrate.setDuration(Duration.millis(50));
         vibrate.setCycleCount(Timeline.INDEFINITE); 
         vibrate.setAutoReverse(true);
         vibrate.setInterpolator(Interpolator.LINEAR); 
         vibrate.play();
      }      

      else if(cMass == 2)
      {
         if(pMass == 1)
         {
            Sphere sphere = new Sphere(12.5);
            sphere.setMaterial(material);

            TranslateTransition vibrate = new TranslateTransition(); 
            
            pane.getChildren().add(sphere); 

            vibrate.setNode(sphere);       
            vibrate.setByX(5); 
            vibrate.setDuration(Duration.seconds(1));
            vibrate.setCycleCount(Timeline.INDEFINITE); 
            vibrate.setAutoReverse(true); 
            vibrate.setInterpolator(Interpolator.LINEAR); 
            vibrate.play();
         }

         else
         {
            Sphere sphere1 = new Sphere(12.5);
            sphere1.setMaterial(material);
            
            Circle circle1 = new Circle(1.5);
       
            PathTransition rotate1 = new PathTransition();
            
            pane.getChildren().add(sphere1);            

            rotate1.setNode(sphere1);
            rotate1.setPath(circle1);
            rotate1.setDuration(Duration.seconds(0.3));
            rotate1.setCycleCount(Timeline.INDEFINITE);
            rotate1.setInterpolator(Interpolator.LINEAR);
            rotate1.play();

            Sphere sphere2 = new Sphere(12.5);
            sphere2.setMaterial(material); 

            Circle circle2 = new Circle(3);
            circle2.setCenterX(6.25);
            circle2.setCenterY(-6.5);
                  
            PathTransition rotate2 = new PathTransition();
            
            pane.getChildren().add(sphere2);

            rotate2.setNode(sphere2);
            rotate2.setPath(circle2);
            rotate2.setDuration(Duration.seconds(0.3));
            rotate2.setCycleCount(Timeline.INDEFINITE);
            rotate2.setInterpolator(Interpolator.LINEAR);   
            rotate2.play();
         }      
      }
         
      else
      {
         if(pMass == 1)
         {
            Sphere sphere = new Sphere(12.5);
            sphere.setMaterial(material);

            TranslateTransition vibrate = new TranslateTransition(); 
            
            pane.getChildren().add(sphere);

            vibrate.setNode(sphere);       
            vibrate.setByX(5); 
            vibrate.setDuration(Duration.seconds(1.2));
            vibrate.setCycleCount(Timeline.INDEFINITE); 
            vibrate.setAutoReverse(true); 
            vibrate.setInterpolator(Interpolator.LINEAR); 
            vibrate.play();
         }

         else if(pMass == 2)
         {
            Sphere sphere1 = new Sphere(12.5);
            sphere1.setMaterial(material);
            
            Circle circle1 = new Circle(1);
       
            PathTransition rotate1 = new PathTransition();

            pane.getChildren().add(sphere1);
            
            rotate1.setNode(sphere1);
            rotate1.setPath(circle1);       
            rotate1.setDuration(Duration.seconds(1.2));       
            rotate1.setCycleCount(Timeline.INDEFINITE);
            rotate1.setInterpolator(Interpolator.LINEAR);
            rotate1.play();
        
            Sphere sphere2 = new Sphere(12.5); 
            sphere2.setMaterial(material);

            Circle circle2 = new Circle(2.5);
            circle2.setCenterX(6.25);
            circle2.setCenterY(-6.5);
                  
            PathTransition rotate2 = new PathTransition();

            pane.getChildren().add(sphere2); 
            
            rotate2.setNode(sphere2);
            rotate2.setPath(circle2);
            rotate2.setDuration(Duration.seconds(1.2));  
            rotate2.setCycleCount(Timeline.INDEFINITE);
            rotate2.setInterpolator(Interpolator.LINEAR);   
            rotate2.play();
         }

         else
         {
            Sphere sphere1 = new Sphere(12.5);
            sphere1.setMaterial(material);
            
            Circle circle1 = new Circle(1.25);
       
            PathTransition rotate1 = new PathTransition();

            pane.getChildren().add(sphere1);
            
            rotate1.setNode(sphere1);
            rotate1.setPath(circle1);
            rotate1.setDuration(Duration.seconds(0.3));
            rotate1.setCycleCount(Timeline.INDEFINITE);
            rotate1.setInterpolator(Interpolator.LINEAR);
            rotate1.play();
       
            Sphere sphere2 = new Sphere(12.5); 
            sphere2.setMaterial(material);

            Circle circle2 = new Circle(2.8);
            circle2.setCenterX(1.56);
            circle2.setCenterY(-6.25);
                  
            PathTransition rotate2 = new PathTransition();

            pane.getChildren().add(sphere2);
            
            rotate2.setNode(sphere2);
            rotate2.setPath(circle2);            
            rotate2.setDuration(Duration.seconds(0.3));              
            rotate2.setCycleCount(Timeline.INDEFINITE);
            rotate2.setInterpolator(Interpolator.LINEAR);   
            rotate2.play();

            Sphere sphere3 = new Sphere(12.5);
            sphere3.setMaterial(material); 

            Circle circle3 = new Circle(3.6);
            circle3.setCenterX(-4.68);
            circle3.setCenterY(-8);
                  
            PathTransition rotate3 = new PathTransition();

            pane.getChildren().add(sphere3);
            
            rotate3.setNode(sphere3);
            rotate3.setPath(circle3);
            rotate3.setDuration(Duration.seconds(0.3));  
            rotate3.setCycleCount(Timeline.INDEFINITE);
            rotate3.setInterpolator(Interpolator.LINEAR);   
            rotate3.play();
         }
      }

      System.out.println("added " + i + " " + j + " pMass = " + pMass);
   } 

   void explode(Color color, StackPane basePane, int cMass)
   {
      StackPane pane = new StackPane();
      // basePane.getChildren().clear();
      //StackPane pane = new StackPane();
      basePane.getChildren().add(pane);
      PhongMaterial material = new PhongMaterial();
      material.setDiffuseColor(color);
      // material.setSpecularColor(color);
      ArrayList<Sphere> list = new ArrayList<Sphere>();
      ParallelTransition ex;
      
      if(cMass == 1)
      {
         Sphere rSphere = new Sphere(12.5);
         Sphere dSphere = new Sphere(12.5);
         list.add(rSphere); 
         list.add(dSphere);
         rSphere.setMaterial(material);
         dSphere.setMaterial(material);

         pane.getChildren().add(rSphere);
         pane.getChildren().add(dSphere);

         TranslateTransition rightTranslate = new TranslateTransition(); 
          
         rightTranslate.setNode(rSphere); 
         rightTranslate.setByX(50); 
         rightTranslate.setDuration(Duration.millis(200));
         rightTranslate.setCycleCount(1); 
         rightTranslate.setAutoReverse(false);
         rightTranslate.setInterpolator(Interpolator.LINEAR); 
         // rightTranslate.play();

         TranslateTransition downTranslate = new TranslateTransition(); 
          
         downTranslate.setNode(dSphere); 
         downTranslate.setByY(50); 
         downTranslate.setDuration(Duration.millis(200));
         downTranslate.setCycleCount(1); 
         downTranslate.setAutoReverse(false);
         downTranslate.setInterpolator(Interpolator.LINEAR); 
         // downTranslate.play();

         ex = new ParallelTransition(rightTranslate, downTranslate);
         ex.play();
      }

      else if(cMass >= 2)
      {
         Sphere lSphere = new Sphere(12.5);
         Sphere rSphere = new Sphere(12.5);
         Sphere dSphere = new Sphere(12.5);
         list.add(rSphere); 
         list.add(dSphere);
         list.add(lSphere);
         lSphere.setMaterial(material);
         rSphere.setMaterial(material);
         dSphere.setMaterial(material);

         pane.getChildren().add(rSphere);
         pane.getChildren().add(dSphere);
         pane.getChildren().add(lSphere);

         TranslateTransition rightTranslate = new TranslateTransition(); 
          
         rightTranslate.setNode(rSphere); 
         rightTranslate.setByX(50); 
         rightTranslate.setDuration(Duration.millis(200));
         rightTranslate.setCycleCount(1); 
         rightTranslate.setAutoReverse(false);
         rightTranslate.setInterpolator(Interpolator.LINEAR); 
         // rightTranslate.play();

         TranslateTransition downTranslate = new TranslateTransition(); 
          
         downTranslate.setNode(dSphere); 
         downTranslate.setByY(50); 
         downTranslate.setDuration(Duration.millis(200));
         downTranslate.setCycleCount(1); 
         downTranslate.setAutoReverse(false);
         downTranslate.setInterpolator(Interpolator.LINEAR); 
         // downTranslate.play();   

         TranslateTransition leftTranslate = new TranslateTransition(); 
         
         leftTranslate.setNode(dSphere); 
         leftTranslate.setByX(-50); 
         leftTranslate.setDuration(Duration.millis(200));
         leftTranslate.setCycleCount(1); 
         leftTranslate.setAutoReverse(false);
         leftTranslate.setInterpolator(Interpolator.LINEAR); 
         // leftTranslate.play();

         ex = new ParallelTransition(rightTranslate, downTranslate, leftTranslate);
         ex.play();
      }

      else
      {
         Sphere lSphere = new Sphere(12.5);
         Sphere rSphere = new Sphere(12.5);
         Sphere dSphere = new Sphere(12.5);
         Sphere uSphere = new Sphere(12.5);
         list.add(rSphere); 
         list.add(dSphere);
         list.add(lSphere);
         list.add(uSphere);
         lSphere.setMaterial(material);
         rSphere.setMaterial(material);
         dSphere.setMaterial(material);
         uSphere.setMaterial(material);

         pane.getChildren().add(rSphere);
         pane.getChildren().add(dSphere);
         pane.getChildren().add(lSphere);
         pane.getChildren().add(uSphere);

         TranslateTransition rightTranslate = new TranslateTransition(); 
          
         rightTranslate.setNode(rSphere); 
         rightTranslate.setByX(50); 
         rightTranslate.setDuration(Duration.millis(200));
         rightTranslate.setCycleCount(1); 
         rightTranslate.setAutoReverse(false);
         rightTranslate.setInterpolator(Interpolator.LINEAR); 
         rightTranslate.play();

         TranslateTransition downTranslate = new TranslateTransition(); 
          
         downTranslate.setNode(dSphere); 
         downTranslate.setByY(50); 
         downTranslate.setDuration(Duration.millis(200));
         downTranslate.setCycleCount(1); 
         downTranslate.setAutoReverse(false);
         downTranslate.setInterpolator(Interpolator.LINEAR); 
         // downTranslate.play();   

         TranslateTransition leftTranslate = new TranslateTransition(); 
         
         leftTranslate.setNode(lSphere); 
         leftTranslate.setByX(-50); 
         leftTranslate.setDuration(Duration.millis(200));
         leftTranslate.setCycleCount(1); 
         leftTranslate.setAutoReverse(false);
         leftTranslate.setInterpolator(Interpolator.LINEAR); 
         // leftTranslate.play();

         TranslateTransition upTranslate = new TranslateTransition(); 
         
         upTranslate.setNode(uSphere); 
         upTranslate.setByY(-50); 
         upTranslate.setDuration(Duration.millis(200));
         upTranslate.setCycleCount(1); 
         upTranslate.setAutoReverse(false);
         upTranslate.setInterpolator(Interpolator.LINEAR); 
         // upTranslate.play();

         ex = new ParallelTransition(rightTranslate, downTranslate, leftTranslate, upTranslate);
         ex.play();
      }

      // PauseTransition delay = new PauseTransition(Duration.millis(200));
      // delay.setOnFinished( e -> basePane.getChildren().remove(pane) );
      // delay.play();

      ex.setOnFinished(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e)
         {
            basePane.getChildren().remove(pane);
            notify();
         }
      });
   }

   static void fade(ArrayList<Sphere> list)
   {
      PhongMaterial material = new PhongMaterial();
      material.setDiffuseColor(Color.TRANSPARENT);

      for(int i=0; i<list.size(); i++)
      {
         list.get(i).setMaterial(material);
      }
   }
}