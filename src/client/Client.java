/*
 * MIT License
 *
 * Copyright (c) 2016 Andrew Michael Teller(https://github.com/AndrewMiTe)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class Client extends Application {
  
  public static int STAGE_WIDTH = 480;
  
  public static int STAGE_HEIGHT = 320;
  
  private static Stage primaryStage = null;
  
  @Override // from Application
  public void start(Stage primaryStage) {
    Client.primaryStage = primaryStage;
    Scene scene = new Scene(ViewFactory.getMainView(), STAGE_WIDTH,
        STAGE_HEIGHT);
    primaryStage.setTitle("Realm Ruler");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Legacy method.
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  public static void setScene(Pane pane) {
    if (primaryStage == null) throw new RuntimeException("Stage not set.");
    Scene scene = new Scene(pane, STAGE_WIDTH, STAGE_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
}
