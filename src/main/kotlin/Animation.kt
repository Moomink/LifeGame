import javafx.animation.AnimationTimer
import javafx.scene.Group
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Rectangle

class Animation(WidChan: Double, HeiChan: Double, LenChan: Double) : AnimationTimer() {

    var group = Group()

    val Vertical_life: Int = (HeiChan / LenChan).toInt()  //縦のブロック数
    val Side_life: Int = (WidChan / LenChan).toInt() //横のブロック数
    var LENGTH: Double = LenChan

    //n用配列
    var life = Array(Vertical_life + 2) { Array(Side_life + 2) { Rectangle() } }

    init {
        initialize(life)


        for (i in 1 until Vertical_life + 1) {
            for (j in 1 until Side_life + 1) {
                group.children.add(life[i][j])
            }
        }

        life[6][2].fill = Color.BLACK
        life[6][3].fill = Color.BLACK
        life[6][4].fill = Color.BLACK


    }

    fun initialize(base:Array<Array<Rectangle>>){
        for (i in 0 until Vertical_life + 2) {  //縦
            for (j in 0 until Side_life + 2) { //横
                base[i][j].height = LENGTH
                base[i][j].width = LENGTH
                base[i][j].fill = Color.WHITE
                base[i][j].stroke = Color.GRAY
            }
        }

        for (i in 1 until Vertical_life + 1) {  //縦
            for (j in 1 until Side_life + 1) { //横
                base[i][j].x = (j - 1) * LENGTH
                base[i][j].y = (i - 1) * LENGTH
            }
        }

    }

    fun dispLife(): Group {
        for (i in 1 until Vertical_life + 1) {
            for (j in 1 until Side_life + 1) {
                life[i][j].x = (j - 1) * LENGTH
                life[i][j].y = (i - 1) * LENGTH
            }
        }

        return group
    }

//    fun Test(): Group {
//        return group
//    }

    override fun handle(now: Long) {
        //n+1用配列
        var nextLife = Array(Vertical_life + 2) { Array(Side_life + 2) { Rectangle() } }
        initialize(nextLife)
        for (i in 1 until Vertical_life + 1) { //縦
            for (j in 1 until Side_life + 1) { //横

                if(life[i][j].fill == Color.BLACK){ //生きていた時


                    nextLife[i][j].fill = when(Surrounding(life[i][j],j,i)){
                        0,1 -> Color.WHITE
                        2,3 -> Color.BLACK
                        else -> Color.WHITE
                    }
                }else{  //死んでいる時

                    nextLife[i][j].fill = when(Surrounding(life[i][j],j,i)){
                        3 -> Color.BLACK
                        else -> Color.WHITE
                    }
                }
            }
        }

        for (i in 1 until Vertical_life + 1) {
            for (j in 1 until Side_life + 1) {
                life[i][j].fill = nextLife[i][j].fill
            }
        }


    }

    fun Surrounding(base: Rectangle, x: Int, y: Int):Int{
        var cnt:Int = 0
        if (life[y - 1][x - 1].fill == Color.BLACK) { //↖
            cnt++
        }

        if (life[y][x - 1].fill == Color.BLACK) { //↑
            cnt++
        }

        if(life[y+1][x-1].fill == Color.BLACK){ // ↗
            cnt++
        }

        if(life[y-1][x].fill == Color.BLACK){ //←
            cnt++
        }

        if(life[y+1][x].fill == Color.BLACK){ //→
            cnt++
        }

        if(life[y-1][x+1].fill == Color.BLACK){ //↙
            cnt++
        }

        if(life[y][x+1].fill == Color.BLACK){ //↓
            cnt++
        }

        if(life[y+1][x+1].fill == Color.BLACK){ //➘
            cnt++
        }

        return cnt
    }
}