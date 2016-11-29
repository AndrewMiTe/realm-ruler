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

package manager;

import manager.Phrase;
import manager.DataItem;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Enumerates the required input, the type of input desired, and the message
 * used to request the input from the user.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public enum RequiredInput {
  
  /**
   * Phrase that refers to the name of the realm.
   */
  REALMS_NAME(
      () -> new Phrase(),
      (o, s) -> {return ((Phrase)o).setPhrase(s);},
      "Now I think I've got exactly the place that you are looking for. The " +
      "perfect place to start a new kingdom. Best part is that it fits right " +
      "into your buget. You're not going to have any money left, but you'll " +
      "own yourself a realm, so that's something.",
      "",
      "It doesn't have a name. You should be the first to give it one. What " +
      "will it be?"
  ),
  
  /**
   * Phrase that refers to the name of the realm's ruler.
   */
  RULERS_NAME(
      () -> new Phrase(),
      (o, s) -> {return ((Phrase)o).setPhrase(s);},
      "Well hello there. I can't say I've seen you here before. Welcome to " +
      "the Abyss! Its an endless dimension of chaos that contains an " +
      "infinite number of planes of existence. Each plane, or realm, is " +
      "unique and in it nearly anything can happen.",
      "",
      "I? Well I have made it my eternal mission to find promising rulers, " +
      "such as yourself, the perfect realm for them to rule. Now let us get " +
      "formalities out of the way. My name is Steve. Who might you be?"
  );
  
  /**
   * @see #getInputType()
   */
  private final DataItem inputType;

  /**
   * @see #getResolveInput()
   */
  private final BiFunction<DataItem, String, DataItem> resolveInput;

  /**
   * @see #getMessage()
   */
  private final String[] message;
  
  /**
   * Initializes the required input with all of the properties explicitly set.
   * @param inputType {@see #getInputType()}
   * @param resolveInput {@see #getResolveInput()}
   * @param message {@see #getMessage()}
   * @throws IllegalArgumentException if any parameter is {@code null}. 
   */
  private RequiredInput(Supplier<DataItem> inputType,
      BiFunction<DataItem, String, DataItem> resolveInput, String... message) {
    if (inputType == null) 
      throw new IllegalArgumentException("inputType: null");
    this.inputType = inputType.get();
    if (resolveInput == null) 
      throw new IllegalArgumentException("resolveInput: null");
    this.resolveInput = resolveInput;
    if (message == null) 
      throw new IllegalArgumentException("message: null");
    this.message = message;
  }

  /**
   * @return object who's class is the type required by the required input.
   */
  public DataItem getInputType() {
    return inputType;
  }

  /**
   * @return function that resolves how the input is incorporated into a final
   *         object.
   */
  public BiFunction<DataItem, String, DataItem> getResolveInput() {
    return resolveInput;
  }

  /**
   * @return text to be displayed to the client user that describes the input
   *         being requested.
   */
  public String[] getMessage() {
    return message;
  }
   
}
