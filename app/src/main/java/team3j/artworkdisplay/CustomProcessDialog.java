package team3j.artworkdisplay;

import android.app.ProgressDialog;
import android.content.Context;


public class CustomProcessDialog {

    private ProgressDialog progressDialog;

    /**
     * Constructs a CustomProcessDialog with the specified activity.
     *
     * @param context Activity.
     */
    public CustomProcessDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading data...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCancelable(true);

    }

    /**
     * Shows the dialog.
     */
    public void show() {
        progressDialog.show();
    }

    /**
     * Hides the dialog.
     */
    public void hide() {
        progressDialog.dismiss();

    }
}
