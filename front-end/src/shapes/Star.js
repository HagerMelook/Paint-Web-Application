import Shape from './Shape'
export default class Star extends Shape {
    innerRadius=0;
    outerRadius=0;
    constructor(x, y ,color, drag ,inner,outer){
        super(x,y,color,drag);
        this.innerRadius=inner;
        this.outerRadius=outer;
    }
    createShape(){
        return new Konva.Star({
            x: this.xIndex,
            y: this.yIndex,
            numPoints: 5,
            innerRadius: this.innerRadius,
            outerRadius: this.outerRadius,
            fill: this.fillColor,
            draggable:this.draggable,
            stroke: 'black',
            strokeWidth: 2,
          });
    }

}