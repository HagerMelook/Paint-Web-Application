package com.example.DrawingApp;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.ObjDoubleConsumer;

import org.hibernate.boot.model.internal.XMLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class drawingService {

    public void saveDrawingsJSON(drawings d, String path) {
        JSONObject jsonf = new JSONObject();
        try {
            FileWriter fw = new FileWriter(path);
            jsonf.put("shapeType", d.getShapeType());
            jsonf.put("height", d.getHeight());
            jsonf.put("length", d.getLength());
            jsonf.put("color", d.getColor());
            jsonf.put("postion", d.getPosition());
            fw.write(jsonf.toString());
            System.out.println("json file is created" + jsonf);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDrawingsXML(drawings d, String path) {
        try {
            File fxml = new File(path);
            FileOutputStream f = new FileOutputStream(fxml);
            XMLEncoder xml = new XMLEncoder(f);
            xml.writeObject(d);
            xml.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String uplaodDrawingsJSON(String path) {
        try {
            FileReader f = new FileReader(path);
            String file = "";
            int i;
            while ((i = f.read()) != -1) {
                file += (char) i;
            }
            return file;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public Object uplaodDrawingsXML(String path) {
        try {
            File fxml = new File(path);
            FileInputStream f = new FileInputStream(fxml);
            XMLDecoder xml = new XMLDecoder(f);
            Object d = xml.readObject();
            xml.close();
            f.close();
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteDrawings(String path) {
        File f = new File(path);
        if (f.delete()) {
            System.out.println(f + " has been deleted successfully :D");
        } else {
            System.out.println("file doesn't exist :/");
        }
    }
}
