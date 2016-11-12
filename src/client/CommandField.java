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
import static client.Client.STAGE_WIDTH;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class CommandField  {

  TextField comandField = new TextField();
  
  
  public static TextField get() {
    return CommandField.get(e -> {});
  }

  public static TextField get(Consumer<String> ifNoCommand) {
    TextField returnValue = new TextField();
    // Styling
    returnValue.setBackground(new Background(new BackgroundFill(
      Paint.valueOf("black"), CornerRadii.EMPTY, Insets.EMPTY
    )));
    returnValue.setPrefWidth(STAGE_WIDTH - (2 * PADDING_WIDTH));
    returnValue.setFont(Font.font("Monospace"));
    returnValue.setStyle("-fx-text-fill: whitesmoke");
    // Set event action
    returnValue.setOnAction(e -> {
      String text = returnValue.getText();
      returnValue.setText("");
      if (text.equalsIgnoreCase("EXIT")) {
        ((Stage)returnValue.getScene().getWindow()).close();
      }
      else {
        ifNoCommand.accept(text);
      }
    });
    return returnValue;
  }

}
