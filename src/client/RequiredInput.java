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

import java.util.function.BiFunction;

/**
 * Enumerates the required input, the type of input desired, and the message
 * used to request the input from the user.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public enum RequiredInput {
  
  RULERS_NAME(
      new String(),
      (o, s) -> {return s;},
      "Well hello there. I can't say I've seen you here before. Welcome to " +
      "the Abyss! Its an endless dimension of chaos that contains an " +
      "infinite number of planes of existence. Each plane, or realm, is " +
      "unique and in it nearly anything can happen.",
      "",
      "I? Well I have made it my eternal mission to find promising rulers, " +
      "such as yourself, the perfect realm for them to rule. Now let us get " +
      "formalities out of the way. My name is Steve. Who might you be?"
  );
  
  private final Object inputType;

  private final BiFunction<Object, String, Object> resolveInput;

  private final String[] message;
  
  private RequiredInput(Object inputType,
      BiFunction<Object, String, Object> resolveInput, String... message) {
    this.inputType = inputType;
    this.resolveInput = resolveInput;
    this.message = message;
  }

  public Object getInputType() {
    return inputType;
  }

  public BiFunction<Object, String, Object> getResolveInput() {
    return resolveInput;
  }

  public String[] getMessage() {
    return message;
  }
  
   
}
