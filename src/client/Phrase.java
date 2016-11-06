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

/**
 * Contains a phrase inputed by the client user that fulfills a requirement for
 * the client's continued operation.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class Phrase {
  
  /**
   * @see #setPhrase
   */
  private String phrase = "";
  
  /**
   * @see #setRequirement
   */
  private RequiredInput requirement;

  /**
   * Initializes an illegal phrase and sets the requirement property that the
   * phrase is to fulfill.
   * @param requirement {@see #setRequirement}
   */
  public Phrase(RequiredInput requirement) {
    if (requirement == null)
      throw new IllegalArgumentException("requirement: null");
    this.requirement = requirement;
  }

  public boolean isLegal() {
    assert phrase != null;
    return !phrase.isEmpty();
  }
  
  /**
   * @return prase property.
   * @see #setPhrase
   */
  public String getPhrase() {
    assert phrase != null;
    return phrase;
  }

  /**
   * @param phrase text to meet the requirement.
   * @throws IllegalArgumentException if the given phrase is {@code null} or an
   *         empty string.
   */
  public void setPhrase(String phrase) {
    if (phrase == null) throw new IllegalArgumentException("phrase: null");
    if (phrase.isEmpty()) throw new IllegalArgumentException("phrase: empty");
    this.phrase = phrase;
  }

  /**
   * @return requirement property.
   * @see #setRequirement
   */
  public RequiredInput getRequirement() {
    assert requirement != null;
    return requirement;
  }

  /**
   * @param requirement requirement that the text fulfills.
   * @throws IllegalArgumentException if the given requirement is {@code null}.
   */
  public void setRequirement(RequiredInput requirement) {
    if (requirement == null) 
      throw new IllegalArgumentException("requirement: null");
    this.requirement = requirement;
  }
  
}
