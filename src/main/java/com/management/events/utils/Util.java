package com.management.events.utils;

import com.management.events.exceptions.InputException;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    static Pattern pattern = Pattern.compile("data:image/(?<ext>(png|jpeg|jpg));base64,");

    public static String saveImage(String image) throws InputException, IOException {
        String path = "/home/tsinjo/workspace/workspace/Mr_Naina/event-manager/out/artifacts/events_war_exploded/resources/images";
        String fileName = DigestUtils.sha1Hex(LocalDateTime.now().toString());
        Image img = parseImage(image);
        if (img == null) {
            throw new InputException("Invalid file");
        }
        fileName = fileName +"."+ img.getExt();
        write(img.getBase64(), path , fileName);
        return fileName;
    }

    public static void write(String base64Str, String path, String fileName) throws IOException {
        byte[] data = Base64.getDecoder().decode(base64Str);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        try (FileOutputStream stream = new FileOutputStream(path+"/"+fileName)) {
            stream.write(data);
        }
    }

    public static Image parseImage(String image) {
        Matcher matcher = pattern.matcher(image);
        if (matcher.find()) {
            Image img = new Image();
            img.setExt(matcher.group("ext"));
            img.setBase64(image.substring(matcher.end()));
            return img;
        }
        return null;
    }

    public static String formatDate (Serializable date) {
        Locale locale = new Locale("fr", "FR");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        return dateFormat.format(date);
    }

}


