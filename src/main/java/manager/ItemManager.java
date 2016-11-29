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

/**
 * Stores the most up-to-date copy of a data item obtained from a data managing
 * object. Allows for the requesting object to get the latest copy, make
 * modifications, and submit the changes for the realm manager to handle. Also
 * allows for an {@link UpdateProcedure} task to be called by the item manager
 * to inform the requesting object when the background data for the item has
 * been changed.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 * @param <T> type of object that the item manager maintains.
 */
public interface ItemManager<T> {
  
  /**
   * Returns the most up-to-date object from the realm manager.
   * @return updated item of the request.
   */
  T get();
  
  /**
   * Requests that the realm manager accept and save changes made to the
   * requested object. If changes have since been made that invalidates the
   * object, a checked exception is thrown.
   * @param item item to be saved.
   * @return {@code true} if the new data was saved successfully.
   * @throws client.OutOfSyncException if time stamp on modified data item is
   *         older then the current data it models.
   */
  boolean set(T item) throws OutOfSyncException;

  /**
   * Releases the manager of the request item from the obligation to update the
   * item after changes are saved.
   */
  void release();
  
  /**
   * Sets the task to be performed when the data has been updated so that the
   * requesting caller can replace its out of sync item data.
   * @param update task for informing the requesting caller to update.
   */
  void onUpdate(UpdateProcedure update);

}
