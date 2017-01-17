/**
 * IFileHandler.java
 */
package org.com1028.project.js00928;

/**
 * The interface implemented by classes that require file handling methods 
 * with deferred implementation
 * 
 * @author Jack Simmonds
 *
 */
public interface IFileHandler {

  public void readFromUserFile(String filename);
  
  public void readFromSaveFile();
  
  public void writeToFile(String filename);
  
  public boolean textFileEmpty(String filename);
  
}
