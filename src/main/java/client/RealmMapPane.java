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

import java.awt.Point;
import javafx.scene.layout.Pane;

/**
 * Displays all plots in the ruler's realm and provides allows navigation to
 * panes for manipulating the plots.
 * @author Andrew M. Teller(https://github.com/AndrewMiTe)
 */
public class RealmMapPane extends Pane {
  
  private final Point[] plots = {
    new Point( 0, 0), // Plot 0, Seat of the realm
    new Point(-2, 0), // Plot 1, Start of the inner ring
    new Point(-1, 1), // Plot 2
    new Point( 1, 1), // Plot 3
    new Point( 2, 0), // Plot 4
    new Point( 1,-1), // Plot 5
    new Point(-1,-1), // Plot 6
    new Point(-4, 0), // Plot 7, Start of the outer ring
    new Point(-3, 1), // Plot 8
    new Point(-2, 2), // Plot 9
    new Point( 0, 2), // Plot 10
    new Point( 2, 2), // Plot 11
    new Point( 3, 1), // Plot 12
    new Point( 4, 0), // Plot 13
    new Point( 3,-1), // Plot 14
    new Point( 2,-2), // Plot 15
    new Point( 0,-2), // Plot 16
    new Point(-2,-2), // Plot 17
    new Point(-3,-1)  // Plot 18
  };
  
}
