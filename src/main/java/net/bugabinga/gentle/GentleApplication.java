/*
 * Gentle is an image editor. Copyright (C) 2017 Oliver Jan Krylow
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package net.bugabinga.gentle;

import javafx.application.Application;
import javafx.application.Preloader.ProgressNotification;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Oliver Jan Krylow <oliver@bugabinga.net>
 * @since 20.07.2017
 *
 */
public class GentleApplication extends Application {

  @Override
  public void init() throws Exception {
    // TODO(oliver): Auto-generated method stub
    super.init();
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    for (int i = 0; i < 100; ++i) {
      Thread.sleep(i);
      System.out.println(i);
      notifyPreloader(new ProgressNotification(i));
    }
    primaryStage.setScene(new Scene(new StackPane(new Text("Gentle App"))));
    primaryStage.show();
  }

  @Override
  public void stop() throws Exception {
    // TODO(oliver): Auto-generated method stub
    super.stop();
  }
}
