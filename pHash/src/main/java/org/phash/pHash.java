package org.phash;

import java.io.File;
import java.io.FileNotFoundException;

public class pHash
 {

  private native static VideoHash videoHash(String file);
  private native static AudioHash audioHash(String file);
  private native static DCTImageHash dctImageHash(String file);
  private native static MHImageHash mhImageHash(String file);
  private native static RadialImageHash radialImageHash(String file);
  private native static TextHash textHash(String file);
  public native static double imageDistance(ImageHash hash1, ImageHash hash2);
  public native static double audioDistance(AudioHash hash1, AudioHash hash2);
  public native static double videoDistance(VideoHash hash1, VideoHash hash2, int threshold);
  private native static int textDistance(TextHash txtHash1, TextHash txtHash2);
  private native static void pHashInit();

  private native static void cleanup();

  private static void checkFileExistence(File file) throws FileNotFoundException
   {
    if (!file.isFile())
     throw new FileNotFoundException("\"" + file.getAbsolutePath() + "\" is not a file.");
    if (!file.exists())
     throw new FileNotFoundException("File \"" + file.getAbsolutePath() + "\" doesn't exist.");
   }

  public static VideoHash videoHash(File file) throws FileNotFoundException
   {
    checkFileExistence(file);
    return videoHash(file.getAbsolutePath());
   }

  public static AudioHash audioHash(File file) throws FileNotFoundException
   {
    checkFileExistence(file);
    return audioHash(file.getAbsolutePath());
   }

  public static DCTImageHash dctImageHash(File file) throws FileNotFoundException
   {
    checkFileExistence(file);
    return dctImageHash(file.getAbsolutePath());
   }

  public static MHImageHash mhImageHash(File file) throws FileNotFoundException
   {
    checkFileExistence(file);
    return mhImageHash(file.getAbsolutePath());
   }

  public static RadialImageHash radialImageHash(File file) throws FileNotFoundException
   {
    checkFileExistence(file);
    return radialImageHash(file.getAbsolutePath());
   }

  static
   {
    System.loadLibrary("pHash-jni");
    pHashInit();

    Runtime.getRuntime().addShutdownHook(new Thread()
     {
      @Override
      public void run()
       {
        try
         {
          cleanup();
         }
        catch (Throwable e) {}
       }
     });
   }

 }
