package com.ncc490.cmpt405.views.node;


import com.ncc490.cmpt405.controllers.clamp.Globals;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class NodePresenter implements Initializable {

    private static final String DEFAULT_VIDEO_FILE = "file://" + Globals
            .getResource("/big_buck_bunny.mp4")
            .orElse("");

    private static final String ALTERNATE_VIDEO = "https://www.youtube.com/embed/ZSclaMC7IP0";

    private static final ImageView IMAGE_VOL_MUTE = new ImageView(Globals
            .getImageResource("/node/vol-mute.png"));

    private static final ImageView IMAGE_VOL_HIGH = new ImageView(Globals
            .getImageResource("/node/vol-high.png"));

    private static final ImageView IMAGE_VOL_MED = new ImageView(Globals
            .getImageResource("/node/vol-med.png"));

    private static final ImageView IMAGE_VOL_LOW = new ImageView(Globals
            .getImageResource("/node/vol-low.png"));

    private static final ImageView IMAGE_PAUSE = new ImageView(Globals
            .getImageResource("/node/pause.png"));

    private static final ImageView IMAGE_PLAY = new ImageView(Globals
            .getImageResource("/node/play.png"));

    private static final double VOLUME_DEFAULT_LEVEL = 0.5;

    @FXML
    private Label mNodeName;

    @FXML
    private MediaView mMediaPlayerView;

    @FXML
    private ProgressBar mVideoProgressIndicator;

    @FXML
    private Slider mVideoProgressSlider;

    @FXML
    private HBox mMediaControlsContainer;

    @FXML
    private Label mPlayButton;

    @FXML
    private Label mGoToStartButton;

    @FXML
    private Label mRewindButton;

    @FXML
    private Label mFastForwardButton;

    @FXML
    private Label mGoToEndButton;

    @FXML
    private Label mVolumeIndicator;

    @FXML
    private ProgressBar mVolumeProgress;

    @FXML
    private Slider mVolumeSlider;

    @FXML
    private ListView<?> mNodesVideoPreviewList;

    private ReadOnlyDoubleProperty mediaDurationProperty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapMediaControls(resources);
    }

    private void mapMediaControls(ResourceBundle resources) {
        mMediaPlayerView.setPreserveRatio(true);
        MediaPlayer mp = new MediaPlayer(new Media(DEFAULT_VIDEO_FILE));
        mMediaPlayerView.setMediaPlayer(mp);

        mMediaControlsContainer.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (!event.getButton().equals(MouseButton.PRIMARY)) {
                event.consume();
            }
        });

        mVolumeSlider.setMax(1);
        mVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            double value = newValue.doubleValue();
            mp.setVolume(value);

            if (value < 0.05) {
                mVolumeIndicator.setGraphic(IMAGE_VOL_MUTE);
            } else if (value < 0.3) {
                mVolumeIndicator.setGraphic(IMAGE_VOL_LOW);
            } else if (value < 0.8) {
                mVolumeIndicator.setGraphic(IMAGE_VOL_MED);
            } else {
                mVolumeIndicator.setGraphic(IMAGE_VOL_HIGH);
            }
        }));

        mVolumeProgress.progressProperty().bind(mVolumeSlider.valueProperty());
        mVolumeSlider.setValue(VOLUME_DEFAULT_LEVEL);
//
//        mVideoProgressIndicator.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println(event.getTarget());
//            }
//        });
//
        mp.setOnReady(() -> {
            mediaDurationProperty = new SimpleDoubleProperty((mp.getStopTime().toSeconds()));
            mp.setCycleCount(MediaPlayer.INDEFINITE);
            mp.setAutoPlay(false);
            mp.play();

            System.out.println(mediaDurationProperty.get());
            mVideoProgressSlider.setMax(1);
            mVideoProgressSlider.valueProperty().bind(Bindings.createDoubleBinding(() -> {
                double currTime = mp.getCurrentTime().toSeconds();
                return currTime / mediaDurationProperty.get();
            }, mp.currentTimeProperty()));

//            Bindings.when(Bindings.not(mVideoProgressSlider.valueChangingProperty()))
//                    .then(mp.seek(Duration.seconds((mVideoProgressSlider.getValue() * mediaDurationProperty.get()))))


//            mVideoProgressSlider.valueChangingProperty().addListener();addEventFilter(DragEvent.DRAG_ENTERED_TARGET, event -> {
//                System.out.println("Dragging");
//                mp.seek(Duration.seconds((mVideoProgressSlider.getValue() * mediaDurationProperty.get())));
//            });

            mVideoProgressSlider.valueChangingProperty().addListener((observable, oldValue, newValue) -> {
//                System.out.println((newValue.doubleValue() * mediaDurationProperty.get()));
                if (!newValue) {
                    System.out.println(mVideoProgressSlider.getValue());

                }
            });

            mVideoProgressIndicator.progressProperty().bind(mVideoProgressSlider.valueProperty());
        });
//
//
//        mPlayButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
//            if (event.getButton().equals(MouseButton.PRIMARY)) {
//                if (mp.getStatus().equals(MediaPlayer.Status.PLAYING)) {
//                    mp.pause();
//                    mPlayButton.setGraphic(IMAGE_PLAY);
//                }
//                else if (mp.getStatus().equals(MediaPlayer.Status.PAUSED)) {
//                    mp.play();
//                    mPlayButton.setGraphic(IMAGE_PAUSE);
//                }
//            }
//        });
    }
}
