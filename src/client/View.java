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

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 * Specialized pane that accepts requests for information from the client user
 * in the form of another View object. Returns the scene to this view when the
 * request has been dismissed.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public abstract class View extends Pane {
  
  private Pane innerPane;

  private final Deque<Pane> paneStack;
  
  /**
   * Initiates the view with the default appearance.
   */
  public View() {
    this(new Node[0]);
  }
  
  /**
   * Initiates the view with the default appearance and adds child nodes.
   * @param children initial set of children for this pane.
   */
  public View(Node... children) {
    super();
    this.innerPane = new Pane(children);
    super.getChildren().add(this.innerPane);
    this.paneStack = new ArrayDeque<>();
    this.paneStack.add(innerPane);
    applyTemplate();
  }
  
  /**
   * Changes the scene of the view to the given view object while the previous
   * view goes into a stack. Calling {@link #unstack() unstack()} reverts the
   * scene to the View object on the top of the stack.
   * @param newView 
   */
  public void stack(View newView) {
    if ((newView != null) && !paneStack.contains(newView)) {
      paneStack.push(newView);
      innerPane = newView;
      super.getChildren().clear();
      super.getChildren().add(innerPane);
    }
  }
  
  /**
   * Reverts the scene to a previous view. If the stack of previous views is
   * empty, then the current view remains as the child of the scene.
   */
  public void unstack() {
    unstack(paneStack.peek());
  }
  
  public void unstack(Pane request) {
    SO.o("unstack(Pane request)");
    if (paneStack.contains(request) && paneStack.size() > 1) {
      SO.o("unstack(): inside if");
      paneStack.remove(request);
      innerPane = paneStack.peek();
      super.getChildren().clear();
      super.getChildren().add(innerPane);
    }
  }

  /**
   * Configures the view to the default appearance.
   */
  private void applyTemplate() {
    this.setBackground(new Background(new BackgroundFill(
      Paint.valueOf("black"), CornerRadii.EMPTY, Insets.EMPTY
    )));
  }
  
  public Pane getInnerPane() {
    return innerPane;
  }

}
