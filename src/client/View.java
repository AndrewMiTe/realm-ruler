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

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Specialized pane that accepts requests for information from the client user
 * in the form of another View object. Returns the scene to this view when the
 * request has been dismissed.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
class View extends Pane {
  
  /**
   * Initiates the view with the default appearance.
   */
  public View() {
    applyTemplate();
  }
  
  /**
   * Temporarily replaces the root of the scene this view belongs to with the
   * given View.
   * @param requestForm 
   */
  public void requestInput(View requestForm) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  /**
   * Replaces the root of any scene this view displaced with its original view.
   */
  public void dismissRequest() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  /**
   * Configures the view to the default appearance.
   */
  private void applyTemplate() {
    this.setBackground(new Background(new BackgroundFill(
      Paint.valueOf("black"), CornerRadii.EMPTY, Insets.EMPTY
    )));
  }
  
}
