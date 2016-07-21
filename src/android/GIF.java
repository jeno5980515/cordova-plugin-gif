package jeno5980515.cordova;

import org.apache.cordova.*;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.app.Application;
import android.content.* ;
import android.net.Uri;

import java.io.*;
import java.util.*;

import jeno5980515.cordova.AnimatedGifEncoder;

public class GIF extends CordovaPlugin {

    public static ByteArrayOutputStream bos = new ByteArrayOutputStream();
    public static AnimatedGifEncoder encoder = new AnimatedGifEncoder();

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ( action.equals("init") ){
            encoder.start(bos);
            return true ;
        } else if ( action.equals("addFrameBase64") ){
            String base64 = args.getString(0); 
            base64 = base64.replaceFirst("^data:image/[^;]*;base64,?","");
            encoder.addFrame(toBitmap(base64));
            return true ;
        } else if ( action.equals("addFrameFileURI") ){
            String path = args.getString(0); 
            encoder.addFrame(BitmapFactory.decodeFile(path));
            return true ;
        } else if ( action.equals("finish") ){
            encoder.finish();
            String path = args.getString(0); 
            try {
                FileOutputStream stream = new FileOutputStream(path);
                stream.write(bos.toByteArray()); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                return false ;
            }
            callbackContext.success(path);
            return true ;
        } else {
            return false;

        }
    }

    public static Bitmap toBitmap(String input){
        byte[] decodedBytes = Base64.decodeBase64(input.getBytes());
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String toBase64(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality){
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, byteArrayOS);
        return new String(Base64.encodeBase64(byteArrayOS.toByteArray())) ;
    }

}
