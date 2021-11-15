package in.handyman.raven.lib;

import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lib.model.CreateFile;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
    actionName = "CreateFile"
)
public class CreateFileAction implements IActionExecution {
  private final Action action;

  private final Logger log;

  private final CreateFile createFile;

  private final Marker aMarker;

  public CreateFileAction(final Action action, final Logger log, final Object createFile) {
    this.createFile = (CreateFile) createFile;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker("CreateFile");
  }

  @Override
  public void execute() throws Exception {
    String fileLocation = createFile.getLocation();
    String fileName = createFile.getFileName();
    String extension = createFile.getExtension();

    String createFile = fileLocation.concat(fileName+extension);
    Path path = Paths.get(createFile);
    if(!Files.exists(path)) {
      switch(extension){
        case ".doc":
        case ".docx":
          XWPFDocument xwpfdocument = new XWPFDocument();
          File file = new File(createFile);
          try(FileOutputStream ostream = new FileOutputStream(file)){
            xwpfdocument.write(ostream);
          }
          break;
        case ".html":
        case ".htm":
        case ".txt":
          Files.createFile(path);
        case ".odt":
          OdfTextDocument odt = OdfTextDocument.newTextDocument();
          odt.save(createFile);
        case ".pdf":
          try(PDDocument document = new PDDocument()){
            document.addPage(new PDPage());
            document.save(createFile);
          }
        case ".xls":
        case ".xlsx":
          XSSFWorkbook workbook = new XSSFWorkbook();
            try(FileOutputStream out = new FileOutputStream(new File(createFile))){
              workbook.write(out);
            }
        case ".ppt":
        case ".pptx":
          try(SlideShow slideShow = new HSLFSlideShow()) {
            Slide slide = slideShow.createSlide();
            FileOutputStream out1 = new FileOutputStream(createFile);
            slideShow.write(out1);
          }
        default:
          //log.info("No Extension found");
      }
    } else {
      //log.info("{} File already exists",path);
    }
  }

  @Override
  public boolean executeIf() throws Exception {
    return false;
  }
}
