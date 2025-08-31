package com.example.main;

import io.github.sosuisen.jfxbuilder.graphics.SceneBuilder;
import io.github.sosuisen.jfxbuilder.graphics.StageBuilder;
import io.github.sosuisen.jfxbuilder.graphics.VBoxBuilder;
import io.github.sosuisen.jfxbuilder.media.MediaPlayerBuilder;
import io.github.sosuisen.jfxbuilder.media.MediaViewBuilder;
import io.github.sosuisen.jfxbuilder.web.HTMLEditorBuilder;
import io.github.sosuisen.jfxbuilder.web.WebViewBuilder;
import javafx.concurrent.Worker.State;
import javafx.scene.media.Media;

public class WebView {
    public WebView() {
        var webView = WebViewBuilder.create()
                .zoom(0.7)
                .build();
        var player = MediaPlayerBuilder.create(
                new Media("https://docs.evostream.com/sample_content/assets/bunny.mp4"))
                .autoPlay(true)
                .build();

        var stage = StageBuilder.withScene(
                SceneBuilder.withRoot(
                        VBoxBuilder.withChildren(
                                webView,
                                HTMLEditorBuilder.create()
                                        .htmlText("<html><body><h1>Hello World!</h1></body></html>")
                                        .build(),
                                MediaViewBuilder.create(player)
                                        .build())
                                .build())
                        .build())
                .width(800)
                .height(800)
                .build();
        stage.show();

        var engine = webView.getEngine();
        engine.load("https://www.google.com");
        engine.getLoadWorker().stateProperty().subscribe(newState -> {
            if (newState == State.SUCCEEDED) {
                stage.setTitle(engine.getDocument().getDocumentURI());
            }
        });

    }

}
