package com.example.chikidesk.util;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.chikidesk.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImgPickerHelper {
    // 1. LAS DEPENDENCIAS (LO QUE NECESITA PARA TRABAJAR)
    private final Fragment fragment;
    private final File destinationFile; // A dónde guardar la imagen
    private final ImageView imageView; // Dónde mostrar la imagen

    // 2. EL LANZADOR DE ACTIVIDADES (LA "NUEVA" FORMA DE HACERLO)
    private final ActivityResultLauncher<String> galleryLauncher;

    // 3. EL CONSTRUCTOR (CÓMO SE CREA EL OBJETO)
    public ImgPickerHelper(Fragment fragment, File destinationFile, ImageView imageView) {
        this.fragment = fragment;
        this.destinationFile = destinationFile;
        this.imageView = imageView;

        // Aquí se registra el "callback" o la respuesta.
        // Esto se prepara ANTES de lanzar la galería.
        this.galleryLauncher = fragment.registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    // Este código se ejecutará DESPUÉS de que el usuario elija una imagen.
                    if (uri != null) {
                        handleImageUri(uri);
                    }
                }
        );
    }
    // 4. LA ACCIÓN PÚBLICA (LO QUE EL FRAGMENT LLAMA)
    public void launch() {
        // Lanza la galería del sistema para que el usuario elija una imagen.
        galleryLauncher.launch("image/*");
    }

    // 5. LA LÓGICA PRIVADA (CÓMO PROCESA LA IMAGEN)
    private void handleImageUri(Uri uri) {
        try {
            Bitmap bitmap = getBitmapFromUri(uri);
            saveBitmapToFile(bitmap);
            imageView.setImageBitmap(bitmap); // Actualiza la UI inmediatamente
            Toast.makeText(fragment.requireContext(), R.string.log_del_img, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("ImgPickerHelper", "Error al procesar la imagen", e);
            Toast.makeText(fragment.requireContext(), R.string.log_del_img, Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // Forma moderna y segura (Android 9+)
            ImageDecoder.Source source = ImageDecoder.createSource(fragment.requireContext().getContentResolver(), uri);
            return ImageDecoder.decodeBitmap(source);
        } else {
            // Forma antigua (obsoleta pero necesaria para compatibilidad)
            return MediaStore.Images.Media.getBitmap(fragment.requireContext().getContentResolver(), uri);
        }
    }

    private void saveBitmapToFile(Bitmap bitmap) throws IOException {
        // Usamos un "try-with-resources" que cierra el FileOutputStream automáticamente.
        try (FileOutputStream fos = new FileOutputStream(destinationFile)) {
            // Comprime el bitmap y lo escribe en el archivo de destino.
            // JPEG es un formato con pérdida. 90 es un buen balance calidad/tamaño.
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
        }
    }
}