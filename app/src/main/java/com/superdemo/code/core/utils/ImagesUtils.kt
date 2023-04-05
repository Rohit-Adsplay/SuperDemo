package com.superdemo.code.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.superdemo.code.BuildConfig
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun createImageFile(context: Context): File {
    // Create an image file name
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "JPEG_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
}

fun getBitmap(context: Context, imageUri: Uri): Bitmap? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(
            ImageDecoder.createSource(
                context.contentResolver,
                imageUri
            )
        )
    } else {
        context
            .contentResolver
            .openInputStream(imageUri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
    }
}

fun convertBitmaptoFile(destinationFile: File, bitmap: Bitmap) {
    //create a file to write bitmap data
    destinationFile.createNewFile()
    //Convert bitmap to byte array
    val bos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bos)
    val bitmapData = bos.toByteArray()
    //write the bytes in file
    val fos = FileOutputStream(destinationFile)
    fos.write(bitmapData)
    fos.flush()
    fos.close()
}


fun sendCameraIntent(context: Context, file: File, cameraLauncher: ActivityResultLauncher<Intent>) {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
        context.let { context ->
            intent.resolveActivity(context.packageManager).also {

                val photoFile: File? = try {
                    file
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                photoFile?.also {
                    val photoURI: Uri? =
                        FileProvider.getUriForFile(
                            context,
                            BuildConfig.APPLICATION_ID + ".fileprovider",
                            it
                        )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    cameraLauncher.launch(intent)
                }
            }
        }
    }
}

fun sendFrontCameraIntent(
    context: Context,
    file: File,
    cameraLauncher: ActivityResultLauncher<Intent>
) {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
        intent.putExtra(
            "android.intent.extras.CAMERA_FACING",
            android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT
        );
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);

        context.let { context ->
            intent.resolveActivity(context.packageManager).also {

                val photoFile: File? = try {
                    file
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }

                photoFile?.also {
                    val photoURI: Uri? =
                        FileProvider.getUriForFile(
                            context,
                            BuildConfig.APPLICATION_ID + ".fileprovider",
                            it
                        )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    cameraLauncher.launch(intent)
                }
            }
        }

    }
}

fun sendGalleryIntent(galleryLauncher: ActivityResultLauncher<Intent>) {
    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
        type = "image/*"
        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
        galleryLauncher.launch(this)
    }
}

fun setImageInImageView(context: Context, path: String, imageview: ImageView) {
    context.let { ctx ->
        Glide.with(ctx)
            .load(path).into(imageview)
    }
}