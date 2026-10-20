package utilities.download;

import utilities.configReader.ConfigReader;

import java.io.File;

public final class DownloadPathManager {

    private static final ThreadLocal<String> DOWNLOAD_PATH =
            new ThreadLocal<>();

    private DownloadPathManager() {

        throw new UnsupportedOperationException(
                "Utility class should not be instantiated.");

    }

    public static String initializeDownloadPath() {

        String basePath =
                ConfigReader.getProperty("download.path");

        String threadPath =
                basePath
                        + File.separator
                        + "thread-"
                        + Thread.currentThread().getId();

        File directory = new File(threadPath);

        if (!directory.exists() && !directory.mkdirs()) {

            throw new RuntimeException(
                    "Unable to create download directory: "
                            + threadPath);
        }

        DOWNLOAD_PATH.set(
                directory.getAbsolutePath());

        return DOWNLOAD_PATH.get();
    }

    public static String getDownloadPath() {

        String path = DOWNLOAD_PATH.get();

        if (path == null) {

            throw new IllegalStateException(
                    "Download path has not been initialized for the current thread.");

        }

        return path;
    }

    public static void removeDownloadPath() {

        DOWNLOAD_PATH.remove();

    }

}
