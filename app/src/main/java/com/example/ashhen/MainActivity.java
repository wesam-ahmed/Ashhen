package com.example.ashhen;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class MainActivity extends AppCompatActivity {

    EditText mresuletet;
    ImageView mpreviewiv;
    Uri image_uri;
    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;

    String[] camerapermission;
    String[] storagepermission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mresuletet = findViewById(R.id.ResulteEt);
        mpreviewiv = findViewById(R.id.imageIV);
        camerapermission = new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagepermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

    }


    public void we(View view) {
        String num=mresuletet.getText().toString();
        String num2="*555*"+num+"#";
        if (num.length()>0){
        Intent weintent=new Intent(Intent.ACTION_DIAL);
        weintent.setData(Uri.parse("tel:"+Uri.encode(num2)));
        startActivity(weintent);}
        else {
            Toast.makeText(MainActivity.this,"take image please ",Toast.LENGTH_SHORT).show();
        }

    }

    public void etisalat(View view) {
        String num=mresuletet.getText().toString();
        String num2="*556*"+num+"#";
        if (num.length()>0){
        Intent etsalatintent=new Intent(Intent.ACTION_DIAL);
        etsalatintent.setData(Uri.parse("tel:"+Uri.encode(num2)));
        startActivity(etsalatintent);}
        else {
            Toast.makeText(MainActivity.this,"take image please ",Toast.LENGTH_SHORT).show();
        }
    }


    public void orange(View view) {
        String num=mresuletet.getText().toString();
        String num2="*102*"+num+"#";
        if (num.trim().length()>0){
        Intent orangeintent=new Intent(Intent.ACTION_DIAL);
        orangeintent.setData(Uri.parse("tel:"+Uri.encode(num2)));
        startActivity(orangeintent);}
        else {
            Toast.makeText(MainActivity.this,"take image please ",Toast.LENGTH_SHORT).show();
        }
    }

    public void vodafone(View view) {
        String num=mresuletet.getText().toString();
        String num2="*858*"+num+"#";
        if (num.trim().length()>0){
        Intent vodafoneintent=new Intent(Intent.ACTION_DIAL);
        vodafoneintent.setData(Uri.parse("tel:"+Uri.encode(num2)));
        startActivity(vodafoneintent);}
        else {
            Toast.makeText(MainActivity.this,"take image please ",Toast.LENGTH_SHORT).show();
        }

    }


    private void showimageimportDialog() {
        String[] items = {"camera", "gallery"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("select image");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                if (which == 0) {
                    if (!checkCamerapermission()) {
                        requestcamerapermission();
                    } else {
                        pickcamera();
                    }
                } else if (which == 1) {
                    if (!checkstoragepermission()) {
                        requeststoragepermission();
                    } else {
                        pickgallery();
                    }
                }


            }
        });
        dialog.create().show();


    }

    private void pickgallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickcamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "NewPic");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image to text");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraintent, IMAGE_PICK_CAMERA_CODE);
    }

    private void requeststoragepermission() {
        ActivityCompat.requestPermissions(this, storagepermission, STORAGE_REQUEST_CODE);

    }

    private boolean checkstoragepermission() {
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestcamerapermission() {
        ActivityCompat.requestPermissions(this, camerapermission, CAMERA_REQUEST_CODE);
    }

    private boolean checkCamerapermission() {
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);


        return result && result1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && writeStorageAccepted) {
                        pickcamera();
                    } else {
                        Toast.makeText(this, "permission denid", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean writeStorageAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted) {
                        pickgallery();
                    } else {
                        Toast.makeText(this, "permission denid", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                CropImage.activity(data.getData()).
                        setGuidelines(CropImageView.Guidelines.ON).start(this);
            } else if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                CropImage.activity(image_uri).
                        setGuidelines(CropImageView.Guidelines.ON).start(this);
            }

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resulturi = result.getUri();
                mpreviewiv.setImageURI(resulturi);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) mpreviewiv.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext())
                        .build();
                if (!recognizer.isOperational()) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock myitem = items.valueAt(i);
                        sb.append(myitem.getValue());
                        sb.append("");
                    }
                    mresuletet.setText(sb.toString());
                }

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void yala_ashhen(View view) {

        showimageimportDialog();
    }

    //menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_etsalat) {
            startActivity(new Intent(MainActivity.this, list_etsalat.class));
        }
        if (id == R.id.menu_orange) {
            startActivity(new Intent(MainActivity.this, list_orange.class));
        }
        if (id == R.id.menu_vodafon) {
            startActivity(new Intent(MainActivity.this, list_vodafone.class));
        }
        if (id == R.id.menu_we) {
            startActivity(new Intent(MainActivity.this, list_we.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
