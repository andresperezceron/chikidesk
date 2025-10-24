package com.example.chikidesk.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.File;

public class ImageManager {
    private final Context context;
    private final String entityPrefix;
    private final String fileExtension;

    public ImageManager(@NonNull Context context, @NonNull String entityPrefix,
                        @NonNull String fileExtension) {
        this.context = context.getApplicationContext();
        this.entityPrefix = entityPrefix;
        this.fileExtension = fileExtension;
    }

    /**
     * Construye el objeto File para una entidad y su ID.
     * Esta es la ÚNICA fuente de verdad para el nombre del archivo.
     */
    public File getImageFile(long entityId) {
        String fileName = entityPrefix + entityId + "." + fileExtension;
        return new File(context.getFilesDir(), fileName);
    }

    /**
     * Carga la imagen de una entidad en un ImageView si existe.
     * Devuelve el objeto File para poder comprobar si existe o borrarlo.
     */
    public File loadImageInto(long entityId, @NonNull ImageView imageView) {
        File imageFile = getImageFile(entityId);
        if(imageFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else {
            // Opcional: poner una imagen por defecto si no existe
            imageView.setImageBitmap(null); // O imageView.setImageResource(R.drawable.placeholder);
        }
        return imageFile;
    }

    /**
     * Borra la imagen asociada a una entidad.
     * Devuelve true si el archivo existía y fue borrado con éxito.
     */
    public boolean deleteImage(long entityId) {
        File imageFile = getImageFile(entityId);
        if(imageFile.exists()) {
            return imageFile.delete();
        }
        // Devuelve false si el archivo no existía en primer lugar.
        return false;
    }
}
