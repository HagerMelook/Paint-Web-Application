
export default class Shape {
     xIndex =0 ;
     yIndex =0;
     fillColor='white';
     draggable =false;

     constructor(x,y,color,drag){
          this.xIndex=x;
          this.yIndex=y;
          this.fillColor=color;
          this.draggable=drag;
     }

     createShape(){};
}