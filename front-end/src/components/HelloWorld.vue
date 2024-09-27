<template>
    <div id="background">
        <div id="board">
        </div>
        <div class="tool">
            <div class="shapes">
                <div class="tool-text">Shapes</div>
                <Button id="line" @click="choseShape('line')" class="bi bi-slash-lg" text rounded></Button>
                <Button id="square" @click="choseShape('square')" class="bi bi-square" text rounded></Button>
                <Button id="circle" @click="choseShape('circle')" class="bi bi-circle" text rounded></Button>
                <Button id="ellipse" @click="choseShape('ellipse')" text rounded> &#11053; </Button>
                <Button id="rectangle" @click="choseShape('rectangle')" text rounded>&#9645;</Button>
                <Button id="triangle" @click="choseShape('triangle')" class="bi bi-triangle" text rounded></Button>
                <Button id="diamond" @click="choseShape('diamond')" class="bi bi-suit-diamond" text rounded></Button>
                <Button id="pentagon" @click="choseShape('pentagon')" class="bi bi-pentagon" text rounded></Button>
                <Button id="star" @click="choseShape('star')" class="bi bi-star" text rounded></Button>
            </div>
            <hr>
            <div class="draw">
                <div class="tool-text">Tools</div>
                <Button class="bi bi-pencil" @click="startBrush()" text rounded></Button>
                <Button class="bi bi-eraser" @click="startEraser()" text rounded></Button>
                <div class="draw">
                    <Button class="bi bi-copy" @click="copy()" text rounded></Button>
                    <Button class="bi bi-x-lg" @click="del()" text rounded></Button>
                </div>
            </div>
            <hr>
            <div class="colors">
                <Button class="bi bi-paint-bucket" @click="updateColor()" id="bucket" text rounded></Button>
                <ColorPicker v-model="fillcolor" id="color-picker" />
            </div>
            <hr>
            <div class="undo-redo">
                <div class="tool-text">Options</div>
                <button class="bi bi-arrow-counterclockwise" @click="undo()" rounded></button>
                <button class="bi bi-arrow-clockwise" @click="redo()" text rounded></button>
            </div>
            <hr>
            <div class="load-store">
                <button class="bi bi-download "></button>
                <button class="bi bi-upload"></button>
            </div>
        </div>
    </div>
