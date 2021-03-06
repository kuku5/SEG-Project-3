package team3j.artworkdisplay;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Process dialog with specific settings
 *
 * @author Team 3-J
 */

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
        progressDialog.setMessage("Processing request...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
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
