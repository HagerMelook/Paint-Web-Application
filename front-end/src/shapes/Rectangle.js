import Shape from './Shape'
export default class Rectangle extends Shape {
    width = 0;
    height = 0;
    constructor(x, y ,color, drag ,width,height){
        super(x,y,color,drag);
        this.width=width;
        this.height=height;
    }
    createShape(){
        return new Konva.Rect({
            x: this.xIndex,
            y: this.yIndex,
            width: this.width,
            height: this.height,
            fill: this.fillColor,
            draggable:this.draggable,
            stroke: 'black',
            strokeWidth: 2,
          });
    }

}