</template>
<script type="module">
import FactoryShapes from '../shapes/FactoryShapes.js';
import Shape from '../shapes/Shape.js';
import Konva from 'konva';
import {Transformer} from 'konva';
export default {
    name: 'HelloWorld',
    data() {
        return {
            factory: new FactoryShapes(),
            shape: null,
            fillcolor: 'ffffff',
            selectedShapeButton: false,
            selectedShape: 'rectangle',
            endXcoord: "",
            endYcoord: "",
            selectedBrush: false,
            Brush: null,
            isdrawing: false,
            item: null,
            selectedEraser: false,
            startXcoord: 0,
            startYcoord: 0,
            storageItem :[{shape: "", startX: 0, startY: 0, endX: 0, endY: 0, fillcolor: ""}],
            storageItemTemp :[{shape: "", startX: 0, startY: 0, endX: 0, endY: 0, fillcolor:""}],
            isRedo: false,
            isCopy: false,
            isDelete: false,
        }
    },

    methods: {
        initTransformer() {

        this.transformer = new Transformer();
        this.layer.add(this.transformer);
        this.transformer.nodes([]);

    },
        choseShape(chosenShape) {
            this.selectedShape = chosenShape;
            this.selectedShapeButton = true;
            this.selectedBrush = false;
            this.selectedEraser = false;

        },
        startBrush() {
            this.selectedBrush = true;
            this.selectedShape = "FreeHand";
            this.selectedShapeButton = false;
            this.selectedEraser = false;

        },
        updateColor() {
            if (this.selectedShapeButton){
                if(this.selectedShape==="line")
                this.Brush.stroke('#' + this.fillcolor);
                else
                this.item.fill('#' + this.fillcolor);
            }
            else if (this.selectedBrush)
                this.Brush.stroke('#' + this.fillcolor);
        },
        startEraser() {
            this.selectedEraser = true;
            this.selectedBrush = false;
            this.selectedShape = "Erase";
            this.selectedShapeButton = false;
        },
        copy(){
            this.isCopy = true;
        },
        del(){
            this.isDelete=true;
        },
        undo(){
            if(this.storageItem.length==1)
                return;
            this.selectedShape=this.storageItem.at(-1).shape;
            this.startXcoord= this.storageItem.at(-1).startX;
            this.startYcoord= this.storageItem.at(-1).startY;
            this.endXcoord = this.storageItem.at(-1).endX;
            this.endYcoord = this.storageItem.at(-1).endY;
            this.fillcolor = this.storageItem.at(-1).fillcolor;
            this.storageItem.pop();
            this.storageItemTemp.push({
                shape: this.selectedShape,
                startX: this.startXcoord,
                startY: this.startYcoord,
                endX: this.endXcoord,
                endY: this.endYcoord,
                fillcolor: this.fillcolor,
            })
            localStorage.setItem('storageTemp', JSON.stringify(this.storageItemTemp));
            console.log((localStorage.getItem('storageTemp')));
            console.log(this.storageItem.length);
            var pos = this.stage.getPointerPosition();
            pos.x= this.startXcoord;
            pos.y =this.startYcoord;
            var s = this.stage.getIntersection(pos);
            s.destroy();
        },
        redo(){
            if(this.storageItemTemp.length==1)
                return;
            this.selectedShape=this.storageItemTemp.at(-1).shape;
            this.startXcoord= this.storageItemTemp.at(-1).startX;
            this.startYcoord= this.storageItemTemp.at(-1).startY;
            this.endXcoord = this.storageItemTemp.at(-1).endX;
            this.endYcoord = this.storageItemTemp.at(-1).endY;
            this.fillcolor = this.storageItemTemp.at(-1).fillcolor;
            this.storageItemTemp.pop();
            this.storageItem.push({
                shape: this.selectedShape,
                startX: this.startXcoord,
                startY: this.startYcoord,
                endX: this.endXcoord,
                endY: this.endYcoord,
                fillcolor: this.fillcolor,
            })
            localStorage.setItem('storage', JSON.stringify(this.storageItem));
            console.log((localStorage.getItem('storage')));
            if(this.selectedShape=="FreeHand")
                this.selectedBrush = true;
            else if(this.selectedShape=="Erase")
                this.selectedEraser = true;
            else
                this.selectedShapeButton = true;
            this.isRedo = true;
            this.handleMouseDown();
            this.handleMouseMove();
            this.isRedo = false;
            this.isdrawing = false;
            this.selectedBrush = false;
            this.selectedEraser = false;
            this.selectedShapeButton = false;
        },

        handleMouseDown() {
        this.isdrawing = true;
        var pos = this.stage.getPointerPosition();
        var shape = this.stage.getIntersection(pos);


   if(shape)
   {
    this.item.draggable(true);
   }

    if (shape) {

        
        this.initTransformer();

        this.transformer.nodes([shape]);
        this.layer.add(this.transformer);
        this.transformer.getLayer().batchDraw();
        

        return;
    
    }else(!shape)
    {
       if(this.transformer != null)
       this.transformer.detach();
    }
            
            
            if(this.isDelete){
                var s = this.stage.getIntersection(pos);
                s.destroy();
                this.isDelete = false;
                return;
            }
            if(this.isCopy){
                var s = this.stage.getIntersection(pos);
                this.selectedShape=s.getClassName();
                if(this.selectedShape ==="Rect"){
                    var shape =s.clone()
                    new Konva.Rect(shape.attrs);
                    console.log(shape.attrs);
                    this.isCopy = false;
                }
            }
            if(this.isRedo){
                pos.x = this.startXcoord;
                pos.y = this.startYcoord;
            }
            this.startXcoord = pos.x;
            this.startYcoord = pos.y
            if (this.selectedShapeButton) {
                if(this.selectedShape === "line"){
                    console.log("Done");
                    this.Brush = new Konva.Line({
                        points: [pos.x, pos.y],
                        lineCap: 'round',
                        lineJoin: 'round',
                        stroke: 'black',
                        strokeWidth: 2,
                    })
                    this.layer.add(this.Brush).batchDraw();
                }
                else{
                    this.shape = this.factory.createObject(this.selectedShape);
                    this.shape.xIndex = pos.x;
                    this.shape.yIndex =pos.y;
                    this.shape.fillColor = '#' + this.fillcolor;
                    this.item = this.shape.createShape();
                    this.layer.add(this.item).batchDraw();
                }
            }
            else if (this.selectedBrush) {
                this.Brush = new Konva.Line({
                    points: [pos.x, pos.y],
                    lineCap: 'round',
                    lineJoin: 'round',
                    stroke: 'black',
                    strokeWidth: 2,
                })
                this.layer.add(this.Brush).batchDraw();
            }
            else if (this.startEraser) {
                this.Brush = new Konva.Line({
                    points: [pos.x,pos.y],
                    lineCap: 'round',
                    lineJoin: 'round',
                    stroke: 'white',
                    strokeWidth: 2,
                })
                this.layer.add(this.Brush).batchDraw();
            }
        
        },
        store(){
            this.storageItem.push({
            shape: this.selectedShape,
            startX: this.startXcoord,
            startY: this.startYcoord,
            endX: this.endXcoord,
            endY: this.endYcoord,
            fillcolor: this.fillcolor,
        })
        localStorage.setItem('storage', JSON.stringify(this.storageItem));
        console.log((localStorage.getItem('storage')));
    },
        handleMouseMove() {
            if (this.isdrawing) {
                if(!this.isRedo){
                    this.endXcoord = this.stage.getPointerPosition().x;
                    this.endYcoord = this.stage.getPointerPosition().y;
                }
                if (this.selectedShapeButton) {
                    if (this.selectedShape === "rectangle") {
                        this.shape.width = (this.endYcoord - this.shape.yIndex);
                        this.shape.height = (this.endXcoord - this.shape.xIndex);
                        this.item.height(this.shape.width);
                        this.item.width(this.shape.height);
                    }
                    else if (this.selectedShape === "square") {
                        this.shape.side = (this.endYcoord - this.shape.yIndex);
                        this.item.height(this.shape.side).width(this.shape.side);
                    }
                    else if (this.selectedShape === "circle" || this.selectedShape === "diamond" || this.selectedShape === "pentagon" || this.selectedShape === "triangle") {
                        var y = Math.pow((this.endYcoord - this.shape.yIndex), 2);
                        var x = Math.pow((this.endXcoord - this.shape.xIndex), 2);
                        this.shape.radius = Math.sqrt(y + x);
                        this.item.radius(this.shape.radius);
                    }
                    else if (this.selectedShape === "star") {
                        var y = Math.pow((this.endYcoord - this.shape.yIndex), 2);
                        var x = Math.pow((this.endXcoord - this.shape.xIndex), 2);
                        this.shape.innerRadius = Math.sqrt(y + x);
                        this.shape.outerRadius = this.shape.innerRadius * 2.618;
                        this.item.outerRadius(this.shape.outerRadius);
                        this.item.innerRadius(this.shape.innerRadius);
                    }
                    else if (this.selectedShape === "ellipse") {
                        this.shape.innerRadius = Math.abs(this.endYcoord - this.shape.yIndex);
                        this.shape.outerRadius = Math.abs(this.endXcoord - this.shape.xIndex);
                        this.item.radiusY(this.shape.innerRadius);
                        this.item.radiusX(this.shape.outerRadius);
                    }
                    else if(this.selectedShape==="line"){
                        var points =[this.Brush.points()[0],this.Brush.points()[1],this.endXcoord, this.endYcoord];
                        this.Brush.points(points);
                    }
                }
                else if (this.selectedBrush || this.selectedEraser) {
                    var points = this.Brush.points().concat([this.endXcoord, this.endYcoord]);
                    this.Brush.points(points);
                }
                this.layer.batchDraw();
                this.layer.batchDraw();
                 if (this.transformer) {
                 this.transformer.getLayer().batchDraw();

        }
            }
            else
                return false;
        },
        handleMouseUp() {
            this.isdrawing = false;
            if((this.selectedShapeButton || this.selectedBrush || this.selectedEraser)){
                this.selectedShapeButton = false;
                this.store();
            }
        }
    },
    mounted() {
        this.stage = new Konva.Stage({
            container: 'board',
            width: window.innerWidth * 0.75 * 0.75 - 8,
            height: 550,
        });
        this.layer = new Konva.Layer();
        this.stage.add(this.layer);

        this.stage.on('mousedown', this.handleMouseDown);
        this.stage.on('mouseup', this.handleMouseUp);
        this.stage.on('mousemove', this.handleMouseMove);



    }
}



