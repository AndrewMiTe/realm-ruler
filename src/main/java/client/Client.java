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

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import manager.View;
import manager.RealmManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import manager.DataItem;
import manager.RequiredInput;

/**
 * Opens the client with a single view registered to the realm manager.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class Client extends Application implements View {
  
  /**
   * Width of the stage.
   */
  public static final int STAGE_WIDTH = 480;
  
  /**
   * Height of the stage.
   */
  public static final int STAGE_HEIGHT = 320;
  
  /**
   * Width of space between the stages edge and the content inside it.
   */
  public static final int PADDING_WIDTH = 10;
  
  private final Set<RequiredInput> requestedInputs;
  
  private final Deque<Pane> paneStack;
  
  private Stage primeStage;
  
  public Client() {
    this.requestedInputs = new HashSet<>();
    this.paneStack = new LinkedList<>();
  }
  
  @Override // from Application
  public void start(Stage primeStage) {
    this.primeStage = primeStage;
    primeStage.setTitle("Realm Ruler");
    Pane pane = new TextRequestPane(System.out::println, "Main View");
    Scene scene = new Scene(pane, STAGE_WIDTH, STAGE_HEIGHT);
    primeStage.setScene(scene);
    primeStage.show();
    RealmManager.getInstance().registerView(this);
  }

  /**
   * Legacy method.
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void request(RequiredInput request) {
    if ((request != null) && requestedInputs.add(request)) {
      DataItem item = request.getInputType().setRequirement(request);
      TextRequestPane pane = new TextRequestPane(
          i -> RealmManager.getInstance().setInput(
              request.getResolveInput().apply(item, i)),
          request.getMessage());
      paneStack.offerFirst(pane);
      primeStage.getScene().setRoot(pane);
    }
  }

  @Override
  public void rescind(RequiredInput request) {
  }
  
}
