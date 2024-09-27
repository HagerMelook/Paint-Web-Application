package com.example.DrawingApp;

import java.awt.Point;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

public class drawings {
    private int idl;
    private int length; 
    private int height;
    private String color;
    private String shapeType;
    private Point position;
    private String path;
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public drawings() {
    }

    
    public drawings(String path, int idl, int length, int height, String color, String shapeType, Point position) {
        this.idl = idl;
        this.length = length;
        this.height = height;
        this.color = color;
        this.shapeType = shapeType;
        this.position = position;
    }

    public drawings(String path, int length, int height, String color, String shapeType, Point position) {
        this.length = length;
        this.height = height;
        this.color = color;
        this.shapeType = shapeType;
        this.position = position;
    }

    public drawings( int length, int height, String color, String shapeType) {
        this.length = length;
        this.height = height;
        this.color = color;
        this.shapeType = shapeType;
    }

    public int getIdl() {
        return idl;
    }
    public void setIdl(int idl) {
        this.idl = idl;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getShapeType() {
        return shapeType;
    }
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }


}
