package de.jubyte.citybuild.utils;

import de.jubyte.citybuild.CityBuildV2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class LibDownloader {
    private static final Method ADD_URL_METHOD;

    static {
        Method addUrlMethod = null;
        try {
            addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addUrlMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        ADD_URL_METHOD = addUrlMethod;
    }

    public enum Library {
        HTMMLUNIT("https://jubyte.de/download.jubyte.de/PretronicDatabaseQuery.jar",
                "PretronicDatabaseQuery", "https://docs.pretronic.net/pretronic-database-query/", "DatabaseQuery-1.3.0");

        private String url = "";
        private String name = "";
        private String description = "";
        private String fileName = "";

        Library(String url, String name, String description, String fileName) {
            setUrl(url);
            setName(name);
            setDescription(description);
            setFileName(fileName);
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    /**
     * Download a library
     *
     * @param lib Library to download
     */
    public static void downloadLib(Library lib) {
        downloadLib(lib.getUrl(), lib.getName(), lib.getDescription(), lib.getFileName());
    }

    /**
     * Download a library
     *
     * @param url         URL to download it from
     * @param name        Name of the lib
     * @param description Description
     * @param fileName    filename to save it as
     */
    public static void downloadLib(String url, String name, String description, String fileName) {
        String localPath = "./plugins/CityBuildV2/lib/" + fileName + ".jar";
        if (!(new File(localPath).exists())) {
            CityBuildV2.getPLUGIN().getLogger().info("Downloading " + name + " ...");
            CityBuildV2.getPLUGIN().getLogger().info("Description: " + description);
            try {
                HtmlUtils.downloadFile(url, localPath);
            } catch (IOException e) {
                CityBuildV2.getPLUGIN().getLogger().severe("An error occured while downloading a required lib.");
                e.printStackTrace();
            }
        }
        CityBuildV2.getPLUGIN().getLogger().info("Loading dependency " + name + " ...");
        try {
            addURL(new URL("jar:file:" + localPath + "!/"));
            CityBuildV2.getPLUGIN().getLogger().info(name + " is now loaded!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addURL(URL u) throws IOException {
        // get the classloader to load into
        ClassLoader classLoader = CityBuildV2.class.getClassLoader();

        if (classLoader instanceof URLClassLoader) {
            try {
                ADD_URL_METHOD.invoke(classLoader, u);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Unable to invoke URLClassLoader#addURL", e);
            }
        } else {
            throw new RuntimeException("Unknown classloader: " + classLoader.getClass());
        }

    }
}