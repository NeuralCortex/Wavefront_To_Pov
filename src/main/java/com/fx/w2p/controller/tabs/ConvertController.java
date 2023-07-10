package com.fx.w2p.controller.tabs;

import com.fx.w2p.Globals;
import com.fx.w2p.controller.MainController;
import com.fx.w2p.controller.PopulateInterface;
import com.fx.w2p.tools.ObjParser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author pscha
 */
public class ConvertController implements Initializable, PopulateInterface {

    @FXML
    private HBox hBoxOben;
    @FXML
    private HBox hBoxUnten;
    @FXML
    private Button btnObj;
    @FXML
    private Button btnPov;
    @FXML
    private Button btnSave;
    @FXML
    private Label lbObj;
    @FXML
    private Label lbPov;
    @FXML
    private TextArea taObj;
    @FXML
    private TextArea taPov;

    private static final org.apache.logging.log4j.Logger _log = LogManager.getLogger(ConvertController.class);
    private final MainController mainController;
    private File selFile;
    private List<String> contentList;

    public ConvertController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        hBoxOben.setId("hec-background-blue");
        hBoxUnten.setId("hec-background-blue");
        lbObj.setId("hec-text-white");
        lbPov.setId("hec-text-white");

        btnObj.setText(bundle.getString("load.obj"));
        btnPov.setText(bundle.getString("convert.obj"));
        btnSave.setText(bundle.getString("save.pov"));

        btnObj.setOnAction(e -> {

            FileChooser fileChooser = new FileChooser();
            String objFile = Globals.propman.getProperty(Globals.OBJ_DIR, System.getProperty("user.dir"));
            fileChooser.setInitialDirectory(new File(objFile));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Wavefront files (*.obj)", "*.obj");
            fileChooser.getExtensionFilters().add(extFilter);
            selFile = fileChooser.showOpenDialog(mainController.getStage());

            if (selFile != null) {
                Globals.propman.setProperty(Globals.OBJ_DIR, selFile.getParent());
                Globals.propman.save();

                try {
                    String content = Files.readString(Paths.get(selFile.getAbsolutePath()), Charset.defaultCharset());

                    contentList = new ArrayList<>();

                    Scanner scanner = new Scanner(content);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        contentList.add(line);
                    }
                    scanner.close();

                    taObj.setText(content);
                    lbObj.setText(selFile.getName());
                } catch (IOException ex) {
                    _log.error(ex.getMessage());
                }
            }
        }
        );

        btnPov.setOnAction(e -> {
            if (selFile != null) {
                try {
                    ObjParser objParser = new ObjParser(contentList);
                    lbPov.setText(bundle.getString("lb.parse") + " " + objParser.getCountObj());
                    taPov.setText(objParser.buildScene());
                } catch (Exception ex) {
                    _log.error(ex.getMessage());
                }
            }
        }
        );

        btnSave.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            String povFile = Globals.propman.getProperty(Globals.POV_DIR, System.getProperty("user.dir"));
            fileChooser.setInitialDirectory(new File(povFile));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("POV-Ray files", "*.pov", "*.inc");
            fileChooser.getExtensionFilters().add(extFilter);
            File saveFile = fileChooser.showSaveDialog(mainController.getStage());

            if (saveFile != null) {
                Globals.propman.setProperty(Globals.POV_DIR, saveFile.getParent());
                Globals.propman.save();

                try {
                    if (!taPov.getText().isEmpty()) {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveFile.getAbsolutePath()));
                        bufferedWriter.write(taPov.getText());
                        bufferedWriter.flush();
                        bufferedWriter.close();
                    }
                } catch (IOException ex) {
                    _log.error(ex.getMessage());
                }
            }
        });
    }

    private String createMesh2(String vertices, String indices) {
        String mesh2 = "mesh2 {\n";
        mesh2 += vertices + "\n";
        mesh2 += indices + "\n";
        mesh2 += "}";
        return mesh2;
    }

    private String createVertexVectors(float vertices[], int blockSize, int stride) {
        String vertexVectors = "vertex_vectors {\n";
        System.out.println("vl: " + vertices.length);
        vertexVectors += (vertices.length / (3)) + ",\n";
        for (int i = 0; i < vertices.length; i += (3 * 3)) {
            String block = "";
            for (int k = 0; k < blockSize; k++) {
                String vec = "<";
                for (int j = 0; j < stride; j++) {
                    vec += vertices[i + k + j];
                    if (j < stride - 1) {
                        vec += ",";
                    }
                }
                vec += ">";
                block += vec;
                if (k < blockSize - 1) {
                    block += ",";
                }
            }
            vertexVectors += block;
            if (i % 9 == 0 && i < vertices.length - 3 * 3) {
                vertexVectors += ",\n";
            }
        }
        vertexVectors += "\n}";
        return vertexVectors;
    }

    private String createVertexIndices(float idx[], int indices[], int blockSize, int stride) {
        String vertexVectors = "face_indices {\n";
        System.out.println(indices.length + "");
        vertexVectors += (idx.length / 3) + ",\n";
        for (int i = 0; i < indices.length; i += (3 * 3)) {
            String block = "";
            for (int k = 0; k < blockSize; k++) {
                String vec = "<";
                for (int j = 0; j < stride; j++) {
                    vec += indices[i + k + j];
                    if (j < stride - 1) {
                        vec += ",";
                    }
                }
                vec += ">";
                block += vec;
                if (k < blockSize - 1) {
                    block += ",";
                }
            }
            vertexVectors += block;
            if (i % 9 == 0 && i < indices.length - 9) {
                vertexVectors += ",\n";
            }
        }
        vertexVectors += "\n}";
        return vertexVectors;
    }

    @Override
    public void populate() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void clear() {

    }

}
