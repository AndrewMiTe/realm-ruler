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

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class ViewFactory {
  
  public static View getMainView() {
    return getView("Main Pane");
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
