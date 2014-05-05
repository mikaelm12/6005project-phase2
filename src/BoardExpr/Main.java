package BoardExpr;

import pingball.datatypes.Board;



public class Main {
    
    public static void main(String[] args) {
     String testBoard1 =   "board name=Mikael gravity=20.0 friction1=0.020 friction2=0.020\n" +
                "# This board is meant for multiple players. It is a loose\n" +

                  "# define a ball\n"+
                  "ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5\n"+

                  "# define a series of square bumpers\n" +
                 " squareBumper name=Square0 x=2 y=2"+
                  "leftFlipper name=FlipL2 x=16 y=9 orientation=0"+
                " circleBumper name=Circle10 x=10 y=3" +
                 "circleBumper name=Circle11 x=11 y=4" + 
                 "circleBumper name=Circle12 x=12 y=5"+
                 "triangleBumper name=Tri12 x=5 y=5 orientation=0"+
                 "absorber name=Abs x=0 y=19 width=20 height=1 "+
                 "fire trigger=Abs action=Abs ";
     String testBoard2 =   "board name=Alex gravity=10.0 friction1=0.020 friction2=0.020\n" +
             "# This board is meant for multiple players. It is a loose\n" +

               "# define a ball\n"+
               "ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5\n"+

               "# define a series of square bumpers\n" +
              " squareBumper name=Square1 x=2 y=2"+
              " squareBumper name=Square2 x=3 y=3"+
              " squareBumper name=Square3 x=3 y=2"+
              " squareBumper name=Square4 x=2 y=3"+
               "leftFlipper name=FlipL2 x=16 y=9 orientation=0"+
             " circleBumper name=Circle10 x=10 y=3" +
             "triangleBumper name=Tri99 x=10 y=10 orientation=90"+
             " squareBumper name=Square5 x=11 y=11"+
             " squareBumper name=Square6 x=12 y=12"+
             " squareBumper name=Square7 x=11 y=12"+
             " squareBumper name=Square8 x=12 y=11"+
              "triangleBumper name=Tri12 x=5 y=5 orientation=0"+
              "absorber name=Abs x=0 y=19 width=20 height=1 "+
              "fire trigger=FlipL2 action=Square8 "+
              "fire trigger=Square8 action=Abs ";
     
     String testBoard3 =   "board name=Peter gravity=10.0\n" +
             "# This board is meant for multiple players. It is a loose\n" +

               "# define a ball\n"+
               "ball name=Ball x=19.5 y=19.5 xVelocity=2.5 yVelocity=2.5\n"+

               "# define a series of square bumpers\n" +
              " squareBumper name=Square1 x=2 y=2"+
              " squareBumper name=Square2 x=3 y=3"+
              "triangleBumper name=Tri09 x=10 y=10 orientation=90"+
              "triangleBumper name=Tri12 x=12 y=12 orientation=180"+
              " squareBumper name=Square3 x=3 y=2"+
              " squareBumper name=Square4 x=2 y=3"+
               "leftFlipper name=FlipL2 x=2 y=13 orientation=0"+
               "rightFlipper name=FlipR2 x=15 y=8 orientation=0"+
             " circleBumper name=Circle10 x=10 y=3" +
             "triangleBumper name=Tri99 x=10 y=10 orientation=90"+
             " squareBumper name=Square5 x=6 y=5"+
             " squareBumper name=Square6 x=6 y=7"+
             " squareBumper name=Square7 x=6 y=8"+
             " squareBumper name=Square7 x=6 y=9";
        
        
        Board board1  = BoardFactory.parse(testBoard1);
        Board board2 =  BoardFactory.parse(testBoard2);
        Board board3 = BoardFactory.parse(testBoard3);
        
        board1.setNeighborBottom(board2);
        board1.setNeighborRight(board3);
        board1.setNeighborTop(board1);
        System.out.println(board1);

 

        
    }

}
