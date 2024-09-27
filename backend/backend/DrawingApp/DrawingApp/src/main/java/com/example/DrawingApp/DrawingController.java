package com.example.DrawingApp;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/drawing")
public class DrawingController {
	private Drawing drawing = new Drawing();

	@GetMapping(path = "/undo")
	public ResponseEntity<String> undo() {
		try {
			drawing.undo();
			return ResponseEntity.ok("Undo operation performed");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to perform undo operation");
		}
	}

	@GetMapping(path = "/redo")
	public ResponseEntity<String> redo() {
		try {
			drawing.redo();
			return ResponseEntity.ok("Redo operation performed");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to perform redo operation");
		}
	}

	@GetMapping(path = "/draw")
	public void drawShape(@RequestParam int id, @RequestParam int x, @RequestParam int y, @RequestParam String color,
			@RequestParam String type, @RequestParam Point position) {
		Shape shape = ShapeFactory.createShape(id, x, y, color, type, position);
		drawing.addShape(id, shape);

	}

	@GetMapping(path = "/drawLine")
	public void drawLine(@RequestParam Point start, @RequestParam Point end,
			@RequestParam int id, @RequestParam int x, @RequestParam String color, @RequestParam String type) {
		Line line = (Line) ShapeFactory.createShape(id, x, 0, color, type, null);
		line.setStartPoint(start);
		line.setEndPoint(end);
		drawing.addShape(id, line);

	}

	@GetMapping(path = "/color")
	public void colorShape(@RequestParam int id, @RequestParam String color) {
		Shape shape = drawing.returnShape(id);
		shape.setColor(color);
		drawing.changeShape(id, shape);

	}

	@GetMapping(path = "/delete")
	public void deleteShape(@RequestParam int id) {
		drawing.removeShape(id);
	}

	@GetMapping(path = "/resize")
	public void resizeShape(@RequestParam int id, @RequestParam int length, @RequestParam int height) {
		Shape shape = drawing.returnShape(id);
		shape.setLength(length);
		shape.setHeight(height);
		drawing.changeShape(id, shape);

	}

	@GetMapping(path = "/resizeLine")
	public void resizeLine(@RequestParam int id, @RequestParam Point start, @RequestParam Point end) {
		Line line = (Line) drawing.returnShape(id);
		line.setStartPoint(start);
		line.setEndPoint(end);
		drawing.changeShape(id, line);

	}

	@GetMapping(path = "/moveShape")
	public void moveShape(@RequestParam int id, @RequestParam Point position) {
		Shape shape = drawing.returnShape(id);
		shape.setPosition(position);
		drawing.changeShape(id, shape);
	}

	@GetMapping(path = "/moveLine")
	public void moveLine(@RequestParam int id, @RequestParam Point start, @RequestParam Point end) {
		Line line = (Line) drawing.returnShape(id);
		line.setStartPoint(start);
		line.setEndPoint(end);
		drawing.changeShape(id, line);
	}

	@GetMapping(path = "/copy")
	public void shapeCopy(@RequestParam int idofCopied) {

		Shape original = drawing.returnShape(idofCopied);
		String color = original.getColor();
		String type = original.getType();
		Point position = original.getPosition();
		int height = original.getHeight();
		int length = original.getLength();
		Shape copy = ShapeFactory.createShape(drawing.getLastItemID() + 1, length, height, color, type, position);
		drawing.addShape(idofCopied, copy);

	}

	@GetMapping(path = "/copyLine")
	public void LineCopy(@RequestParam int idofCopied) {

		Line original = (Line) drawing.returnShape(idofCopied);
		String color = original.getColor();
		String type = original.getType();
		int length = original.getLength();
		Point start = original.getStartPoint();
		Point end = original.getEndPoint();
		Line copy = (Line) ShapeFactory.createShape(drawing.getLastItemID() + 1, length, 0, color, type, null);
		copy.setStartPoint(start);
		copy.setEndPoint(end);
		drawing.addShape(idofCopied, copy);

	}

	private final drawingService drawingsservice;
	public DrawingController(drawingService drawingsservice) {
		this.drawingsservice = drawingsservice;
	}

	//load json file
	@GetMapping("/upload/json/{location}")
	public String uplaodDrawingsJSON(@PathVariable("location") String path) {
		return drawingsservice.uplaodDrawingsJSON(path);
	}

	//load xml file
	@GetMapping("/upload/xml/{location}")
	public Object uplaodDrawingsXML(@PathVariable("location") String path) {
		return drawingsservice.uplaodDrawingsXML(path);
	}

	//save in json format
	@PostMapping("/save/json/{location}")
	public void saveDrawingsJSON(@RequestBody drawings d, @PathVariable("location") String path) {
		drawingsservice.saveDrawingsJSON(d, path);
	}

	//save in xml
	@PostMapping("/save/xml/{location}")
	public void saveDrawingsXML(@RequestBody drawings d, @PathVariable("location") String path) {
		drawingsservice.saveDrawingsXML(d, path);
	}

	//delete
	@DeleteMapping("/delete/{location}")
	public void deleteDrawings(@PathVariable("location") String path) {
		drawingsservice.deleteDrawings(path);
	}

}
