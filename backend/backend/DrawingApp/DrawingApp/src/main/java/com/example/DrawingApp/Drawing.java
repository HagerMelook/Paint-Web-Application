package com.example.DrawingApp;


import java.util.HashMap;
import java.util.Stack;

public class Drawing {
	
	private HashMap<Integer,Shape> shapes;
	private Stack<HashMap<Integer,Shape>> undoStack;
	private Stack<HashMap<Integer,Shape>> redoStack;
	private int lastItemID;
	
	public Drawing()
	{
		shapes = new HashMap<>();
		undoStack = new Stack<>();
		redoStack = new Stack<>();
	}
	public HashMap<Integer, Shape> getShapes()
	{
		return shapes;
	}
	public void addShape(int id,Shape shape)
	{
		shapes.put(id,shape);
		lastItemID = id;
		undoStack.push(new HashMap<>(shapes));
		redoStack.clear();
		
		
	}
	public Shape returnShape(int id)
	{
		return shapes.get(id);
	}
	public void undo()
	{
		if(!undoStack.isEmpty())
		{
			redoStack.push(new HashMap<>(shapes));
			shapes = undoStack.pop();
		}
	}
	public void redo()
	{
		if(!redoStack.isEmpty())
		{
			undoStack.push(new HashMap<>(shapes));
			shapes = redoStack.pop();
		}
	}
    public void removeShape(int id) {
        shapes.remove(id);
        undoStack.push(new HashMap<>(shapes));
        redoStack.clear();
    }
    // after resizing or coloring 
    public void changeShape(int id, Shape shape)
    {
    	shapes.replace(id, shape);
		undoStack.push(new HashMap<>(shapes));
		redoStack.clear();
    }
    public int getLastItemID()
    {
    	return lastItemID;
    }
 

}
