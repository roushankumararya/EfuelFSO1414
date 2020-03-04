package com.developtech.efuelfo.util;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.developtech.efuelfo.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import static butterknife.internal.Utils.arrayOf;

/**
 * Created by dt on 1/11/18.
 */

public class SelectImage implements View.OnClickListener {

    public static int IMAGE_REQUEST_CAMERA_CODE = 101;
    public static int IMAGE_REQUEST_GALLERY_CODE = 102;
    public int PERMISSION_REQUEST_STORAGE = 103;
    public int PERMISSION_REQUEST_CAMERA = 104;
    public String destinationFileName = "";
    public Uri photoUri;
    Dialog dialog;
    Uri resultUri = null;
    private Context context;
    private ImageView imageView;

    public SelectImage(Context context, ImageView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    public void showDialog() {
        dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_select_image, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        TextView txt_camera = view.findViewById(R.id.txt_camera);
        TextView txt_gallery = view.findViewById(R.id.txt_gallery);
        txt_gallery.setOnClickListener(this);
        txt_camera.setOnClickListener(this);
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_camera: {
                dialog.dismiss();
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    getPic();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            PERMISSION_REQUEST_CAMERA);
                }
                break;
            }
            case R.id.txt_gallery: {
                dialog.dismiss();
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    chooseFromGallery();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_STORAGE);
                }
                break;
            }
        }
    }

    private void chooseFromGallery() {
        Intent intent;
        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        }
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        ((Activity) context).startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_REQUEST_GALLERY_CODE);
    }

    private void getPic() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
            photoUri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            ((Activity) context).startActivityForResult(intent, IMAGE_REQUEST_CAMERA_CODE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CAMERA_CODE && resultCode == Activity.RESULT_OK) {
            try {
                destinationFileName = System.currentTimeMillis() + "myPic.png";

                UCrop.Options uOptions = new UCrop.Options();
                uOptions.setStatusBarColor(context.getResources().getColor(R.color.colorPrimary));
                uOptions.setToolbarColor(context.getResources().getColor(R.color.colorPrimaryDark));
                uOptions.setActiveWidgetColor(context.getResources().getColor(R.color.colorAccent));

                UCrop.of(photoUri, Uri.fromFile(new File(context.getCacheDir(), destinationFileName)))
                        .withAspectRatio(1, 1)
                        .withOptions(uOptions)
                        .start((Activity) context);

            } catch (Exception e) {
                Log.e("excep", "camera :> " + e.toString());
            }
        } else if (requestCode == IMAGE_REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            try {
                destinationFileName = System.currentTimeMillis() + "myPic.png";
                Uri photoUri = data.getData();
                if (photoUri != null) {

                    UCrop.Options uOptions = new UCrop.Options();
                    uOptions.setStatusBarColor(context.getResources().getColor(R.color.colorPrimary));
                    uOptions.setToolbarColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    uOptions.setActiveWidgetColor(context.getResources().getColor(R.color.colorAccent));

                    UCrop.of(photoUri, Uri.fromFile(new File(context.getCacheDir(), destinationFileName)))
                            .withAspectRatio(1, 1)
                            .withOptions(uOptions)
                            .start((Activity) context);
                }
            } catch (Exception e) {
                Log.e("excep", "camera :> " + e.toString());
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            resultUri = UCrop.getOutput(data);
            if (resultUri != null)
                imageView.setImageURI(resultUri);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Throwable cropError = UCrop.getError(data);
        }
    }

    public Uri getResultUri() {
        return resultUri;
    }

    public void onRequestPermissionChange(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                getPic();
            }
        } else if (requestCode == PERMISSION_REQUEST_STORAGE) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                chooseFromGallery();
            }

        }
    }
}
