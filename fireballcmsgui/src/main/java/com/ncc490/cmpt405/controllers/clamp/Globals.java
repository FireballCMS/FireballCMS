package com.ncc490.cmpt405.controllers.clamp;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.function.Function;

public final class Globals {

    public static Optional<String> getResource(@NamedArg("path") String path) {
       return getResourceURL(path).map(URL::getPath);
    }

    public static <T> Optional<T> getResource(@NamedArg("path") String path, Function<URL, T> transform) {
        return getResourceURL(path).map(transform);
    }

    public static Image getImageResource(@NamedArg("img_path") String img_path) {
        return getResource(img_path, url -> {
            try {
                return new Image(url.openStream());
            } catch (IOException ignored) {}
            return null;
        }).orElse(null);
    }

    private static Optional<URL> getResourceURL(@NamedArg("path") String path) {
        try {
            return Optional.of(new URL("file://" + Globals.class.getResource(path).getPath()));
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        }
        return Optional.empty();
    }
}
