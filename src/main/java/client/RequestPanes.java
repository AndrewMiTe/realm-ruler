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

import javafx.scene.layout.Pane;
import manager.DataItem;
import manager.Phrase;
import manager.RealmManager;
import manager.RequiredInput;

/**
 * Factory for creating a {@link Pane} object that gets all of the input
 * required to 
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class RequestPanes {
  
  /**
   * Factory method for returning a Pane object able to fulfill the given input
   * request.
   * @param request the requested input to be filled.
   * @return pane for getting input from client user.
   */
  public static Pane get(RequiredInput request) {
    DataItem item = request.getInputType().setRequirement(request);
    if (item instanceof Phrase) {
      return new PhraseRequestPane(i -> RealmManager.getInstance().setInput(
          request.getResolveInput().apply(item, i)), request.getMessage());
    }
    throw new IllegalArgumentException("request not supported");
  }
  
}
