package com.example.chikidesk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImgPickerHelper {
    private final ActivityResultLauncher<String> launcher;

    public ImgPickerHelper(Fragment fragment, int id, String prefix, String extension, ImageView imageView) {
        launcher = fragment.registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if(uri != null) {
                        try {
                            Context context = fragment.requireContext();
                            String filename = prefix + id + "." + extension;
                            File file = saveImageToInternalStorage(context, uri, filename);
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            imageView.setImageBitmap(bitmap);
                        }catch(IOException e) { e.printStackTrace(); }
                    }
                }
        );
    }

    public void launch() {
        launcher.launch("image/*");
    }

    private File saveImageToInternalStorage(Context context, Uri uri, String filename) throws IOException {
        File file = new File(context.getFilesDir(), filename);
        try(InputStream inputStream = context.getContentResolver().openInputStream(uri);
            FileOutputStream outputStream = new FileOutputStream(file)) {
            if(inputStream != null) {
                byte[] buffer = new byte[4096];
                int len;
                while ((len = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
            }
        }
        return file;
    }
}