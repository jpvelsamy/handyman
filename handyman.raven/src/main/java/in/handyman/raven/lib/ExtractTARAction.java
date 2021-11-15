package in.handyman.raven.lib;

import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lib.model.ExtractTAR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
    actionName = "ExtractTAR"
)
public class ExtractTARAction implements IActionExecution {
  private final Action action;

  private final Logger log;

  private final ExtractTAR extractTAR;

  private final Marker aMarker;

  public ExtractTARAction(final Action action, final Logger log, final Object extractTAR) {
    this.extractTAR = (ExtractTAR) extractTAR;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker("ExtractTAR");
  }

  @Override
  public void execute() throws Exception {
    String sourceFolder = extractTAR.getSource();
    String tarFolder = "tar";
    String destinationFolder = extractTAR.getDestination();
    try {
      File inputFile = new File(sourceFolder);
      if(!Files.exists(Paths.get(sourceFolder))) {
        //log.info("{} Sorce Folder not found",path);
      }
      Path path = Paths.get(tarFolder);
      if(!Files.exists(path)) {
        Files.createDirectory(path);
      }
      String outputFile = getFileName(inputFile, tarFolder);
      File tarFile = new File(outputFile);
      tarFile = deCompressGZipFile(inputFile, tarFile);
      File destFile = new File(destinationFolder);
      if(!destFile.exists()){
        destFile.mkdir();
      }
      unTarFile(tarFile, destFile);
      deleteDirectory(new File(tarFolder));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getFileName(File inputFile, String outputFolder){
    return outputFolder + File.separator +
            inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.'));
  }
  private File deCompressGZipFile(File gZippedFile, File tarFile) throws IOException{
    FileInputStream fis = new FileInputStream(gZippedFile);
    GZIPInputStream gZIPInputStream = new GZIPInputStream(fis);

    FileOutputStream fos = new FileOutputStream(tarFile);
    byte[] buffer = new byte[1024];
    int len;
    while((len = gZIPInputStream.read(buffer)) > 0){
      fos.write(buffer, 0, len);
    }
    fos.close();
    gZIPInputStream.close();
    return tarFile;
  }
  private void unTarFile(File tarFile, File destFile) throws IOException{
    FileInputStream fis = new FileInputStream(tarFile);
    TarArchiveInputStream tis = new TarArchiveInputStream(fis);
    TarArchiveEntry tarEntry = null;
    while ((tarEntry = tis.getNextTarEntry()) != null) {
      File outputFile = new File(destFile + File.separator + tarEntry.getName());
      if(tarEntry.isDirectory()){
        if(!outputFile.exists()){
          outputFile.mkdirs();
        }
      }else{
        outputFile.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(outputFile);
        IOUtils.copy(tis, fos);
        fos.close();
      }
    }
    tis.close();
  }
  boolean deleteDirectory(File directoryToBeDeleted) {
    File[] allContents = directoryToBeDeleted.listFiles();
    if (allContents != null) {
      for (File file : allContents) {
        deleteDirectory(file);
      }
    }
    return directoryToBeDeleted.delete();
  }
  @Override
  public boolean executeIf() throws Exception {
    return false;
  }
}
