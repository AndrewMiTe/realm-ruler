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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jminor.io.ObjectFiles;
import jminor.io.ObjectStream;

/**
 * Feeds data to View objects based on what information about the realm they are
 * currently requesting.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class RealmManager {
  
  /**
   * Name of the file where the game data is read from and written to.
   */
  public static final String SAVE_FILE = "save.dat";
  
  /**
   * The singleton realm manager.
   */
  private static final RealmManager INSTANCE = new RealmManager();
  
  /**
   * The set of all views that allow the realm manager to request information
   * from the client user.
   */
  private final Set<View> registeredViews = new HashSet<>();
  
  /**
   * The path of the save file.
   */
  private final Path savePath = Paths.get(SAVE_FILE);
  
  /**
   * Initializes the realm manager privately and without parameters so as to
   * encourage a proper singleton pattern.
   */
  private RealmManager() {
    ObjectStream input = ObjectFiles.objects(savePath);
    input.forEach(System.out::println);
  }
  
  /**
   * Returns the singleton RealmManager class.
   * @return instance of realm manager.
   */
  public static RealmManager getInstance() {
    return INSTANCE;
  }
  
  /**
   * Adds a view to the set of View objects that can take requests for
   * information from the client user that the realm ruler needs.
   * @param view view that can take requests.
   * @return {@code true} if the view was not already registered.
   */
  public boolean registerView(View view) {
    return view == null ? false : registeredViews.add(view);
  }
  
  /**
   * Removes a view from the set of View objects that can take requests for
   * information from the client user that the realm ruler needs.
   * @param view view that can no longer take requests.
   * @return {@code true} if the view was successfully unregistered.
   */
  public boolean unregisterView(View view) {
    return registeredViews.remove(view);
  }
  
  /**
   * Returns an object that updates whenever changes are made to the requested
   * plot data.
   * @param plotID number that identifies which plot data is requested.
   * @return up-to-date plot data.
   */
  public ItemManager<Plot> requestPlot(int plotID) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * Returns an list of objects that update whenever changes are made to the
   * requested plot data they correspond to.
   * @return all up-to-date plot data.
   */
  public List<ItemManager<Plot>> requestAllPlots() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  /**
   * Returns an object that updates whenever changes are made to the requested
   * resource data.
   * @param resourceID number that identifies which resource data is requested.
   * @return up-to-date resource data.
   */
  public ItemManager<Resource> requestResource(int resourceID) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * Returns an list of objects that update whenever changes are made to the
   * requested resource data they correspond to.
   * @return all up-to-date resource data.
   */
  public List<ItemManager<Resource>> requestAllResources() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  /**
   * Stores the most up-to-date copy of a data item obtained from the realm
   * manager. Allows for the requesting object to get the latest copy, make
   * modifications, and submit the changes for the realm manager to handle. Also
   * allows for an {@link UpdateProcedure} task to be called by the item manager
   * to inform the requesting object when the background data for the item has
   * been changed.
   * @author Andrew M. Teller(https://github.com/AndrewMiTe)
   * @param <T> type of object that the item manager maintains.
   */
  private static class RelamRequest<T> implements ItemManager<T> {

    /**
     * The procedure prescribed by the requesting caller for what to do when the
     * item data has been changed.
     */
    private UpdateProcedure updater;
    
    @Override // from RequestItem
    public T get() {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override // from RequestItem
    public boolean set(T item) throws OutOfSyncException {
      throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override // from RequestItem
    public void release() {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override // from RequestItem
    public void onUpdate(UpdateProcedure updater) {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Used by the realm manager to update any changes to the requested item and
     * inform the view that made the request that a change has occurred.
     * @param item 
     */
    private void update(T item) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
    
  }
  
}
