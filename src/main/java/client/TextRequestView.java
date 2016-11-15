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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A view that displays text to the user that describes what input is being
 * requested and returns the input to given function.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class TextRequestView extends View {
  
  /**
   * Initializes the view with the given message to the client user and function
   * to consume the user's response.
   * @param input function for getting text input form the view.
   * @param messages list of String objects describing to the client user what
   *        input is being requested.
   */
  public TextRequestView(Consumer<String> input, String... messages) {
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
    TextField textInput = CommandField.get((t) -> input.accept(t));
    bottom.getChildren().add(textInput);
    // Creates outer border pane.
    BorderPane outer = new BorderPane();
    outer.setPrefHeight(STAGE_HEIGHT);
    outer.setPadding(
        new Insets(PADDING_WIDTH, PADDING_WIDTH, PADDING_WIDTH, PADDING_WIDTH)
    );
    outer.setTop(top);
    outer.setBottom(bottom);
    // Finalizes view.
    getInnerPane().getChildren().add(outer);    
  }
  
}
