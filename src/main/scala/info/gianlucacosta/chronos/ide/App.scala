package info.gianlucacosta.chronos.ide

import javafx.stage.Stage

import info.gianlucacosta.chronos.chronos_ide.ArtifactInfo
import info.gianlucacosta.chronos.icons.MainIcon
import info.gianlucacosta.helios.apps.{AppInfo, AuroraAppInfo}
import info.gianlucacosta.helios.fx.apps.{AppBase, AppMain, SplashStage}
import info.gianlucacosta.omnieditor.OmniEditor


object App extends AppMain[App](classOf[App])

class App extends AppBase(AuroraAppInfo(ArtifactInfo, MainIcon)) {
  override def startup(appInfo: AppInfo, splashStage: SplashStage, primaryStage: Stage): Unit = {
    splashStage.statusText = "Creating OmniEditor..."
    val omniEditor = new OmniEditor(new IdeStrategy)

    splashStage.statusText = "Starting OmniEditor..."
    omniEditor.start(primaryStage)
  }
}