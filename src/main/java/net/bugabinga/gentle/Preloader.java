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

import static java.util.Objects.requireNonNull;
import static javafx.animation.Interpolator.EASE_IN;
import static javafx.scene.input.KeyCode.ESCAPE;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;
import static javafx.scene.paint.Color.gray;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.FontSmoothingType.LCD;
import static javafx.util.Duration.millis;
import static org.reactfx.EventStreams.eventsOf;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.util.Duration;

/**
 * TODO: separate preloader into jar
 *
 * @author oliver
 * @since 09.07.2017
 */
public class Preloader extends javafx.application.Preloader {
  @Override
  public void handleApplicationNotification(final PreloaderNotification info) {
    // TODO(oliver): Auto-generated method stub
    super.handleApplicationNotification(info);
  }

  @Override
  public boolean handleErrorNotification(final ErrorNotification info) {
    // TODO(oliver): Auto-generated method stub
    return super.handleErrorNotification(info);
  }

  @Override
  public void handleProgressNotification(final ProgressNotification info) {
    // TODO(oliver): Auto-generated method stub
    super.handleProgressNotification(info);
  }

  @Override
  public void handleStateChangeNotification(final StateChangeNotification info) {
    // TODO(oliver): Auto-generated method stub
    super.handleStateChangeNotification(info);
  }

  @Override
  public void init() throws Exception {
    // TODO(oliver): Auto-generated method stub
    super.init();
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    requireNonNull(primaryStage);

    final Text text = new Text("Welcome to Gentle!");
    text.setFontSmoothingType(LCD);
    text.setFont(font("sans-serif", 24.0));
    final Color gray = gray(0.25);
    text.setFill(gray);
    final StackPane root = new StackPane(text);
    root.setBorder(new Border(
        new BorderStroke(gray, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4.0))));
    root.setOpacity(0.0);
    final Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.initStyle(StageStyle.TRANSPARENT);
    primaryStage.centerOnScreen();
    primaryStage.setOpacity(0.0);

    primaryStage.setWidth(1);
    primaryStage.setHeight(1);

    eventsOf(primaryStage, KEY_RELEASED).map(KeyEvent::getCode).filter(ESCAPE::equals)
        .subscribeForOne(__ -> primaryStage.close());

    final Duration duration = millis(222);

    final FadeTransition fadeInRoot = new FadeTransition(duration, root);
    fadeInRoot.setFromValue(0.0);
    fadeInRoot.setToValue(1.0);

    final Transition fadeInScene = new Transition() {
      {
        setCycleDuration(duration);
      }

      @Override
      protected void interpolate(final double frac) {
        scene.setFill(gray(frac, frac));
      }
    };

    final Transition growScene = new Transition() {
      {
        setCycleDuration(duration);
      }

      @Override
      protected void interpolate(final double frac) {
        primaryStage.setWidth(400 * frac);
        primaryStage.setHeight(400 * frac);
        primaryStage.centerOnScreen();
      }
    };

    final ParallelTransition parallel = new ParallelTransition(growScene, fadeInScene);
    final SequentialTransition sequence = new SequentialTransition(parallel, fadeInRoot);
    sequence.setInterpolator(EASE_IN);

    primaryStage.setOnShown(__ -> sequence.play());

    primaryStage.show();
  }

  @Override
  public void stop() throws Exception {
    // TODO(oliver): Auto-generated method stub
    super.stop();
  }

  /**
   * @param args ignored.
   */
  public static void main(final String[] args) {
    launch(args);
  }
}
