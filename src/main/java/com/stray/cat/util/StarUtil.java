package com.stray.cat.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

public class StarUtil {
    public static String getImageWh(File file) {
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(file));
            return image.getWidth() + "x" + image.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
