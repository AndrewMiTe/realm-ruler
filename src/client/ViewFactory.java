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

import static client.Client.PADDING_WIDTH;
import static client.Client.STAGE_HEIGHT;
import static client.Client.STAGE_WIDTH;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Factory for making View objects based on the methods called and input given.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class ViewFactory {
  
  public static View getMapView() {
    return getView("Map Pane");
  }
  
  /**
   * Generates a View object that displays text to the user that describes what
   * input is being requested.
   * @param input function for getting text input form the view.
   * @param messages list of String objects describing to the client user what
   *        input is being requested.
   * @return view that displays a message and obtains input form the client
   *         user.
   */
  public static View getTextRequest(Consumer<String> input, String... messages) {
    // Adds text to the top.
    VBox top = new VBox();
    for (String msg : messages) {
      Text text = new Text(msg);
      text.setFont(Font.font("Monospace"));
      text.setFill(Color.WHITESMOKE);
      text.setWrappingWidth(STAGE_WIDTH - (2 * PADDING_WIDTH));
      top.getChildren().add(text);
    }
    // Adds text field to the bottom.
    AnchorPane bottom = new AnchorPane();
    TextField textInput = new TextField();
    textInput.setOnAction((e) -> input.accept(textInput.getText()));
    bottom.getChildren().add(textInput);
    textInput.setPrefWidth(STAGE_WIDTH - (2 * PADDING_WIDTH));
    // Creates outer pane.
    BorderPane outer = new BorderPane();
    outer.setPrefHeight(STAGE_HEIGHT);
    outer.setPadding(
        new Insets(PADDING_WIDTH, PADDING_WIDTH, PADDING_WIDTH, PADDING_WIDTH)
    );
    outer.setTop(top);
    outer.setBottom(bottom);
    // Finalizes.
    View root = new View();
    root.getChildren().add(outer);
    return root;
  }
  
  public static View getView(String paneMsg) {
    View root = new View();
    // Create test button.
    Button btn = new Button();
    btn.setText(paneMsg);
    btn.setOnAction(e -> root.getScene().setRoot(getView("Next Pane")));
    // Create and populate the inner pane.
    StackPane inner = new StackPane();
    inner.getChildren().add(btn);
    // Create and populate the root pane.
    root.getChildren().add(inner);
    return root;
  }
  
}
