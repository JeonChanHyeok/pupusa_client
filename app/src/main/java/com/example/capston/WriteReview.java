package com.example.capston;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteReview extends AppCompatActivity {
    ImageButton writeReviewImageBtn;
    Button writeReviewConfirmBtn;
    ImageView writetm;
    int REQUST_IMAGE_OPEN = 1;
    final int CAMERA = 100;
    final int GALLERY = 101;
    String imagePath;
    float avg = 0;
    String avgStr;

    RatingBar writeReviewTasteRb;
    RatingBar writeReviewAmountRb;
    RatingBar writeReviewDeliveryRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        takeCamera();
        countingRate();
    }

    //별 평점 계산
    public float countingRate(){
        writeReviewConfirmBtn = findViewById(R.id.btn_write_review_confirm);

        writeReviewTasteRb = (RatingBar) findViewById(R.id.rb_write_review_taste);
        writeReviewAmountRb = (RatingBar) findViewById(R.id.rb_write_review_amount);
        writeReviewDeliveryRb = (RatingBar) findViewById(R.id.rb_write_review_delivery);

        writeReviewConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float tasteRatingValue = writeReviewTasteRb.getRating();
                float amountRatingValue = writeReviewAmountRb.getRating();
                float deliveryRatingValue = writeReviewDeliveryRb.getRating();
                avgStr = String.format("%.1f", (tasteRatingValue + amountRatingValue + deliveryRatingValue) / 3);
                avg = Float.parseFloat(avgStr);

                Log.d("countingRate", ""+avg);
            }
        });
        return avg;
    }

    //커스텀 다이얼로그 클릭 부분
    public void custom_dialog(View v) {
        View dialogView = getLayoutInflater().inflate(R.layout.review_image_custom_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //갤러리 버튼 클릭
        Button galleryBtn = dialogView.findViewById(R.id.btn_review_image_custom_dialog_gallery);
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY); // final int GALLERY = 101;
                alertDialog.dismiss();
            }
        });

        //카메라 버튼 클릭
        Button cameraBtn = dialogView.findViewById(R.id.btn_review_image_custom_dialog_camera);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    File imageFile = null;
                    try {
                        imageFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (imageFile != null) {
                        Uri imageUri = FileProvider.getUriForFile(getApplicationContext(),
                                "com.example.getimage.fileprovider",
                                imageFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, CAMERA); // final int CAMERA = 100;
                    }
                }
                alertDialog.dismiss();
            }
        });

        //취소 버튼 클릭
        Button cancleBtn = dialogView.findViewById(R.id.btn_review_image_custom_dialog_cancel);
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    //카메라 이미지 버튼 클릭시 갤러리와 카메라 다이얼로그 뜨게함
    public void takeCamera() {
        writeReviewImageBtn = findViewById(R.id.btn_write_review_image);

        //갤러리에서 사진 가져오기
        writeReviewImageBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                custom_dialog(view);
                //권한 체크
                boolean hasCamPerm = checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
                boolean hasWritePerm = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                if (!hasCamPerm || !hasWritePerm)  // 권한 없을 시  권한설정 요청
                    ActivityCompat.requestPermissions(WriteReview.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    File createImageFile() throws IOException {
        //	이미지 파일 생성
        SimpleDateFormat imageDate = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = imageDate.format(new Date()); // 파일명 중복을 피하기 위한 "yyyyMMdd_HHmmss"꼴의 timeStamp
        String fileName = "IMAGE_" + timeStamp; // 이미지 파일 명
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = File.createTempFile(fileName, ".jpg", storageDir); // 이미지 파일 생성
        imagePath = file.getAbsolutePath(); // 파일 절대경로 저장하기, String
        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //writeReviewImage2Btn = findViewById(R.id.btn_write_review_image2);
        writeReviewImageBtn = findViewById(R.id.btn_write_review_image);

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) { // 결과가 있을 경우
            Bitmap bitmap = null;
            switch (requestCode) {
                case GALLERY: // 갤러리에서 이미지로 선택한 경우
//				1) 이미지 절대경로로 이미지 세팅하기
                    Cursor cursor = getContentResolver().query(data.getData(), null, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                        imagePath = cursor.getString(index);
                        cursor.close();
                    }

                    //2) InputStream 으로 이미지 세팅하기
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(data.getData());
                        bitmap = BitmapFactory.decodeStream(inputStream);

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 7; // 이미지 축소 정도. 원 크기에서 1/inSampleSize 로 축소됨
                        bitmap = BitmapFactory.decodeFile(imagePath, options);

                        inputStream.close();
                        writeReviewImageBtn.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case CAMERA: // 카메라로 이미지 가져온 경우
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 10; // 이미지 축소 정도. 원 크기에서 1/inSampleSize 로 축소됨

                    ExifInterface exif = null;
                    try {
                        exif = new ExifInterface(imagePath); // path 파일 uri
                    } catch (IOException e) { e.printStackTrace();
                    }
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

                    //찍은사진을 bitmap으로 decodeFile
                    bitmap = BitmapFactory.decodeFile(imagePath, options);

                    //찍은사진 rotate
                    Bitmap bmrotated = rotateBitmap(bitmap, orientation);

                    writeReviewImageBtn.setImageBitmap(bmrotated);
                    break;
            }
        }
    }

    //이미지 회전 문제 수정
    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;

            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);  //좌우반전
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;

            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;

            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;

            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;

            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;

            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    //EditText이외에 다른 부분 클릭 시 키보드 내려가기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

}
