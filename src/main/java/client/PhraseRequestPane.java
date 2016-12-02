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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * A view that displays text to the user that describes what input is being
 * requested and returns the input to given function.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class PhraseRequestPane extends Pane {
  
  /**
   * Initializes the view with the given message to the client user and function
   * to consume the user's response.
   * @param input function for getting text input form the view.
   * @param messages list of String objects describing to the client user what
   *        input is being requested.
   */
  public PhraseRequestPane(Consumer<String> input, String... messages) {
    applyTemplate();
    // Adds text to the top.
    VBox top = new VBox();
    for (String msg : messages) {
      Label label = new Label(msg);
      label.setFont(Font.font("Monospace"));
      label.setWrapText(true);
      label.setTextFill(Color.WHITESMOKE);
      top.getChildren().add(label);
    }
    // Adds command field to the bottom.
    AnchorPane bottom = new AnchorPane();
    TextField textInput = CommandField.get(input::accept);
    bottom.getChildren().add(textInput);
    // Creates outer border pane.
    BorderPane outer = new BorderPane();
    outer.setPrefHeight(STAGE_HEIGHT);
    outer.setPrefWidth(STAGE_WIDTH);
    outer.setPadding(new Insets(PADDING_WIDTH));
    outer.setTop(top);
    outer.setBottom(bottom);
    // Finalizes pane.
    getChildren().add(outer);
  }

  /**
   * Configures the pane to the default appearance.
   */
  private void applyTemplate() {
    this.setBackground(new Background(new BackgroundFill(
      Paint.valueOf("black"), CornerRadii.EMPTY, Insets.EMPTY
    )));
  }
  
}
