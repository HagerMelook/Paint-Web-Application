import Shape from './Shape'
export default class Square extends Shape {
    side = 0;
    constructor(x, y ,color, drag ,side){
        super(x,y,color,drag);
        this.side=side;
    }
    createShape(){
        return new Konva.Rect({
            x: this.xIndex,
            y: this.yIndex,
            width: this.side,
            height: this.side,
            fill: this.fillColor,
            draggable:this.draggable,
            stroke: 'black',
            strokeWidth: 2,
          });
    }

}