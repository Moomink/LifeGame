import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.stage.Stage

class AppMain : Application() {
    val HEIGHT: Double = 700.0
    val WIDTH: Double = 800.0

    override fun start(primaryStage: Stage) {
        primaryStage.title = "HogeHoge"
        val fxml = javaClass.getResource("fxml/board.fxml")

        val root = FXMLLoader.load<Pane>(fxml)

        val scene = Scene(root)

        scene.fill = Color.TRANSPARENT
        root.style = "-fx-background-color: transparent"


        primaryStage.scene = scene

        primaryStage.height = HEIGHT

        primaryStage.width = WIDTH

        val animation = Animation(WIDTH, HEIGHT, 10.0)

        root.children.add(animation.dispLife())
        primaryStage.show()

        scene.setOnKeyPressed {
            when (it.code){
                KeyCode.SPACE -> animation.stop()
                else -> animation.start()
            }
        }


    }


}
