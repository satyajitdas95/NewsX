

package com.newsorg.newsapp.android.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.newsorg.newsapp.android.R;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by satyajit on 18/01/21.
 */

public final class CommonUtils {
    private static final int AUTOCOMPLETE_REQUEST_CODE = 0;


    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getTimestamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidMobileNo(String MobileNumber) {
        Pattern pattern = Pattern.compile("^[6-9]\\d{9}$");
        Matcher matcher = pattern.matcher(MobileNumber);

        return matcher.matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static MaterialAlertDialogBuilder showAlertDialogueWithPostivieButton(Context context, String title, String message, String actionName, AlertDialogueCallback alertDialogueCallback) {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(actionName, (dialog, id) -> {
                    if (alertDialogueCallback != null) {
                        alertDialogueCallback.dialogueCallback();
                    }
                    dialog.cancel();
                })
                .setCancelable(true);
        materialAlertDialogBuilder.create();
        materialAlertDialogBuilder.show();
        return materialAlertDialogBuilder;
    }

    public static void showErrorDialogue(Context context, String message) {
        showAlertDialogueWithPostivieButton(context, context.getResources().getString(R.string.label_error),
                message,
                context.getResources().getString(R.string.label_okay), new AlertDialogueCallback() {
                    @Override
                    public void dialogueCallback() {
                    }
                });
    }

    public static void showSuccessDialogue(Context context, String message, AlertDialogueCallback alertDialogueCallback) {
        showAlertDialogueWithPostivieButton(context, context.getResources().getString(R.string.label_success),
                message,
                context.getResources().getString(R.string.label_okay), alertDialogueCallback);
    }

    public static void showNoInternetDialogue(Context context) {
        showAlertDialogueWithPostivieButton(context, context.getResources().getString(R.string.label_no_network), context.getResources().getString(R.string.label_no_network_description),
                context.getResources().getString(R.string.label_okay), new AlertDialogueCallback() {
                    @Override
                    public void dialogueCallback() {

                    }
                });
    }

    public static String uTCToLocalTime(String ourDate,String outputDateFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(ourDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat(outputDateFormat); //this format changeable
            dateFormatter.setTimeZone(TimeZone.getDefault());
            ourDate = dateFormatter.format(value);
        } catch (Exception e) {
            ourDate = "00-00-0000 00:00";
        }
        return ourDate;
    }

    public interface AlertDialogueCallback {

        void dialogueCallback();
    }


}
