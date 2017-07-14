package net.bugabinga.gentle;

import static java.util.Objects.requireNonNull;
import static javafx.scene.paint.Color.TRANSPARENT;
import static javafx.scene.text.FontSmoothingType.LCD;
import static javafx.scene.text.TextAlignment.CENTER;
import static javafx.util.Duration.millis;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * @author oliver
 * @since 09.07.2017
 */
public class Preloader extends javafx.application.Preloader
{
	@Override
	public void handleApplicationNotification(final PreloaderNotification info)
	{
		// TODO(oliver): Auto-generated method stub
		super.handleApplicationNotification(info);
	}

	@Override
	public boolean handleErrorNotification(final ErrorNotification info)
	{
		// TODO(oliver): Auto-generated method stub
		return super.handleErrorNotification(info);
	}

	@Override
	public void handleProgressNotification(final ProgressNotification info)
	{
		// TODO(oliver): Auto-generated method stub
		super.handleProgressNotification(info);
	}

	@Override
	public void handleStateChangeNotification(final StateChangeNotification info)
	{
		// TODO(oliver): Auto-generated method stub
		super.handleStateChangeNotification(info);
	}

	@Override
	public void init() throws Exception
	{
		// TODO(oliver): Auto-generated method stub
		super.init();
	}

	@Override
	public void start(final Stage primaryStage) throws Exception
	{
		requireNonNull(primaryStage);

		final Text text = new Text("Welcome to Gentle!");
		text.setFontSmoothingType(LCD);
		text.setFont(Font.font("sans-serif", 24.0));
		final TextFlow textFlow = new TextFlow(text);
		textFlow.setTextAlignment(CENTER);
		final StackPane root = new StackPane(textFlow);
		root.setOpacity(0.0);
		final Scene scene = new Scene(root, TRANSPARENT);

		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.centerOnScreen();
		primaryStage.setOpacity(0.0);
		primaryStage.setWidth(400);
		primaryStage.setHeight(400);

		primaryStage.show();

		// primaryStage.setOnShowing(__ ->
		// {
		final Duration duration = millis(3333);
		final FadeTransition fadeInRoot = new FadeTransition(duration, root);
		fadeInRoot.setFromValue(0.0);
		fadeInRoot.setToValue(1.0);

		final Transition fadeInScene = new Transition() {

			{
				setCycleDuration(duration);
			}

			@Override
			protected void interpolate(final double frac)
			{
				scene.setFill(Color.gray(frac, frac));
			}
		};

		final ParallelTransition bothFades = new ParallelTransition(fadeInRoot, fadeInScene);

		bothFades.play();
		// fadeInScene.play();
		// });

	}

	@Override
	public void stop() throws Exception
	{
		// TODO(oliver): Auto-generated method stub
		super.stop();
	}

	/**
	 * @param args
	 *            ignored.
	 */
	public static void main(final String[] args)
	{
		launch(args);
	}
}
