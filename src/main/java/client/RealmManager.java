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

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import io.ObjectFiles;

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
   * Singleton realm manager.
   */
  private static final RealmManager INSTANCE = new RealmManager();

  /**
   * Path to the save file.
   */
  private final Path savePath;
  
  /**
   * View objects that allow for views requesting data from the client user to
   * supersede them.
   */
  private final Set<View> registeredViews;
  
  /**
   * Map of views requesting input from the client user who's key identifies
   * what required input they fulfill.
   */
  private final Map<RequiredInput, View> outstandingRequests;

  /**
   * Initializes the realm manager privately and without parameters so as to
   * encourage a proper singleton pattern.
   */
  private RealmManager() {
    this.savePath = Paths.get(SAVE_FILE);
    this.registeredViews = new HashSet<>();
    this.outstandingRequests = new HashMap<>();
    checkRequirements();
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
    if (view != null && registeredViews.add(view)) {
      checkRequirements();
      return true;
    }
    return false;
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
   * Opens the save file and makes requests for any missing required data
   * needed from the client user. The request is packaged as a View object and
   * sent to any other View objected registered to allow the request to
   * supersede it until the request has been filled.
   */
  private void checkRequirements() {
    createSaveFile();
    Set<RequiredInput> fulfilledRequirements = ObjectFiles.objects(savePath)
        .filter(o -> o instanceof DataItem)
        .map(o -> ((DataItem)o).getRequirement())
        .filter(o -> o != null)
        .collect(Collectors.toSet());
    for (RequiredInput r : RequiredInput.values()) {
      if (!fulfilledRequirements.contains(r) &&
          !outstandingRequests.containsKey(r)) {
        DataItem inputType = r.getInputType().setRequirement(r);
        TextRequestView request = new TextRequestView(
            (i) -> setInput(r.getResolveInput().apply(inputType, i)),
            r.getMessage());
        outstandingRequests.put(r, request);
      }
    }
    registeredViews.stream().forEach(
      v -> outstandingRequests.values().stream().forEach(o -> v.stack(o)));
  }

  /**
   * Ensures that the save file exists and makes an empty file if it doesn't.
   */
  private void createSaveFile() {
    try {
      Files.createFile(savePath);
    }
    catch (FileAlreadyExistsException e) {
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void setInput(DataItem input) {
    RequiredInput requirement = input.getRequirement();
    View request = outstandingRequests.remove(requirement);
    if (request != null) {
      registeredViews.stream().forEach(v -> v.unstack(request));
    }
    if (!ObjectFiles.objects(savePath)
        .filter(o -> o instanceof DataItem)
        .map(o -> ((DataItem)o).getRequirement())
        .anyMatch(r -> r == requirement)) {
      ObjectFiles.append(savePath, input);
    }
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