</script>
  
<style>
body {
    background-color: #4A98F7;
}

#background {
    display: flex;
    width: 75%;
    height: 550px;
    margin: auto;
}

#board {
    width: 75%;
    margin-right: 25px;
}

.tool {
    width: 20%;
}

#board,
.tool {
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.264);
}

.tool,
#board {
    background-color: white;
    border-radius: 5px;
}

.shapes Button,
.draw Button {
    margin-top: 5px;
    margin-left: 5px;
    width: calc ((100% - 20px) / 3);
}

.tool-text {
    margin-top: 15px;
    font-size: small;
}

.shapes {
    margin-left: -5px;
}

.load-store Button,
.undo-redo Button {
    border: none;
    cursor: pointer;
    font-size: x-large;
    color: #4a98f7;
    width: ((100% -25px)/2);
    background-color: white;
    margin: 5px 5px;
    border-radius: 10px;
    padding: 0 15px;
}

button:hover {
    background-color: #f1f0f09b;
}

#rectangle,
#ellipse {
    color: #4A98F7;
    font-size: 22px;
}

hr {
    width: 75%;
    border: #cfcece70 0.05px solid;
    stroke: none;
    border-radius: 30px;
}

.load-store {
    margin-top: 25px;
}

#color-picker {
    margin: 5px 0;
}

#color-picker input {
    padding: 0 50px;
}

.colors #bucket {
    margin-right: 15px;
}

.colors #bucket :hover {
    margin-right: 15px;
    border: #ffffff 2px solid;
}
</style>
  

